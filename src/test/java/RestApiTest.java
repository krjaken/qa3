import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.http.Request;
import com.github.tomakehurst.wiremock.http.RequestListener;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import restapi.RestApiController;

import java.util.ArrayList;
import java.util.HashMap;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;
import static java.lang.Thread.sleep;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertTrue;

public class RestApiTest extends BaseTest {
    RestApiController restApiController;

    @Before
    public void getSqlConfig() {
        restApiController = new RestApiController(getProperties().getProperty("restApiUrl"));
    }

    @Test
    public void firstTask() {
        restApiController.shuffleCard().
                then().
                assertThat().statusCode(200)
                .body("deck_id", notNullValue())
                .body("remaining", equalTo(52))
                .body("shuffled", equalTo(true));
    }

    @Test
    public void secondTest() {
        restApiController.shuffleCard();
        Object deck_id = restApiController.getParameterValue("deck_id");
        if (deck_id != null) {
            restApiController.getCard(deck_id.toString(), 2).
                    then().assertThat().statusCode(200)
                    .body("success", equalTo(true))
                    .body("cards", notNullValue())
                    .body("deck_id", equalTo(deck_id.toString()))
                    .body("remaining", equalTo(52 - 2));

            ArrayList cards = (ArrayList) restApiController.getParameterValue("cards");

            for (Object card : cards) {
                HashMap hashMap = (HashMap) card;
                assertTrue(hashMap.containsKey("image"));
                assertTrue(hashMap.containsKey("value"));
                assertTrue(hashMap.containsKey("suit"));
                assertTrue(hashMap.containsKey("code"));
            }
        } else {
            assert false;
        }
    }

    @Test
    public void thertyTest() {

    }

    @Test
    public void fiveTest() throws InterruptedException {
        //todo create normal Mock server
        WireMockServer wireMockServer = new WireMockServer(9999);
        wireMockServer.start();
        configureFor("localhost", wireMockServer.port());
        stubFor(post(urlEqualTo("/create"))
                .withHeader("content-type", WireMock.equalTo("application/json"))
                .withRequestBody(containing("testing-framework"))
                .willReturn(aResponse().withStatus(200)));
        wireMockServer.addMockServiceRequestListener(new RequestListener() {
            public void requestReceived(Request request, com.github.tomakehurst.wiremock.http.Response response) {
                System.out.println("REQUEST RECEIVED");
            }
        });
        sleep(3000);
        given()
                .contentType(ContentType.JSON)
                .body("{\"name\":\"test\",\"salary\":\"123\",\"age\":\"23\"}")
                .post("http://localhost:9999/create").then().assertThat().statusCode(200);
    }
}

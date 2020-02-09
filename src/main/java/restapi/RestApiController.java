package restapi;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RestApiController implements RestConnector {
    private String url;
    private Response response;

    public RestApiController(String url) {
        this.url = url;
    }


    public Response shuffleCard() {
        return given().
                when().
                get(url + "/new/shuffle/?deck_count=1");
    }

    public Response reShuffleCard(String deckId) {
        return given().when().get(url + "/" + deckId + "/shuffle/");
    }

    public Response getCard(String cardDeckId, Integer cardCount) {
        return given().
                when().
                get(url + "/" + cardDeckId + "/draw/?count=" + cardCount);
    }

    public Object getParameterValue(String path) {
        JsonPath jsonPath = response.jsonPath();
        return jsonPath.get(path);
    }
}

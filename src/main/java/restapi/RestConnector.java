package restapi;

import io.restassured.response.Response;

public interface RestConnector {
    public Response shuffleCard();

    public Response reShuffleCard(String deckId);

    public Response getCard(String cardDeckId, Integer cardCount);

    public Object getParameterValue(String path);
}

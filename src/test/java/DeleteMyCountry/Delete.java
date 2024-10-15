package DeleteMyCountry;

import CreateNewCountries.TestBase;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class Delete extends TestBase {

    private static final String COUNTRY_ID = "34";

    @Test(priority = 0)
    public void deleteCountry() {
        Response response = sendDeleteRequest(POST_RESOURCE + "/" + COUNTRY_ID);

        response.then()
                .statusCode(200);

        // Optionally, you could check if the resource is really deleted by trying to get it and asserting a 404 status
        Response checkResponse = sendGetRequest(POST_RESOURCE + "/" + COUNTRY_ID);
        checkResponse.then().statusCode(404);
    }
}

package ModifyLocation;

import CreateNewCountries.TestBase;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;

public class EditdataCountries extends TestBase {

    private static final String COUNTRY_ID = "3";

    @Test(priority = 0)
    public void update() {
        Map<String, Object> updateMap = new HashMap<>();
        updateMap.put("name", "omar");
        updateMap.put("country", "egypt");
        updateMap.put("city", "shobra");
        updateMap.put("code", "73945-1223");

        // PUT request to update the user
        Response updateResponse = sendPutRequest(POST_RESOURCE + "/" + COUNTRY_ID, updateMap);

        validateResponse(updateResponse, 200, "omar", "egypt");

        // Print the updated user details for debugging
        System.out.println("Updated User Response: " + updateResponse.getBody().asString());
    }

    @Test(priority = 1)
    public void getUser() {
        // Use the User ID in a GET request
        Response response = sendGetRequest(POST_RESOURCE + "/" + COUNTRY_ID);
        validateResponse(response, 200, "omar", "egypt");
    }
}

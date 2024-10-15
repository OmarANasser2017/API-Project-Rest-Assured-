package GetCountries;

import CreateNewCountries.TestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;

public class CountriesData extends TestBase {

    private static final String COUNTRY_ID = "4";
    private static final String COUNTRY_NOT_FOUND_ID = "37";

    @Test(priority = 0)
    public void getUser() {
        Response response = sendGetRequest(POST_RESOURCE);

        Assert.assertEquals(response.getStatusCode(), 200);
        JsonPath jsonPath = response.jsonPath();
        List<String> userNames = jsonPath.getList("name");
        String expectedUserName = "salwa";
        Assert.assertTrue(userNames.contains(expectedUserName), "User name not found in the list");
        System.out.println("User Names: " + userNames);
    }

    @Test(priority = 1)
    public void getFirstCountry() {
        Response response = sendGetRequest(POST_RESOURCE);

        Assert.assertEquals(response.getStatusCode(), 200);
        JsonPath jsonPath = response.jsonPath();
        Object firstUser = jsonPath.getList("$").get(0);
        Assert.assertNotNull(firstUser, "First user should not be null");

        String expectedName = "Conrad Gleichner"; // Change this to your expected value
        String actualName = jsonPath.getString("[0].name");
        Assert.assertEquals(actualName, expectedName, "First user name does not match");

        // Assert the ID of the first user (example)
        int expectedId = 1; // Change this to your expected ID
        int actualId = jsonPath.getInt("[0].id");
        Assert.assertEquals(actualId, expectedId, "First user ID does not match");

        System.out.println("First User: " + firstUser);
    }

    @Test(priority = 2)
    public void getLastCountry() {
        Response response = sendGetRequest(POST_RESOURCE);

        Assert.assertEquals(response.getStatusCode(), 200);
        List<HashMap<String, Object>> responseList = response.jsonPath().getList("");

        // Assert that the last user's name is "salwa"
        Assert.assertEquals(responseList.get(responseList.size() - 1).get("name"), "salwa");
        System.out.println("Last User: " + responseList.get(responseList.size() - 1));
    }

    @Test(priority = 3)
    public void getCountryById() {
        Response response = sendGetRequest(POST_RESOURCE + "/" + COUNTRY_ID);

        validateResponse(response, 200, "Jordan Zulauf", "Pakistan");
    }

    @Test(priority = 4)
    public void getNotFoundData() {
        Response response = sendGetRequest(POST_RESOURCE + "/" + COUNTRY_NOT_FOUND_ID);

        Assert.assertEquals(response.getStatusCode(), 404);
    }
}

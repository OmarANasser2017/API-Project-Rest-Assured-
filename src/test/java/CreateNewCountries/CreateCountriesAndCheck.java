package CreateNewCountries;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class CreateCountriesAndCheck extends CreateNewCountries.TestBase {

    private String userId;

    @DataProvider(name = "userData")
    public Object[][] userData() {
        return new Object[][] {
                {"omar", "Ghana"},
                {"salwa", "Egypt"}
        };
    }

    @Test(priority = 0)
    public void test_post() {
        Map<String, Object> userData = new HashMap<>();
        userData.put("name", "omar");
        userData.put("country", "Ghana");
        userData.put("avatar", "https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/1244.jpg");
        userData.put("city", "Cairo");
        userData.put("code", "56403-2276");

        Response response = sendPostRequest(userData);
        validateResponse(response, 201, "omar", "Ghana");

        JsonPath jsonPath = response.jsonPath();
        userId = jsonPath.getString("id");
        System.out.println("Saved User ID: " + userId);
    }

    @Test(priority = 1, dependsOnMethods = "test_post")
    public void getUser() {
        Response response = RestAssured.given().get(POST_RESOURCE + "/" + userId);
        validateResponse(response, 200, "omar", "Ghana");
    }

    @Test(priority = 2, dependsOnMethods = "test_post")
    public void update() {
        Map<String, Object> updateData = new HashMap<>();
        updateData.put("name", "salwa");
        updateData.put("country", "egypt");
        updateData.put("city", "New Cairo");
        updateData.put("code", "87403-1576");

        Response updateResponse = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(updateData)
                .put(POST_RESOURCE + "/" + userId);

        validateResponse(updateResponse, 200, "salwa", "egypt");
        System.out.println("Updated User Response: " + updateResponse.getBody().asString());
    }
}

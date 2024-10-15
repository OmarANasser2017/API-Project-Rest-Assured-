package CreateNewCountries;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;

import static org.testng.AssertJUnit.assertEquals;

public class TestBase {

    protected static final String BASE_URL = "https://66bf916342533c4031468871.mockapi.io/api/v1";
    protected static final String POST_RESOURCE = "/omar";

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = BASE_URL;
    }
    protected Response sendGetRequest(String endpoint) {
        return RestAssured.given().get(endpoint);
    }

    protected Response sendPostRequest(Object body) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .body(body)
                .post(POST_RESOURCE);
    }
    protected Response sendPutRequest(String endpoint, Object body) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .body(body)
                .put(endpoint);
    }
    protected Response sendDeleteRequest(String endpoint) {
        return RestAssured.given().delete(endpoint);
    }

    protected void validateResponse(Response response, int expectedStatusCode, String expectedName, String expectedCountry) {
        assertEquals(response.getStatusCode(), expectedStatusCode);
        assertEquals(response.jsonPath().getString("name"), expectedName);
        assertEquals(response.jsonPath().getString("country"), expectedCountry);
    }
}

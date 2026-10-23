package BookingManagement;

import core.StatusCode;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class ValidAuth {

    public String CreateAuthToken() {
        // Set base URI for the API
        RestAssured.baseURI = "https://restful-booker.herokuapp.com/";

        // Authenticate and get an authorization token
        Response response = given()
                .header("Content-Type", "application/json")
                .body("{\"username\":\"admin\",\"password\":\"password123\"}")
                .when()
                .post("/auth");
        assertEquals(response.getStatusCode(), StatusCode.SUCCESS.code);
        String token = response.path("token");
        return token;

    }

}
package BookingManagement;

import core.StatusCode;
import core.BaseTest;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class HealthAndReadTests extends BaseTest {

    @Test
    public void validateAPIHealth() {
        // Set base URI for the API
//        RestAssured.baseURI = "https://restful-booker.herokuapp.com/";

        Response response = given()
                .spec(requestSpec)
                .when()
                .get("/ping");
        System.out.println(response.getBody().asString());
        assertEquals(response.getStatusCode(), StatusCode.CREATED.code);
        System.out.println("ValidateAPIHealth executed successfully");
    }

    @Test
    public void ValidateContentTypeHeader() {
        Response response = given()
                .spec(requestSpec)
                .when()
                .get("/booking");
        String contentType = response.getHeader("Content-Type");
        System.out.println("Content-type: " + contentType);
        assertEquals(response.getStatusCode(), StatusCode.SUCCESS.code);
        System.out.println("ValidateContentTypeHeader executed successfully");

    }

}

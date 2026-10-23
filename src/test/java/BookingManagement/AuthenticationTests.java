package BookingManagement;

import core.StatusCode;
import core.BaseTest;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.RestAssuredWrapper;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNull;

public class AuthenticationTests extends BaseTest {

    @Test
    public void ValidateInvalidAccessToken() {
        // Set base URI for the API
//        RestAssured.baseURI = "https://restful-booker.herokuapp.com/";

        Response response = given()
                .spec(requestSpec)
//                .cookie("token", "32ab561b67bc3c1")
//                .header("Content-Type", "application/json")
                .body("{\"firstname\":\"James\",\"lastname\":\"Brown\",\"totalprice\":111,\"depositpaid\":true," +
                        "\"bookingdates\":{\"checkin\":\"2018-01-01\",\"checkout\":\"2019-01-01\"}," +
                        "\"additionalneeds\":\"breakfast\"}")
                .when()
                .put("/booking/1");
        System.out.println(response.getBody().asString());
        assertEquals(response.getStatusCode(), StatusCode.Forbidden.code);
        System.out.println("ValidateInvalidAccessToken executed successfully");
    }

    @Test
    public void ValidateMissingToken() {
        // Set base URI for the API
//        RestAssured.baseURI = "https://restful-booker.herokuapp.com/";

        Response response = given()
                .spec(requestSpec)
//                .cookie("token", "")
//                .header("Content-Type", "application/json")
                .body("{\"firstname\":\"James\",\"lastname\":\"Brown\",\"totalprice\":111,\"depositpaid\":true," +
                        "\"bookingdates\":{\"checkin\":\"2018-01-01\",\"checkout\":\"2019-01-01\"}," +
                        "\"additionalneeds\":\"breakfast\"}")
                .when()
                .put("/booking/1");
        System.out.println(response.getBody().asString());
        assertEquals(response.getStatusCode(), StatusCode.Forbidden.code);
        System.out.println("ValidateMissingToken executed successfully");
    }

    @Test
    public void testInvalidPassword() {

        Response response = RestAssuredWrapper.invalidPassword();
        System.out.println(response.getBody().asString());
        assertEquals(response.statusCode(), StatusCode.SUCCESS.code);
        assertEquals(response.jsonPath().getString("reason"), "Bad credentials");
        assertNull(response.jsonPath().getString("token"));
    }

    @Test
    public void testInvalidUserName() {

        Response response = RestAssuredWrapper.invalidUserName();
        System.out.println(response.getBody().asString());
        assertEquals(response.statusCode(), StatusCode.SUCCESS.code);
        assertEquals(response.jsonPath().getString("reason"), "Bad credentials");
        assertNull(response.jsonPath().getString("token"));
    }

    @Test
    public void testEmptyAuthenticationBody() {

        Response response = RestAssuredWrapper.emptyAuthenticationBody();
        System.out.println(response.getBody().asString());
        assertEquals(response.statusCode(), StatusCode.SUCCESS.code);
        assertEquals(response.jsonPath().getString("reason"), "Bad credentials");
        assertNull(response.jsonPath().getString("token"));
    }

    @Test
    public void testMissingUserName() {

        Response response = RestAssuredWrapper.missingUserName();
        System.out.println(response.getBody().asString());
        assertEquals(response.statusCode(), StatusCode.SUCCESS.code);
        assertEquals(response.jsonPath().getString("reason"), "Bad credentials");
        assertNull(response.jsonPath().getString("token"));
    }
}
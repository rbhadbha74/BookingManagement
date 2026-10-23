package BookingManagement;

import core.StatusCode;
import core.BaseTest;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class UpdateBookingTest extends BaseTest {

    @Test
    public void validatePutBooking() {

        // Authenticate and get an authorization token

        ValidAuth auth = new ValidAuth();

        String Token = auth.CreateAuthToken();

//        System.out.println(Token);
        System.out.println("Token: " + Token);

        Response response = given()
                .spec(requestSpec)
                .cookie("token", Token)
//                .header("Content-Type", "application/json")
//                .header("Accept","application/json")
                .body("{\"firstname\":\"Ahaan\",\"lastname\":\"Bhadbhade\",\"totalprice\":105,\"depositpaid\":false," +
                        "\"bookingdates\":{\"checkin\":\"2026-01-01\",\"checkout\":\"2027-01-01\"}," +
                        "\"additionalneeds\":\"lunch\"}")
                .when()
                .put("/booking/3292");
        System.out.println(response.getBody().asString());
        assertEquals(response.getStatusCode(), StatusCode.SUCCESS.code);
        System.out.println("ValidatePutBooking executed successfully");
    }

    @Test
    public void validatePatchBooking() {
        // Set base URI for the API
//        RestAssured.baseURI = "https://restful-booker.herokuapp.com/";

        // Authenticate and get an authorization token

        ValidAuth auth = new ValidAuth();

        String Token = auth.CreateAuthToken();

        System.out.println(Token);

        Response response = given()
                .spec(requestSpec)
                .cookie("token", Token)
//                .header("Content-Type", "application/json")
//                .header("Accept","application/json")
                .body("{\"firstname\":\"James\",\"lastname\":\"Brown\"}")
                .when()
                .patch("/booking/1");
        System.out.println(response.getBody().asString());
        assertEquals(response.getStatusCode(), StatusCode.SUCCESS.code);
        System.out.println("ValidatePatchBooking executed successfully");
    }
}

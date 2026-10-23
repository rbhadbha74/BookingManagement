package BookingManagement;

import core.BaseTest;
import core.StatusCode;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class PutPatchLifecycleTest  extends BaseTest {

    /* Create Booking */

    @Test
    public void TestCreateNewBooking() {

        String firstName = "Siya";
        String lastName = "Bhadbhade";
        String additionalNeeds = "breakfast";

        Response response = given()
                .spec(requestSpec)
                .body("{\"firstname\":\"Siya\",\"lastname\":\"Bhadbhade\",\"totalprice\":101,\"depositpaid\":true," +
                        "\"bookingdates\":{\"checkin\":\"2024-01-01\",\"checkout\":\"2025-01-01\"}," +
                        "\"additionalneeds\":\"breakfast\"}")
                .when()
                .post("/booking/");
        assertEquals(response.getStatusCode(), StatusCode.SUCCESS.code);
        System.out.println(response.getBody().asString());

    }

    @Test
    public void TestUpdateBooking() {

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
                .body("{\"firstname\":\"Ajita\",\"lastname\":\"Bhadbhade\",\"totalprice\":108,\"depositpaid\":false," +
                        "\"bookingdates\":{\"checkin\":\"2026-01-01\",\"checkout\":\"2027-01-01\"}," +
                        "\"additionalneeds\":\"lunch\"}")
                .when()
                .put("/booking/1028");
        System.out.println(response.getBody().asString());
        assertEquals(response.getStatusCode(), StatusCode.SUCCESS.code);
        System.out.println("TestUpdateBooking executed successfully");

    }

    @Test
    public void validateUpdatedBooking() {
        // Set base URI for the API
//        RestAssured.baseURI = "https://restful-booker.herokuapp.com/";

        // Authenticate and get an authorization token
        Response response = given()
                .spec(requestSpec)
//                .header("Content-Type", "application/json")
                .when()
                .get("/booking/1028");
        System.out.println(response.getBody().asString());
        assertEquals(response.getStatusCode(), StatusCode.SUCCESS.code);
        System.out.println("ValidateUpdateBooking executed successfully");
    }
}

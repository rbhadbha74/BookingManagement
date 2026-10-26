package BookingManagement;

import core.BaseTest;
import core.StatusCode;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class PutPatchLifecycleTest  extends BaseTest {

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
                .put("/booking/1048");
        System.out.println(response.getBody().asString());
        assertEquals(response.getStatusCode(), StatusCode.SUCCESS.code);
        System.out.println("TestUpdateBooking executed successfully");

    }

    @Test
    public void UpdateBookingWithFirstNameAndValidateUnchangedFields() {

//       validate fields before updating with first name

        Response originalResponse = given()
                .spec(requestSpec)
//                .header("Content-Type", "application/json")
                .when()
                .get("/booking/1048");
        System.out.println(originalResponse.getBody().asString());
        assertEquals(originalResponse.getStatusCode(), StatusCode.SUCCESS.code);

        Object originalPrice = originalResponse.jsonPath().get("booking.totalprice");
        Object originalDeposit = originalResponse.jsonPath().get("booking.depositpaid");
        String originalCheckin = originalResponse.jsonPath().getString("bookingdates.checkin");
        String originalCheckout = originalResponse.jsonPath().getString("bookingdates.checkout");
        String originalAdditionalNeeds = originalResponse.jsonPath().getString("booking.additionalneeds");

//        update booking with only first name

        // Authenticate and get an authorization token

        ValidAuth auth = new ValidAuth();

        String Token = auth.CreateAuthToken();

        System.out.println(Token);

        Response response = given()
                .spec(requestSpec)
                .cookie("token", Token)
//                .header("Content-Type", "application/json")
//                .header("Accept","application/json")
                .body("{\"firstname\":\"Mahesh\"}")
                .when()
                .patch("/booking/1048");
        System.out.println(response.getBody().asString());
        assertEquals(response.getStatusCode(), StatusCode.SUCCESS.code);

//        validate unchanged fields remain the same after updating the first name

        Response updatedResponse = given()
                .spec(requestSpec)
//                .header("Content-Type", "application/json")
                .when()
                .get("/booking/1048");
        System.out.println(updatedResponse.getBody().asString());
        assertEquals(updatedResponse.getStatusCode(), StatusCode.SUCCESS.code);

        assertEquals(updatedResponse.jsonPath().get("booking.totalprice"),originalPrice);
        assertEquals(updatedResponse.jsonPath().get("booking.depositpaid"),originalDeposit);
        assertEquals(updatedResponse.jsonPath().getString("bookingdates.checkin"),originalCheckin);
        assertEquals(updatedResponse.jsonPath().getString("bookingdates.checkout"),originalCheckout);
        assertEquals(updatedResponse.jsonPath().getString("booking.additionalneeds"),originalAdditionalNeeds);

        System.out.println("UpdateBookingWithFirstNameAndValidateUnchangedFields executed successfully");
    }
}

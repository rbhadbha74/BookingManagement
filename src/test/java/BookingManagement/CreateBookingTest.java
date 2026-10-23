package BookingManagement;

import core.StatusCode;
import core.BaseTest;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.internal.junit.ArrayAsserts.assertArrayEquals;

public class CreateBookingTest extends BaseTest{

    @Test
    public void validateCreateNewBooking() {

        String firstName= "Rakhee";
        String lastName= "Bhadbhade";
        String additionalNeeds = "breakfast";

        Response response = given()
                .spec(requestSpec)
                .body("{\"firstname\":\"Rakhee\",\"lastname\":\"Bhadbhade\",\"totalprice\":100,\"depositpaid\":true," +
                        "\"bookingdates\":{\"checkin\":\"2025-01-01\",\"checkout\":\"2026-01-01\"}," +
                        "\"additionalneeds\":\"breakfast\"}")
                .when()
                .post("/booking/");
        assertEquals(response.getStatusCode(), StatusCode.SUCCESS.code);
        System.out.println(response.getBody().asString());

        /*Verify First and Last Name of the created Booking*/

        assertEquals(response.jsonPath().getString("booking.firstname"),firstName);
        assertEquals(response.jsonPath().getString("booking.lastname"),lastName);

        /* Verify that the total price is number*/

        Object totalPrice = response.jsonPath().get("booking.totalprice");
        assertTrue(totalPrice instanceof Number,"totalprice should be a number");

        /* Verify that deposit paid is a boolean */

        Object depositPath = response.jsonPath().getBoolean("booking.depositpaid");
        assertEquals(depositPath,true);

        /* Verify Checkin and Checkout dates*/

        String checkin = response.jsonPath().getString("bookingdates.checkin");
        String checkout = response.jsonPath().getString("bookingdates.checkout");
        assertEquals(checkin,"2018-01-01","2018-01-01");
        assertEquals(checkout,"2019-01-01","2019-01-01");

        /* additional needs is returned*/

        assertEquals(response.jsonPath().getString("booking.additionalneeds"),additionalNeeds);

        System.out.println("ValidateCreateNewBooking executed successfully");
    }
}
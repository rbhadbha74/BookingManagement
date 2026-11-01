package BookingManagement;
import core.BaseTest;
import core.StatusCode;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

public class ResponseValidationTests extends BaseTest {

    @Test
    public void ValidateResponseAgainstSchema() {

        Response response = given()
                .spec(requestSpec)
                .when()
                .get("/booking/4");
        System.out.println(response.getBody().asString());
        assertEquals(response.getStatusCode(), StatusCode.SUCCESS.code);

//        Validate response Schema

        response.then()
                .body(matchesJsonSchemaInClasspath("Schemas/booking-schema.json"));
        System.out.println("ValidateResponseAgainstSchema executed successfully");
    }

    @Test
    public void validateRequiredFieldsInResponse() {

        Response response = given()
                .spec(requestSpec)
                .when()
                .get("/booking/4");
        System.out.println(response.getBody().asString());
        assertEquals(response.getStatusCode(), StatusCode.SUCCESS.code);
        assertNotNull(response.jsonPath().getString("booking.firstname"),"Firstname is missing");
        assertNotNull(response.jsonPath().getString("booking.lastname"),"Lastname is missing");
        assertNotNull(response.jsonPath().get("booking.totalprice"),"Total price is missing");
        assertNotNull(response.jsonPath().get("booking.depositpaid"),"Depositpaid is missing");
        assertNotNull(response.jsonPath().get("bookingdates.checkin"),"Checkin date is missing");
        assertNotNull(response.jsonPath().get("bookingdates.checkout"),"Checkout date is missing");
        System.out.println("ValidateRequiredFieldsInResponse executed successfully");
    }
}

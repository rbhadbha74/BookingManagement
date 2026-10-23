package BookingManagement;

import core.StatusCode;
import core.BaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;


import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.testng.AssertJUnit.assertEquals;

public class GetBookingTest extends BaseTest{

    @Test
    public void validateGetAllBookings() {
        // Set base URI for the API
//        RestAssured.baseURI = "https://restful-booker.herokuapp.com/";

        // Authenticate and get an authorization token

        Response response = given()
                .spec(requestSpec)
//                .header("Content-Type", "application/json")
                .when()
                .get("/booking");
        System.out.println(response.getBody().asString());
        assertEquals(response.getStatusCode(), StatusCode.SUCCESS.code);
        System.out.println("ValidateGetAllBookings executed successfully");
    }

    @Test
    public void validateGetBookingsById() {
        // Set base URI for the API
//        RestAssured.baseURI = "https://restful-booker.herokuapp.com/";

        // Authenticate and get an authorization token
        Response response = given()
                .spec(requestSpec)
//                .header("Content-Type", "application/json")
                .when()
                .get("/booking/4");
        System.out.println(response.getBody().asString());
        assertEquals(response.getStatusCode(), StatusCode.SUCCESS.code);
        System.out.println("ValidateGetBookingsById executed successfully");
    }

    @Test
    public void GetBookingIdsFilteredByFirstName() {
//        RestAssured.baseURI = "https://restful-booker.herokuapp.com/";

        Response response = given()
                .spec(requestSpec)
                .queryParam("firstname", "Jane")
                .when()
                .get("/booking")
                .then()
                .extract()
                .response();
        response.then().body("", not(empty()));

        // Assert that the bookingid  in the list has the correct values
        response.then().body("bookingid", everyItem(notNullValue()));

        // Extract booking IDs dynamically
        List<Integer> bookingIds =
                response.jsonPath().getList("bookingid", Integer.class);

        System.out.println("Booking IDs: " + bookingIds);
        System.out.println("Number of bookings: " + bookingIds.size());

        assertEquals(response.getStatusCode(), StatusCode.SUCCESS.code);
        System.out.println("GetBookingIdsFilteredByFirstName executed successfully");
    }

    @Test
    public void GetBookingIdsFilteredByMultipleParameters() {
//        RestAssured.baseURI = "https://restful-booker.herokuapp.com/";

        Response response = given()
                .spec(requestSpec)
                .queryParam("firstname", "Jane")
                .queryParam("lastname", "Doe")
                .queryParam("bookingdates", "2018-01-01")
                .queryParam("bookingdates", "2019-01-01")
                .when()
                .get("/booking")
                .then()
                .extract()
                .response();
        response.then().body("", not(empty()));

        // Assert that the bookingid  in the list has the correct values
        response.then().body("bookingid", everyItem(notNullValue()));

        // Extract booking IDs dynamically
        List<Integer> bookingIds =
                response.jsonPath().getList("bookingid", Integer.class);

        System.out.println("Booking IDs: " + bookingIds);
        System.out.println("Number of bookings: " + bookingIds.size());

        assertEquals(response.getStatusCode(), StatusCode.SUCCESS.code);
        System.out.println("GetBookingIdsFilteredByMultipleParameters executed successfully");
    }

    @Test
    public void validateBookingIdWithGetPathParam() {
//        RestAssured.baseURI = "https://restful-booker.herokuapp.com/";
        Response resp = given()
                .spec(requestSpec)
                .pathParam("id", 4)
                .when()
                .get("/Booking/{id}"); //RestAssured
        int actualStatusCode = resp.statusCode();  //RestAssured
        Assert.assertEquals(actualStatusCode, 200); //Testng
//below step will help to print the response body in the console
        System.out.println(resp.body().asString());
    }

    @Test
    public void GetBookingIdsFilteredByLastName() {
//        RestAssured.baseURI = "https://restful-booker.herokuapp.com/";

        Response response = given()
                .spec(requestSpec)
                .queryParam("lastname", "Doe")
                .when()
                .get("/booking")
                .then()
                .extract()
                .response();
        response.then().body("", not(empty()));

        // Assert that the bookingid  in the list has the correct values
        response.then().body("bookingid", everyItem(notNullValue()));

        // Extract booking IDs dynamically
        List<Integer> bookingIds =
                response.jsonPath().getList("bookingid", Integer.class);

        System.out.println("Booking IDs: " + bookingIds);
        System.out.println("Number of bookings: " + bookingIds.size());

        assertEquals(response.getStatusCode(), StatusCode.SUCCESS.code);
        System.out.println("GetBookingIdsFilteredByLastName executed successfully");
    }


}

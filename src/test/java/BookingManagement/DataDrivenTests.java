package BookingManagement;

import core.BaseTest;
import Pojo.Booking;
import core.StatusCode;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import java.io.IOException;
import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class DataDrivenTests extends BaseTest {

    @Test
    public void validatePostWithPojo() throws IOException {

        Booking postRequest = new Booking();
        Booking.BookingDates postRequestDate = new Booking.BookingDates();
        postRequest.setFirstname("Rakhee");
        postRequest.setLastname("Bhadbhade");
        postRequest.setTotalprice(105);
        postRequest.setDepositpaid(true);
        postRequestDate.setCheckin("2018-01-01");
        postRequestDate.setCheckout("2019-01-01");
        postRequest.setAdditionalneeds("breakfast");

        postRequest.setBookingdates(postRequestDate);

        Response response = given()
                .spec(requestSpec)
                .body(postRequest)
                .when()
                .post("/booking/");
        assertEquals(response.getStatusCode(),StatusCode.SUCCESS.code);
        System.out.println(response.getBody().asString());
        System.out.println("ValidatePostWithPojo executed successfully");
    }

    @Test
    public void validatePutWithPojo() throws IOException {

        Booking postRequest = new Booking();
        Booking.BookingDates postRequestDate = new Booking.BookingDates();
        postRequest.setFirstname("Siya");
        postRequest.setLastname("Bhadbhade");
        postRequest.setTotalprice(108);
        postRequest.setDepositpaid(false);
        postRequestDate.setCheckin("2024-01-01");
        postRequestDate.setCheckout("2025-01-01");
        postRequest.setAdditionalneeds("lunch");

        postRequest.setBookingdates(postRequestDate);

        // Authenticate and get an authorization token

        ValidAuth auth = new ValidAuth();

        String Token = auth.CreateAuthToken();

//        System.out.println(Token);
        System.out.println("Token: " + Token);

        Response response = given()
                .spec(requestSpec)
                .cookie("token",Token  )
                .body(postRequest)
                .when()
                .put("/booking/2681");
        assertEquals(response.getStatusCode(),StatusCode.SUCCESS.code);
        System.out.println(response.getBody().asString());
        System.out.println("ValidatePutWithPojo executed successfully");
    }

    @Test
    public void validatePatchWithPojo() throws IOException {

        Booking postRequest = new Booking();
        Booking.BookingDates postRequestDate = new Booking.BookingDates();
        postRequest.setFirstname("Ahaan");
        postRequest.setLastname("Bhadbhade");
        postRequest.setTotalprice(108);
        postRequest.setDepositpaid(false);
        postRequestDate.setCheckin("2024-01-01");
        postRequestDate.setCheckout("2025-01-01");
        postRequest.setAdditionalneeds("lunch");

        postRequest.setBookingdates(postRequestDate);

        // Authenticate and get an authorization token

        ValidAuth auth = new ValidAuth();

        String Token = auth.CreateAuthToken();

//        System.out.println(Token);
        System.out.println("Token: " + Token);

        Response response = given()
                .spec(requestSpec)
                .cookie("token",Token  )
                .body(postRequest)
                .when()
                .put("/booking/772");
        assertEquals(response.getStatusCode(),StatusCode.SUCCESS.code);
        System.out.println(response.getBody().asString());
        System.out.println("ValidatePatchWithPojo executed successfully");
    }

    @Test
    public void validatePatchWithResponsePojo() throws IOException {
        // Set base URI for the API
        RestAssured.baseURI = "https://reqres.in/api";

        String job = "morpheus";

        postRequest patchRequest = new postRequestBody();
        patchRequest.setJob(job);

        // Authenticate and get an authorization token
        Response response = given()
                .header("x-api-key", "reqres_ba038908290340539c12fe45b4aecdb4")
                .header("Content-Type", "application/json")
                .body(patchRequest)
                .when()
                .patch("/users/2");
        postRequestBody responseBody = response.as(postRequestBody.class);
        System.out.println(responseBody.getJob());
        assertEquals(job, responseBody.getJob());
        assertEquals(response.getStatusCode(), StatusCode.SUCCESS.code);
        System.out.println("ValidatePatchWithResponsePojo executed successfully");
        System.out.println(response.getBody().asString());

    }
}

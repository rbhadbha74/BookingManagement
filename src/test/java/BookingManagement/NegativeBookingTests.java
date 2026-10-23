package BookingManagement;

import core.StatusCode;
import core.BaseTest;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class NegativeBookingTests extends BaseTest{

    @Test
    public void validateInvalidBookingId() {
        // Set base URI for the API
//        RestAssured.baseURI = "https://restful-booker.herokuapp.com/";

        // Authenticate and get an authorization token
        Response response = given()
//                .header("Content-Type", "application/json")
                .when()
                .get("/booking/A");
        System.out.println(response.getBody().asString());
        assertEquals(response.getStatusCode(), StatusCode.NOT_FOUND.code);
        System.out.println("ValidateInvalidBookingId executed successfully");
    }

    @Test
    public void validateMissingMandatoryField() {
        // Set base URI for the API
//        RestAssured.baseURI = "https://restful-booker.herokuapp.com/";

        // Authenticate and get an authorization token

        ValidAuth auth = new ValidAuth();

        String Token = auth.CreateAuthToken();

        System.out.println(Token);

        Response response = given()
                .spec(requestSpec)
                .cookie("", Token)
//                .header("Content-Type", "application/json")
//                .header("Accept","application/json")
                .body("{\"firstname\":\"James\",\"lastname\":\"Brown\"}")
                .when()
                .patch("/booking/1");
        System.out.println(response.getBody().asString());
        assertEquals(response.getStatusCode(), StatusCode.Forbidden.code);
        System.out.println("ValidateMissingMandatoryField executed successfully");
    }

    @Test
    public void validateInvalidDataType() {
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
                .body("{\"firstname\":\"James\",\"lastname\":\"Brown\",\"totalprice\":abc,\"depositpaid\":true," +
                        "\"bookingdates\":{\"checkin\":\"2018-01-01\",\"checkout\":\"2019-01-01\"}," +
                        "\"additionalneeds\":\"breakfast\"}")
                .when()
                .put("/booking/1");
        System.out.println(response.getBody().asString());
        assertEquals(response.getStatusCode(), StatusCode.BAD_REQUEST.code);
        System.out.println("ValidateInvalidDataType executed successfully");
    }

    @Test()
    public void validateEmptyRequestBody() {
        // Set base URI for the API
//        RestAssured.baseURI = "https://restful-booker.herokuapp.com/";

        // Authenticate and get an authorization token

        ValidAuth auth = new ValidAuth();

        String Token = auth.CreateAuthToken();

        System.out.println(Token);

        Response response = given()
                .spec(requestSpec)
                .cookie("", Token)
//                .header("Content-Type", "application/json")
//                .header("Accept","application/json")
                .body("")
                .when()
                .patch("/booking/1");
        System.out.println(response.getBody().asString());
        assertEquals(response.getStatusCode(), StatusCode.Forbidden.code);
        System.out.println("ValidateEmptyRequestBody executed successfully");

    }

    @Test()
    public void validateInvalidEndpoint() {
        // Set base URI for the API
//        RestAssured.baseURI = "https://restful-booker.herokuapp.com/";

        // Authenticate and get an authorization token

        ValidAuth auth = new ValidAuth();

        String Token = auth.CreateAuthToken();

        System.out.println(Token);

        Response response = given()
                .spec(requestSpec)
                .cookie("", Token)
//                .header("Content-Type", "application/json")
//                .header("Accept","application/json")
                .body("")
                .when()
                .patch("/booking/");
        System.out.println(response.getBody().asString());
        assertEquals(response.getStatusCode(), StatusCode.NOT_FOUND.code);
        System.out.println("ValidateInvalidEndpoint executed successfully");

    }
}

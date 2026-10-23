package BookingManagement;

import core.StatusCode;
import core.BaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteBookingTest extends BaseTest{

    @Test()
    public void ValidateDeleteBooking() {

//        RestAssured.baseURI = "https://restful-booker.herokuapp.com/";

        // Authenticate and get an authorization token

        ValidAuth auth = new ValidAuth();

        String Token = auth.CreateAuthToken();

        System.out.println(Token);

        Response resp = given()
                .spec(requestSpec)
                .cookie("token", Token)
//                .header("Content-Type", "application/json")
                .delete("/booking/1");
        Assert.assertEquals(resp.getStatusCode(), StatusCode.NO_CONTENT.code);
        System.out.println(" ValidateDeleteBooking Executed Successfully");
    }
}

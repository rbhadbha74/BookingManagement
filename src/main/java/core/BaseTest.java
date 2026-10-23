package core;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

import org.testng.annotations.BeforeClass;

import static io.restassured.RestAssured.given;

    public class BaseTest {

        protected RequestSpecification requestSpec;

        @BeforeClass
        public void setUp() {

            RestAssured.baseURI =
                    "https://restful-booker.herokuapp.com";

            requestSpec = given()
                    .baseUri(RestAssured.baseURI)
//                    .cookie("token", "")
//                    .cookie("token", "32ab561b67bc3c1")
                    .contentType("application/json")
                    .accept("application/json");
        }
    }



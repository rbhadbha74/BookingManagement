package core;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

import org.testng.annotations.BeforeClass;

import static io.restassured.RestAssured.given;

    public class BaseTest {

        protected RequestSpecification requestSpec;

        @BeforeClass
        public void setUp() {

            System.out.println("BaseTest setup started");

            RestAssured.baseURI =
                    "https://restful-booker.herokuapp.com";

            requestSpec = given()
                    .baseUri(RestAssured.baseURI)
                    .contentType("application/json")
                    .accept("application/json");

            System.out.println("RequestSpec initialized: " + requestSpec);

        }
    }



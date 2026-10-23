package utils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RestAssuredWrapper {

    public static Response invalidPassword() {
        return given()
                .contentType(ContentType.JSON)
                .body("{\"username\":\"admin\",\"password\":\"wrongPassword\"}")
                .when()
                .post("/auth");
    }

    public static Response invalidUserName() {
        return given()
                .contentType(ContentType.JSON)
                .body("{\"username\":\"nonadmin\",\"password\":\"password123\"}")
                .when()
                .post("/auth");
    }

    public static Response emptyAuthenticationBody() {
        return given()
                .contentType(ContentType.JSON)
                .body("")
                .when()
                .post("/auth");
    }

    public static Response missingUserName() {
        return given()
                .contentType(ContentType.JSON)
                .body("{\"username\":\"\",\"password\":\"password123\"}")
                .when()
                .post("/auth");
    }
}

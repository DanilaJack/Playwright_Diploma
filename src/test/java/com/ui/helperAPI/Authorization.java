package com.ui.helperAPI;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class Authorization {

    static String token;

    public static String getAccessToken(String username, String password) {

        token = given()
                .spec(
                        new RequestSpecBuilder()
                                .setContentType(ContentType.JSON)
                                .setRelaxedHTTPSValidation()
                                .build()
                )
                .body("{\"username\":\"" + username + "\",\"password\":\"" + password + "\"}")
                .when()
                .post("https://app.dev.ggis.iccdev.ru/ggis/api/auth")
                .then()
                .log().all()
                .statusCode(200)
                .time(lessThan(5000L)) //--> time in milliseconds / time response from server
                .extract().body().jsonPath().getString("access_token");

        return token;
    }

}

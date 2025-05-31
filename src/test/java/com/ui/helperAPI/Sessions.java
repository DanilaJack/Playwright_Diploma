package com.ui.helperAPI;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;

import static com.ui.helperAPI.Authorization.token;
import static io.restassured.RestAssured.given;

public class Sessions {

    public static void delete_all_sessions() {

        given()
                .spec(
                        new RequestSpecBuilder()
                                .setContentType(ContentType.JSON)
                                .setRelaxedHTTPSValidation()
                                .build()
                )
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .when()
                .delete("https://objects.stage.ggis.iccdev.ru/ggis/api/sessions")
                .then()
                .statusCode(200);
    }
}

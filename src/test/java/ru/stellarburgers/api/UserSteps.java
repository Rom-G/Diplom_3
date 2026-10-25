package ru.stellarburgers.api;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static ru.stellarburgers.data.UserData.*;

public class UserSteps {

    public static Response registerUser(String email, String password, String name) {
        UserRequestModel user = new UserRequestModel(email, password, name);
        return given()
                .spec(ApiClient.getBaseSpec())
                .body(user)
                .when()
                .post(USER_REGISTER_PATH);
    }

    public static Response loginUser(String email, String password) {
        UserRequestModel user = new UserRequestModel(email, password);
        return given()
                .spec(ApiClient.getBaseSpec())
                .body(user)
                .when()
                .post(USER_LOGIN_PATH);
    }

    public static void deleteUser(String accessToken) {
        given()
                .spec(ApiClient.getAuthSpec(accessToken))
                .when()
                .delete(USER_PATH);
    }
}

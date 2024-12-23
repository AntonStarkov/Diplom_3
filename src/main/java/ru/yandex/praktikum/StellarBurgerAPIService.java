package ru.yandex.praktikum;

import io.qameta.allure.Param;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.qameta.allure.model.Parameter.Mode.HIDDEN;
import static io.restassured.RestAssured.given;

public class StellarBurgerAPIService {
    private static final String URI = "https://stellarburgers.nomoreparties.site";
    private static final String API_AUTH_USER = "/api/auth/user";
    private static final String API_AUTH_REGISTER = "/api/auth/register";
    private static final String API_AUTH_LOGIN = "/api/auth/login";
    private static final String USER_NAME = "ninja";
    private static final String USER_PASSWORD = "ninja123";
    private static final String USER_EMAIL = "ninja@yandex.ru";
    @Step("Send DELETE request to delete user to /api/auth/user")
    public void deleteUserResponse(@Param(mode=HIDDEN)String userToken){
        given()
                .baseUri(URI)
                .header("Authorization", userToken)
                .delete(API_AUTH_USER);
    }
    @Step("Send POST request to create user to /api/auth/register")
    public Response createUserResponse() {
        return given()
                .baseUri(URI)
                .header("Content-type", "application/json")
                .body(userData())
                .post(API_AUTH_REGISTER);
    }
    @Step("Send POST request to login user to /api/auth/login")
    public Response loginUserResponse() {
        Map<String,String> map = userData();
        map.remove("name");
        return given()
                .baseUri(URI)
                .header("Content-type", "application/json")
                .body(map)
                .post(API_AUTH_LOGIN);
    }
    @Step("Put to Map email, password, name to create user")
    private Map<String,String> userData (){
        Map<String,String> map = new HashMap<>();
        map.put("email", USER_EMAIL);
        map.put("password", USER_PASSWORD);
        map.put("name", USER_NAME);
        return map;
    }
}

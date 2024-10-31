package ru.yandex.praktikum;

import io.qameta.allure.Step;

import static io.restassured.RestAssured.given;

public class StellarBurgerAPIService {
    @Step("Send DELETE request to delete user to /api/auth/user")
    public void deleteUserResponse(String userToken){
        given()
                .baseUri("https://stellarburgers.nomoreparties.site")
                .header("Authorization", userToken)
                .delete("/api/auth/user");
    }
}

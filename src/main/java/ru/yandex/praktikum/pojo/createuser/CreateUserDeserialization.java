package ru.yandex.praktikum.pojo.createuser;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserDeserialization {
    private boolean success;
    private User user;
    private String accessToken;
    private String refreshToken;
}

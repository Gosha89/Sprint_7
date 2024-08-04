package ru.yandex.praktikum;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class Client {
    static final String BASE_URI = "https://qa-scooter.praktikum-services.ru";
    private static final String BASE_PATH = "/api/v1";

    @Step("Настройка и инициализация HTTP-запроса")
    public RequestSpecification spec() {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI)
                .basePath(BASE_PATH);
    }
}
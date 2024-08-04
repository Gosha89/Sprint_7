package ru.yandex.praktikum.courier;

import org.apache.commons.lang3.RandomStringUtils;

public class CourierGenerator {

    public static Courier random() {
        return new Courier("Test" + RandomStringUtils.randomAlphanumeric(5, 10), "password1", "Ivanova");
    }
}

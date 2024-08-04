package ru.yandex.praktikum.courier;

import ru.yandex.praktikum.Client;
import ru.yandex.praktikum.order.OrderClient;
import ru.yandex.praktikum.order.OrderCreate;
import ru.yandex.praktikum.order.OrderData;
import ru.yandex.praktikum.order.OrderList;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class OrderCreateTest extends Client {

    private final OrderClient client = new OrderClient();
    private final OrderCreate chek = new OrderCreate();
    private final OrderList checkList = new OrderList();

    @Parameterized.Parameter(0)
    public List<String> scooterColors;
    private String firstName;
    private String lastName;
    private String address;
    private String metroStation;
    private String phone;
    private String rentTime;
    private String deliveryDate;
    private String comment;
    private List<String> scooterColor;

    @Parameterized.Parameters(name = "Цвет самоката: {0}")
    @DisplayName("Параметризованный тест создания заказа")
    public static Collection<List<String>> initParamsForTest() {
        return Arrays.asList(
                Arrays.asList(),
                Arrays.asList("BLACK"),
                Arrays.asList("GREY"),
                Arrays.asList("BLACK", "GREY")
        );
    }

    @Before
    @DisplayName("Подготовка тестовых данных")
    public void prepareTestData() {
        this.firstName = "testName";
        this.lastName = "testLastName";
        this.address = "Москва, Ленина ул., д. 445";
        this.metroStation = "Черкизовская";
        this.phone = "+79857777777";
        this.rentTime = "3";
        this.deliveryDate = "2024-08-04";
        this.comment = "Some comment";
        this.scooterColor = scooterColors;
    }

    @After
    @DisplayName("Удаление заказа по его трекномеру")
    public void cleanupTestData() {
        if (chek.getTrackNumber() != null) {
            client.deleteOrder(chek.getTrackNumber());
        }
    }

    @Test
    @DisplayName("Создание заказа")
    public void testPostRequest() {
        OrderData orderData = new OrderData(
                firstName,
                lastName,
                address,
                metroStation,
                phone,
                rentTime,
                deliveryDate,
                comment,
                scooterColor
        );


        ValidatableResponse response;
        response = client.createOrder(OrderData.from(orderData));
        chek.createdOrderSuccessfully(response);

        String trackNumber = chek.getTrackNumber();
    }

    @Test
    @DisplayName("Список заказов")
    public void testGetOrdersList() {
        ValidatableResponse response = client.getOrdersList();
        checkList.getOrdersList(response);
    }

    @Test
    @DisplayName("Список заказов не равен нулю")
    public void testCheckOrdersInResponse() {
        ValidatableResponse response = client.getOrdersList();
        checkList.checkOrdersInResponse(response);
    }
 }

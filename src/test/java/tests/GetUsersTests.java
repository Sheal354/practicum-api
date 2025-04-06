package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.Test;
import pojo.Entity;
import helpers.UserServices;

import java.util.List;

@Epic("Управление клиентами")
@Feature("Получение данных клиента")
public class GetUsersTests {

    private String userId;

    @BeforeMethod
    @Parameters("userTestID")
    public void setup(String id) {
        userId = id;
    }

    @Story("Получение списка данных всех клиентов")
    @Description("Проверка верификации всех клиентов.")
    @Test(description = "Get All Users Test")
    public void getAllUsersTest() {
        // Получаем всех клиентов
        List<Entity> users = UserServices.getAllUsers();

        // Проверяем результат
        Assert.assertNotNull(users);
        Assert.assertFalse(users.isEmpty());
        users.forEach(user -> Assert.assertTrue(user.isVerified()));
    }

    @Story("Получение данных о клиенте")
    @Description("Проверка корректности данных клиента")
    @Test(description = "Get User Test")
    public void getUser() {
        // Получаем конкретного клиента
        Entity user = UserServices.getUserById(userId, 200);

        // Проверяем результат
        Assert.assertNotNull(user);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(user.getId().toString(), userId);
        softAssert.assertEquals(user.getId(), user.getAddition().getId());
        softAssert.assertAll();
    }
}
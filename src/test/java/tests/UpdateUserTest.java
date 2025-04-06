package tests;

import helpers.UserServices;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pojo.Addition;
import pojo.Entity;

import java.util.Arrays;

@Epic("Управление клиентами")
@Feature("Обновление данных клиента")
public class UpdateUserTest {

    private Entity oldUser;
    private String userId;

    @BeforeMethod
    @Parameters("userTestID")
    public void setup(String id) {
        userId = id;
    }

    @Story("Обновление данных клиента по указанному id")
    @Description("Проверка успешного обновления данных клиента.")
    @Test(description = "Update User Test")
    public void updateUserTest() {
        // Создаем объект Addition
        Addition additionPojo = Addition.builder()
                .additional_info("Неизвестно")
                .additional_number(0)
                .build();

        // Создаем объект Entity
        Entity newUser = Entity.builder()
                .addition(additionPojo)
                .important_numbers(Arrays.asList(0, 0, 0))
                .title("Неизвестно")
                .verified(false)
                .build();

        // Сохраняем изначальную информацию о клиенте
        oldUser = UserServices.getUserById(userId, 200);

        // Обновляем и получаем данные
        UserServices.updateUserById(userId, newUser);
        Entity updatedUser = UserServices.getUserById(userId, 200);

        // Проверяем результат
        Assert.assertNotNull(updatedUser);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(newUser.getTitle(), updatedUser.getTitle());
        softAssert.assertEquals(newUser.getAddition().getAdditional_info(),
                updatedUser.getAddition().getAdditional_info());
        softAssert.assertAll();
    }

    @AfterMethod
    public void returnUser() {
        // Восстанавливаем старые данные
        UserServices.updateUserById(userId, oldUser);
    }
}

package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pojo.Addition;
import pojo.Entity;
import helpers.UserServices;

import java.util.Arrays;

@Epic("Управление клиентами")
@Feature("Создание данных о клиенте")
public class CreateUserTest {

    private String userId;

    @Story("Создание клиента")
    @Description("Проверка успешного создания клиента.")
    @Test(description = "Create User Test")
    public void createUserTest() {
        // Создаем объект Addition
        Addition additionPojo = Addition.builder()
                .additional_info("Ульяновск")
                .additional_number(73)
                .build();

        // Создаем объект Entity
        Entity entityPojo = Entity.builder()
                .addition(additionPojo)
                .important_numbers(Arrays.asList(20, 182, 65))
                .title("Алексей")
                .verified(true)
                .build();

        // Создаем пользователя
        userId = UserServices.createUser(entityPojo);

        // Получаем созданного пользователя
        Entity user = UserServices.getUserById(userId, 200);

        // Проверяем результат
        Assert.assertEquals(entityPojo.getTitle(), user.getTitle());
        Assert.assertEquals(entityPojo.getAddition().getAdditional_info(), user.getAddition().getAdditional_info());
        Assert.assertEquals(entityPojo.getAddition().getAdditional_number(), user.getAddition().getAdditional_number());
    }

    @AfterMethod
    public void deleteUser() {
        // Удаление созданного пользователя
        UserServices.deleteUserById(userId);
    }
}
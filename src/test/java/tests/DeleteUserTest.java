package tests;

import helpers.UserServices;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pojo.Addition;
import pojo.Entity;

import java.util.Arrays;

@Epic("Управление клиентами")
@Feature("Удаление данных о клиенте")
public class DeleteUserTest {

    private String userId;

    @Description("Создание клиента для удаления.")
    @BeforeMethod
    public void createUser() {
        // Создаем объект Addition
        Addition additionPojo = Addition.builder()
                .additional_info("Ульяновск")
                .additional_number(73)
                .build();

        // Создаем объект Entity
        Entity user = Entity.builder()
                .addition(additionPojo)
                .important_numbers(Arrays.asList(20, 182, 65))
                .title("Алексей")
                .verified(true)
                .build();

        // Создаем пользователя через сервис
        userId = UserServices.createUser(user);
    }

    @Story("Удаление данных  по указанному id")
    @Description("Проверка успешного удаления данных о клиенте.")
    @Test(description = "Delete User Test")
    public void deleteUserTest(){
        // Удаление
        UserServices.deleteUserById(userId);
        // Попытка получения удаленных данных
        UserServices.getUserById(userId, 500);
    }
}

package helpers;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.mapper.ObjectMapperType;
import models.Entity;

import java.util.List;

import static io.restassured.RestAssured.given;

/**
 * Класс методов для взаимодействия с API управления клиентами.
 * Используется для выполнения CRUD-операций (создание, чтение, обновление, удаление) над сущностями клиентов.
 */
public class UserServices {

    /**
     * @param entityPojo объект типа {@link Entity}, содержащий данные для создания клиента.
     * @return строковое значение, представляющее уникальный идентификатор созданного клиента (userId).
     * Статус-код ответа: 200 OK.
     */
    @Step("Создание нового клиента через отправку POST-запроса на сервер.")
    public static String createUser(Entity entityPojo) {
        return given()
                .spec(Specifications.initRequestSpecification())
                .body(entityPojo)
                .when()
                .post("/api/create")
                .then()
                .statusCode(200)
                .extract().asString();
    }

    /**
     * @param userId строковое значение, представляющее уникальный идентификатор клиента.
     *               Статус-код ответа: 204 No Content.
     */
    @Step("Удаление клиента по его уникальному идентификатору через отправку DELETE-запроса на сервер.")
    public static void deleteUserById(String userId) {
        given()
                .filter(new AllureRestAssured())
                .when()
                .delete("/api/delete/" + userId)
                .then()
                .statusCode(204);
    }

    /**
     * @return список объектов типа {@link Entity}, представляющих всех клиентов.
     * Статус-код ответа: 200 OK.
     */
    @Step("Получение списка всех клиентов, доступных на сервере через отправку GET-запроса.")
    public static List<Entity> getAllUsers() {
        return given()
                .spec(Specifications.initRequestSpecification())
                .when()
                .get("/api/getAll")
                .then()
                .statusCode(200)
                .extract().jsonPath().getList("entity", Entity.class);
    }

    /**
     * @param userId строковое значение, представляющее уникальный идентификатор клиента.
     * @param statusCode ожидаемый статус-код ответа от сервера.
     * @return объект типа {@link Entity}, представляющий данные конкретного клиента.
     */
    @Step("Получение данных конкретного клиента по его уникальному идентификатору через отправку GET-запроса на сервер.")
    public static Entity getUserById(String userId, int statusCode) {
        return given()
                .spec(Specifications.initRequestSpecification())
                .when()
                .get("/api/get/" + userId)
                .then()
                .statusCode(statusCode)
                .extract().as(Entity.class, ObjectMapperType.GSON);
    }

    /**
     * @param userId строковое значение, представляющее уникальный идентификатор клиента.
     * @param entityPojo объект типа {@link Entity}, содержащий новые данные для обновления клиента.
     *                   Статус-код ответа: 204 No Content.
     */
    @Step("Обновление данных существующего клиента по его уникальному идентификатору через отправку PATCH-запроса на сервер.")
    public static void updateUserById(String userId, Entity entityPojo) {
        given()
                .spec(Specifications.initRequestSpecification())
                .body(entityPojo)
                .when()
                .patch("/api/patch/" + userId)
                .then()
                .statusCode(204);
    }
}
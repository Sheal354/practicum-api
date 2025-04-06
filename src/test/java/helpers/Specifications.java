package helpers;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

/**
 * Класс спецификации для настройки HTTP-запросов.
 */
public class Specifications {

    /**
     * Инициализирует и возвращает объект спецификации запроса ({@link RequestSpecification}).
     * <p>
     * Настройки:
     * <p>
     * - Content-Type: application/json
     * <p>
     * - Базовый URI: http://localhost:8080
     *
     * @return объект типа {@link RequestSpecification}, содержащий базовые настройки для HTTP-запросов.
     */
    public static RequestSpecification initRequestSpecification() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri("http://localhost:8080")
                .build();
    }
}
# practicum
SDET practicum from Simbirsoft

Тестирование API  
5 тестов:
1. CreateUserTest - создание клиента
2. DeleteUserTest - удаление клиента
3. GetUsersTest - getUserTest - получение клинта по id
4. GetUsersTest - getUsersTest - получение списка всех клиентов
5. UpdateUserTest - обновление клиента по id

Классы Pojo:
1. Addition - дополнительная информация о пользователе
2. Entity - основная информация о пользователе

Классы-хелперы:
1. Specifications - спецификации для настройки HTTP-запросов
2. UserServices - методы для взаимодействия с API управления клиентами

Реализовано формирование отчетов Allure  
Реализован параллельный запуск тестов на уровне методов.  
Реализован запуск в системе CI/CD при помощи GitHub Actions.

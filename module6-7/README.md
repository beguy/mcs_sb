# Инструкция по запуску приложения
#### (ИЛИ) 1. Из jar архива
Качаем jar и запускаем командой:
```sh
java -jar module6-7-0.0.1-SNAPSHOT.jar
```
#### (ИЛИ) 2. Из исходных кодов
Клонируем приложение

```sh
git clone -b ntc --single-branch git@github.com:beguy/mcs_sb.git
```
Переходим в папку приложения:
```sh
cd mcs_sb
```
Запускаем приложение при помощи mvn:
```sh
mvn spring-boot:run
```
### Результат запуска
Идем по адресу http://localhost:8080 и видим запущенное приложение

# Инструкция по тестированию указанных доработок
### Задание 1
Добавить Spring Security, авторизацию, logout (web интерфейс и api для работы с пользователями добавлять не нужно).
Запросы на получение должны быть доступны не авторизованному пользователю, запросы на модификацию - только 
авторизованному, при этом при попытке не авторизованного запроса возвращать статус 401.
#### Тест
Пытаемся изменить любое поле. Получаем 401.

Заходим http://localhost:8080/login с учетными данными:

username: u

password: p

Редактируем что-нибудь, видим, что редактируется успешно.

Заходим http://localhost:8080/login, жмем Logout.

Пробуем редактировать еще что-нибудь. Получаем 401.

### Задание 2
При создании клиента указывать остаток средств на счёте клиента и периодически списывать со счёта процент за обслуживание,
при этом если остаток меньше порогового значения, средства списываться не должны (период, процент и пороговое значение
должны конфигурироваться). 
#### Тест
[AccountRepositoryTest.java](src/test/java/com/github/beguy/module6/account/AccountRepositoryTest.java)
[AccountServiceTest.java](src/test/java/com/github/beguy/module6/account/AccountServiceTest.java
)

Запускаем тесты при помощи mvn:
```
mvn test
```

### Задание 3
Логирование всех запросов, включая не успешные.
#### Тест
Идем на станицу приложения http://localhost:8080 и видим в log все запросы:
```
2019-07-25 12:05:27.770  INFO 3912 --- [tp1528769018-17] c.g.b.m.c.filter.RequestLoggingFilter    : Request(GET //localhost:8080/clients)@5f339f10
2019-07-25 12:05:27.956  INFO 3912 --- [tp1528769018-18] c.g.b.m.c.filter.RequestLoggingFilter    : Request(GET //localhost:8080/css/core.css)@5f339f10
2019-07-25 12:05:27.957  INFO 3912 --- [tp1528769018-20] c.g.b.m.c.filter.RequestLoggingFilter    : Request(GET //localhost:8080/js/helpers.js)@73a57407
2019-07-25 12:05:28.111  INFO 3912 --- [tp1528769018-19] c.g.b.m.c.filter.RequestLoggingFilter    : Request(GET //localhost:8080/css/icons/material-icons.css)@73a57407
2019-07-25 12:05:28.193  INFO 3912 --- [tp1528769018-20] c.g.b.m.c.filter.RequestLoggingFilter    : Request(GET //localhost:8080/css/icons/MaterialIcons-Regular.woff2)@73a57407
2019-07-25 12:05:28.338  INFO 3912 --- [tp1528769018-19] c.g.b.m.c.filter.RequestLoggingFilter    : Request(GET //localhost:8080/favicon.ico)@73a57407
```
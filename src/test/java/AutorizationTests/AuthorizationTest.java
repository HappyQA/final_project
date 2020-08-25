package AutorizationTests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.Test;
import BaseTests.BaseTest;

import static AutorizationPages.AuthorizationPage.*;
import static Common.UrlPage.*;

/**
 *   autor a.stupin
 */

@Epic("Тестирование модуля Аутентификации")
public class AuthorizationTest extends BaseTest {

    @Feature("Модуль Authentication")
    @Test
    @Description("Успешный вход пользователя")
    public void successLogin () {
        openAuthModule();
        sendDataInputLogin("admin");
        sendDataInputPassword("1");
        clickOnButtonSubmit();
        checkSuccessLogin("Добро пожаловать");
    }
    @Feature("Модуль Authentication")
    @Test
    @Description("НЕ успешный вход пользователя")
    public void unSuccessLogin () {
        openAuthModule();
        sendDataInputLogin("bruh");
        sendDataInputPassword("bruh");
        clickOnButtonSubmit();
        checkUserError("Неверный логин или пароль");
    }
    @Feature("Модуль Authentication")
    @Test
    @Description("Нажатие на кнопку Забыли пароль с успешным его сбросом")
    public void successResettingPassword () {
        openAuthModule();
        clickOnButtonForgotPassword();
        sendDataInputNameOrEmail("a.stupin@tetra-soft.ru");
        clickOnButtonSend();
        checkUserResetEmail("Мы отправили на адрес пользователя письмо с дальнейшими инструкциями.");
    }
    @Feature("Модуль Authentication")
    @Test
    @Description("Нажатие на кнопку Забыли пароль и ввели имя пользователя или пароль которого нет в БД")
    public void unSuccessfulResettingPassword () {
        openAuthModule();
        clickOnButtonForgotPassword();
        sendDataInputNameOrEmail("example@test.ru");
        clickOnButtonSend();
        checkUserError("Пользователь не найден.");
    }
    @Feature("Модуль Authentication")
    @Test
    @Description("Выполнение входа в учетную запись пользователя с последующим выходом")
    public void loginWithNextExitUser () {
        openAuthModule();
        sendDataInputLogin("admin");
        sendDataInputPassword("1");
        clickOnButtonSubmit();
        checkSuccessLogin("Добро пожаловать");
        clickOnButtonExit();
        checkAuthTitle("Rigspace Login");
    }
}
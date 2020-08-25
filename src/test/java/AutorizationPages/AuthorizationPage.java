package AutorizationPages;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

/**
 *   autor a.stupin
 */

public class AuthorizationPage {

    @Step("Проверка title модуля аутентификации")
    public static void checkAuthTitle (String title) {
        $x("//title[contains(text(), '" + title + "')]")
                .shouldHave(Condition.text("Rigspace Login"));
    }
    @Step("Ввод данных в поле Логин")
    public static void sendDataInputLogin (String login) {
        $x("//input[@name='username']")
                .shouldBe(Condition.visible)
                .sendKeys(login);
    }
    @Step("Ввод данных в поле Пароль")
    public static void sendDataInputPassword (String pass) {
        $x("//input[@name='password']")
                .shouldBe(Condition.visible)
                .sendKeys(pass);
    }
    @Step("Нажатие на кнопку Войти")
    public static void clickOnButtonSubmit () {
        $x("//span[contains(text(), 'Войти')]")
                .shouldBe(Condition.visible)
                .click();
    }
    @Step("Нажатие на кнопку Забыли пароль?")
    public static void clickOnButtonForgotPassword () {
        $x("//a[@href='/auth/user/resetPassword']")
                .shouldBe(Condition.visible)
                .click();
    }
    @Step("Ввод в инпут Имя пользователя или Email")
    public static void sendDataInputNameOrEmail (String email) {
        $x("//input[@name='loginOrEmail']")
                .shouldNotBe(Condition.visible)
                .sendKeys(email);
    }
    @Step("Нажатие на кнопку Отправить")
    public static void clickOnButtonSend () {
        $x("//span[contains(text(), 'Отправить')]")
                .shouldBe(Condition.visible)
                .click();
    }
    @Step("Отображение ошибки о том что пользователь не найден")
    public static void checkUserError (String text) {
        $x("//mat-error[contains(text(), '" + text + "')]")
                .shouldHave(Condition.text("Пользователь не найден."));
    }
    @Step("Отображение сообщения о том что инструкции по сбросу пароля отправлены")
    public static void checkUserResetEmail (String text) {
        $x("//mat-error[contains(text(), '" + text + "')]")
                .shouldHave(Condition.text("Мы отправили на адрес пользователя письмо с дальнейшими инструкциями."));
    }
    @Step("Отображение о том что вход выполнен успешно")
    public static void checkSuccessLogin (String text) {
        $x("//h2[contains(text(), '" + text + "')]")
                .shouldHave(Condition.text("Добро пожаловать"));
    }
    @Step("Нажатие на кнопку Выйти")
    public static void clickOnButtonExit () {
        $x("//span[contains(text(), 'Выйти')]")
                .click();
    }
}




package Common;

import io.qameta.allure.Feature;
import io.qameta.allure.Step;

import static AutorizationPages.AuthorizationPage.*;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.$;

@Feature("Выполнение логина")
public class LogIntoModule {

    @Step("Выполнение входа в УЗ пользователя")
    public static void loginIntoModules (String login, String pass) {
        sendDataInputLogin(login);
        sendDataInputPassword(pass);
        clickOnButtonSubmit();
    }
    @Step("Проверка title при открытии моудля")
    public static void checkTitlesInModules (String title) {
        $("title").
                shouldHave(attribute("text" , title));
    }
}

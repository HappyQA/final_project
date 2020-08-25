package Common;
import io.qameta.allure.Epic;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.open;

/**
 *   autor a.stupin
 */

@Epic("Открытие страниц модулей Rigspace")
public class UrlPage {

    @Step("Открытие страницы Аутентификации")
    public static void openAuthModule () {
        open("http://qa.rig.space/auth/");
    }
    @Step("Открытие страницы Админки")
    public static void openAdminModule () {
        open("http://qa.rig.space/admin/");
    }
    @Step("Открытие страницы Репортинга")
    public static void openReportingModule () {
        open("http://qa.rig.space/reporting/");
    }
    @Step("Открытие страницы Еквипмента")
    public static void openEquipmentModule () {
        open("http://qa.rig.space/equipment/");
    }
    @Step("Открытие страницы Аналитики")
    public static void openAnalyticsModule () {
        open("http://qa.rig.space/analytics/");
    }
    @Step("Открытие страницы Мониторинга")
    public static void openMonitoringModule () {
        open("http://qa.rig.space/monitoring/");
    }
}

package BaseTests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.Test;

import static Common.LogIntoModule.checkTitlesInModules;
import static Common.LogIntoModule.loginIntoModules;
import static Common.UrlPage.*;

import static com.codeborne.selenide.Selenide.refresh;

/**
 *   autor a.stupin
 */

@Epic("Проверка доступности модулей")
public class AvailableModulesTest extends BaseTest {

    @Feature("Модуль Admin")
    @Test
    @Description("Проверка доступности модуля Admin")
    public void availableAdmin () {
        openAdminModule();
        loginIntoModules("admin", "1");
        checkTitlesInModules("Rigspace Administrative Panel");
        refresh();
        checkTitlesInModules("Rigspace Administrative Panel");
    }
    @Feature("Модуль Reporting")
    //@Test
    @Description("Проверка доступности модуля Reporting")
    public void availableReporting () {
        openReportingModule();
        loginIntoModules("admin", "1");
        checkTitlesInModules("Rigspace Reporting");
        refresh();
        checkTitlesInModules("Rigspace Reporting");
    }
    @Feature("Модуль Equipment")
    @Test
    @Description("Проверка доступности модуля Equipment")
    public void AvailableEquipment () {
        openEquipmentModule();
        loginIntoModules("admin", "1");
        checkTitlesInModules("Rigspace Equipment");
        refresh();
        checkTitlesInModules("Rigspace Equipment");
    }
    @Feature("Модуль Analytics")
    @Test
    @Description("Проверка доступности модуля Analytics")
    public void AvailableAnalytics () {
        openAnalyticsModule();
        loginIntoModules("admin", "1");
        checkTitlesInModules("Rigspace Analytics");
        refresh();
        checkTitlesInModules("Rigspace Analytics");
    }
    @Feature("Модуль Monitoring")
    @Test
    @Description("Проверка доступности модуля Monitoring")
    public void AvailableMonitoring () {
        openMonitoringModule();
        loginIntoModules("admin", "1");
        checkTitlesInModules("Rigspace Monitoring");
        refresh();
        checkTitlesInModules("Rigspace Monitoring");
    }
}
package BaseTests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Objects;

/**
 *   autor a.stupin
 */

public class BaseTest {

    @Before
    public void setUp () {
        /**
         Запускаем тесты через Selenoid
         */
        Configuration.remote = "http://192.168.0.8:4444/wd/hub";
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC",  true);
        capabilities.setCapability( "enableVideo", false);
        Configuration.browserCapabilities = capabilities;
    }
        /**
        Запускаем тесты локально
         */
////        baseUrl = "http://qa.rig.space/auth";
//        browser = "chrome";
//        startMaximized = false;
//        timeout = 15000;
//        holdBrowserOpen = true;
////        open(baseUrl);
//    }

    @After
    public void tearsDown () {
          if (Objects.nonNull(WebDriverRunner.getWebDriver())) {
          WebDriverRunner.getWebDriver().quit();
        }
    }
}

package framework.driver;

import aqa.framework.logger.Logger;
import aqa.framework.utils.filemanager.ResourceManager;
import aqa.framework.utils.json.JsonUtils;
import com.codeborne.selenide.Configuration;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import framework.configurations.ProjectConfiguration;
import framework.configurations.ResourcePath;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

/**
 * Класс (фабрика), отвечающий за создание экземпляра веб-драйвера.
 */
public class SelenideConfiguration implements ISelenideConfiguration {

    private final Logger logger = Logger.getInstance();
    private static final Browser BROWSER = Browser.getValue(
            ProjectConfiguration.getInstance().getPropertyIfSystemEnvAbsent("browser.global.name"));
    public static final Long ELEMENT_TIMEOUT =
            ProjectConfiguration.getInstance().getTimeout("timeout.element.wait");
    public static final Long ELEMENT_POLLING_TIMEOUT =
            ProjectConfiguration.getInstance().getTimeout("timeout.element.polling");
    private static final Boolean IS_REMOTE = Boolean.valueOf(
            ProjectConfiguration.getInstance().getPropertyIfSystemEnvAbsent("browser.remote_wd.is_remote"));
    private static final String REMOTE_WD_URL = ProjectConfiguration.getInstance()
            .getPropertyIfSystemEnvAbsent("browser.remote_wd.url");
    private static final String PLATFORM = System.getProperty("os.name").toLowerCase();

    /**
     * Получение экземпляра веб-драйвера, готового к использованию.
     */
    @Override
    public WebDriver getDriver() {
        WebDriver driver;
        if (IS_REMOTE) {
            driver = getRemoteDriver();
        } else {
            driver = getLocalDriver();
        }
        setTimeouts(driver);
        driver = listenersRegistration(driver);

        return driver;
    }

    /**
     * Регистрация слушателей в драйвере
     */
    private WebDriver listenersRegistration(WebDriver driver) {
        logger.info("Добавление слушайтелей к прогону");
        return driver;
    }

    private WebDriver addListeners(WebDriver webdriver, List<WebDriverEventListener> listeners) {
        if (listeners.isEmpty()) {
            return webdriver;
        }

        EventFiringWebDriver wrapper = new EventFiringWebDriver(webdriver);
        for (WebDriverEventListener listener : listeners) {
            logger.info(String.format("Add listener to webdriver: %s", listener));
            wrapper.register(listener);
        }

        return wrapper;
    }

    private void setTimeouts(WebDriver driver) {
        driver.manage().timeouts().pageLoadTimeout(ProjectConfiguration.getInstance().getTimeout("timeout.page.wait"),
                TimeUnit.MILLISECONDS);
        driver.manage().timeouts().setScriptTimeout(ProjectConfiguration.getInstance().getTimeout("timeout.script.wait"),
                TimeUnit.MILLISECONDS);
        Configuration.timeout = ELEMENT_TIMEOUT;
        Configuration.pollingInterval = ELEMENT_POLLING_TIMEOUT;
    }

    /**
     * Создание экземпляра ChromeOptions, на основании файла driverOptions.json из ресурсов.
     *
     * @return ChromeOptions для инициализации драйвера.
     */
    private ChromeOptions getChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();

        String fileWithOptions = new ResourceManager(ResourcePath.PROJECT_DRIVER_OPTIONS.getPath()).getPath();
        JsonObject options = JsonUtils.readFromFileObject(fileWithOptions).getAsJsonObject(BROWSER.getBrowser());
        options = options.getAsJsonObject(getPlatformName());

        String[] args = JsonUtils.getStringArray(options, "args");
        chromeOptions.addArguments(args);

        JsonObject experimentalOptions = options.getAsJsonObject("experimentalOptions");
        String[] excludeSwitches = JsonUtils.getStringArray(experimentalOptions, "excludeSwitches");
        Map<String, Object> prefs = JsonUtils.mapToObject(experimentalOptions.getAsJsonObject("prefs"),
                new TypeToken<HashMap<String, Object>>() {
                }.getType());
        chromeOptions.setExperimentalOption("excludeSwitches", excludeSwitches);
        chromeOptions.setExperimentalOption("prefs", prefs);
        chromeOptions.setExperimentalOption("w3c", false);

        chromeOptions.setCapability(CapabilityType.LOGGING_PREFS, getLoggingPreferences());

        Map<String, Object> capabilities = JsonUtils.mapToObject(options.getAsJsonObject("capabilities"),
                new TypeToken<HashMap<String, Object>>() {
                }.getType());
        capabilities.forEach(chromeOptions::setCapability);
        return chromeOptions;
    }

    /**
     * Получение имени платформы, на которой выполняются тесты.
     *
     * @return имя платформы.
     */
    private String getPlatformName() {
        String platform;
        if (PLATFORM.contains("win")) {
            platform = "windows";
        } else if (PLATFORM.contains("linux")) {
            platform = "linux";
        } else {
            throw new IllegalArgumentException(String.format("Platform %s is not supported", PLATFORM));
        }
        return platform;
    }

    /**
     * Создание локального веб-драйвера.
     *
     * @return экземпляр локального драйвера.
     */
    private WebDriver getLocalDriver() {
        WebDriver driver;
        switch (BROWSER) {
            case CHROME:
                WebDriverManager.chromedriver().setup();

                ChromeOptions chromeOptions = getChromeOptions();

                driver = new ChromeDriver(chromeOptions);
                break;
            default:
                throw new IllegalArgumentException(String.format("Browser %s is not supported", BROWSER));
        }
        return driver;
    }

    /**
     * Создание удаленного веб-драйвера.
     *
     * @return экземпляр локального драйвера.
     */
    private WebDriver getRemoteDriver() {
        WebDriver driver;
        Configuration.remote = REMOTE_WD_URL;
        switch (BROWSER) {
            case CHROME:
                ChromeOptions chromeOptions = getChromeOptions();
                DesiredCapabilities caps = DesiredCapabilities.chrome();
                caps.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
                driver = createRemoteDriver(caps);
                break;
            default:
                throw new IllegalArgumentException(String.format("Browser %s is not supported", BROWSER));
        }
        return driver;
    }

    private LoggingPreferences getLoggingPreferences() {
        LoggingPreferences loggingPreferences = new LoggingPreferences();
        loggingPreferences.enable(LogType.PERFORMANCE, Level.ALL);

        return loggingPreferences;
    }


    private WebDriver createRemoteDriver(DesiredCapabilities caps) {
        try {
            RemoteWebDriver webDriver = new RemoteWebDriver(new URL(Configuration.remote), caps);
            webDriver.setFileDetector(new LocalFileDetector());
            return webDriver;
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Invalid 'remote' parameter: " + Configuration.remote, e);
        }
    }

}

package framework.driver;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;

/**
 * Класс-контейнер, содержащий экземпляры веб-драйвера, готовые к использованию.
 */
public class DriverContainer {

    private static Instance currentInstance;

    private final static ThreadLocal<HashMap<Instance, WebDriver>> drivers =
            new InheritableThreadLocal<HashMap<Instance, WebDriver>>() {
                @Override
                protected HashMap<Instance, WebDriver> initialValue() {
                    return new HashMap<>();
                }
            };

    /**
     * Инициализация основного экземпляра драйвера.
     */
    public static void setDrivers() {
        currentInstance = Instance.FIRST;
        createDriver(Instance.FIRST);
        switchToFirst();
    }

    /**
     * Создание драйвера и помещение его в контейнер.
     *
     * @param instanceKey ключ, относящийся к определенному экземпляру драйвера.
     */
    private static void createDriver(Instance instanceKey) {
        ISelenideConfiguration selenideConfiguration = new SelenideConfiguration();
        WebDriver webDriver = selenideConfiguration.getDriver();
        WebDriverRunner.setWebDriver(webDriver);
        drivers.get().put(instanceKey, webDriver);
    }

    /**
     * Получение драйвера и помещение его в контейнер.
     */
    public static WebDriver getDriver() {
        return drivers.get().get(currentInstance);
    }

    /**
     * Переключение на основной экземпляр драйвера.
     */
    public static void switchToFirst() {
        switchDriver(Instance.FIRST);
    }

    /**
     * Переключение на второй экземпляр драйвера.
     */
    public static void switchToSecond() {
        switchDriver(Instance.SECOND);
    }

    /**
     * Переключение на третий экземпляр драйвера.
     */
    public static void switchToThird() {
        switchDriver(Instance.THIRD);
    }

    /**
     * Переключение на третий экземпляр драйвера.
     */
    public static void switchToFourth() {
        switchDriver(Instance.FOURTH);
    }

    /**
     * Переключение на определенный экземпляр драйвера.
     * Если драйвер с заданным ключом отсутствует в контейнере, то происходит его создание.
     *
     * @param instanceKey ключ, относящийся к определенному экземпляру драйвера.
     */
    private static void switchDriver(Instance instanceKey) {
        if (drivers.get().get(instanceKey) == null) {
            createDriver(instanceKey);
        }
        currentInstance = instanceKey;
    }

    public static WebDriver getCurrentDriver() {
        return drivers.get().get(currentInstance);
    }

    /**
     * Уничтожение всех созданных экземпляров драйверов и очищение контейнера.
     */
    public static void quit() {
        for (Instance instanceKey : drivers.get().keySet()) {
            drivers.get().get(instanceKey).quit();
        }
        drivers.get().clear();
    }

    public static void restart() {
        quit();
        setDrivers();
    }

    /**
     * Ключи, идентифицирующие экземпляры драйверов.
     */
    public enum Instance {
        FIRST,
        SECOND,
        THIRD,
        FOURTH
    }
}

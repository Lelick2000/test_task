package framework.driver;

import org.openqa.selenium.WebDriver;

/**
 * Интерфейс, описывающий фабрику для создания экземпляра веб-драйвера.
 */
public interface ISelenideConfiguration {
    WebDriver getDriver();
}

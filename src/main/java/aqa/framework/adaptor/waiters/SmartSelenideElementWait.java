package aqa.framework.adaptor.waiters;

import aqa.framework.logger.Logger;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;

import java.time.Duration;

/**
 * Класс с умными ожиданиями.
 * <p>
 * Класс имеет место быть для остальных сложных ожиданий, например ожидание прогрузки всех элементов одного типа на странице.
 */
public class SmartSelenideElementWait {

    private SmartSelenideElementWait() {
        //ignore
    }

    /**
     * Функция ожидания, что элемент не перейдет в указанное состояние.
     *
     * @param selenideElement Элемент для ожидания.
     * @param condition       Состояние элеменита.
     * @param timeMillis      Время ожидания.
     * @param pollingMillis   Частота опроса ожидания.
     * @return True - элемент не перешел в указанное состояние. False - элемент перешел в указанное состояние.
     */
    public static boolean isElementNotInState(SelenideElement selenideElement, Condition condition, long timeMillis,
                                              long pollingMillis) {
        try {
            return Selenide.Wait()
                    .withTimeout(Duration.ofMillis(timeMillis))
                    .pollingEvery(Duration.ofMillis(pollingMillis))
                    .until(driver -> !selenideElement.is(condition));
        } catch (TimeoutException | NoSuchElementException | ElementNotFound e) {
            Logger.getInstance().info(String.format("Element %1$s is not %2$s; selenideElement.is(not(%2$s)) = %3$s",
                    selenideElement.toString(), condition.toString(), !selenideElement.is(condition)));
            return false;
        }
    }

    /**
     * Функция ожидания, что элемент не перейдет в указанное состояние.
     *
     * @param selenideElement Элемент для ожидания.
     * @param condition       Состояние элеменита.
     * @param timeMillis      Время ожидания.
     * @return True - элемент не перешел в указанное состояние. False - элемент перешел в указанное состояние.
     */
    public static boolean isElementNotInState(SelenideElement selenideElement, Condition condition, long timeMillis) {
        return isElementNotInState(selenideElement, condition, timeMillis, Configuration.pollingInterval);

    }

    /**
     * Функция ожидания, что элемент не перейдет в указанное состояние.
     *
     * @param selenideElement Элемент для ожидания.
     * @param condition       Состояние элеменита.
     * @return True - элемент не перешел в указанное состояние. False - элемент перешел в указанное состояние.
     */
    public static boolean isElementNotInState(SelenideElement selenideElement, Condition condition) {
        return isElementNotInState(selenideElement, condition, Configuration.timeout);
    }


    /**
     * Функция ожидания, пока элемент перейден в указанное состояние.
     *
     * @param selenideElement Элемент для ожидания.
     * @param condition       Состояние элемента.
     * @param timeMillis      Время ожидания.
     * @param pollingMillis   Частота опроса ожидания.
     * @return True - элемента перешел в указанное состояние. False - элемент не перешел в указанное состояние.
     */
    public static boolean isElementInState(SelenideElement selenideElement, Condition condition, long timeMillis,
                                           long pollingMillis) {
        try {
            return Selenide.Wait()
                    .withTimeout(Duration.ofMillis(timeMillis))
                    .pollingEvery(Duration.ofMillis(pollingMillis))
                    .until(driver -> selenideElement.is(condition));
        } catch (TimeoutException | NoSuchElementException | ElementNotFound e) {
            Logger.getInstance().info(String.format("Element %1$s is %2$s; selenideElement.is(%2$s) = %3$s",
                    selenideElement.toString(), condition.toString(), selenideElement.is(condition)));
            return false;
        }
    }

    /**
     * Функция ожидания, пока элемент перейден в указанное состояние.
     *
     * @param selenideElement Элемент для ожидания.
     * @param condition       Состояние элемента.
     * @param timeMillis      Время ожидания.
     * @return True - элемента перешел в указанное состояние. False - элемент не перешел в указанное состояние.
     */
    public static boolean isElementInState(SelenideElement selenideElement, Condition condition, long timeMillis) {
        return isElementInState(selenideElement, condition, timeMillis, Configuration.pollingInterval);
    }

    /**
     * Функция ожидания, пока элемент перейден в указанное состояние.
     *
     * @param selenideElement Элемент для ожидания.
     * @param condition       Состояние элемента.
     * @return True - элемента перешел в указанное состояние. False - элемент не перешел в указанное состояние.
     */
    public static boolean isElementInState(SelenideElement selenideElement, Condition condition) {
        return isElementInState(selenideElement, condition, Configuration.timeout);
    }
}


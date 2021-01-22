package framework.base;

import aqa.framework.adaptor.waiters.SmartSelenideElementWait;
import aqa.framework.annotations.PageInfo;
import aqa.framework.logger.Logger;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.NoSuchElementException;

import static aqa.framework.adaptor.element.SelenideElementWrapper.wrap;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;


public abstract class BaseForm {

    /**
     * Locator for specified form
     */
    protected SelenideElement mainElement;

    /**
     * Name of specified form
     */
    protected String name;

    protected final Logger logger = Logger.getInstance();

    private static final String PAGE_BODY = "//body";


    /**
     * Contructor
     */
    protected BaseForm() {
        PageInfo pageInfo = this.getClass().getAnnotation(PageInfo.class);
        name = pageInfo.pageName();
        mainElement = wrap($(getLocatorFromPageInfo(pageInfo)), String.format("Основной элемент страницы '%1$s'", name));
    }

    /**
     * Contructor with parameters
     */
    protected BaseForm(By mainElement, String name) {
        this.mainElement = $(mainElement);
        this.name = name;
    }

    /**
     * Get locator for form from pageInfo annotation
     *
     * @param pageInfo
     * @return Form locator
     */
    protected By getLocatorFromPageInfo(PageInfo pageInfo) {
        if (!"".equals(pageInfo.xpath())) {
            return By.xpath(pageInfo.xpath());
        }
        if (!"".equals(pageInfo.id())) {
            return By.id(pageInfo.id());
        }
        if (!"".equals(pageInfo.css())) {
            return By.cssSelector(pageInfo.css());
        }
        logger.fatal(String.format("loc.baseform.loc.is.absent", name), new NoSuchElementException());
        throw new NoSuchElementException();
    }

    public void scrollIntoView(boolean isAlignTop) {
        $x(PAGE_BODY).scrollIntoView(isAlignTop);
    }

    protected SelenideElement getMainElement() {
        return mainElement;
    }

    public void shouldBe(Condition condition) {
        getMainElement().shouldBe(condition);
    }

    public void waitUntil(Condition condition, long timeout) {
        getMainElement().waitUntil(condition, timeout);
    }

    public boolean isElementInState(Condition condition) {
        return SmartSelenideElementWait.isElementInState(getMainElement(), condition);
    }

    public boolean isElement(Condition condition) {
        return getMainElement().is(condition);
    }

    public boolean isElementInState(Condition condition, long timeout) {
        return SmartSelenideElementWait.isElementInState(getMainElement(), condition, timeout);
    }

    public boolean isElementNotInState(Condition condition) {
        return SmartSelenideElementWait.isElementNotInState(getMainElement(), condition);
    }

    public boolean isElementNotInState(Condition condition, long timeout) {
        return SmartSelenideElementWait.isElementNotInState(getMainElement(), condition, timeout);
    }
}

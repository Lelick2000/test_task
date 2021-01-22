package aqa.framework.adaptor.element;

import com.codeborne.selenide.SelenideElement;

import java.lang.reflect.Proxy;

public class SelenideElementWrapper {
    private SelenideElementWrapper() {
    }

    public static SelenideElement wrap(SelenideElement element, String name) {
        return wrapSelenideElement(element, name);
    }

    private static SelenideElement wrapSelenideElement(SelenideElement selenideElement, String name) {
        return (SelenideElement) Proxy.newProxyInstance(selenideElement.getClass().getClassLoader(),
                new Class<?>[]{SelenideElement.class},
                new SelenideElementProxyDecorator(selenideElement, name));
    }


}

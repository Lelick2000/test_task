package aqa.framework.adaptor.element;

import aqa.framework.logger.Logger;
import com.codeborne.selenide.SelenideElement;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.stream.Collectors;

class SelenideElementProxyDecorator implements InvocationHandler {
    private final Logger logger = Logger.getInstance();

    private final String name;
    private final SelenideElement selenideElement;

    protected SelenideElementProxyDecorator(SelenideElement selenideElement, String name) {
        this.selenideElement = selenideElement;
        this.name = name;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        logMessage(method, args);
        return Proxy.getInvocationHandler(selenideElement).invoke(selenideElement, method, args);
    }

    private void logMessage(Method method, Object[] args) {
        String arguments = ArrayUtils.isEmpty(args)
                ? StringUtils.EMPTY
                : String.format(" :: Аргументы метода '%1$s'",
                Arrays.stream(args).map(Object::toString).collect(Collectors.joining(", ")));
        logger.info(String.format("Имя элемента '%1$s' :: Действие '%2$s'%3$s", name, method.getName(), arguments));
    }
}

package project.factories;

import aqa.framework.logger.Logger;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class ObjectFactory<E> {
    protected final Map<Class<? extends E>, E> instances = new ConcurrentHashMap<>();

    public synchronized <T extends E> T getInstance(Class<T> tClass) {
        return tClass.cast(register(tClass));

    }

    protected <T extends E> T register(Class<T> tClass) {
        try {
            return tClass.getDeclaredConstructor().newInstance();
        } catch (InvocationTargetException e) {
            Logger.getInstance().error("InvocationTargetException " + e.getMessage());
            return null;
        } catch (Exception invalidClassName) {
            throw new IllegalStateException(invalidClassName);
        }
    }
}

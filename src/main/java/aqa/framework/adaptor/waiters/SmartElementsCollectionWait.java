package aqa.framework.adaptor.waiters;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.ex.UIAssertionError;

/**
 * Класс с умными ожиданиями.
 * <p>
 * Класс имеет место быть для остальных сложных ожиданий, например ожидание прогрузки всех элементов одного типа на странице.
 */
public class SmartElementsCollectionWait {
    private SmartElementsCollectionWait() {
        //ignore
    }

    /**
     * Функция ожидания, что элементы не перейдут в указанное состояние.
     *
     * @param elementsCollection   Элементы для ожидания.
     * @param collectionConditions Состояние элементов.
     * @return True - элементы не перешли в указанное состояние. False - элемент перешел в указанное состояние.
     */
    public static boolean isElementsNotHave(ElementsCollection elementsCollection,
                                            CollectionCondition collectionConditions, long timeMillis) {
        try {
            elementsCollection.shouldHave(collectionConditions, timeMillis);
            return false;
        } catch (UIAssertionError e) {
            return true;
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
    public static boolean isElementsNotHave(ElementsCollection elementsCollection,
                                            CollectionCondition collectionCondition) {
        return isElementsNotHave(elementsCollection, collectionCondition, Configuration.timeout);
    }

    /**
     * Функция ожидания, пока элемент перейден в указанное состояние.
     *
     * @param selenideElement Элемент для ожидания.
     * @param condition       Состояние элемента.
     * @return True - элемента перешел в указанное состояние. False - элемент не перешел в указанное состояние.
     */
    public static boolean isElementsHave(ElementsCollection elementsCollection,
                                         CollectionCondition collectionCondition, long timeMillis) {
        try {
            elementsCollection.shouldHave(collectionCondition, timeMillis);
            return true;
        } catch (UIAssertionError e) {
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
    public static boolean isElementsHave(ElementsCollection elementsCollection,
                                         CollectionCondition collectionCondition) {
        return isElementsHave(elementsCollection, collectionCondition, Configuration.timeout);
    }
}


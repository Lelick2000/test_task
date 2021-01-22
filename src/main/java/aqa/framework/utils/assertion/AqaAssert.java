package aqa.framework.utils.assertion;

import aqa.framework.logger.Logger;
import org.testng.asserts.Assertion;

public class AqaAssert {
    private static final Assertion assertion = new Assertion();

    private AqaAssert() {
        throw new IllegalStateException("Utility class");
    }

    private static void logCheckMessage(String message) {
        Logger.getInstance().info("loc.assert.check" + message);
    }

    protected static void logPassMessage() {
        Logger.getInstance().info("loc.assert.pass");
    }

    public static void isTrue(Boolean condition, String message) {
        logCheckMessage(message);
        assertion.assertTrue(condition, message);
        logPassMessage();
    }

    public static void isFalse(Boolean condition, String message) {
        logCheckMessage(message);
        assertion.assertFalse(condition, message);
        logPassMessage();
    }

    private static <T> void areEqualTemplate(T actual, T expected, String message) {
        logCheckMessage(message);
        assertion.assertEquals(actual, expected, message);
        logPassMessage();
    }

    public static void areEqual(String actual, String expected, String message) {
        areEqualTemplate(actual, expected, message);
    }

    public static void areEqual(Boolean actual, Boolean expected, String message) {
        areEqualTemplate(actual, expected, message);
    }

    public static void areEqual(int actual, int expected, String message) {
        areEqualTemplate(actual, expected, message);
    }

    public static void areEqual(long actual, long expected, String message) {
        areEqualTemplate(actual, expected, message);
    }

    public static void areEqual(Object actual, Object expected, String message) {
        areEqualTemplate(actual, expected, message);
    }

    public static void areEqual(double actual, double expected, double delta, String message) {
        logCheckMessage(message);
        assertion.assertEquals(actual, expected, delta, message);
        logPassMessage();
    }

    public static void areEqual(float actual, float expected, float delta, String message) {
        logCheckMessage(message);
        assertion.assertEquals(actual, expected, delta, message);
        logPassMessage();
    }

    public static void areEqual(byte actual, byte expected, String message) {
        areEqualTemplate(actual, expected, message);
    }

    public static void areEqual(char actual, char expected, String message) {
        areEqualTemplate(actual, expected, message);
    }

    public static void areEqual(short actual, short expected, String message) {
        areEqualTemplate(actual, expected, message);
    }

    public static void isNull(Object object, String message) {
        logCheckMessage(message);
        assertion.assertNull(object, message);
        logPassMessage();
    }

    public static void areSame(Object actual, Object expected, String message) {
        logCheckMessage(message);
        assertion.assertSame(actual, expected, message);
        logPassMessage();
    }

    public static void isNotNull(Object object, String message) {
        logCheckMessage(message);
        assertion.assertNotNull(object, message);
        logPassMessage();
    }

    public static void areNotSame(Object actual, Object expected, String message) {
        logCheckMessage(message);
        assertion.assertNotSame(actual, expected, message);
        logPassMessage();
    }

    private static <T> void areNotEqualTemplate(T actual, T expected, String message) {
        logCheckMessage(message);
        assertion.assertNotEquals(actual, expected, message);
        logPassMessage();
    }

    public static void areNotEqual(Object actual, Object expected, String message) {
        areNotEqualTemplate(actual, expected, message);
    }

    public static void areNotEqual(String actual, String expected, String message) {
        areNotEqualTemplate(actual, expected, message);
    }

    public static void areNotEqual(long actual, long expected, String message) {
        areNotEqualTemplate(actual, expected, message);
    }

    public static void areNotEqual(Boolean actual, Boolean expected, String message) {
        areNotEqualTemplate(actual, expected, message);
    }

    public static void areNotEqual(byte actual, byte expected, String message) {
        areNotEqualTemplate(actual, expected, message);
    }

    public static void areNotEqual(char actual, char expected, String message) {
        areNotEqualTemplate(actual, expected, message);
    }

    public static void areNotEqual(short actual, short expected, String message) {
        areNotEqualTemplate(actual, expected, message);
    }

    public static void areNotEqual(int actual, int expected, String message) {
        areNotEqualTemplate(actual, expected, message);
    }

    public static void areNotEqual(double actual, double expected, double delta, String message) {
        logCheckMessage(message);
        assertion.assertNotEquals(actual, expected, delta, message);
        logPassMessage();
    }

    public static void areNotEqual(float actual, float expected, float delta, String message) {
        logCheckMessage(message);
        assertion.assertNotEquals(actual, expected, delta, message);
        logPassMessage();
    }
}

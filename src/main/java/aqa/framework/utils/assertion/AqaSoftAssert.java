package aqa.framework.utils.assertion;

import aqa.framework.logger.Logger;
import org.testng.asserts.SoftAssert;

public class AqaSoftAssert extends SoftAssert {
    private final Logger logger = Logger.getInstance();

    @Override
    public void assertAll() {
        logger.info("loc.assert.assert_all");
        try {
            super.assertAll();
        } catch (AssertionError e) {
            throw e;
        }
        AqaAssert.logPassMessage();
    }

    @Override
    public void assertAll(String message) {
        logger.info(message);
        super.assertAll(message);
    }
}

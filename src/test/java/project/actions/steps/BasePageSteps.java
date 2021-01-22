package project.actions.steps;

import aqa.framework.logger.Logger;
import aqa.framework.utils.assertion.AqaSoftAssert;
import project.factories.PageFactory;

public abstract class BasePageSteps {
    protected static final PageFactory pageFactory = PageFactory.getFactory();
    protected AqaSoftAssert aqaSoftAssert = new AqaSoftAssert();
    protected final Logger logger = Logger.getInstance();
}

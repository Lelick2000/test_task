package framework.base;

import aqa.framework.logger.Logger;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.testng.ScreenShooter;
import framework.driver.DriverContainer;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import project.factories.StepsFactory;
import project.utils.autocloser.PopupsClosingScheduler;

import java.net.MalformedURLException;

import static project.constants.Urls.BASE_URL;


@Listeners({ScreenShooter.class})
public abstract class BaseTest {
    protected final Logger logger = Logger.getInstance();
    protected static final StepsFactory stepsFactory = StepsFactory.getFactory();

    // Утилита по закрытию попапов на сайте
    protected PopupsClosingScheduler popupsClosingScheduler = new PopupsClosingScheduler();

    /**
     * To override.
     */
    protected abstract void runTest() throws MalformedURLException, InterruptedException;

    /**
     * Before Class method
     * Make a browser window
     */
    @BeforeMethod
    public void before(ITestContext testContext) {
        logger.logPreconditions();
        openBrowserAndExecuteBasicActions();
    }

    protected void openBrowserAndExecuteBasicActions() {
        DriverContainer.setDrivers();
        Selenide.open(BASE_URL.getValue());
        popupsClosingScheduler.startPopupsCloserSchedule();
    }

    /**
     * Close browser
     */
    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        popupsClosingScheduler.shutdownPopupsCloserSchedule();
        DriverContainer.quit();
    }

}


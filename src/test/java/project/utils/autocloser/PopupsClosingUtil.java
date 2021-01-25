package project.utils.autocloser;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.WebDriver;
import project.factories.PageFactory;
import project.page.objects.modals.BuyDirectOnMarketModal;
import project.page.objects.modals.SearchEverywhereModal;

public class PopupsClosingUtil implements Runnable {
    private static final PageFactory pageFactory = PageFactory.getFactory();

    private final SearchEverywhereModal searchEverywhereModal = pageFactory.getInstance(SearchEverywhereModal.class);
    private final BuyDirectOnMarketModal buyDirectOnMarketModal = pageFactory.getInstance(BuyDirectOnMarketModal.class);

    private final WebDriver webDriver;

    public PopupsClosingUtil(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Override
    public void run() {
        try {
            WebDriverRunner.setWebDriver(webDriver);

            if (WebDriverRunner.driver().hasWebDriverStarted() && WebDriverRunner.getWebDriver() != null
                    && !WebDriverRunner.getWebDriver().toString().contains("null")) {
                synchronized (webDriver) {
                    startModalClosers();
                }
            }
        } catch (Throwable ex) {
            System.out.println("In the popups closing util appeared exception");
            ex.printStackTrace();
        }
    }

    public void startModalClosers() {
        closeModalsAndPromotionsIfVisible();
    }

    public void closeModalsAndPromotionsIfVisible() {
        if (searchEverywhereModal.isAcceptBannerVisible()) {
            searchEverywhereModal.acceptBanner();
        }
        if (buyDirectOnMarketModal.isAcceptBannerVisible()) {
            buyDirectOnMarketModal.acceptBanner();
        }
    }
}

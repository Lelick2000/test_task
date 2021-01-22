package project.tests;

import com.codeborne.selenide.Selenide;
import framework.base.BaseTest;
import jdk.jfr.Description;
import org.testng.annotations.Test;
import project.actions.steps.MarketSteps;

public class YandexPageTest extends BaseTest {
    private final MarketSteps marketSteps = stepsFactory.getInstance(MarketSteps.class);

    @Override
    @Test
    @Description("Тестовое задание Яндекс.Маркет")
    public void runTest() {
        logger.step(1, "Launch the default browser and maximize the window");
        logger.step(2, "Open yandex.ru");
        logger.step(3, "Go to Yandex.Market");
        marketSteps.openMarketPage();
        Selenide.switchTo().window(1);

        logger.step(4, "Go to “Электроника”");
        marketSteps.openMarketMenu();

        logger.step(5, "Go to “Смартфоны и аксессуары”");
        marketSteps.openSmartphonesLeftMenu();

        logger.step(6, "Go to “Мобильные телефоны”");
        marketSteps.openMobilePhonesLeftMenu();

        logger.step(7, "Set search parameters");
        marketSteps.setSearchFilters();

        logger.step(8, "Sort search results by the best price");
        marketSteps.clickPriceSort();

        logger.step(9, "Remember the 1st item in the list");
        String phoneName = marketSteps.getCheapestPhone();

        logger.step(10, "Change sorting to “Sort by popularity”");
        marketSteps.clickPopularitySort();

        logger.step(11, "Find the remembered item by name");
        marketSteps.findCheapestPhonePrice(phoneName);
    }
}

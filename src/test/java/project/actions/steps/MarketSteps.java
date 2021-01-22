package project.actions.steps;

import aqa.framework.logger.Logger;
import project.page.objects.menu.YandexMainServiceMenu;
import project.page.objects.menu.YandexMarketMainMenu;
import project.page.objects.pages.YandexCatalogPage;
import project.page.objects.pages.YandexPhoneResultPage;


public class MarketSteps extends BasePageSteps {
    private final YandexMainServiceMenu yandexMainService = pageFactory.getInstance(YandexMainServiceMenu.class);
    private final YandexMarketMainMenu yandexMarketMain = pageFactory.getInstance(YandexMarketMainMenu.class);
    private final YandexCatalogPage yandexCatalogPage = pageFactory.getInstance(YandexCatalogPage.class);
    private final YandexPhoneResultPage yandexPhoneResultPage = pageFactory.getInstance(YandexPhoneResultPage.class);

    private final Logger logger = Logger.getInstance();

    public void openMarketPage() {
        logger.step("Нажать на ссылку меню над поиском маркета");
        yandexMainService.goToMarketPage();
    }

    public void openMarketMenu() {
        logger.step("Нажать на верхнее меню в маркете");
        yandexMarketMain.clickTopMenu();
    }

    public void openSmartphonesLeftMenu() {
        logger.step("Нажать на ссылку меню смартфоны");
        yandexCatalogPage.clickSmartphoneMenu();
    }

    public void openMobilePhonesLeftMenu() {
        logger.step("Нажать на ссылку меню мобильные телефоны");
        yandexCatalogPage.clickMobilePhonesMenu();
    }

    public void clickPriceSort() {
        logger.step("Нажать на сортировку по цене");
        yandexPhoneResultPage.clickCheapestSorting();
    }

    public void clickPopularitySort() {
        logger.step("Нажать на сортировку по популярности");
        yandexPhoneResultPage.clickPopularSorting();
    }

    public String getCheapestPhone() {
        logger.step("Запомнить самый дешевый телефон");
        logger.info("Телефон=  " + yandexPhoneResultPage.getCheapestPhone().text());
        return yandexPhoneResultPage.getCheapestPhone().text();
    }

    public void findCheapestPhonePrice(String phoneName) {
        logger.step(String.format("Найти цену телефона %s", phoneName));
        yandexPhoneResultPage.getPhonePrice(phoneName);
    }

    public void setSearchFilters() {
        yandexPhoneResultPage.setFilters();
    }

}

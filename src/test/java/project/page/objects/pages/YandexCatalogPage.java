package project.page.objects.pages;

import aqa.framework.annotations.PageInfo;
import com.codeborne.selenide.SelenideElement;
import framework.base.BaseForm;

import static aqa.framework.adaptor.element.SelenideElementWrapper.wrap;
import static com.codeborne.selenide.Selenide.$x;

@PageInfo(xpath = "//div[@data-apiary-widget-name='@MarketNode/CatalogPage']", pageName = "Yandex market catalog page")
public class YandexCatalogPage extends BaseForm {
    private final SelenideElement smartPhonesLink = wrap($x("//a[contains(@href, 'catalog--smartfony-i-aksessuary')]"), "Левое навигационное меню Смартфоны");
    private final SelenideElement mobilePhonesLink = wrap($x("//a[contains(@href, 'catalog--mobilnye-telefony')]"), "Левое навигационное меню Мобильные телефоны");

    public void clickSmartphoneMenu() {
        smartPhonesLink.click();
    }

    public void clickMobilePhonesMenu() {
        mobilePhonesLink.click();
    }
}

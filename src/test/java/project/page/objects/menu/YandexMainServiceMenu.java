package project.page.objects.menu;

import aqa.framework.annotations.PageInfo;
import com.codeborne.selenide.SelenideElement;
import framework.base.BaseForm;

import static aqa.framework.adaptor.element.SelenideElementWrapper.wrap;
import static com.codeborne.selenide.Selenide.$x;

@PageInfo(css = ".services-new__list", pageName = "Yandex services menu")
public class YandexMainServiceMenu extends BaseForm {
    private final SelenideElement lnkMarket = wrap($x("//a[@data-id='market']"), "Яндекс маркет ссылка");

    public void goToMarketPage() {
        lnkMarket.scrollIntoView(false).click();
    }
}

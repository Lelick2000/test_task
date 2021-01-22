package project.page.objects.menu;

import aqa.framework.annotations.PageInfo;
import com.codeborne.selenide.SelenideElement;
import framework.base.BaseForm;

import static aqa.framework.adaptor.element.SelenideElementWrapper.wrap;
import static com.codeborne.selenide.Selenide.$x;

@PageInfo(xpath = "//div[@role='tablist']", pageName = "Yandex market main menu")
public class YandexMarketMainMenu extends BaseForm {
    private final SelenideElement menuElect = wrap($x("//a[contains(@href, 'catalog--elektronika')]"), "Яндекс маркет кнопка меню Электроника");

    public void clickTopMenu() {
        menuElect.click();
    }

}

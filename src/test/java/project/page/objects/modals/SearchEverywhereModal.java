package project.page.objects.modals;

import aqa.framework.adaptor.waiters.SmartSelenideElementWait;
import aqa.framework.annotations.PageInfo;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import framework.base.BaseForm;

import static aqa.framework.adaptor.element.SelenideElementWrapper.wrap;

@PageInfo(xpath = "//div[@data-position='bottom']", pageName = "Search everywhere banner")
public class SearchEverywhereModal extends BaseForm {
    private SelenideElement btnAccept = wrap(getMainElement().$x(".//span[contains(text(),'Понятно')]"), "Подтверждение поиски по всем магазинам");

    public void acceptBanner() {
        btnAccept.click();
    }

    public boolean isAcceptBannerVisible() {
        return btnAccept.is(Condition.visible);
    }

    public boolean isCookiesBannerDisplayed() {
        return SmartSelenideElementWait.isElementInState(btnAccept, Condition.visible);
    }
}

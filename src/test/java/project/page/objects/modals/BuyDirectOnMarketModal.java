package project.page.objects.modals;

import aqa.framework.adaptor.waiters.SmartSelenideElementWait;
import aqa.framework.annotations.PageInfo;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import framework.base.BaseForm;

import static aqa.framework.adaptor.element.SelenideElementWrapper.wrap;

@PageInfo(xpath = "//div[@data-position='left']", pageName = "Buy directly on market modal")
public class BuyDirectOnMarketModal extends BaseForm {
    private final SelenideElement btnAccept = wrap(getMainElement().$x(".//span[contains(text(),'Понятно')]"), "Подтверждение покупки сразу на маркете");

    public void acceptBanner() {
        btnAccept.click();
    }

    public boolean isAcceptBannerVisible() {
        return btnAccept.is(Condition.visible);
    }

    public boolean isBuyBannerDisplayed() {
        return SmartSelenideElementWait.isElementInState(btnAccept, Condition.visible);
    }
}

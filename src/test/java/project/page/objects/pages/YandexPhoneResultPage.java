package project.page.objects.pages;

import aqa.framework.annotations.PageInfo;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import framework.base.BaseForm;
import org.openqa.selenium.By;

import static aqa.framework.adaptor.element.SelenideElementWrapper.wrap;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

@PageInfo(xpath = "//div[@data-apiary-widget-name='@MarketNode/SearchHeadline']/parent::div", pageName = "Yandex mobile phones page")
public class YandexPhoneResultPage extends BaseForm {
    private final String DISPLAY_SIZE = "3";
    private final String PRICE_UPTO = "20000";

    private final String PHONE_SELECTOR = ".//a[contains(@href, 'product--smartfon')]/span";
    private final String SORTING_PRICE = ".//button[@data-autotest-id='dprice']";
    private final String SORTING_POPULAR = ".//button[@data-autotest-id='dpop']";
    private final String PRODUCT_ARTICLE = ".//article[@data-autotest-id='product-snippet']";
    private final String PRODUCT_ARTICLE_PRICE = ".//div[@data-zone-name='price']";

    private final SelenideElement sortingLine = wrap($x("//div[@data-apiary-widget-name='@MarketNode/SortPanel']"), "Меню выбора сортировки");
    private final SelenideElement priceTo = getMainElement().find(By.id("glpriceto"));
    private final SelenideElement checkApple = wrap($x("//li//span[contains(text(),'Apple')]"), "Фильтр производитель Apple");
    private final SelenideElement checkHonor = wrap($x("//li//span[contains(text(),'HONOR')]"), "Фильтр производитель HONOR");
    private final SelenideElement checkHuawei = wrap($x("//li//span[contains(text(),'HUAWEI')]"), "Фильтр производитель HUAWEI");
    private final SelenideElement diagonal = wrap($x(".//input[@name='Диагональ экрана (точно) от']"), "Устанавливаем диоганаль 8");
    private final SelenideElement btnNextSearch = wrap($x(".//a[@aria-label='Следующая страница']"), "Следующая страница поиска");
    private final SelenideElement waitBtn = $x("//li[contains(text(),'оперативная память:')]");

    public void clickCheapestSorting() {
        sortingLine.$x(SORTING_PRICE).click();
        waitBtn.click();
    }

    public void clickPopularSorting() {
        sortingLine.$x(SORTING_POPULAR).click();
        waitBtn.click();
    }

    public SelenideElement getCheapestPhone() {
        return $x(PHONE_SELECTOR);
    }

    public void getPhonePrice(String phoneName) {
        String price = "";
        if (btnNextSearch.is(visible)) {
            while (price.equals("")) {
                price = getPhoneAndGetPrice(phoneName);
                if (btnNextSearch.isDisplayed()) {
                    btnNextSearch.click();
                    waitBtn.click();
                }
            }
            logger.info("Phone price= " + price);
        } else {
            waitBtn.click();
            logger.info("Phone price= " + getPhoneAndGetPrice(phoneName));
        }
    }

    private String getPhoneAndGetPrice(String phoneName) {
        String price = "";
        ElementsCollection results = $$x(PRODUCT_ARTICLE);
        for (SelenideElement el : results) {
            if (el.$x(PHONE_SELECTOR).text().equals(phoneName)) {
                price = el.$x(PRODUCT_ARTICLE_PRICE).text();
            }
        }

        return price;
    }

    public void setFilters() {
        priceTo.shouldBe(visible).sendKeys(PRICE_UPTO);
        diagonal.sendKeys(DISPLAY_SIZE);
        checkApple.scrollIntoView(true).click();
        checkHonor.click();
        checkHuawei.click();
        waitBtn.click();
    }
}

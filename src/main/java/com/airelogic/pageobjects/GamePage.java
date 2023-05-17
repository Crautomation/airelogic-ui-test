package com.airelogic.pageobjects;

import com.github.crautomation.core.ui.test.BasePageObject;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GamePage extends BasePageObject<GamePage> {
    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(1));

    @FindBy(css = "button#click")
    private WebElement clickCookieButton;

    @FindBy(id = "cookies")
    private WebElement cookieAmount;

    @FindBy(id = "money")
    private WebElement moneyAmount;

    @FindBy(id = "factories-to-buy")
    private WebElement factoryField;

    @FindBy(id = "buy-factories")
    private WebElement buyFactoriesButton;

    @FindBy(linkText = "Cookie Clicker!")
    private WebElement mainPageLink;

    @FindBy(id = "cookies-to-sell")
    private WebElement cookiesToSellField;

    @FindBy(id = "sell-cookies")
    private WebElement sellCookiesButton;

    public GamePage() {
        initElements();
    }

    @Step("Clicking the 'Click Cookie!' button")
    public GamePage clickCookieButton() {
        int initialAmount = getCookieAmount();

        clickCookieButton.click();

        // Waits until it confirms the value in the element has changed to reduce non-determinism
        wait.until((ExpectedCondition<Boolean>) driver -> getCookieAmount() != initialAmount);

        return this;
    }

    @Step("Returning current page title.")
    public String getPageTitle() {
        return driver.getTitle();
    }

    @Step("Returning the current amount of cookies")
    public Integer getCookieAmount() {
        initElements();

        return Integer.parseInt(cookieAmount.getText());
    }

    @Step("Clicking the button to return to the main page")
    public MainPage clickMainPageButton() {
        mainPageLink.click();

        return new MainPage();
    }

    @Step("Returning the name in this current game")
    public String getCurrentGameName() {
        WebElement lineElement = driver.findElement(By.xpath("//p[starts-with(text(), 'Hello')]"));

        // Basic way to remove the "Hello " from the line to just return the name.
        return lineElement.getText().substring(6);
    }

    @Step("Selling the set amount of cookies {cookies}")
    public GamePage sellCookies(String cookies) {

        double initialAmount = getMoneyAmount();

        cookiesToSellField.sendKeys(cookies);
        sellCookiesButton.click();
        cookiesToSellField.clear();

        wait.until((ExpectedCondition<Boolean>) driver -> getMoneyAmount() != initialAmount);

        return this;
    }

    @Step("Returning the current amount of money")
    public Double getMoneyAmount() {
        initElements();

        return Double.valueOf(moneyAmount.getText());
    }

    @Step("Buying {number} of factories")
    public GamePage buyFactories(String factories) {
        double initialAmount = getMoneyAmount();

        factoryField.sendKeys(factories);
        buyFactoriesButton.click();
        factoryField.clear();

        wait.until((ExpectedCondition<Boolean>) driver -> getMoneyAmount() != initialAmount);

        return this;
    }
}

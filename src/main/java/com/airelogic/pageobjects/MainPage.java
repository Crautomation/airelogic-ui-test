package com.airelogic.pageobjects;

import com.airelogic.util.CurrentTest;
import com.github.crautomation.core.ui.test.BasePageObject;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.NoSuchElementException;
import java.util.Random;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class MainPage extends BasePageObject<MainPage> {
    private final String URL = "https://craig-brooks-2023-05-12.cookieclickertechtest.airelogic.com/";
    private String name;

    @FindBy(css = "form button")
    private WebElement startButton;

    @FindBy(name = "name")
    private WebElement nameBox;

    public MainPage() {
        initElements();
        openMainPage();
    }

    @Step("Opening the main page")
    private void openMainPage() {
        driver.get(URL);
        assertThat("Expected page title is not present", driver.getTitle(), equalTo("Cookie Clicker!"));
    }

    @Step("Entering the name {name}")
    public MainPage enterName(String name) {
        nameBox.sendKeys(name);
        return this;
    }

    @Step("Entering the name")
    public MainPage enterName() {
        generateName();
        enterName(name);

        return this;
    }

    @Step("Clicking the start button")
    public GamePage clickStartButton() {
        startButton.click();

        return new GamePage();
    }

    @Step("Returning if the user is present in the score table")
    public Boolean isPlayerPresentInTable(String name) {
        try {
            // Try to find the element
            WebElement element = driver.findElement(By.xpath(String.format("//tr[td/a[contains(text(),'%s')]]", name)));
            return true; // Element found, return true
        } catch (NoSuchElementException e) {
            return false; // Element not found, return false
        }
    }

    @Step("Returning score from score table for entry {name}")
    public String returnScore(String name) {

        WebElement playerRow = driver.findElement(
                By.xpath(String.format("//tr[td/a[contains(text(),'%s')]]", name)));

        return playerRow.findElement(By.xpath("td[2]")).getText();
    }

    @Step("Generating a random username")
    private void generateName() {
        int random = new Random().nextInt(900000) + 100000;

        name = String.format(
                "TEST-%s-%d",
                CurrentTest.getCurrentTestName(),
                random);
    }

    @Step("Returning the name used for this session")
    public String getName() {

        return name;
    }

    @Step("Entering the game of {name}")
    public GamePage clickExistingGame(String name) {

        WebElement playerRow = driver.findElement(
                By.xpath(String.format("//tr[td/a[contains(text(),'%s')]]", name)));

        playerRow.findElement(By.xpath("td[1]/a")).click();

        return new GamePage();
    }
}

package com.airelogic;

import com.airelogic.globalconstants.TestFeatures;
import com.airelogic.globalconstants.TestGroups;
import com.airelogic.pageobjects.GamePage;
import com.airelogic.pageobjects.MainPage;
import com.github.crautomation.core.ui.test.BaseUITestCase;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class MustNotAllowGameToStartWithNameAlreadyPresent extends BaseUITestCase {

    @Severity(SeverityLevel.CRITICAL)
    @Feature(TestFeatures.TEST_FAILURE_FEATURE)
    @Description("Validates that when you attempt to start a game with a name already present in the list it will not" +
            "allow you")
    @Test(groups = {TestGroups.AIRELOGIC}, description = "MustNotAllowGameToStartWithNameAlreadyPresent")
    public void mustNotAllowGameToStartWithNameAlreadyPresent() {

        MainPage mainPage = new MainPage().enterName();
        String currentName = mainPage.getName();
        GamePage gamePage = mainPage.clickStartButton();

        IntStream.range(0, 5).forEach(i -> gamePage.clickCookieButton());

        for (int i = 0; i < 3; i++) {
            gamePage.clickCookieButton();
        }

        mainPage = gamePage.clickMainPageButton();

        assertThat("User does not appear in the current games table",
                mainPage.isPlayerPresentInTable(currentName));

        mainPage.enterName(currentName).clickStartButton();

        // Validates we're in the correct game by the username display, and then validates that we have the
        // expected amount of cookies saved from the first interaction with the game.
        assertThat("The name in the current game does not match what's expected",
                gamePage.getCurrentGameName(),
                equalTo(currentName));

        assertThat("Cookie amount is incorrect - The game appears to of reset",
                gamePage.getCookieAmount(),
                equalTo(5));
    }
}
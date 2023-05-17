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

public class GameSaveMustPersistAfterNavigatingToMainPage extends BaseUITestCase {
    @Severity(SeverityLevel.CRITICAL)
    @Feature(TestFeatures.TEST_PASSED_FEATURE)
    @Description("GameSaveMustPersistAfterNavigatingToMainPage")
    @Test(groups = {TestGroups.AIRELOGIC}, description = "Validates that the game save will persist when navigating " +
            "back to the main page.")
    public void gameSaveMustPersistAfterNavigatingToMainPage() {

        MainPage mainPage = new MainPage().enterName();
        String currentName = mainPage.getName();
        GamePage gamePage = mainPage.clickStartButton();

        IntStream.range(0, 5).forEach(i -> gamePage.clickCookieButton());

        int expectedScore = gamePage.getCookieAmount();
        System.out.println(expectedScore);

        mainPage = gamePage.clickMainPageButton();

        // Validates the row is here for this user and captures the current score
        int score = Integer.parseInt(mainPage.returnScore(currentName));
        System.out.println(score);

        assertThat("User doesn't seem to be present or the score mismatches what's expected",
                expectedScore,
                equalTo(score));

        mainPage.clickExistingGame(currentName);

        assertThat("The name in the current game does not match what's expected",
                gamePage.getCurrentGameName(),
                equalTo(currentName));
    }
}
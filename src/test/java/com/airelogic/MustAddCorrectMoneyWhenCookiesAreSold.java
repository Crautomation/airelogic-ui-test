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

public class MustAddCorrectMoneyWhenCookiesAreSold extends BaseUITestCase {
    @Severity(SeverityLevel.CRITICAL)
    @Feature(TestFeatures.TEST_PASSED_FEATURE)
    @Description("Validates that when selling a cookie the correct amount (25c) is added to the money field.")
    @Test(groups = {TestGroups.AIRELOGIC}, description = "MustAddCorrectMoneyWhenCookiesAreSold")
    public void mustAddCorrectMoneyWhenCookiesAreSold() {

        GamePage gamePage = new MainPage().enterName().clickStartButton();

        IntStream.range(0, 5).forEach(i -> gamePage.clickCookieButton());

        assertThat("Cookie amount is incorrect",
                gamePage.getCookieAmount(),
                equalTo(5));

        // Will sell a few items and validate that the cookie amount reduces and money amount increases.
        IntStream.range(0, 3).forEach(i -> {
            gamePage.sellCookies(Integer.toString(1));

            assertThat("The money value is not what is expected",
                    gamePage.getMoneyAmount(),
                    equalTo((0.25 * (i + 1))));
        });
    }
}
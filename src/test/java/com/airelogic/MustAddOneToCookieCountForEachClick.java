package com.airelogic;

import com.airelogic.globalconstants.TestFeatures;
import com.airelogic.globalconstants.TestGroups;
import com.airelogic.pageobjects.GamePage;
import com.airelogic.pageobjects.MainPage;
import com.github.crautomation.core.ui.test.BaseUITestCase;
import io.qameta.allure.*;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class MustAddOneToCookieCountForEachClick extends BaseUITestCase {
    private GamePage gamePage;

    @Severity(SeverityLevel.CRITICAL)
    @Feature(TestFeatures.TEST_PASSED_FEATURE)
    @Description("Validates that when clicking the 'Click Cookie' button, " +
            "a single cookie is added to the 'Cookies' counter")
    @Test(groups = {TestGroups.AIRELOGIC}, description = "MustAddOneToCookieCountForEachClick")
    public void mustAddOneToCookieCountForEachClick() {

        gamePage = new MainPage().openMainPage().enterName().clickStartButton();

        clickCookieButtonAndAssertCounter();
    }

    @Step("Clicking the cookie counter iteratively and validating each time the counter increments correctly")
    private void clickCookieButtonAndAssertCounter() {
        // Amount of iterations to test the add function
        final int cookieCount = 10;

        for (int i = 0; i < cookieCount; i++) {

            gamePage.clickCookieButton();

            assertThat("Cookie amount is incorrect",
                    gamePage.getCookieAmount(),
                    equalTo(i + 1));
        }
    }
}
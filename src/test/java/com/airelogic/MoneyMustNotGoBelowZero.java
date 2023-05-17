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
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class MoneyMustNotGoBelowZero extends BaseUITestCase {

    @Severity(SeverityLevel.CRITICAL)
    @Feature(TestFeatures.TEST_FAILURE_FEATURE)
    @Description("Validates that a users money cannot go below zero.")
    @Test(groups = {TestGroups.AIRELOGIC}, description = "MoneyMustNotGoBelowZero")
    public void moneyMustNotGoBelowZero() {

        GamePage gamePage = new MainPage().openMainPage().enterName().clickStartButton();

        // Confirms the current money value is 0.
        assertThat("The money value is not what is expected",
                gamePage.getMoneyAmount(),
                equalTo(0.0));

        gamePage.buyFactories("1");

        assertThat("Money total is less than 0",
                gamePage.getMoneyAmount(),
                Matchers.not(Matchers.lessThan(0.0)));
    }
}
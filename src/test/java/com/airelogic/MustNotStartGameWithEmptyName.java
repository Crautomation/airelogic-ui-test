package com.airelogic;

import com.airelogic.globalconstants.TestFeatures;
import com.airelogic.globalconstants.TestGroups;
import com.airelogic.pageobjects.MainPage;
import com.github.crautomation.core.ui.test.BaseUITestCase;
import io.qameta.allure.*;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

public class MustNotStartGameWithEmptyName extends BaseUITestCase {

    @Severity(SeverityLevel.CRITICAL)
    @Feature(TestFeatures.TEST_FAILURE_FEATURE)
    @Description("Attempts to enter the game with the username field empty")
    @Test(groups = {TestGroups.AIRELOGIC}, description = "MustNotStartGameWithEmptyName")
    public void mustNotStartGameWithEmptyName() {

        new MainPage().clickStartButton();

        validateCurrentPage();
    }

    @Step("Asserting current page")
    private void validateCurrentPage() {
        assertThat("Browser is currently on the /game/ page, this should not be possible with an empty name.",
                getDriver().getCurrentUrl(), not(containsString("/game/")));
    }
}
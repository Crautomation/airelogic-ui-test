package com.airelogic.util;

import io.qameta.allure.Step;
import org.testng.ITestResult;
import org.testng.Reporter;

public class CurrentTest {
    @Step("Obtaining the name of the currently executing test")
    public static String getCurrentTestName() {
        String testName = null;
        ITestResult testResult = Reporter.getCurrentTestResult();
        if (testResult != null) {
            testName = testResult.getName();
        }
        return testName;
    }
}

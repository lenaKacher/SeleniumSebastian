package de.proficom.advantageshop.reporting;

import com.aventstack.extentreports.Status;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.SkipException;

public class ExtentTestListener implements ITestListener {

    @Override
    public synchronized void onStart(ITestContext pContext) {
    }

    @Override
    public synchronized void onFinish(ITestContext pContext) {
        ExtentTestManager.endTest();
        ExtentReportsManager.getInstance().flush();
    }

    @Override
    public synchronized void onTestStart(ITestResult pResult) {
        // Report Node
        ExtentTestManager.createReportNode(pResult.getMethod().getDescription(), pResult.getMethod().getMethodName());
    }

    @Override
    public synchronized void onTestSuccess(ITestResult pResult) {
        ExtentTestManager.getTest().log(Status.PASS, "Test passed");
    }

    @Override
    public synchronized void onTestFailure(ITestResult pResult) {
        // https://github.com/cbeust/testng/issues/1632
        if (!pResult.getThrowable().getClass().equals(SkipException.class)) {

            // on test failure capture screen and add the screenshot to the report
            ExtentTestManager.getTest().log(Status.FAIL, ExceptionUtils.getStackTrace(pResult.getThrowable()));
            String aScreenshot = CaptureScreen.getSeleniumScreenshotAsBase64();
            ReportHelper.addScreenshot("Fehler", aScreenshot);
        }
    }

    @Override
    public synchronized void onTestSkipped(ITestResult pResult) {
        ExtentTestManager.createReportNode(pResult.getMethod().getDescription(), pResult.getMethod().getMethodName());
        ExtentTestManager.getTest().log(Status.SKIP, "Test skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult pResult) {
    }
}
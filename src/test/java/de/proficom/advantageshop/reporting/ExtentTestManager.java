package de.proficom.advantageshop.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class ExtentTestManager {

    static Map<Integer, ExtentTest> extentTestMap = new HashMap<>();
    static ExtentReports extent = ExtentReportsManager.getInstance();

    public static synchronized ExtentTest getTest() {
        return extentTestMap.get((int) Thread.currentThread().getId());
    }

    public static synchronized void endTest() {
        extent.flush();
    }

    public static synchronized void createReportNode(String pTestName, String pInternerTestName) {
        if (StringUtils.isBlank(pTestName)) {
            pTestName = "Testbeschreibung fehlt";
        }
        ExtentTest aTest = extent.createTest(pTestName, pInternerTestName);
        extentTestMap.put((int) Thread.currentThread().getId(), aTest);
    }
}
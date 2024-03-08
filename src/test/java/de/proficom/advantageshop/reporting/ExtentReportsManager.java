package de.proficom.advantageshop.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import de.proficom.advantageshop.common.Global_VARS;

public class ExtentReportsManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null)
            createInstance();
        return extent;
    }

    public synchronized static void createInstance() {
        // Property for ignore warnings
        System.setProperty("org.freemarker.loggerLibrary", "none");

        // New Spark Reporter
        ExtentSparkReporter aSparkReporter = new ExtentSparkReporter(Global_VARS.PATH_SUREFIRE_REPORTS + "TestReport.html");

        // Add Reporter to extent reports and also set some configuration
        extent = new ExtentReports();
        extent.attachReporter(aSparkReporter);
        aSparkReporter.config().setReportName("Test Report");
    }
}
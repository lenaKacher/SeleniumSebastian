package de.proficom.advantageshop.common;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public abstract class TestBase {

    @BeforeClass
    protected void setUp() {
        // Get system properties
        String aDriverType = System.getProperty("driverType", "FIREFOX");
        String aEnvironment = System.getProperty("environment", "http://advantage.proficom.de:8080/#/");

        // Create the specific driver
        CreateWebDriver.getInstance().setWebDriver(aDriverType);
        WebDriver aDriver = CreateWebDriver.getInstance().getWebDriver();

        // Open the AUT
        aDriver.get(aEnvironment);
        System.out.println();
        System.out.println("Test Properties - driver:" + aDriverType + " | env:" + aEnvironment);
    }

    @BeforeMethod
    public void beforeMethod(ITestResult pResult) {
        String aTestDescription = pResult.getMethod().getDescription();
        System.out.println();
        System.out.println("=== Start Test: " + aTestDescription + " ===");
        System.out.println();
    }

    @AfterMethod
    public void afterMethod(ITestResult pResult) {
        String aTestDescription = pResult.getMethod().getDescription();
        System.out.println("*** Test abgeschlossen: " + aTestDescription + " ***");
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        CreateWebDriver.getInstance().closeWebDriver();
    }
}
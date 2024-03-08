package de.proficom.advantageshop.reporting;

import de.proficom.advantageshop.common.CreateWebDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.util.Base64;

public class CaptureScreen {

    /**
     * Take a Screenshot and return the Screenshot as a Base64 String
     *
     * @return Screenshot as Base 64 String
     */
    public static String getSeleniumScreenshotAsBase64() {
        WebDriver aDriver = CreateWebDriver.getInstance().getWebDriver();
        byte[] sourceScreen = ((TakesScreenshot) aDriver).getScreenshotAs(OutputType.BYTES);
        return Base64.getEncoder().encodeToString(sourceScreen);
    }
}
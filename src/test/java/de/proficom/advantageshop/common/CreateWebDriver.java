package de.proficom.advantageshop.common;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.configuration2.Configuration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

/**
 * WebDriver Singleton
 * <p>
 * Example: To get access to browser use
 * <pre>{@code
 * 		CreateWebDriver.getInstance().setWebDriver("CHROME");}</pre>
 */
public class CreateWebDriver {

    ////////////////////////////////////////
    // WebDriverManager instance
    ////////////////////////////////////////

    private static final ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();
    private static CreateWebDriver instance = null;

    /**
     * Don't allow public constructor
     */
    public CreateWebDriver() {
    }

    /**
     * Create single instance for WebDriver access
     *
     * @return instance as singleton
     */
    public static synchronized CreateWebDriver getInstance() {
        if (instance == null) {
            instance = new CreateWebDriver();
        }

        return instance;
    }

    ////////////////////////////////////////
    //  Browser / WebDriver access
    ////////////////////////////////////////

    /**
     * Get access to currently used WebDriver to be used in Selenium tests.
     *
     * @return WebDriver class
     */
    public synchronized WebDriver getWebDriver() {
        return webDriver.get();
    }

    /**
     * Set a new WebDriver.
     * If no valid value is given the fallback is Firefox.
     *
     * @param pBrowserName - examples:
     *                     <ul>
     *                     	<li>CHROME</li>
     *                     	<li>FIREFOX</li>
     *                     	<li>CHROME-REMOTE</li>
     *                     	<li>FIREFOX-REMOTE</li>
     *                     </ul>
     */
    public final synchronized void setWebDriver(String pBrowserName) {
        WebDriver aDriver;
        switch (pBrowserName.toUpperCase()) {
            case "CHROME":
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                DesiredCapabilities cp = new DesiredCapabilities();
                cp.setCapability(ChromeOptions.CAPABILITY,options);
                options.merge(cp);
                aDriver = new ChromeDriver(options);
                break;

            case "FIREFOX":
            default:
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions optionsFire = new FirefoxOptions();
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setCapability("marionette",true);
                optionsFire.merge(capabilities);
                aDriver = new FirefoxDriver(optionsFire);
                break;

            case "CHROME-REMOTE":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setCapability("browserName", "chrome");
                chromeOptions.addArguments("--disable-dev-shm-usage");
                chromeOptions.addArguments("--no-sandbox");
                aDriver = new RemoteWebDriver(getRemoteHubUrl(), chromeOptions);
                break;

            case "FIREFOX-REMOTE":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setCapability("browserName", "firefox");
                aDriver = new RemoteWebDriver(getRemoteHubUrl(), firefoxOptions);
                break;
        }

        // maximize browser window
        aDriver.manage().window().maximize();

        // set implicit wait for finding web elements
        aDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(SynchronizationUtils.TIMEOUT_IMPLICIT_WAIT));

        // set page load timeout
        aDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(SynchronizationUtils.TIMEOUT_PAGE_LOAD));

        // delete all cookies
        aDriver.manage().deleteAllCookies();

        webDriver.set(aDriver);
    }

    /**
     * Destroy instance of WebDriver instance
     */
    public synchronized void closeWebDriver() {
        if (hasWebDriver()) {
            getWebDriver().quit();
            webDriver.set(null);
        }
    }

    /**
     * Check if browser specific WebDriver has been created for used browser
     *
     * @return TRUE if WebDriver has been created, otherwise FALSE
     */
    public synchronized boolean hasWebDriver() {
        return (getWebDriver() != null);
    }

    /**
     * Return the URL of Selenium Hub
     *
     * @return URL of Selenium Hub
     */
    private URL getRemoteHubUrl() {
        try {
            Configuration aRemoteConfig = FileIO.loadConfig(Global_VARS.REMOTE_CONFIG_FILE);
            String aGivenURL = aRemoteConfig.getString("selenium.hub.url");
            return new URL(aGivenURL);
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
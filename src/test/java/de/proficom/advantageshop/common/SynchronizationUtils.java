package de.proficom.advantageshop.common;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SynchronizationUtils {

    // Default Timeouts
    public static final long TIMEOUT_IMPLICIT_WAIT = 20;
    public static final long TIMEOUT_PAGE_LOAD = 30;
    public static final int TIMEOUT_WAITFORCLICKABLE = 30;
    public static final int TIMEOUT_WAITFORVISIBILITY = 30;
    public static final int TIMEOUT_WAITFORINVISIBILITY = 20;

    /**
     * Wait for JavaScript, JQeury and AngularJS is completely loaded on a page, explanation:
     * https://www.swtestacademy.com/selenium-wait-javascript-angular-ajax/
     */
    public static void waitForPageLoad() {
        waitForJavaScript();
        waitForJQuery();
        waitForAngularJs();
        waitForLoadingIcon();
    }

    public static boolean waitForJavaScript() {
        WebDriver driver = CreateWebDriver.getInstance().getWebDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_PAGE_LOAD));
        ExpectedCondition<Boolean> jsLoad = driver1 -> ((JavascriptExecutor) driver1).executeScript("return document.readyState").toString()
                .equals("complete");
        return wait.until(jsLoad);
    }

    public static boolean waitForJQuery() {
        WebDriver driver = CreateWebDriver.getInstance().getWebDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_PAGE_LOAD));
        ExpectedCondition<Boolean> jQeryLoad = driver1 -> {
            JavascriptExecutor js = (JavascriptExecutor) driver1;
            return (Boolean) js.executeScript("return !!window.jQuery && window.jQuery.active == 0");
        };
        return wait.until(jQeryLoad);
    }

    public static boolean waitForAngularJs() {
        WebDriver driver = CreateWebDriver.getInstance().getWebDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_PAGE_LOAD));
        ExpectedCondition<Boolean> angularLoad = pDriver1 -> Boolean.valueOf(((JavascriptExecutor) pDriver1).executeScript(
                        "return (window.angular !== undefined) && (angular.element(document).injector() !== undefined) && (angular.element(document).injector().get('$http').pendingRequests.length === 0)")
                .toString());
        return wait.until(angularLoad);
    }

    /**
     * Only useful for the advantageshop.
     */
    public static void waitForLoadingIcon() {
        WebDriver aDriver = CreateWebDriver.getInstance().getWebDriver();
        WebElement loadingIcon = aDriver.findElement(By.cssSelector("body > div.loader"));
        WebDriverWait wait = new WebDriverWait(aDriver, Duration.ofSeconds(TIMEOUT_PAGE_LOAD));

        wait.until((ExpectedCondition<Boolean>) pDriver1 -> {
            String isHidden = loadingIcon.getAttribute("style");
            return isHidden.equals("display: none; opacity: 0;");
        });
    }

    // =================================
    // other useful methods, explanation:
    // https://stackoverflow.com/questions/44912203/selenium-web-driver-java-element-is-not-clickable-at-point-x-y-other-elem
    // =================================

    /**
     * Solution for: Element is present in the DOM but not click able.
     */
    public static void waitForClickable(WebElement pWebElement) {
        WebDriver driver = CreateWebDriver.getInstance().getWebDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_WAITFORCLICKABLE));
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(pWebElement)));
    }

    /**
     * Solution for: Element is present but having temporary Overlay.
     *
     * @param pWebElement - WebElement
     */
    public static void waitForVisibility(WebElement pWebElement) {
        WebDriver driver = CreateWebDriver.getInstance().getWebDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_WAITFORVISIBILITY));
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(pWebElement)));
    }

    public static void waitForInvisibility(WebElement pWebElement) {
        WebDriver driver = CreateWebDriver.getInstance().getWebDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_WAITFORINVISIBILITY));
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.invisibilityOf(pWebElement)));
    }

    /**
     * Prueft mit isDisplayed(), ob ein Element zu sehen ist. Faengt dabei die
     * NoSuchElementException ab, wenn die driver.findElement() Methode einen Fehler wirft.
     * --> dann gibt es das Element auf der aktuellen Seite nicht. Wartet maximal 3 Sekunden.
     *
     * @param pXpath - xpath vom Pruefobjekt
     * @return true or false
     */
    public static boolean isElementVisible(String pXpath) {
        // timeout runtersetzen
        WebDriver aDriver = CreateWebDriver.getInstance().getWebDriver();
        aDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        boolean aUsVisible;

        try {
            aUsVisible = aDriver.findElement(By.xpath(pXpath)).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException
                | org.openqa.selenium.NoSuchWindowException
                | org.openqa.selenium.StaleElementReferenceException exp) {
            aUsVisible = false;
        }

        // timeout wieder zuruecksetzen
        aDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT_IMPLICIT_WAIT));
        return aUsVisible;
    }

    /**
     * Stop the current thread.
     *
     * @param pSleepInMiliSeconds - long
     */
    public static void threadSleeper(long pSleepInMiliSeconds) {
        try {
            Thread.sleep(pSleepInMiliSeconds);
        } catch (InterruptedException exp) {
            System.out.println(exp);
        }
    }
}
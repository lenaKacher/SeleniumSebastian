package de.proficom.advantageshop.common;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePO {

    protected WebDriver driver = CreateWebDriver.getInstance().getWebDriver();

    // === WebElements ===
    @FindBy(id = "Layer_1")
    protected WebElement svgLogo;

    // === Constructor ===
    public BasePO() {
        SynchronizationUtils.waitForPageLoad();
        PageFactory.initElements(driver, this);
    }

    // === Methods ===

    /**
     * Fuehrt JavaScrript im Browser aus.
     * Achtung: das ist nicht gleichzusetzen mit einer Eingabe vom User.
     *
     * @param pScript - auszufuehrendes Script
     * @param pWebElement - WebElement, auf dem das Script ausgefuehrt wird
     */
    protected void executeJavaScript(String pScript, WebElement pWebElement) {
        JavascriptExecutor aExecutor = (JavascriptExecutor) driver;
        aExecutor.executeScript(pScript, pWebElement);
    }

    /**
     * Loest das 'could not be scrolled into view' Problem.
     *
     * @param pWebElement - WebElement.
     */
    protected void scrollIntoView(WebElement pWebElement) {
        SynchronizationUtils.waitForVisibility(pWebElement);
        executeJavaScript("arguments[0].scrollIntoView(true);", pWebElement);
    }
}
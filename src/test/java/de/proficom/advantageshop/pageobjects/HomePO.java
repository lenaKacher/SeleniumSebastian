package de.proficom.advantageshop.pageobjects;

import de.proficom.advantageshop.common.BasePO;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePO extends BasePO {

    // === Web Elements ===
    @FindBy(id = "speakersImg")
    protected WebElement divSpeakers;

    @FindBy(id = "tabletsImg")
    protected WebElement divTablets;

    @FindBy(id = "autoComplete")
    protected WebElement inputHP;

    // === Constructor ===
    public HomePO() {
        super();
    }

    // === Methods ===

    // Click on the Speaker icon
    public void goToSpeakers() {
        divSpeakers.click();
    }

    // Click on the tablet icon
    public void goToTablets() {
        divTablets.click();
    }

    // Send keys in the input in Home Page
    public void sendKeysToInputInHomePage(String pInput) {
        inputHP.sendKeys(pInput);
    }
}
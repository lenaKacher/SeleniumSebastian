package de.proficom.advantageshop.pageobjects;

import de.proficom.advantageshop.common.BasePO;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SpeakerDetailsPO extends BasePO {

    // === WebElements ===
    protected WebElement spanColor;

    @FindBy(xpath = "//input[@name='quantity']")
    protected WebElement inputQuantity;

    @FindBy(xpath = "//button[@name='save_to_cart']")
    protected WebElement addToCart;

    @FindBy(xpath = "//a[@class='ng-binding']")
    protected WebElement speakerReturn;

    // === Constructor ===
    public SpeakerDetailsPO() {
        super();
    }

    // === Methods ===
    public void inputColorOfSpeaker(String pColorOfSpeaker) {
        spanColor = driver.findElement(By.xpath("//div[not(contains(@class,'ng-hide'))]/span[@title='" + pColorOfSpeaker + "']"));
        spanColor.click();
    }

    public void inputNumberOfSpeakers(String pNumberOfSpeakers) {
        inputQuantity.sendKeys(Keys.CONTROL + "a");
        inputQuantity.sendKeys(pNumberOfSpeakers);
    }

    public void GoBackToSpeakerList() {
        speakerReturn.click();
    }

    public void addSpeakerToCart() {
        addToCart.click();
    }
}
package de.proficom.advantageshop.pageobjects;

import de.proficom.advantageshop.common.BasePO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TabletDetailsPO extends BasePO {

    // === Web Elements ===
    @FindBy(xpath = "//div[@id='Description']/h1")
    protected WebElement nameOfTheTablet;

    @FindBy(xpath = "//div[@id='Description']/h2")
    protected WebElement priceOfTheTablet;

    @FindBy(xpath = "//p[@class='roboto-light ng-binding']")
    protected WebElement detailsOfTheTablet;

    protected WebElement linkTablets;

    // === Constructor ===
    public TabletDetailsPO() {
        super();
    }

    // === Methods ===

    public String getNameOfTablet() {
        return nameOfTheTablet.getText();
    }

    public String getPriceOfTablet() {
        return priceOfTheTablet.getText();
    }

    public String getDetailsOfAndClassOfTablet() {
        return detailsOfTheTablet.getText();
    }

    // Go to the detail interface of a selected interface
    public void goToSelectedTablet(String nameOfTheTablet) {
        linkTablets = driver.findElement(By.linkText(nameOfTheTablet));
        linkTablets.click();
    }
}
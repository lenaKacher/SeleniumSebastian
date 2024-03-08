package de.proficom.advantageshop.pageobjects;

import de.proficom.advantageshop.common.BasePO;
import de.proficom.advantageshop.common.SynchronizationUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class PriceFilterPO extends BasePO {

    // === Web Elements ===
    @FindBy(xpath = "//h4[@href='javascript:void(0)']")
    protected WebElement linkPriceFilter;

    @FindBy(xpath = "//div[contains(@class,'noUi-origin') and contains(@class,'noUi-connect')]")
    protected WebElement divSetPriceLow;

    @FindBy(xpath = "//div[contains(@class,'noUi-origin') and contains(@class,'noUi-background')]")
    protected WebElement divSetPriceUp;

    // === Constructor ===
    public PriceFilterPO() {
        super();
    }

    // === Methods ===

    // Click on the "Price Filter" in the product overview interface
    public void clickPriceFilter() {
        SynchronizationUtils.waitForClickable(linkPriceFilter);
        linkPriceFilter.click();
    }

    /**
     * Setzt den Preis-Filter fuer Tablets in Prozent.
     *
     * @param pStart Start Price in %
     * @param pEnd   End Price in %
     */
    public void setPriceFromBothEndsDragAndDrop(int pStart, int pEnd) {
        Actions aBuilder = new Actions(driver);
        Action aSetLowPrice = aBuilder.dragAndDropBy(divSetPriceLow, pStart, 0).build();
        aSetLowPrice.perform();
        Action aSetHighPrice = aBuilder.dragAndDropBy(divSetPriceUp, pEnd - 100, 0).build();
        aSetHighPrice.perform();
    }
}
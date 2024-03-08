package de.proficom.advantageshop.pageobjects;

import de.proficom.advantageshop.common.BasePO;
import de.proficom.advantageshop.common.SynchronizationUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TabletsOverviewPO extends BasePO {

    // === Web Elements ===
    @FindBy(xpath = "//div[@class='cell categoryRight']//li[1]//img")
    protected WebElement firstTablet;

    // === Constructor ===
    public TabletsOverviewPO() {
        super();
    }

    // === Methods ===

    // Click on the first tablet that is shown in the overview interface
    public void clickFirstTablet() {
        SynchronizationUtils.threadSleeper(1000);
        SynchronizationUtils.waitForClickable(firstTablet);
        firstTablet.click();
    }
}
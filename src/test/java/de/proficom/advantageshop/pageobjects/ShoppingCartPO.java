package de.proficom.advantageshop.pageobjects;

import de.proficom.advantageshop.common.BasePO;
import org.openqa.selenium.By;

public class ShoppingCartPO extends BasePO {

    private String tableRowXpath;

    // === WebElements ===

    // === Constructor ===
    public ShoppingCartPO() {
        super();
    }

    // === Methods ===
    public void setTableRowXpath(String pSpeakerName) {
        tableRowXpath = "//table[@class='fixedTableEdgeCompatibility']/tbody//td/label[text()='"
                + pSpeakerName.toUpperCase() + "']/ancestor::tr";
    }

    public String getDataCellName() {
        return driver.findElement(By.xpath(tableRowXpath + "/td[2]")).getText();
    }

    public String getDataCellColor() {
        return driver.findElement(By.xpath(tableRowXpath + "/td[4]/span")).getAttribute("title");
    }

    public String getDataCellQuantity() {
        return driver.findElement(By.xpath(tableRowXpath + "/td[5]")).getText();
    }
}
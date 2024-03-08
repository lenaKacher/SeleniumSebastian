package de.proficom.advantageshop.tests;

import de.proficom.advantageshop.common.TestBase;
import de.proficom.advantageshop.pageobjects.HomePO;
import de.proficom.advantageshop.pageobjects.PriceFilterPO;
import de.proficom.advantageshop.pageobjects.TabletDetailsPO;
import de.proficom.advantageshop.pageobjects.TabletsOverviewPO;
import de.proficom.advantageshop.reporting.CaptureScreen;
import de.proficom.advantageshop.reporting.ReportHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TabletFiltering extends TestBase {

    // Test Data
    protected String nameOfTheTablet = "HP ELITEPAD 1000 G2 TABLET";
    protected String priceOfTheTablet = "$1,009.00";

    @Test(description = "Tablet filter bedienen")
    public void tesTabletFilteringt() {
        // Steps to click the first tablet
        goToTabletsOverview();
        setPriceOfTablets();
        clickFirstTablet();

        // Assert if the name of the tablet match
        Assert.assertEquals(getNameOfSelectedTablet(), nameOfTheTablet, "name of Tablet match");

        // Assert if the price of the tablet match
        Assert.assertEquals(getPriceOfSelectedTablet(), priceOfTheTablet, "price of Tablet match");

        // Reporting in table
        String[][] aInfos = {
                {"Name of the tablet", nameOfTheTablet},
                {"Price of the tablet", priceOfTheTablet}
        };
        ReportHelper.createTable(aInfos);

        // Screenshot vom Warenkorb
        String screenshot = CaptureScreen.getSeleniumScreenshotAsBase64();
        ReportHelper.addScreenshot("Details of the tablet ", screenshot);
    }

    // Go to interface of the tablets overview
    public void goToTabletsOverview() {
        HomePO aHome = new HomePO();
        aHome.goToTablets();
    }

    // Move the slider to set the price of the tablets
    public void setPriceOfTablets() {
        PriceFilterPO pFilter = new PriceFilterPO();
        pFilter.clickPriceFilter();
        pFilter.setPriceFromBothEndsDragAndDrop(50, 80);
    }

    // Click of the first tablet of the resulting ones
    public void clickFirstTablet() {
        TabletsOverviewPO aTablets = new TabletsOverviewPO();
        aTablets.clickFirstTablet();
    }

    public String getNameOfSelectedTablet() {
        TabletDetailsPO aTabDetails = new TabletDetailsPO();
        return aTabDetails.getNameOfTablet();
    }

    public String getPriceOfSelectedTablet() {
        TabletDetailsPO aTabDetails = new TabletDetailsPO();
        return aTabDetails.getPriceOfTablet();
    }
}
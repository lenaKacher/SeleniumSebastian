package de.proficom.advantageshop.tests;

import de.proficom.advantageshop.common.TestBase;
import de.proficom.advantageshop.pageobjects.*;
import de.proficom.advantageshop.reporting.CaptureScreen;
import de.proficom.advantageshop.reporting.ReportHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class AddSpeakerToCart extends TestBase {

    // Test data
    protected String firstSpeakerName = "HP Roar Mini Wireless Speaker";
    protected String colorOfFirstSpeaker = "BLACK";
    protected String numberOfFirstSpeaker = "3";

    protected String secondSpeakerName = "Bose SoundLink Wireless Speaker";
    protected String colorOfSecondSpeaker = "TURQUOISE";
    protected String numberOfSecondSpeaker = "2";

    @Test(description = "Lautsprecher in den Warenkorb packen")
    protected void testAddSpeakerToCart() {
        // Steps for going to the cart overview after adding a product
        goToSpeakers();
        goToFirstSelectedSpeaker();
        enterDetailsForFirstSpeaker();
        goBack();
        goToSecondSelectedSpeaker();
        enterDetailsForSecondSpeaker();
        goToCart();

        // Check the name , color and amount of the first speaker with asserts
        Assert.assertEquals(getSpeakerResults1().get(0), firstSpeakerName.toUpperCase(), "Speaker name match");
        Assert.assertEquals(getSpeakerResults1().get(1), colorOfFirstSpeaker, "Color of speaker match");
        Assert.assertEquals(getSpeakerResults1().get(2), numberOfFirstSpeaker, "Amount of speakers match");

        // Check the name , color and amount of the second speaker with asserts
        Assert.assertEquals(getSpeakerResults2().get(0), secondSpeakerName.toUpperCase(), "Speaker name match");
        Assert.assertEquals(getSpeakerResults2().get(1), colorOfSecondSpeaker, "Color of speaker match");
        Assert.assertEquals(getSpeakerResults2().get(2), numberOfSecondSpeaker, "Amount of speakers match");

        // Reporting of first speaker
        String[][] aInfos1 = {
                {"Lautsprecher", firstSpeakerName},
                {"Farbe", colorOfFirstSpeaker},
                {"Anzahl", numberOfFirstSpeaker},
        };
        ReportHelper.createTable(aInfos1);

        // Reporting of second speaker
        String[][] aInfos2 = {
                {"Lautsprecher", secondSpeakerName},
                {"Farbe", colorOfSecondSpeaker},
                {"Anzahl", numberOfSecondSpeaker},
        };
        ReportHelper.createTable(aInfos2);

        // Screenshot vom Warenkorb
        String aScreenshot = CaptureScreen.getSeleniumScreenshotAsBase64();
        ReportHelper.addScreenshot("Warenkorb", aScreenshot);
    }

    // Go to the Speaker overview page
    public void goToSpeakers() {
        HomePO aHome = new HomePO();
        aHome.goToSpeakers();
    }

    // Go to the selected Speaker page
    public void goToFirstSelectedSpeaker() {
        SpeakerOverviewPO aSpeakerOverviewPO = new SpeakerOverviewPO();
        aSpeakerOverviewPO.goToSelectedSpeakers(firstSpeakerName);
    }

    // Enter specific inputs to the selected speaker
    public void enterDetailsForFirstSpeaker() {
        SpeakerDetailsPO aSpeakerDetails = new SpeakerDetailsPO();
        aSpeakerDetails.inputColorOfSpeaker(colorOfFirstSpeaker);
        aSpeakerDetails.inputNumberOfSpeakers(numberOfFirstSpeaker);
        aSpeakerDetails.addSpeakerToCart();
    }

    // Go back to Speaker Overview
    public void goBack() {
        SpeakerDetailsPO aSpeakerReturn = new SpeakerDetailsPO();
        aSpeakerReturn.GoBackToSpeakerList();
    }

    public void goToCart() {
        MenuPO aMenu = new MenuPO();
        aMenu.goToCart();
    }

    // Go to the selected Speaker page
    public void goToSecondSelectedSpeaker() {
        SpeakerOverviewPO aSpeakerOverviewPO = new SpeakerOverviewPO();
        aSpeakerOverviewPO.goToSelectedSpeakers(secondSpeakerName);
    }

    // Enter specific inputs to the selected speaker
    public void enterDetailsForSecondSpeaker() {
        SpeakerDetailsPO aSpeakerDetails = new SpeakerDetailsPO();
        aSpeakerDetails.inputColorOfSpeaker(colorOfSecondSpeaker);
        aSpeakerDetails.inputNumberOfSpeakers(numberOfSecondSpeaker);
        aSpeakerDetails.addSpeakerToCart();
    }

    // Get the different Strings from the resulted list
    public ArrayList<String> getSpeakerResults1() {
        List<String> aListOfResults = new ArrayList<>();
        ShoppingCartPO aCartOverview = new ShoppingCartPO();
        aCartOverview.setTableRowXpath(firstSpeakerName);
        aListOfResults.add(aCartOverview.getDataCellName());
        aListOfResults.add(aCartOverview.getDataCellColor());
        aListOfResults.add(aCartOverview.getDataCellQuantity());
        return (ArrayList<String>) aListOfResults;
    }

    // Get the different Strings from the resulted list
    public ArrayList<String> getSpeakerResults2() {
        List<String> aListOfResults = new ArrayList<>();
        ShoppingCartPO aCartOverview = new ShoppingCartPO();
        aCartOverview.setTableRowXpath(secondSpeakerName);
        aListOfResults.add(aCartOverview.getDataCellName());
        aListOfResults.add(aCartOverview.getDataCellColor());
        aListOfResults.add(aCartOverview.getDataCellQuantity());
        return (ArrayList<String>) aListOfResults;
    }
}
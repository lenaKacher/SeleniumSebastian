package de.proficom.advantageshop.pageobjects;

import de.proficom.advantageshop.common.BasePO;
import de.proficom.advantageshop.common.SynchronizationUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SpeakerOverviewPO extends BasePO {

    // === WebElements ===
    protected WebElement linkSpeakers;

    // === Constructor ===
    public SpeakerOverviewPO() {
        super();
    }

    // === Methods ===

    // Go to the detail interface of the selected speaker
    public void goToSelectedSpeakers(String pNameOfSpeaker) {
        linkSpeakers = driver.findElement(By.linkText(pNameOfSpeaker));
        linkSpeakers.click();
    }
}
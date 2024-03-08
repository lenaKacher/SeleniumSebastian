package de.proficom.advantageshop.pageobjects;

import de.proficom.advantageshop.common.BasePO;
import de.proficom.advantageshop.common.SynchronizationUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MenuPO extends BasePO {

    // === Web Elements ===
    @FindBy(id = "menuUser")
    protected WebElement svgUser;

    @FindBy(id = "menuCart")
    protected WebElement svgCart;

    @FindBy(id = "menuUserLink")
    protected WebElement linkFunctionsUser;

    @FindBy(xpath = "//a[@id='menuUserLink']/span")
    protected WebElement userLogged;

    @FindBy(xpath = "//label[@ng-click='signOut($event)']")
    protected WebElement labelSignOut;

    // === Constructor ===
    public MenuPO() {
        super();
    }

    // === Methods ===

    // Click on the user icon on the menu
    public void goToUser() {
        SynchronizationUtils.waitForVisibility(svgUser);
        svgUser.click();
    }

    // Click on the cart icon on the menu
    public void goToCart() {
        SynchronizationUtils.waitForVisibility(svgCart);
        svgCart.click();
    }

    // String of the empty user
    public String readBlankUser() {
        return svgUser.getText();
    }

    // Get String of the logged-in user
    public String getLoggedUser() {
        SynchronizationUtils.waitForVisibility(userLogged);
        return userLogged.getAttribute("innerHTML");
    }

    // Click on the user icon on the menu , when someone is logged in
    public void clickUserFunctions() {
        linkFunctionsUser.click();
    }

    // Click to log out
    public void clickToLogOut() {
        labelSignOut.click();
    }
}
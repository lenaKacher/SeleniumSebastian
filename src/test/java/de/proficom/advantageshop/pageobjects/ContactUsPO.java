package de.proficom.advantageshop.pageobjects;

import de.proficom.advantageshop.common.BasePO;
import de.proficom.advantageshop.common.SynchronizationUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ContactUsPO extends BasePO {

    // === Web Elements ===
    @FindBy(name = "categoryListboxContactUs")
    protected WebElement selectCategory;

    protected WebElement categorySelected;

    @FindBy(name = "productListboxContactUs")
    protected WebElement selectProduct;

    protected WebElement productSelected;

    @FindBy(name = "emailContactUs")
    protected WebElement inputEmail;

    @FindBy(xpath = "//textarea[@name='subjectTextareaContactUs']")
    protected WebElement inputSubject;

    @FindBy(id = "send_btnundefined")
    protected WebElement buttonSend;

    @FindBy(xpath = "//p[@class='roboto-regular successMessage ng-binding']")
    protected WebElement formularySended;

    // === Constructor ===
    public ContactUsPO() {
        super();
    }

    // === Methods ===
    public void enterCategoryList() {
        scrollIntoView(selectCategory);
        selectCategory.click();
    }

    public void setCategory(String pCategory) {
        categorySelected = driver.findElement(By.xpath("//option[@label='" + pCategory + "']"));
        categorySelected.click();
    }

    public void enterProductList() {
        selectProduct.click();
    }

    public void setSelectProduct(String pProduct) {
        productSelected = driver.findElement(By.xpath("//option[@label='" + pProduct + "']"));
        productSelected.click();
    }

    public void setEmail(String pEmail) {
        inputEmail.click();
        inputEmail.sendKeys(pEmail);
    }

    public void setSubject(String pSubject) {
        inputSubject.sendKeys(pSubject);
    }

    public void pressSend() {
        buttonSend.click();
    }

    public String getStringOfText() {
        SynchronizationUtils.waitForVisibility(formularySended);
        return formularySended.getText();
    }
}
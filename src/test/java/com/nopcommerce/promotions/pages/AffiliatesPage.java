package com.nopcommerce.promotions.pages;

import com.nopcommerce.promotions.base.BasePage;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import java.util.List;


public class AffiliatesPage extends BasePage {

    public AffiliatesPage(WebDriver driver) {
        super(driver);
    }
    
    @FindBy(xpath = "//p[normalize-space()='Promotions']")
    WebElement promotionsMenu;

    @FindBy(xpath = "//p[normalize-space()='Affiliates']")
    WebElement affiliatesSection;

    @FindBy(xpath = "//h1[contains(text(),'Affiliates')]")
    WebElement pageTitle;
    
    @FindBy(xpath = "//a[@class='btn btn-primary']")
    WebElement addNewBtn;

    @FindBy(xpath = "//button[@class='btn btn-tool']")
    WebElement dropAdd;

    @FindBy(id = "Address_FirstName")
    WebElement firstName;

    @FindBy(id = "Address_LastName")
    WebElement lastName;

    @FindBy(id = "Address_Email")
    WebElement email;

    @FindBy(id = "Address_Company")
    WebElement company;

    @FindBy(id = "Address_CountryId")
    WebElement countryDropdown;

    @FindBy(id = "Address_StateProvinceId")
    WebElement stateDropdown;

    @FindBy(id = "Address_City")
    WebElement city;

    @FindBy(id = "Address_Address1")
    WebElement address1;

    @FindBy(xpath = "//button[@name ='save-continue']")
    WebElement saveContinueBtn;

    @FindBy(xpath = "//button[@name ='save']")
    WebElement saveBtn;

    @FindBy(xpath = "//table//tbody/tr")
    List<WebElement> tableRows;

    
    @FindBy(xpath = "//table//a[contains(@href,'Edit')]")
    WebElement editBtn;
    
    @FindBy(id = "affiliate-delete")
    WebElement deleteBtn;

    @FindBy(xpath = "//button[contains(@class,'btn-danger')]")
    WebElement confirmDeleteBtn;

    
    public void navigateToAffiliates() {
        click(promotionsMenu);
        click(affiliatesSection);
    }
    
    public boolean isPageLoaded() {
        return isDisplayed(pageTitle);
    }

    public void addAffiliate(String fname, String lname, String mail) {

        click(addNewBtn);
        click(dropAdd);

        wait.until(ExpectedConditions.visibilityOf(firstName));
        type(firstName, fname);
        type(lastName, lname);
        type(email, mail);
        type(company, "Test Company");

        Select country = new Select(countryDropdown);
        country.selectByValue("237");

        Select state = new Select(stateDropdown);
        wait.until(d -> state.getOptions().size() > 1);
        state.selectByVisibleText("New York");

        type(city, "NY");
        type(address1, "Street");

        click(saveContinueBtn);
        click(saveBtn);
    }
    
    public int getAffiliateCount() {
        return tableRows.size();
    }


    public void updateFirstAffiliate() {

        wait.until(ExpectedConditions.elementToBeClickable(editBtn));
        click(editBtn);

        type(company, "UpdatedCompany");

        click(saveBtn);
    }


    public void deleteFirstAffiliate() {

        click(editBtn);
        click(deleteBtn);
        click(confirmDeleteBtn);
    }
}

package com.nopcommerce.promotions.pages;

import com.nopcommerce.promotions.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NewsletterListPage extends BasePage {

    public NewsletterListPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "SearchEmail")
    WebElement emailField;

    @FindBy(xpath = "//button[normalize-space()='Search']")
    WebElement searchBtn;

    @FindBy(xpath = "//a[contains(normalize-space(),'Add new')]")
    WebElement addNewBtn;

    @FindBy(xpath = "//button[contains(normalize-space(),'Export to CSV')]")
    WebElement exportBtn;

    @FindBy(xpath = "//button[contains(normalize-space(),'Import from CSV')]")
    WebElement importBtn;

    @FindBy(xpath = "//a[normalize-space()='Edit']")
    WebElement editBtn;

    @FindBy(id = "newsletter-subscriptions-grid")
    WebElement grid;

    @FindBy(xpath = "//input[@type='file']")
    WebElement fileUpload;

    @FindBy(xpath = "//div[contains(@class,'modal-footer')]//button[contains(.,'Import')]")
    WebElement importSubmitBtn;

    @FindBy(xpath = "//div[contains(@class,'alert-success')]")
    WebElement successMessage;

    public void search(String email) {

        type(emailField, email);
        click(searchBtn);

        wait.until(ExpectedConditions.refreshed(
                ExpectedConditions.visibilityOfElementLocated(
                        org.openqa.selenium.By.id("newsletter-subscriptions-grid")
                )
        ));

        System.out.println("Search completed with fresh grid");
    }

    public NewsletterDetailsPage clickAddNew() {
        click(addNewBtn);
        System.out.println("Add new clicked");
        return new NewsletterDetailsPage(driver);
    }


    public NewsletterDetailsPage clickEdit() {

        for (int i = 0; i < 3; i++) {
            try {
                WebElement edit = wait.until(ExpectedConditions.elementToBeClickable(
                        org.openqa.selenium.By.xpath("(//a[normalize-space()='Edit'])[1]")
                ));
                edit.click();
                return new NewsletterDetailsPage(driver);
            } catch (org.openqa.selenium.StaleElementReferenceException e) {
                System.out.println("Retrying edit click...");
            }
        }

        throw new RuntimeException("Failed to click Edit");
    }


    public void exportCSV() {
        click(exportBtn);
        System.out.println("Export clicked");
    }


    public void importCSV(String filePath) {

        click(importBtn);
        System.out.println("Import popup opened");

        fileUpload.sendKeys(filePath);
        System.out.println("File uploaded");

        click(importSubmitBtn);
        System.out.println("Import submitted");
    }



    public boolean isOnNewsletterPage() {
        return getCurrentUrl().contains("NewsLetterSubscription");
    }

    public boolean isGridVisible() {
        return isDisplayed(grid);
    }

    public boolean isSuccessMessageDisplayed() {
        return isDisplayed(successMessage);
    }
}
package com.nopcommerce.promotions.pages;

import com.nopcommerce.promotions.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewsletterDetailsPage extends BasePage {

    public NewsletterDetailsPage(WebDriver driver) {
        super(driver);
    }


    @FindBy(id = "Email")
    WebElement emailField;

    @FindBy(name = "save")
    WebElement saveBtn;

    @FindBy(xpath = "//span[contains(normalize-space(),'Delete')]")
    WebElement deleteBtn;

    @FindBy(xpath = "//div[@class='modal-footer']//button")
    WebElement confirmDeleteBtn;

    @FindBy(xpath = "//div[contains(@class,'alert-success')]")
    WebElement successMessage;

    public void enterEmail(String email) {
        type(emailField, email);
        System.out.println("Email entered: " + email);
    }

    public void clickSave() {
        click(saveBtn);
        System.out.println("Saved successfully");
    }


    public void deleteSubscriber() {
        click(deleteBtn);
        click(confirmDeleteBtn);
        System.out.println("Subscriber deleted successfully");
    }


    public boolean isSuccessMessageDisplayed() {
        return isDisplayed(successMessage);
    }
}
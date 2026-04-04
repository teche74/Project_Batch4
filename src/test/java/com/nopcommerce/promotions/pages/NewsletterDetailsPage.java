package com.nopcommerce.promotions.pages;

import com.nopcommerce.promotions.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewsletterDetailsPage extends BasePage {

    public NewsletterDetailsPage(WebDriver driver) {
        super(driver);
    }

    // 🔍 Elements
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

    // ✏️ ENTER EMAIL
    public void enterEmail(String email) {
        type(emailField, email);
        System.out.println("Email entered: " + email);
    }

    // 💾 SAVE
    public void clickSave() {
        click(saveBtn);
        System.out.println("Saved successfully");
    }

    // ❌ DELETE
    public void deleteSubscriber() {
        click(deleteBtn);          // Click delete
        click(confirmDeleteBtn);   // Confirm delete
        System.out.println("Subscriber deleted successfully");
    }

    // ✅ VALIDATION METHOD (FOR ASSERTIONS)
    public boolean isSuccessMessageDisplayed() {
        return isDisplayed(successMessage);
    }
}
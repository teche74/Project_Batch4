package com.nopcommerce.promotions.pages;

import com.nopcommerce.promotions.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SubscriptionTypes extends BasePage {

    public SubscriptionTypes(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//p[normalize-space()='Promotions']")
    WebElement promotionsMenu;

    @FindBy(xpath = "//p[normalize-space()='Newsletter subscribers']")
    WebElement newsletterMenu;

    @FindBy(xpath = "//p[normalize-space()='Newsletter subscribers']")
    WebElement subscriptionTypeMenu;

    @FindBy(xpath = "//p[normalize-space()='Campaigns']")
    WebElement campaignsMenu;

    @FindBy(xpath = "//h1[contains(text(),'Newsletter subscription types')]")
    WebElement pageTitle;

    public void navigateToSubscriptionTypes() {
        click(promotionsMenu);
        click(newsletterMenu);
        click(subscriptionTypeMenu);
    }

    public boolean isPageLoaded() {
        return isDisplayed(pageTitle);
    }
}
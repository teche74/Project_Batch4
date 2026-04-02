package com.nopcommerce.promotions.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.nopcommerce.promotions.base.BasePage;

public class SubscriptionTypes extends BasePage {

    public SubscriptionTypes(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//p[normalize-space()='Promotions']")
    WebElement promotionsMenu;

    @FindBy(xpath = "//p[normalize-space()='Discounts']")
    WebElement discountsSection;

    @FindBy(xpath = "//p[normalize-space()='Affiliates']")
    WebElement affiliatesSection;

    @FindBy(xpath = "//p[normalize-space()='Newsletter subscribers']")
    WebElement NewsletterSubscribersSection;

    @FindBy(xpath = "//p[normalize-space()='Subscription types']")
    WebElement SubscriptionTypesSection;

    @FindBy(xpath = "//p[normalize-space()='Campaigns']")
    WebElement CampaignsSection;

    @FindBy(xpath = "//h1[normalize-space()='Subscription types']")
    WebElement pageTitle;

    @FindBy(xpath = "//a[normalize-space()='Add new']")
    WebElement addNewButton;

    @FindBy(xpath = "//div[@id=\"subscriptiontypes-grid_wrapper\"]//table[@id=\"subscriptiontypes-grid\"]//tbody//tr")
    List<WebElement> rows;

    public void navigateToSubscriptionTypes() {
        click(promotionsMenu);
        click(SubscriptionTypesSection);
    }

    public boolean isPageLoaded() {
        return isDisplayed(pageTitle);
    }

    public int getRowCount() {
        return rows.size();
    }
}
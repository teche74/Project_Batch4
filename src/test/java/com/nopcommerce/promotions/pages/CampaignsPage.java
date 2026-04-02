package com.nopcommerce.promotions.pages;

import com.nopcommerce.promotions.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CampaignsPage extends BasePage {

    public CampaignsPage(WebDriver driver) {
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

    @FindBy(xpath = "//h1[contains(text(),'Campaigns')]")
    WebElement pageTitle;

    public void navigateToCampaigns() {
        click(promotionsMenu);
        click(CampaignsSection);
    }

    public boolean isPageLoaded() {
        return isDisplayed(pageTitle);
    }
}
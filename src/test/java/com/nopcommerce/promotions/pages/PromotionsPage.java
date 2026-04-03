package com.nopcommerce.promotions.pages;

import com.nopcommerce.promotions.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PromotionsPage extends BasePage {

    public PromotionsPage(WebDriver driver) {
        super(driver);
    }

    // 🔍 Elements (optional if you want UI navigation)
    @FindBy(xpath = "//p[contains(text(),'Promotions')]")
    WebElement promotionsMenu;

    @FindBy(xpath = "//a[@href='/Admin/NewsLetterSubscription/List']")
    WebElement newsletterLink;

    @FindBy(xpath = "//h1[contains(text(),'Newsletter subscribers')]")
    WebElement pageHeading;

    // 📍 NAVIGATION (DIRECT URL - BEST PRACTICE)
    public NewsletterListPage goToNewsletter() {

        driver.get("https://admin-demo.nopcommerce.com/Admin/NewsLetterSubscription/List");

        // Wait handled via BasePage method
        isDisplayed(pageHeading);

        System.out.println("Navigated to Newsletter page");

        return new NewsletterListPage(driver);
    }

    // 📍 OPTIONAL (UI Navigation if needed)
    public NewsletterListPage goToNewsletterViaUI() {

        click(promotionsMenu);
        click(newsletterLink);

        isDisplayed(pageHeading);

        System.out.println("Navigated via UI");

        return new NewsletterListPage(driver);
    }
}
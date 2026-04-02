package com.nopcommerce.promotions.tests;

import com.nopcommerce.promotions.base.BaseTest;
import com.nopcommerce.promotions.pages.NewsletterSubscriptionPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NewsletterSubscriptionPageTest extends BaseTest {

    @Test
    public void verifyNewsletterSubscriptionPage() {

        driver.findElement(org.openqa.selenium.By.xpath("//button[contains(text(),'Log in')]")).click();

        NewsletterSubscriptionPage page = new NewsletterSubscriptionPage(driver);

        page.navigateToSubscriptionTypes();

        Assert.assertTrue(page.isPageLoaded(),
                "Newsletter Subscription Types page not loaded");
    }
}
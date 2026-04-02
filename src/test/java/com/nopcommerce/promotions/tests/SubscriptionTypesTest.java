package com.nopcommerce.promotions.tests;

import com.nopcommerce.promotions.base.BaseTest;
import com.nopcommerce.promotions.pages.SubscriptionTypes;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SubscriptionTypesTest extends BaseTest {

    @Test
    public void verifySubscriptionTypesPage() {

        driver.findElement(By.xpath("//button[contains(text(),'Log in')]")).click();

        SubscriptionTypes page = new SubscriptionTypes(driver);

        page.navigateToSubscriptionTypes();

        Assert.assertTrue(page.isPageLoaded(),
                "Subscription Types page not loaded");
    }
}
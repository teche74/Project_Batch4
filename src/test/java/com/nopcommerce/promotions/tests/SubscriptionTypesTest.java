package com.nopcommerce.promotions.tests;

import com.nopcommerce.promotions.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.nopcommerce.promotions.pages.SubscriptionTypes;

public class SubscriptionTypesTest extends BaseTest {

    SubscriptionTypes page = new SubscriptionTypes(driver);

    @Test
    public void verifySubscriptionTypesPage() {

        page.navigateToSubscriptionTypes();

        Assert.assertTrue(page.isPageLoaded());
    }

    @Test
    public void AddingNewSubscriptionType() {
        System.out.println("Count : " + page.getRowCount());
    }
}
package com.nopcommerce.promotions.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.nopcommerce.promotions.base.BaseTest;
import com.nopcommerce.promotions.pages.SubscriptionTypes;

public class SubscriptionTypesTest extends BaseTest {

    SubscriptionTypes page;

    @BeforeClass
    public void initPage() {
        page = new SubscriptionTypes(driver);
    }

    @Test
    public void verifySubscriptionTypesPage() {
        page.navigateToSubscriptionTypes();
        Assert.assertTrue(page.isPageLoaded(), "Page did not load properly");
    }

    @Test
    public void addingNewSubscriptionType() {
        page.navigateToSubscriptionTypes();
        System.out.println("Row Count: " + page.getRowCount());
    }
}
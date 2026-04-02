package com.nopcommerce.promotions.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.nopcommerce.promotions.base.BaseTest;
import com.nopcommerce.promotions.pages.SubscriptionTypes;

public class SubscriptionTypesTest extends BaseTest {

    SubscriptionTypes page;

    String testName = "TestUser_" + System.currentTimeMillis();

    @BeforeClass
    public void initPage() {
        page = new SubscriptionTypes(driver);
    }

    @Test(priority = 1)
    public void verifySubscriptionTypesPage() {
        page.navigateToSubscriptionTypes();
        Assert.assertTrue(page.isPageLoaded(), "Page did not load properly");
    }

    @Test(priority = 2)
    public void verifyAddSubscriptionType() {
        page.navigateToSubscriptionTypes();

        int beforeCount = page.getRowCount();
        System.out.println("Before Count: " + beforeCount);

        page.addEntry(testName, "5");

        page.navigateToSubscriptionTypes();
        int afterCount = page.getRowCount();
        System.out.println("After Count: " + afterCount);

        Assert.assertTrue(afterCount > beforeCount, "Entry was not added");
    }

    @Test(priority = 3)
    public void verifyDeleteSubscriptionType() {
        page.navigateToSubscriptionTypes();

        int beforeCount = page.getRowCount();
        System.out.println("Before Delete Count: " + beforeCount);

        page.deleteByName(testName);
        page.navigateToSubscriptionTypes();
        int afterCount = page.getRowCount();
        System.out.println("After Delete Count: " + afterCount);

        Assert.assertTrue(afterCount < beforeCount, "Entry was not deleted");
    }
}
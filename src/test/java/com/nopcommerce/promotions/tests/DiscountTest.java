package com.nopcommerce.promotions.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.nopcommerce.promotions.base.BaseTest;
import com.nopcommerce.promotions.pages.DiscountPage;

public class DiscountTest extends BaseTest {

    DiscountPage page;
    String discountName = "batch4_" + System.currentTimeMillis();

    @BeforeClass
    public void initPage() {
        page = new DiscountPage(driver);
    }

    @Test(priority = 1)
    public void verifyDiscountPageLoaded() {
        page.navigateToDiscounts();
        Assert.assertTrue(page.isPageLoaded(), "Discount page failed to load.");
    }

    @Test(priority = 2, dependsOnMethods = "verifyDiscountPageLoaded")
    public void verifyAddDiscount() {
        page.navigateToDiscounts();
        int before = page.getDiscountCount();

        page.addDiscount(discountName);

        page.navigateToDiscounts();
        int after = page.getDiscountCount();
        Assert.assertTrue(after > before, "Count did not increase after adding discount!");
    }

    @Test(priority = 3, dependsOnMethods = "verifyAddDiscount")
    public void verifyDeleteDiscount() {
        page.navigateToDiscounts();
        int before = page.getDiscountCount();

        page.deleteByName(discountName);

        page.navigateToDiscounts();
        int after = page.getDiscountCount();
        System.out.println("After Delete: " + after + " | Before Delete: " + before);
        Assert.assertTrue(after < before, "Count did not decrease after deletion!");
    }
}

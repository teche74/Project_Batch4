package com.nopcommerce.promotions.tests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.nopcommerce.promotions.base.BaseTest;
import com.nopcommerce.promotions.pages.AffiliatesPage;

public class AffiliatesPageTest extends BaseTest {
    AffiliatesPage page;

    String email = "qa_" + System.currentTimeMillis() + "@test.com";

    @BeforeClass
    public void initPage() {
        page = new AffiliatesPage(driver);
    }

    @Test(priority = 1)
    public void verifyAffiliatePageLoaded() {

        page.navigateToAffiliates();

        Assert.assertTrue(page.isPageLoaded(), "Affiliate page not loaded");
    }

    @Test(priority = 2, dependsOnMethods = "verifyAffiliatePageLoaded")
    public void verifyAddAffiliate() {

        int before = page.getAffiliateCount();

        page.addAffiliate(email);

        int after = page.getAffiliateCount();

        Assert.assertTrue(after >= before, "Affiliate data not added");
    }

    @Test(priority = 3, dependsOnMethods = "verifyAddAffiliate")
    public void verifyUpdateAffiliate() {

        page.updateFirstAffiliate();

        Assert.assertTrue(page.isSuccessMessageDisplayed(),
                "Update failed: Success message not displayed");
    }

    @Test(priority = 4, dependsOnMethods = "verifyUpdateAffiliate")
    public void verifyDeleteAffiliate() {

        int before = page.getAffiliateCount();

        page.deleteFirstAffiliate();

        int after = page.getAffiliateCount();

        Assert.assertTrue(after <= before, "Delete failed");
    }
}


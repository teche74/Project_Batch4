package com.nopcommerce.promotions.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.nopcommerce.promotions.base.BaseTest;
import com.nopcommerce.promotions.pages.CampaignsPage;

public class CampaignsPageTest extends BaseTest {

    CampaignsPage page;

    String campaignName = "Campaign_" + System.currentTimeMillis();

    @BeforeClass
    public void initPage() {
        page = new CampaignsPage(driver);
    }

    @Test(priority = 1)
    public void verifyCampaignPageLoaded() throws InterruptedException{
        page.navigateToCampaigns();
        Assert.assertTrue(page.isPageLoaded(), "Campaign page not loaded");
    }

    @Test(priority = 2 , dependsOnMethods = "verifyCampaignPageLoaded")
    public void verifyAddCampaign() throws InterruptedException{
        int before = page.getCampaignCount();
        page.addCampaign(campaignName, "Test Subject", "Test Body");

        int after = page.getCampaignCount();

        Assert.assertTrue(after > before, "Campaign not added");
    }

    @Test(priority = 3 , dependsOnMethods = "verifyAddCampaign")
    public void verifyDeleteCampaign() {

        int before = page.getCampaignCount();

        page.deleteByName(campaignName);

        int after = page.getCampaignCount();

        System.out.println("after : " + after + " before : " + before);

        Assert.assertTrue(after < before, "Campaign not deleted");
    }

    @Test(priority = 4 , dependsOnMethods = "verifyDeleteCampaign")
    public void verifyDeleteLastCampaign() {

        int before = page.getCampaignCount();

        page.deleteLastCampaign();

        int after = page.getCampaignCount();

        Assert.assertTrue(after <= before, "Delete last failed");
    }
}
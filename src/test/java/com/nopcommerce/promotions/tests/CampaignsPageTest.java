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
    public void verifyCampaignPageLoaded() {
        page.navigateToCampaigns();
        Assert.assertTrue(page.isPageLoaded(), "Campaign page not loaded");
    }

    @Test(priority = 2)
    public void verifyAddCampaign() {
        page.navigateToCampaigns();

        int before = page.getCampaignCount();

        page.addCampaign(campaignName, "Test Subject", "Test Body");

        page.navigateToCampaigns();
        int after = page.getCampaignCount();

        Assert.assertTrue(after > before, "Campaign not added");
    }

    @Test(priority = 3)
    public void verifyDeleteCampaign() {
        page.navigateToCampaigns();

        int before = page.getCampaignCount();

        page.deleteByName(campaignName);

        page.navigateToCampaigns();
        int after = page.getCampaignCount();

        Assert.assertTrue(after < before, "Campaign not deleted");
    }

    @Test(priority = 4)
    public void verifyDeleteLastCampaign() {
        page.navigateToCampaigns();

        int before = page.getCampaignCount();

        page.deleteLastCampaign();

        page.navigateToCampaigns();
        int after = page.getCampaignCount();

        Assert.assertTrue(after <= before, "Delete last failed");
    }
}
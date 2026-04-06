package com.nopcommerce.promotions.tests;

import com.nopcommerce.promotions.base.BaseTest;
import com.nopcommerce.promotions.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class NewsletterModuleTest extends BaseTest {

    @Test
    public void testNewsletterModule() {
        PromotionsPage promo = new PromotionsPage(driver);
        NewsletterListPage list = promo.goToNewsletter();

        System.out.println("Current URL: " + driver.getCurrentUrl());
        Assert.assertTrue(list.isOnNewsletterPage(), "Navigation failed");

        list.search("test@example.com");

        Assert.assertTrue(list.isGridVisible(), "Search failed");


        NewsletterDetailsPage details = list.clickAddNew();

        String email = "test" + System.currentTimeMillis() + "@mail.com";
        details.enterEmail(email);
        details.clickSave();


        Assert.assertTrue(details.isSuccessMessageDisplayed(), "Save failed");

        // 🔙 Back to list
        list = promo.goToNewsletter();


        list.search(email);
        details = list.clickEdit();


        Assert.assertTrue(driver.getCurrentUrl().contains("Edit"), "Edit failed");


        details.deleteSubscriber();

        Assert.assertTrue(details.isSuccessMessageDisplayed(), "Delete failed");


        list = promo.goToNewsletter();


        list.exportCSV();


        File folder = new File("C:\\Users\\vanshaj.yadav\\Downloads");
        File[] files = folder.listFiles((dir, name) -> name.endsWith(".csv"));

        Assert.assertTrue(files != null && files.length > 0, "Export failed");


        list.importCSV("C:\\Users\\vanshaj.yadav\\Downloads\\newsletter_correct.csv");


        Assert.assertTrue(list.isSuccessMessageDisplayed(), "Import failed");

        System.out.println("🎉 ALL ASSERTIONS PASSED SUCCESSFULLY");
    }
}

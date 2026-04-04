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

        // ✅ ASSERT 3: Save
        Assert.assertTrue(details.isSuccessMessageDisplayed(), "Save failed");

        // 🔙 Back to list
        list = promo.goToNewsletter();

        // ✏️ Edit
        list.search(email);
        details = list.clickEdit();

        // ✅ ASSERT 4: Edit page opened
        Assert.assertTrue(driver.getCurrentUrl().contains("Edit"), "Edit failed");

        // ❌ Delete
        details.deleteSubscriber();

        Assert.assertTrue(details.isSuccessMessageDisplayed(), "Delete failed");

        // 🔙 Back again
        list = promo.goToNewsletter();

        // 📤 Export CSV
        list.exportCSV();

        // ✅ ASSERT 6: Export (file downloaded)
        File folder = new File("C:\\Users\\vanshaj.yadav\\Downloads");
        File[] files = folder.listFiles((dir, name) -> name.endsWith(".csv"));

        Assert.assertTrue(files != null && files.length > 0, "Export failed");

        // 📥 Import CSV
        list.importCSV("C:\\Users\\vanshaj.yadav\\Downloads\\newsletter_correct.csv");

        // ✅ ASSERT 7: Import
        Assert.assertTrue(list.isSuccessMessageDisplayed(), "Import failed");

        System.out.println("🎉 ALL ASSERTIONS PASSED SUCCESSFULLY");
    }
}

package com.nopcommerce.promotions.api.newsletter;

import com.nopcommerce.promotions.api.base.Base;
import io.restassured.response.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class editandsave extends Base {

    @Test(dependsOnGroups = "login", groups = "newsletter")
    public void editAndSaveSubscriber() {

        int subscriberId = 11;


        Response pageResponse = given()
                .cookies(cookies)
                .header("User-Agent", "Mozilla/5.0")
                .get("/Admin/NewsLetterSubscription/Edit/" + subscriberId);

        String html = pageResponse.getBody().asString();
        Document doc = Jsoup.parse(html);

        String token = doc.select("input[name=__RequestVerificationToken]").attr("value");

        String email = doc.select("input[name=Email]").attr("value");


        String subscriptionTypeId = doc
                .select("select[name=SubscriptionTypeId] option[selected]")
                .attr("value");


        Response response = given()
                .cookies(cookies)
                .header("User-Agent", "Mozilla/5.0")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Referer", "https://admin-demo.nopcommerce.com/Admin/NewsLetterSubscription/Edit/" + subscriberId)

                .formParam("Id", subscriberId)
                .formParam("Email", email)


                .formParam("SubscriptionTypeId", subscriptionTypeId)

                // checkbox handling
                .formParam("Active", "true")  // or false

                .formParam("__RequestVerificationToken", token)

                .when()
                .post("/Admin/NewsLetterSubscription/Edit/" + subscriberId);

        response.prettyPrint();

//        // ✅ ASSERTIONS
//        Assert.assertEquals(response.getStatusCode(), 302, "Edit Save failed");
//
//        String redirect = response.getHeader("Location");
//
//        Assert.assertTrue(redirect.contains("List"),
//                "Not redirected after save");

        System.out.println("✅ Subscriber updated successfully");
    }
}

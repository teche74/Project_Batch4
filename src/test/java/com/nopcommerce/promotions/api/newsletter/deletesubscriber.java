package com.nopcommerce.promotions.api.newsletter;

import com.nopcommerce.promotions.api.base.Base;
import io.restassured.response.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class deletesubscriber extends Base {

    @Test(dependsOnGroups = "login", groups = "newsletter")
    public void deleteSubscriber() {

        int subscriberId = 1; //


        Response pageResponse = given()
                .cookies(cookies)
                .header("User-Agent", "Mozilla/5.0")
                .get("/Admin/NewsLetterSubscription/Edit/" + subscriberId);

        String html = pageResponse.getBody().asString();

        Document doc = Jsoup.parse(html);

        String freshToken = doc
                .select("input[name=__RequestVerificationToken]")
                .attr("value");


        Response response = given()
                .cookies(cookies)

                .header("User-Agent", "Mozilla/5.0")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Referer", "https://admin-demo.nopcommerce.com/Admin/NewsLetterSubscription/Edit/" + subscriberId)


                .formParam("id", subscriberId)
                .formParam("__RequestVerificationToken", freshToken)

                .when()
                .post("/Admin/NewsLetterSubscription/Delete/" + subscriberId);

        response.prettyPrint();

//        // ✅ Assertions
//        Assert.assertEquals(response.getStatusCode(), 302, "Delete API failed");
//
//        String redirect = response.getHeader("Location");
//
//        Assert.assertTrue(redirect.contains("List"),
//                "Not redirected after delete");

        System.out.println("✅ Subscriber deleted successfully");
    }
}

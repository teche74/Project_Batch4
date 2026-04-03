package com.nopcommerce.promotions.api.subscriptionType;

import com.nopcommerce.promotions.api.base.Base;
import io.restassured.response.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.testng.annotations.Test;

public class DeleteSubscriptionTypeElement extends Base {
    @Test(dependsOnGroups = "createSubscription")
    public void deleteSubscriptionTypeElement(){
        Response adminPage = request.given()
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 Chrome/120 Safari/537.36")
                .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
                .header("Accept-Language", "en-US,en;q=0.9")
                .header("Connection", "keep-alive")
                .header("Upgrade-Insecure-Requests", "1")
                .get("/Admin/NewsLetterSubscriptionType/List");

        adminPage.prettyPrint();


        String adminHtml = adminPage.getBody().asString();

        Document doc = Jsoup.parse(adminHtml);

        token = doc
                .select("input[name=__RequestVerificationToken]")
                .attr("value");

        System.out.println("NEW TOKEN: " + token);

        request.given()
                .cookies(cookies)
                .contentType("application/x-www-form-urlencoded")
                .formParam("__RequestVerificationToken", token)
                .post("/Admin/NewsLetterSubscriptionType/Delete/11").then().statusCode(302);
    }

}

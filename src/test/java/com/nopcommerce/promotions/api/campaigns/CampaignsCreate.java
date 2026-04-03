package com.nopcommerce.promotions.api.campaigns;

import com.nopcommerce.promotions.api.base.Base;
import io.restassured.response.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.testng.annotations.Test;

public class CampaignsCreate extends Base {

    @Test(dependsOnGroups = "login" , groups = "createCampaigns")
    public void testCreateCampaign() {
        Response adminPage = request.given()
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 Chrome/120 Safari/537.36")
                .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
                .header("Accept-Language", "en-US,en;q=0.9")
                .header("Connection", "keep-alive")
                .header("Upgrade-Insecure-Requests", "1")
                .get("/Admin/Campaign/List");

//        adminPage.prettyPrint();


        String adminHtml = adminPage.getBody().asString();

        Document doc = Jsoup.parse(adminHtml);

        token = doc
                .select("input[name=__RequestVerificationToken]")
                .attr("value");

        System.out.println("NEW TOKEN: " + token);

        request.given()
                .cookies(cookies)
                .contentType("application/x-www-form-urlencoded")
                .formParam("save", "")
                .formParam("Id", "0")
                .formParam("Name", "Ram")
                .formParam("SelectedStoreIds", "1")
                .formParam("DisplayOrder", "12")
                .formParam("__Invariant", "DisplayOrder")
                .formParam("TickedByDefault", "true")
                .formParam("__RequestVerificationToken", token)
                .post("/Admin/NewsLetterSubscriptionType/Create").then().statusCode(302);
    }
}

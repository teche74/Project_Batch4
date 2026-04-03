package com.nopcommerce.promotions.api.subscriptionType;

import com.nopcommerce.promotions.api.base.Base;
import io.restassured.response.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.testng.annotations.Test;

public class CreateNewSubscriptionTypeList extends Base {

    @Test(dependsOnGroups = "login" , groups = "createSubscription")
    public void createNewSubscriptionTypeListTest(){

        Response adminPage = request.given()
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 Chrome/120 Safari/537.36")
                .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
                .header("Accept-Language", "en-US,en;q=0.9")
                .header("Connection", "keep-alive")
                .header("Upgrade-Insecure-Requests", "1")
                .get("/Admin/NewsLetterSubscriptionType/List");

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
                .formParam("Name", "Ujjwal")
                .formParam("Subject", "Test Boy")
                .formParam("Body", "Test Boy")
                .formParam("DontSendBeforeDate", "")
                .formParam("__Invariant", "DontSendBeforeDate")
                .formParam("StoreId", "0")
                .formParam("CustomerRoleId", "0")
                .formParam("NewsLetterSubscriptionTypeId", "0")
                .formParam("__RequestVerificationToken", token)
                .post("/Admin/Campaign/Create")
                .then()
                .statusCode(302);
    }
}

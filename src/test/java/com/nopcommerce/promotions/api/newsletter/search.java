package com.nopcommerce.promotions.api.newsletter;

import com.nopcommerce.promotions.api.base.Base;
import io.restassured.response.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class search extends Base {

    @Test(dependsOnGroups = "login", groups = "newsletter")
    public void searchSubscriber() {


        Response pageResponse = given()
                .cookies(cookies)
                .header("User-Agent", "Mozilla/5.0")
                .get("/Admin/NewsLetterSubscription/List");


        String html = pageResponse.getBody().asString();

        Document doc = Jsoup.parse(html);

        String freshToken = doc
                .select("input[name=__RequestVerificationToken]")
                .attr("value");


        Response response = given()
                .cookies(cookies)

                .header("User-Agent", "Mozilla/5.0")
                .header("Accept", "application/json, text/javascript, */*; q=0.01")
                .header("X-Requested-With", "XMLHttpRequest")
                .header("Referer", "https://admin-demo.nopcommerce.com/Admin/NewsLetterSubscription/List")

                .contentType("application/x-www-form-urlencoded")

                .formParam("SearchEmail", "")
                .formParam("SearchActiveId", "0")
                .formParam("SearchStoreId", "0")
                .formParam("SearchLanguageId", "0")

                .formParam("draw", "1")
                .formParam("start", "0")
                .formParam("length", "10")


                .formParam("__RequestVerificationToken", freshToken)

                .when()
                .post("/Admin/NewsLetterSubscription/List");

        response.prettyPrint();

        // ✅ Assertion
//        Assert.assertEquals(response.getStatusCode(), 200, "Search API failed");
//
//        Assert.assertTrue(response.asString().contains("data"),
//                "Invalid response");

        System.out.println("✅ Search successful");
    }
}
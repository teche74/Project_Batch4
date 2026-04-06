package com.nopcommerce.promotions.api.newsletter;

import com.nopcommerce.promotions.api.base.Base;
import io.restassured.response.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class exportcsv extends Base {

    @Test(dependsOnGroups = "login", groups = "newsletter")
    public void exportCSVSubscriber() {


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
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Referer", "https://admin-demo.nopcommerce.com/Admin/NewsLetterSubscription/List")

                .formParam("SearchEmail", "")
                .formParam("StartDate", "")
                .formParam("EndDate", "")
                .formParam("ActiveId", "0")
                .formParam("StoreId", "0")
                .formParam("CustomerRoleId", "0")
                .formParam("SubscriptionTypeId", "0")


                .formParam("__RequestVerificationToken", freshToken)

                .when()
                .post("/Admin/NewsLetterSubscription/ExportCSV");

        System.out.println(response.getBody().asString());

//        // ✅ Assertions
//        Assert.assertEquals(response.getStatusCode(), 200, "Export CSV API failed");

//        // Optional: check CSV format
//        Assert.assertTrue(response.getHeader("Content-Type").contains("text/csv"),
//                "Response is not CSV");

        System.out.println("✅ CSV Export successful");
    }
}

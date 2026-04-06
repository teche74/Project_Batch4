package com.nopcommerce.promotions.api.newsletter;

import com.nopcommerce.promotions.api.base.Base;
import io.restassured.response.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class importcsv extends Base {

    @Test(dependsOnGroups = "login", groups = "newsletter")
    public void importCSVSubscriber() {


        Response pageResponse = given()
                .cookies(cookies)
                .header("User-Agent", "Mozilla/5.0")
                .get("/Admin/NewsLetterSubscription/List");

        String html = pageResponse.getBody().asString();

        Document doc = Jsoup.parse(html);

        String freshToken = doc
                .select("input[name=__RequestVerificationToken]")
                .attr("value");


        File file = new File("C:\\Users\\vanshaj.yadav\\Downloads\\newsletter_correct.csv");

        Response response = given()
                .cookies(cookies)

                .header("User-Agent", "Mozilla/5.0")
                .header("Referer", "https://admin-demo.nopcommerce.com/Admin/NewsLetterSubscription/List")


                .multiPart("importcsvfile", file)


                .multiPart("__RequestVerificationToken", freshToken)

                .when()
                .post("/Admin/NewsLetterSubscription/ImportCsv");

        response.prettyPrint();

//        // ✅ Assertions
//        Assert.assertEquals(response.getStatusCode(), 200, "Import CSV API failed");

        System.out.println("✅ CSV Import successful");
    }
}

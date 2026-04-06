package com.nopcommerce.promotions.api.Affiliate;
import com.nopcommerce.promotions.api.base.Base;
import io.restassured.response.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class deleteAffiliate extends Base {

    @Test(dependsOnGroups = "login" , groups = "affiliate")
    public void deleteAffiliateTest() {


        String affiliateId = "1"; // change this

        Response page = given()
                .cookies(cookies)
                .get("/Admin/Affiliate/Edit/" + affiliateId);

        Document doc = Jsoup.parse(page.getBody().asString());

        token = doc.select("input[name=__RequestVerificationToken]")
                .attr("value");

        Response response = given()
                .cookies(cookies)
                .contentType("application/x-www-form-urlencoded")

                .formParam("Id", affiliateId)
                .formParam("delete", "")
                .formParam("__RequestVerificationToken", token)

                .post("/Admin/Affiliate/Edit/" + affiliateId);

        response.prettyPrint();

        Assert.assertEquals(response.getStatusCode(), 302,
                "Affiliate deletion failed");

        System.out.println(" Affiliate Deleted Successfully: ID = " + affiliateId);
    }
}

package com.nopcommerce.promotions.api.Affiliate;

import com.nopcommerce.promotions.api.base.Base;
import io.restassured.response.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class AffiliateCreate extends Base {

    @Test(dependsOnGroups = "login" , groups = "affiliate")
    public void addAffiliateTest() {

        Response page = given()
                .cookies(cookies)
                .get("/Admin/Affiliate/Create");

        Document doc = Jsoup.parse(page.getBody().asString());

        token = doc.select("input[name=__RequestVerificationToken]")
                .attr("value");


        String email = "test" + System.currentTimeMillis() + "@mail.com";

        Response response = given()
                .cookies(cookies)
                .contentType("application/x-www-form-urlencoded")

                .formParam("Id", "0")

                .formParam("Address.FirstName", "John")
                .formParam("Address.LastName", "Smith")
                .formParam("Address.Email", email)
                .formParam("Address.Company", "Test Company")

                .formParam("Address.CountryId", "237")
                .formParam("Address.StateProvinceId", "1791")
                .formParam("Address.County", "Albama")
                .formParam("Address.City", "NY")
                .formParam("Address.Address1", "Street 34")
                .formParam("Active", "true")
                .formParam("__RequestVerificationToken", token)
                .formParam("save", "")
                .post("/Admin/Affiliate/Create");

        response.prettyPrint();

        Assert.assertEquals(response.getStatusCode(), 302,
                "Affiliate creation failed");

        System.out.println("Affiliate Created Successfully: " + email);
    }
}

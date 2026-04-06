package com.nopcommerce.promotions.api.discounts;

import com.nopcommerce.promotions.api.base.Base;
import io.restassured.response.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.testng.annotations.Test;

public class discountCreate extends Base {

    @Test(dependsOnGroups = "login", groups = "createDiscounts")
    public void testCreateDiscount() {
        Response adminPage = request.given()
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64)")
                .get("/Admin/Discount/Create");

        Document doc = Jsoup.parse(adminPage.getBody().asString());
        String token = doc.select("input[name=__RequestVerificationToken]").attr("value");


        request.given()
                .cookies(cookies)
                .contentType("application/x-www-form-urlencoded")
                .formParam("Name", "Discount4")
                .formParam("DiscountTypeId", "1")
                .formParam("UsePercentage", "true")
                .formParam("DiscountPercentage", "15")
                .formParam("IsActive", "true")
                .formParam("__RequestVerificationToken", token)
                .post("/Admin/Discount/Create")
                .then()
                .statusCode(302);


        System.out.println("Discount Create Token: " + token);

    }
}

package com.nopcommerce.promotions.api.discounts;

import com.nopcommerce.promotions.api.base.Base;
import io.restassured.response.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.testng.annotations.Test;

public class discountDelete extends Base {

    @Test(dependsOnGroups = "createDiscounts")
    public void testDeleteDiscount() {
        Response adminPage = request.given()
                .header("User-Agent", "Mozilla/5.0")
                .get("/Admin/Discount/List");

        Document doc = Jsoup.parse(adminPage.getBody().asString());
        String token = doc.select("input[name=__RequestVerificationToken]").attr("value");

        request.given()
                .cookies(cookies)
                .contentType("application/x-www-form-urlencoded")
                .formParam("__RequestVerificationToken", token)
                .post("/Admin/Discount/Delete/1")
                .then()
                .statusCode(302);


        System.out.println("Discount Delete Token: " + token);

    }
}
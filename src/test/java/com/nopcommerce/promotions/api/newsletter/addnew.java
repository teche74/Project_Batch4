package com.nopcommerce.promotions.api.newsletter;

import com.nopcommerce.promotions.api.base.Base;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class addnew extends Base {

    @Test(dependsOnGroups = "login",groups = "newsletter")
    public void addnewfile() {

        String email = "test" + System.currentTimeMillis() + "@mail.com";

        Response response = given()
                .cookies(cookies)
                .contentType("application/x-www-form-urlencoded")
                .formParam("Email", email)
                .formParam("SelectedNewsLetterSubscriptionTypeId", "1")
                .formParam("SelectedNewsLetterSubscriptionStoreId", "1")
                .formParam("SelectedNewsLetterSubscriptionLanguageId", "1")
                .formParam("Active", "false")
                .formParam("__RequestVerificationToken", token)
                .post("/Admin/NewsLetterSubscription/Create");

        response.prettyPrint();

        Assert.assertEquals(response.getStatusCode(), 302,
                "Subscriber creation failed");

        System.out.println("✅ Subscriber Created Successfully: " + email);
    }
}
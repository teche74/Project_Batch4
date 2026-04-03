package com.nopcommerce.promotions.api.login;

import com.nopcommerce.promotions.api.base.Base;
import io.restassured.response.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class LoginApiTest extends Base {

    @Test(groups = "login")
    public void testLogin() {

        Response getResponse = request
                .given()
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 Chrome/120 Safari/537.36")
                .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
                .header("Accept-Language", "en-US,en;q=0.9")
                .header("Connection", "keep-alive")
                .header("Upgrade-Insecure-Requests", "1")
                .get("/login?returnurl=%2Fadmin%2F");

        String html = getResponse.getBody().asString();

        System.out.println("html length: " + html.length());

        Document doc = Jsoup.parse(html);

        String token = doc
                .select("input[name=__RequestVerificationToken]")
                .attr("value");

        System.out.println("TOKEN: " + token);

        cookies = getResponse.getCookies();

        System.out.println("COOKIES: " + cookies);

        Response postResponse = request
                .cookies(cookies)
                .contentType("application/x-www-form-urlencoded")
                .formParam("Email", "admin@yourstore.com")
                .formParam("Password", "admin")
                .formParam("RememberMe", "false")
                .formParam("__RequestVerificationToken", token)
                .formParam("returnurl", "/admin/")
                .when()
                .post("/login?returnurl=%2Fadmin%2F");

        System.out.println("Status Code: " + postResponse.getStatusCode());
        System.out.println("Redirect: " + postResponse.getHeader("Location"));

        String redirect = postResponse.getHeader("Location");

        Assert.assertNotNull(redirect, "Redirect is null. login failed");

        Assert.assertTrue(redirect.contains("/admin"), "Login failed");
    }
}
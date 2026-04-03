package com.nopcommerce.promotions.api.base;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;

import java.util.Map;

public class Base {

    protected RequestSpecification request;
    protected String token;
    protected static Map<String, String> cookies;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://admin-demo.nopcommerce.com";

        request = RestAssured
                .given();
    }
}
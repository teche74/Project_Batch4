package com.nopcommerce.promotions.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.nopcommerce.promotions.base.BasePage;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "Email")
    WebElement email;

    @FindBy(id = "Password")
    WebElement password;

    @FindBy(xpath = "//button[contains(text(),'Log in')]")
    WebElement loginBtn;

    public void login(String user, String pass) {
        type(email, user);
        type(password, pass);
        click(loginBtn);
    }
}
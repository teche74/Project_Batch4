package com.nopcommerce.promotions.pages;

import com.nopcommerce.promotions.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class DiscountPage extends BasePage {

    public DiscountPage(WebDriver driver) {
        super(driver);
    }


    @FindBy(id = "nopSideBarPusher")
    WebElement sideBarToggle;

    @FindBy(xpath = "//li[contains(@class,'nav-item')]//p[contains(text(),'Promotions')]")
    WebElement promotionsMenu;

    @FindBy(xpath = "//a[@href='/Admin/Discount/List']")
    WebElement discountsLink;

    @FindBy(xpath = "//h1[contains(normalize-space(),'Discounts')]")
    WebElement pageTitle;

    @FindBy(xpath = "//a[normalize-space()='Add new']")
    WebElement addNewButton;

    @FindBy(id = "Name")
    WebElement nameInput;

    @FindBy(xpath = "//button[@name='save']")
    WebElement saveButton;

    @FindBy(id = "discount-delete")
    WebElement deleteButton;

    @FindBy(xpath = "//button[contains(@class,'btn-danger') and @type='submit']")
    WebElement confirmDeleteButton;

    private By rowsLocator = By.xpath("//table[@id='discounts-grid']//tbody/tr");

    private void jsClick(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public void navigateToDiscounts() {
        wait.until(ExpectedConditions.elementToBeClickable(sideBarToggle));
        click(sideBarToggle);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[contains(text(),'Promotions')]")));
        jsClick(promotionsMenu);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@href='/Admin/Discount/List']")));
        jsClick(discountsLink);

        wait.until(ExpectedConditions.visibilityOf(pageTitle));
    }

    public boolean isPageLoaded() {
        return isDisplayed(pageTitle);
    }

    public int getDiscountCount() {
        wait.until(ExpectedConditions.presenceOfElementLocated(rowsLocator));
        return driver.findElements(rowsLocator).size();
    }

    public void addDiscount(String name) {
        click(addNewButton);
        wait.until(ExpectedConditions.visibilityOf(nameInput));
        type(nameInput, name);
        click(saveButton);
        wait.until(ExpectedConditions.visibilityOf(addNewButton));
    }

    public void deleteByName(String name) {
        List<WebElement> rows = driver.findElements(rowsLocator);
        for (WebElement row : rows) {
            if (row.getText().contains(name)) {
                WebElement editBtn = row.findElement(By.xpath(".//a[contains(@href, 'Edit')]"));
                click(editBtn);

                wait.until(ExpectedConditions.elementToBeClickable(deleteButton));
                click(deleteButton);

                wait.until(ExpectedConditions.visibilityOf(confirmDeleteButton));
                click(confirmDeleteButton);
                break;
            }
        }
    }
}

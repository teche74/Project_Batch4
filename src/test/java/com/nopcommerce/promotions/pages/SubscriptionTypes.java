package com.nopcommerce.promotions.pages;

import java.time.Duration;
import java.util.List;

import com.nopcommerce.promotions.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SubscriptionTypes extends BasePage {

    WebDriverWait wait;

    public SubscriptionTypes(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @FindBy(xpath = "//a[@id='nopSideBarPusher']")
    WebElement sideBarNavigation;

    @FindBy(xpath = "//p[normalize-space()='Promotions']")
    WebElement promotionsMenu;

    @FindBy(xpath = "//p[normalize-space()='Subscription types']")
    WebElement subscriptionTypesSection;

    @FindBy(xpath = "//h1[normalize-space()='Subscription types']")
    WebElement pageTitle;

    @FindBy(xpath = "//a[normalize-space()='Add new']")
    WebElement addNewButton;

    @FindBy(xpath = "//table[@id='subscriptiontypes-grid']//tbody//tr")
    List<WebElement> rows;

    @FindBy(id = "Name")
    WebElement subscriptionNameInput;

    @FindBy(id = "DisplayOrder")
    WebElement displayOrderInput;

    @FindBy(id = "TickedByDefault")
    WebElement tickedByDefaultCheckbox;

    @FindBy(xpath = "//button[@name='save']")
    WebElement saveButton;

    @FindBy(xpath = "//span[@role='combobox']")
    WebElement storeDropdown;

    @FindBy(xpath = "//input[@class='select2-search__field']")
    WebElement searchInput;

    @FindBy(xpath = "//li[contains(text(),'nopCommerce admin demo store')]")
    WebElement storeOption;

    @FindBy(xpath = "//span[normalize-space()='Delete']")
    WebElement deleteButton;

    @FindBy(xpath = "//button[normalize-space()='Delete']")
    WebElement confirmDeleteButton;


    public void navigateToSubscriptionTypes() {

        if (driver.getCurrentUrl().contains("SubscriptionType/List")) {
            wait.until(ExpectedConditions.visibilityOf(pageTitle));
            return;
        }

        wait.until(ExpectedConditions.visibilityOf(promotionsMenu));

        WebElement parentMenu = promotionsMenu.findElement(By.xpath("./ancestor::li"));

        String classes = parentMenu.getAttribute("class");

        if (!classes.contains("menu-open")) {
            click(promotionsMenu);
        }

        wait.until(ExpectedConditions.visibilityOf(subscriptionTypesSection));
        click(subscriptionTypesSection);

        wait.until(ExpectedConditions.visibilityOf(pageTitle));
    }


    public boolean isPageLoaded() {
        return isDisplayed(pageTitle);
    }

    public int getRowCount() {
        return rows.size();
    }

    public void addEntry(String name, String displayOrder) {
        click(addNewButton);

        wait.until(ExpectedConditions.visibilityOf(subscriptionNameInput));

        type(subscriptionNameInput, name);

        selectLimitedStoreOption();

        type(displayOrderInput, displayOrder);

        click(tickedByDefaultCheckbox);

        click(saveButton);

    }

    public void selectLimitedStoreOption() {
        click(storeDropdown);
        wait.until(ExpectedConditions.visibilityOf(searchInput));
        type(searchInput, "nopCommerce admin demo store");
        click(storeOption);
    }

    public void deleteLastEntry() {

        if (rows.size() == 0) {
            System.out.println("No records found to delete");
            return;
        }

        WebElement lastRow = rows.get(rows.size() - 1);

        WebElement editButton = lastRow.findElement(
                By.xpath(".//a[contains(@href,'Edit')]")
        );

        click(editButton);

        wait.until(ExpectedConditions.visibilityOf(deleteButton));
        click(deleteButton);

        wait.until(ExpectedConditions.visibilityOf(confirmDeleteButton));
        click(confirmDeleteButton);

    }

    public void deleteByName(String name) {

        for (WebElement row : rows) {

            if (row.getText().contains(name)) {

                WebElement editButton = row.findElement(
                        By.xpath(".//a[contains(@href,'Edit')]")
                );

                click(editButton);

                wait.until(ExpectedConditions.visibilityOf(deleteButton));
                click(deleteButton);

                wait.until(ExpectedConditions.visibilityOf(confirmDeleteButton));
                click(confirmDeleteButton);

                break;
            }
        }
    }
}
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

public class CampaignsPage extends BasePage {

    WebDriverWait wait;

    public CampaignsPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @FindBy(xpath = "//p[normalize-space()='Promotions']")
    WebElement promotionsMenu;

    @FindBy(xpath = "//p[normalize-space()='Campaigns']")
    WebElement campaignsSection;

    @FindBy(xpath = "//h1[normalize-space()='Campaigns']")
    WebElement pageTitle;

    @FindBy(xpath = "//a[normalize-space()='Add new']")
    WebElement addNewButton;

    @FindBy(id = "campaigns-grid")
    WebElement campaignsGrid;

    @FindBy(xpath = "//table[@id='campaigns-grid']//tbody//tr")
    List<WebElement> rows;

    @FindBy(id = "Name")
    WebElement nameInput;

    @FindBy(id = "Subject")
    WebElement subjectInput;

    @FindBy(id = "Body")
    WebElement bodyIframe;

    @FindBy(xpath = "//button[@name='save']")
    WebElement saveButton;

    @FindBy(xpath = "//span[normalize-space()='Delete']")
    WebElement deleteButton;

    @FindBy(xpath = "//button[normalize-space()='Delete']")
    WebElement confirmDeleteButton;

    public void navigateToCampaigns() {
        click(promotionsMenu);
        wait.until(ExpectedConditions.visibilityOf(campaignsSection));
        click(campaignsSection);
        wait.until(ExpectedConditions.visibilityOf(pageTitle));
    }

    public boolean isPageLoaded() {
        return isDisplayed(pageTitle);
    }

    public int getCampaignCount() {
        return rows.size();
    }

    public void clickAddNew() {
        click(addNewButton);
        wait.until(ExpectedConditions.visibilityOf(nameInput));
    }

    public void enterCampaignName(String name) {
        type(nameInput, name);
    }

    public void enterSubject(String subject) {
        type(subjectInput, subject);
    }

    public void enterBody(String text) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(bodyIframe));
        driver.findElement(By.tagName("body")).clear();
        driver.findElement(By.tagName("body")).sendKeys(text);
        driver.switchTo().defaultContent();
    }

    public void clickSave() {
        click(saveButton);
        wait.until(ExpectedConditions.visibilityOf(pageTitle));
    }

    public void addCampaign(String name, String subject, String body) {
        clickAddNew();
        enterCampaignName(name);
        enterSubject(subject);
        enterBody(body);
        clickSave();
    }

    public void deleteLastCampaign() {
        if (rows.size() == 0) return;

        WebElement lastRow = rows.get(rows.size() - 1);

        WebElement editBtn = lastRow.findElement(
                By.xpath(".//a[contains(@href,'Edit')]")
        );

        click(editBtn);

        wait.until(ExpectedConditions.visibilityOf(deleteButton));
        click(deleteButton);

        wait.until(ExpectedConditions.visibilityOf(confirmDeleteButton));
        click(confirmDeleteButton);

        wait.until(ExpectedConditions.visibilityOf(pageTitle));
    }

    public void deleteByName(String name) {
        for (WebElement row : rows) {
            if (row.getText().contains(name)) {

                WebElement editBtn = row.findElement(
                        By.xpath(".//a[contains(@href,'Edit')]")
                );

                click(editBtn);

                wait.until(ExpectedConditions.visibilityOf(deleteButton));
                click(deleteButton);

                wait.until(ExpectedConditions.visibilityOf(confirmDeleteButton));
                click(confirmDeleteButton);

                wait.until(ExpectedConditions.visibilityOf(pageTitle));
                break;
            }
        }
    }
}
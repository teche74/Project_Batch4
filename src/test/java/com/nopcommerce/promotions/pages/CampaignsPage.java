package com.nopcommerce.promotions.pages;

import java.time.Duration;
import java.util.List;

import com.nopcommerce.promotions.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CampaignsPage extends BasePage {

    public CampaignsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[@id='nopSideBarPusher']")
    WebElement sideBarNavigation;

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

    @FindBy(id = "Name")
    WebElement nameInput;

    @FindBy(id = "Subject")
    WebElement subjectInput;

    @FindBy(id = "Body")
    WebElement bodyInput;

    @FindBy(xpath = "//button[@name='save']")
    WebElement saveButton;

    @FindBy(xpath = "//span[normalize-space()='Delete']")
    WebElement deleteButton;

    @FindBy(xpath = "//button[normalize-space()='Delete']")
    WebElement confirmDeleteButton;

    private By rowsLocator = By.xpath("//table[@id='campaigns-grid']//tbody//tr");

    public void navigateToCampaigns() {
        hover(sideBarNavigation);
        click(promotionsMenu);
        wait.until(ExpectedConditions.visibilityOf(campaignsSection));
        click(campaignsSection);
    }

    public boolean isPageLoaded() {
        return isDisplayed(pageTitle);
    }


    public int getCampaignCount() {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(rowsLocator));
        return driver.findElements(rowsLocator).size();
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
        type(bodyInput, text);
    }

    public void clickSave() {
        click(saveButton);
        wait.until(ExpectedConditions.visibilityOf(campaignsGrid));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(rowsLocator));
    }

    public void addCampaign(String name, String subject, String body) {
        clickAddNew();
        enterCampaignName(name);
        enterSubject(subject);
        enterBody(body);
        clickSave();
    }

    public void deleteLastCampaign() {
        List<WebElement> rows = driver.findElements(rowsLocator);
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
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(rowsLocator));
    }

    public void deleteByName(String name) {
        List<WebElement> rows = driver.findElements(rowsLocator);

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
                wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(rowsLocator));
                break;
            }
        }
    }
}
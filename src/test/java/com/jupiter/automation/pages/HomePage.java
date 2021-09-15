package com.jupiter.automation.pages;

import com.framework.codebase.CodeBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;


public class HomePage extends CodeBase {
    WebDriver driver;

    @CacheLookup
    @FindBy(xpath = "//li[@id='nav-contact']")
    private WebElement lnk_contact;

    @CacheLookup
    @FindBy(how = How.XPATH, using = "//a[text()='Submit']")
    private WebElement btn_submit;

    @CacheLookup
    @FindBy(how = How.XPATH, using = "//Strong[text()='We welcome your feedback']")
    private WebElement lbl_Welcome;

    @CacheLookup
    @FindBy(how = How.XPATH, using = "//span[text()='Forename is required']")
    private  WebElement lbl_ForeNameError;

    @CacheLookup
    @FindBy(how = How.XPATH, using = "//span[@id='email-err']")
    private WebElement lbl_EmailError;

    @CacheLookup
    @FindBy(how = How.XPATH, using = "//span[@id='message-err']")
    private WebElement lbl_MessageError;

    @CacheLookup
    @FindBy(xpath = "//input[@id='forename']")
     private WebElement txt_forename;

    @CacheLookup
    @FindBy(xpath = "//input[@id='surname']")
     private WebElement txt_surname;

    @CacheLookup
    @FindBy(xpath = "//li[@id='nav-shop']/a")
    private WebElement lnk_shop;


    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void openGoogleWeb(String url) {
        openUrl(url);
    }

    public void clickOnContactLink() {
        isDisplayed(lnk_contact);
        click(lnk_contact);
        writeToReport(">> Clicked Contact link");
    }

    public void verifyErrorMessages(){
        wait(4);
        click(btn_submit);
        isDisplayed(lbl_Welcome);
        writeToReport(">> Verified Welcome Message");
        isDisplayed(lbl_ForeNameError);
        writeToReport(">> Verified Forename Error");
        isDisplayed(lbl_EmailError);
        writeToReport(">> Verified Email Error");
        isDisplayed(lbl_MessageError);
    }

    public void navigateToShopPage(){
        click(lnk_shop);
    }

}

package com.jupiter.automation.pages;

import com.framework.codebase.CodeBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ContactPage extends CodeBase {

    WebDriver driver;

    @CacheLookup
    @FindBy(xpath = "//input[@id='forename']")
    private WebElement txt_forename;

    @CacheLookup
    @FindBy(xpath = "//input[@id='surname']")
    private WebElement txt_surname;

    @CacheLookup
    @FindBy(xpath = "//input[@id='email']")
    private WebElement txt_email;

    @CacheLookup
    @FindBy(xpath = "//input[@id='telephone']")
    private WebElement txt_telephone;

    @CacheLookup
    @FindBy(xpath = "//textarea[@id='message']")
    private WebElement txt_message;

    @CacheLookup
    @FindBy(xpath = "//a[text()='Submit']")
    private WebElement btn_submit;

    @CacheLookup
    @FindBy(xpath = "//div[@class='alert alert-success']")
    private WebElement lbl_success;

    @CacheLookup
    @FindBy(how = How.XPATH, using = "//span[text()='Forename is required']")
    WebElement lbl_ForeNameError;

    @CacheLookup
    @FindBy(how = How.XPATH, using = "//span[@id='email-err']")
    WebElement lbl_EmailError;

    @CacheLookup
    @FindBy(how = How.XPATH, using = "//span[@id='message-err']")
    WebElement lbl_MessageError;

    public ContactPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public  void fillContactDetails(String name,String surname,String email,String mobilenumber,String message) throws InterruptedException {
        type(txt_forename,name);
        type(txt_surname,surname);
        type(txt_email,email);
        type(txt_telephone,mobilenumber);
        type(txt_message,message);
        wait(3);
        click(btn_submit);
        wait(lbl_success);
        isDisplayed(lbl_success);
        writeToReport(">> Verified Success Message");
    }

    public  void fillContactDetailsMandatoryOnly(String name,String email,String message) throws InterruptedException {
        type(txt_forename,name);
        type(txt_email,email);
        type(txt_message,message);
        boolean emailstate=isDisplayedBoolen(lbl_EmailError);
        if (emailstate==true){
           fail("Fail");
        }else {
            writeToReport("Not Display Error Message");
        }

        boolean ForeNameState=isDisplayedBoolen(lbl_ForeNameError);
        if (ForeNameState==true){
            fail("Fail");
        }else {
            writeToReport("Not Display Error Message");
        }

        boolean MessageState=isDisplayedBoolen(lbl_MessageError);
        if (MessageState==true){
            fail("Fail");
        }else {
            writeToReport("Not Display Error Message");
        }

        writeToReport(">> Verified Success Message");
    }
}

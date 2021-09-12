 package com.jupiter.automation.testsuites;

import com.framework.codebase.Initializer;
import com.jupiter.automation.pages.ContactPage;
import com.jupiter.automation.pages.HomePage;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class Test1 extends Initializer {

    /**
     * @param url
     *
     * Description: Verify error messages after submitting without entering mandatory fields and after entering mandatory fields not display error messages
     */
    @Test(dataProvider="checkErrorMessages")
    public void Test1(String url,String name,String email,String message) throws InterruptedException {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.openGoogleWeb(url);
        homePage.clickOnContactLink();
        homePage.verifyErrorMessages();
        ContactPage contactePage = PageFactory.initElements(driver, ContactPage.class);
        contactePage.fillContactDetailsMandatoryOnly(name,email,message);
    }

    @DataProvider(name="checkErrorMessages")
    public Object[][] getDataFromDataprovider(){
        return new Object[][]
                {
                        { "http://jupiter.cloud.planittesting.com","David","darshana1@gmail.com","JupitorTest1"}
                };
    }

}

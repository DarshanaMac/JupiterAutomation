package com.jupiter.automation.testsuites;

import com.framework.codebase.Initializer;
import com.jupiter.automation.pages.HomePage;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class Test1 extends Initializer {

    /**
     * @param url
     *
     * Description: Verify error messages after submitting without entering mandatory fields
     */
    @Test(dataProvider="checkErrorMessages")
    public void Test1(String url) {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.openGoogleWeb(url);
        homePage.clickOnContactLink();
        homePage.verifyErrorMessages();
    }

    @DataProvider(name="checkErrorMessages")
    public Object[][] getDataFromDataprovider(){
        return new Object[][]
                {
                        { "http://jupiter.cloud.planittesting.com"}
                };
    }

}
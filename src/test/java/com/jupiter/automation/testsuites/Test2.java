package com.jupiter.automation.testsuites;

import com.framework.codebase.Initializer;
import com.jupiter.automation.pages.ContactPage;
import com.jupiter.automation.pages.HomePage;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class Test2 extends Initializer {

    /**
     *
     *
     * @param url
     * @param name
     * @param surname
     * @param email
     * @param mobilenumber
     * @param message
     * @throws InterruptedException
     *
     * Description: Submit contact details with valid details
     *
     */

    @Test(dataProvider="contactDetails")
    public void Test2(String url, String name,String surname,String email,String mobilenumber,String message) throws InterruptedException {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.openGoogleWeb(url);
        homePage.clickOnContactLink();
        ContactPage contactPage=PageFactory.initElements(this.driver, ContactPage.class);
        contactPage.fillContactDetails(name,surname,email,mobilenumber,message);
    }

    @DataProvider(name="contactDetails")
    public Object[][] getDataFromDataprovider(){
        return new Object[][]
                {
                        { "http://jupiter.cloud.planittesting.com", "David","Joy","darshana1@gmail.com","711542537","JupitorTest1"},
                        { "http://jupiter.cloud.planittesting.com", "Mak1","Ken1","darshana2@gmail.com","721542400","JupitorTest2" },
                        { "http://jupiter.cloud.planittesting.com", "MaK2","Ken3","darshana3@gmail.com","721542401","JupitorTest3" },
                        { "http://jupiter.cloud.planittesting.com", "MaK3","Ken3","darshana4@gmail.com","721542402","JupitorTest4" },
                        { "http://jupiter.cloud.planittesting.com", "MaK4","Ken4","darshana5@gmail.com","721542403","JupitorTest5" }
                };
    }
}

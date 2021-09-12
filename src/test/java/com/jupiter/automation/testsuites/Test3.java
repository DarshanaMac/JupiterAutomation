package com.jupiter.automation.testsuites;

import com.framework.codebase.Initializer;
import com.jupiter.automation.pages.HomePage;
import com.jupiter.automation.pages.ShopPage;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Test3 extends Initializer {

    /**
     *
     *
     * @param url
     * @param product1
     * @param product2
     * @throws InterruptedException
     *
     * Description: Add items to cart and verify added items in cart
     */

    @Test(dataProvider="products")
    public void Test3(String url,String product1,String product2) throws InterruptedException {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.openGoogleWeb(url);
        homePage.navigateToShopPage();
        ShopPage shopPage = PageFactory.initElements(driver, ShopPage.class);
        shopPage.addItemsToCart(product1,product2);
        shopPage.verifyCartItems(product1,product2);
    }

    @DataProvider(name="products")
    public Object[][] getDataFromDataprovider(){
        return new Object[][]
                {
                        {"http://jupiter.cloud.planittesting.com","Funny Cow=2","Fluffy Bunny=1"}
                };
    }
}

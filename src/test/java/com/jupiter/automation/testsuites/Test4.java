package com.jupiter.automation.testsuites;

import com.framework.codebase.Initializer;
import com.jupiter.automation.pages.HomePage;
import com.jupiter.automation.pages.ShopPage;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Test4  extends Initializer {

    /**
     *
     *
     * @param url
     * @param products
     * @param prices
     * @throws InterruptedException
     *
     * Description: Buy specific products and verify subtotal and total
     */

    @Test(dataProvider="BuyProducts")
    public void Test4(String url,String products,String prices) {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.openGoogleWeb(url);
        homePage.navigateToShopPage();
        ShopPage shopPage = PageFactory.initElements(driver, ShopPage.class);
        shopPage.splitProductsFromList(products);
        shopPage.verifySubTotal(prices);
        shopPage.resolveProductNamesAndUnitPrice(products,prices);
    }

    @DataProvider(name="BuyProducts")
    public Object[][] getDataFromDataprovider(){
        return new Object[][]
                {
                        { "http://jupiter.cloud.planittesting.com","Stuffed Frog=2,Fluffy Bunny=5,Valentine Bear=3","10.99=2,9.99=5,14.99=3"}
                };
    }

}

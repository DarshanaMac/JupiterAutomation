package com.jupiter.automation.pages;

import com.aventstack.extentreports.Status;
import com.framework.codebase.CodeBase;
import com.jupiter.automation.testsuites.Test4;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;

public class ShopPage extends CodeBase {

    WebDriver driver;

    @CacheLookup
    @FindBy(xpath = "//input[@id='forename']")
    private WebElement txt_forename;

    @CacheLookup
    @FindBy(xpath = "//li[@id='nav-cart']")
    private WebElement btn_cart;

    @CacheLookup
    @FindBy(xpath = "//strong[contains(text(),'Total:')]")
    private WebElement lbl_total;


    public ShopPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    String productname;
    int productcount;
    String productname2;
    int productcount2;

    public void addItemsToCart(String product1,String product2) throws InterruptedException {
        Thread.sleep(5000);
        productname=getItem(product1);
        productcount=getItemCount(product1);

        for (int i=1;i<=productcount;i++){
            click(resolveProduct(productname));
        }

        productname2=getItem(product2);
        productcount2=getItemCount(product2);

        for (int i=1;i<=productcount2;i++){
            click(resolveProduct(productname2));
        }
    }

    public void verifyCartItems(String product1,String product2) throws InterruptedException {
        click(btn_cart);
        productname=getItem(product1);
        productname2=getItem(product2);
        isDisplayed(resolveProductInCart(productname));
        isDisplayed(resolveProductInCart(productname2));
    }

    private WebElement resolveProduct(String items){
        wait(6);
        WebElement element=driver.findElement(By.xpath("//h4[text()='"+items+"']/..//a"));
        return element;
    }

    private WebElement resolveProductInCart(String items) throws InterruptedException {
        Thread.sleep(5000);
        WebElement element=driver.findElement(By.xpath("//*[text()=' " +items+"']"));
        return element;
    }

    private String getItem(String items){
        String[] parts = items.split("=");
        String part1 = parts[0];
        return part1;
    }

    private int getItemCount(String items){
        String[] parts = items.split("=");
        String part2 = parts[1];
        int count = Integer.parseInt(part2);
        return count;
    }

    public void splitProductsFromList(String items) {
        List<String> itemsArray = Arrays.asList(items.split(","));
        for (String itemWithQty : itemsArray) {
            String productname = itemWithQty.split("=")[0];
            String qtyString = itemWithQty.split("=")[1];
            for (int i = 0; i < Integer.parseInt(qtyString); i++) {
                click(resolveProduct(productname));
            }
        }
        writeToReport(">> Added Products");
    }

    public void verifySubTotal(String prices) {
        double total=0.0;
        click(btn_cart);
        List<String> itemPrices = Arrays.asList(prices.split(","));
        for (String itemCost : itemPrices) {
            String perunitcost = itemCost.split("=")[0];
            int qty = Integer.parseInt(itemCost.split("=")[1]);
            double subtotal = Double.parseDouble(perunitcost) * qty;
            total=total+subtotal;
        }

        System.out.println("Total - "+total);
        writeToReport(">> Total : "+total);
        validateTotal(total);
    }

    private void validateTotal(double total){
        wait(5);
        WebElement element=driver.findElement(By.xpath("//strong[contains(text(),'"+total+"')]"));
        isDisplayed(element);
        String actualTotal=lbl_total.getText();
        System.out.println("Total - "+total);
        Assert.assertEquals(actualTotal,"Total: "+total);
    }

    public void resolveProductNamesAndUnitPrice(String items,String unitprice){
        String productname=null;
        String perunitcost=null;
        List<String> itemsArray = Arrays.asList(items.split(","));
        for (String itemWithQty : itemsArray) {
             productname = itemWithQty.split("=")[0];
            WebElement element=driver.findElement(By.xpath("//*[text()=' "+productname+"']"));
            isDisplayed(element);
            checkUnitPrice(unitprice);
        }
    }

    private void checkUnitPrice(String unitprice){
        List<String> itemPrices = Arrays.asList(unitprice.split(","));
        for (String itemCost : itemPrices) {
            unitprice  = itemCost.split("=")[0];
            wait(6);
            WebElement element=driver.findElement(By.xpath("//*[contains(text(),'"+unitprice+"')]"));
            isDisplayed(element);
        }
    }


}

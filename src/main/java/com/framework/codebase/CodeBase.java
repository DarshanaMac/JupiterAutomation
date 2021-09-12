package com.framework.codebase;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


public class CodeBase extends Initializer {

    //default wait time.
    static int default_timeout = 20;

    public void openUrl(String url) {
        driver.navigate().to(url);
        test1.log(Status.PASS, url);
    }

    /**
     @param element : Web element

     Function: Wait until given element is displayed

     */
    public void wait(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, default_timeout);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     *
     * @param element
     *
     * Function : If the element is displayed,then the value returned is true. If not, then the value returned is false.
     *
     */

    public void isDisplayed(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, default_timeout);
            wait.until(ExpectedConditions.visibilityOf(element));
            test1.log(Status.PASS, "ELEMENT DISPLAYED ");
        }catch (Exception e){
            test1.log(Status.FAIL, "ELEMENT NOT DISPLAYED " + element.toString());
            extent.flush();
        }
    }

    /**
     *
     * @param element
     *
     * Function: Click on web element
     */

    public void click(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, default_timeout);
            WebElement obj = wait.until(ExpectedConditions.visibilityOf(element));
            element.click();
            test1.log(Status.PASS, "ELEMENT CLICKED ");
        }catch (Exception e){
            test1.log(Status.FAIL, "CLICK FAIL: " + element.toString());
            extent.flush();
        }
    }

    /**
     *
     *
     * @param element
     * @param text
     *
     * Function: Type content into an editable field
     */

    public void type(WebElement element, String text) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, default_timeout);
            WebElement obj = wait.until(ExpectedConditions.visibilityOf(element));
            obj.sendKeys(text);
            test1.log(Status.PASS, "TYPE : "+ text);
        }catch (Exception e){
            test1.log(Status.FAIL, "TYPE FAIL: " + element.toString());
            extent.flush();
        }
    }

    /**
     *
     * @param second
     */
    public void wait(int second){
        driver.manage().timeouts().implicitlyWait(second, TimeUnit.SECONDS);
    }

    /**
     *
     * @param message
     *
     * Function: Write message in report
     */
    public void writeToReport(String message){
        test1.log(Status.INFO,message);
    }

}

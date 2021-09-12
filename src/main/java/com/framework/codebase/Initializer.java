package com.framework.codebase;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.io.File;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class Initializer {

    public static WebDriver driver;
    static File file;

   static ExtentSparkReporter htmlReporter;
    static ExtentReports extent = new ExtentReports();
    static ExtentTest test1;

    /**
     * Generate Report
     *
     */
    public static void startReport() {
        String filepath = file.getAbsolutePath();
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        htmlReporter = new ExtentSparkReporter(filepath + "\\JupitorToy_"+timeStamp+".html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @BeforeTest
    public void genReport(){
        startReport();
    }

    @BeforeMethod
    public void createtest(Method method){
        test1 = extent.createTest(method.getName(), "test to validate search box");
    }

    @BeforeSuite
    public void config() {

        file = new File("");
    }

    /**
     * Create Browser instance
     */
    @BeforeSuite
    public static void init() {
        System.setProperty("webdriver.chrome.driver", file.getAbsolutePath() + File.separator + "Lib" + File.separator + "chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterTest
    public void down()
    {
        driver.quit();
    }

    @AfterMethod
    public void flushReport(){
        extent.flush();


    }


}

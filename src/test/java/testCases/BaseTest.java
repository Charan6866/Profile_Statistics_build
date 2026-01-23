package testCases;

import com.aventstack.extentreports.ExtentTest;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Optional;
import pageObjects.Homepage;
import pageObjects.PortfolioPage;

import java.io.*;
import java.time.Duration;
import java.util.Properties;


public class BaseTest {
protected WebDriver driver;
protected Logger logger;
protected Homepage homepage;
protected PortfolioPage portfolioPage;
protected static Properties properties;
public ExtentTest test;
public WebDriverWait wait;
@BeforeClass(alwaysRun = true)
@Parameters({"browser"})
public void setup(@Optional("chrome") String browser) throws IOException {
    logger = LogManager.getLogger(this.getClass());
    // initialize driver based on requested browser
    String b = browser==null?"chrome":browser.toLowerCase();
    switch(b){
        case "edge":
            io.github.bonigarcia.wdm.WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
            break;
        case "firefox":
            io.github.bonigarcia.wdm.WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            break;
        case "chrome":
        default:
            io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            break;
    }
    driver.manage().deleteAllCookies();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    properties = new Properties();
    FileReader file=new FileReader("src/test/resources/config.properties");
    properties.load(file);
    driver.get(properties.getProperty("url"));
    driver.manage().window().maximize();
    wait=new WebDriverWait(driver,Duration.ofSeconds(3));
    homepage = new Homepage(driver);
    portfolioPage = new PortfolioPage(driver);
}
//    @BeforeMethod
//    public void initpages(){
//
//    }

@AfterClass(alwaysRun = true)
    public void tearDown(){
    driver.quit();
}
    @Attachment(value = "Screenshot", type = "image/png", fileExtension = "png")
    public byte[] takeScreenshotForAllure(){
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
    public String Takescreenshot(String ssName){
    String date=new java.text.SimpleDateFormat("yyyyMMddhhmmss").format(new java.util.Date());
    TakesScreenshot ts=(TakesScreenshot) driver;
    File sou=ts.getScreenshotAs(OutputType.FILE);
    String des=System.getProperty("user.dir")+"/screenshots/" + ssName + "_" + date + ".png";
    File finaldes=new File(des);
    try{
        FileUtils.copyFile(sou,finaldes);
        System.out.println("ScreenShot saved: "+des);
        if(test!=null){
            test.addScreenCaptureFromPath(des);
        }
    }
    catch (Exception e){
        e.printStackTrace();
    }
    return des;

}
}

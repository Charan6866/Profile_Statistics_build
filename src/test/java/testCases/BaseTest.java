package testCases;

import com.aventstack.extentreports.ExtentTest;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import pageObjects.Homepage;
import pageObjects.PortfolioPage;

import java.io.*;
import java.net.URL;
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
    @Parameters({"os","browser"})
    public void setup(String os, String browser) throws IOException {
        logger = LogManager.getLogger(this.getClass());
        // initialize driver based on requested browser
        FileReader file = new FileReader("./src//test//resources//config.properties");
        properties = new Properties();
        properties.load(file);

        if (properties.getProperty("execution_env").equalsIgnoreCase("remote")) {
            DesiredCapabilities capabilities = new DesiredCapabilities();

            //os
            if (os.equalsIgnoreCase("windows")) {
                capabilities.setPlatform(Platform.WIN11);
            } else if (os.equalsIgnoreCase("mac")) {
                capabilities.setPlatform(Platform.MAC);
            } else {
                System.out.println("No matching os");
                return;
            }

            //browser
            switch (browser.toLowerCase()) {
                case "chrome":
                    capabilities.setBrowserName("chrome");
                    break;
                case "edge":
                    capabilities.setBrowserName("MicrosoftEdge");
                    break;
                default:
                    System.out.println("No matching browser");
                    return;
            }

            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
        }


        if (properties.getProperty("execution_env").equalsIgnoreCase("local")) {

            switch (browser.toLowerCase()) {
                case "chrome":
                    driver = new ChromeDriver();
                    break;
                case "edge":
                    driver = new EdgeDriver();
                    break;
                case "firefox":
                    driver = new FirefoxDriver();
                    break;
                default:
                    System.out.println("Invalid browser name..");
                    return;
            }
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get("https://dev-profile-eight.vercel.app/");
        driver.manage().window().maximize();
        wait=new WebDriverWait(driver,Duration.ofSeconds(3));
        homepage = new Homepage(driver);
        portfolioPage = new PortfolioPage(driver);
    }

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
    @BeforeMethod
    public void init(){
        homepage=new Homepage(driver);
        portfolioPage=new PortfolioPage(driver);
    }

}


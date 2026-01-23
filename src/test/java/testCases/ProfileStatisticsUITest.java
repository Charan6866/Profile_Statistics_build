package testCases;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObjects.Homepage;
import java.util.Arrays;
import java.util.List;

public class ProfileStatisticsUITest extends BaseTest {

    SoftAssert sa=new SoftAssert();
    @Test(groups = {"smoke"})
    public void ProfileStatistics_TC001(){

       // Assert.assertEquals(driver.getCurrentUrl(),url);
        //or
        logger.info("Checking the url status");
        String readystate=(String) ((JavascriptExecutor)driver).executeScript("return document.readyState");
        try {
            Assert.assertEquals(readystate, "complete");
            logger.info("Test Passed");
        }
        catch(Exception e){
            logger.error("Test Failed"+e.getMessage());
        }

        logger.info("ProfileStatistics_TC001 completed");
    }
    @Test(groups = {"smoke"})
    public void ProfileStatistics_TC002(){
        logger.info("Starting ProfileStatistics_TC002()");
        homepage=new Homepage(driver);
        try{
        String title=homepage.getPagetitle();
        sa.assertEquals(title,"DevProfile");
        String subtitle=homepage.getSubtitle();
        sa.assertEquals(subtitle,"Showcase your development journey with style");
        logger.info("Test Passed");
        }
        catch (Exception e){
            logger.error("Test Failed"+e.getMessage());
        }
        logger.info("ProfileStatistics_TC002 completed");
    }
    @Test(groups = {"smoke"})
    public void ProfileStatistics_TC003(){
        logger.info("Starting ProfileStatistics_TC003()");
        List<String> labellist=homepage.getLabels();
        List<String> expectedLabelList= Arrays.asList(
                "Full Name",
                "Email Address",
                "Years of Experience",
                "Current Job Title",
                "Websites Developed",
                "Apps Made",
                "Key Skills (comma-separated)");
        try {
            sa.assertEquals(labellist, expectedLabelList);
            logger.info("Test Passed");
        }
        catch (Exception e){
            logger.error("Test Failed"+e.getMessage());
        }
        logger.info("ProfileStatistics_TC003 completed");
    }
    @Test(groups = {"smoke"})
    public void ProfileStatistics_TC004(){
        logger.info("Starting ProfileStatistics_TC004()");
        homepage=new Homepage(driver);
        List<Boolean> enables=homepage.getEnabled();
        List<Boolean> expectedenables=Arrays.asList(
                true,true,true,true,true,true
        );
        Boolean yrs=true;
        Boolean expectedyrsenable=homepage.yeardropdwn_Enable();
        List<Boolean> buttonenables=homepage.buttons_enabled();
        List<Boolean> expectedButtons=Arrays.asList(
                true,true
        );
        try {
            sa.assertEquals(enables, expectedenables);
            sa.assertEquals(yrs, expectedyrsenable);
            sa.assertEquals(buttonenables, expectedButtons);
            logger.info("tests Passed");
        }
        catch (Exception e){
            logger.error("Test Failed"+e.getMessage());
        }
        logger.info("ProfileStatistics_TC004 completed");
    }

    @Test(groups = {"smoke"})
    public void ProfileStatistics_TC005(){
        logger.info("Starting ProfileStatistics_TC005()");
        List<String> placeholder=homepage.getPlaceholders();
        List<String> expected_placeholders=Arrays.asList(
                "Enter your full name",
                "Enter your email",
                "e.g. Frontend developer",
                "Enter number of websites",
                "Enter number of apps",
                "e.g. JavaScript,React,Node.js"
        );
        List<String> options=homepage.getExpdropdown();
        String Experience=options.get(0);
        try{
        sa.assertEquals(placeholder,expected_placeholders);
        sa.assertEquals(Experience,"Select experience");
        logger.info("Test passed");
        }
        catch (Exception e){
            logger.error("Test Failed"+e.getMessage());
        }
        logger.info("ProfileStatistics_TC005 completed");
    }
    @Test(groups = {"smoke"})
    public void ProfileStatistics_TC006(){
        logger.info("Starting ProfileStatistics_TC006()");
        try {
            sa.assertEquals(homepage.measureText(), "Measure Profile");
            sa.assertEquals(homepage.createText(), "Create Portfolio");
            logger.info("Test Passed");
        }
        catch (Exception e){
            logger.error("Test Failed"+e.getMessage());
        }
        logger.info("ProfileStatistics_TC006 completed");
    }

}

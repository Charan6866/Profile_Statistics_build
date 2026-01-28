package testCases;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObjects.Homepage;
import java.util.Arrays;
import java.util.List;

public class ProfileStatisticsUITest extends BaseTest {

    @Test(groups = {"smoke"})
    public void ProfileStatistics_TC001(){

        Assert.assertEquals(driver.getCurrentUrl(),properties.getProperty("url"));


    }
    @Test(groups = {"smoke"})
    public void ProfileStatistics_TC002(){

        String title=homepage.getPagetitle();
        Assert.assertEquals(title,"DevProfile");
        String subtitle=homepage.getSubtitle();
        Assert.assertEquals(subtitle,"Showcase your development journey with style","Title mismatch");


    }
    @Test(groups = {"smoke"})
    public void ProfileStatistics_TC003(){

        List<String> labellist=homepage.getLabels();
        List<String> expectedLabelList= Arrays.asList(
                "Full Name",
                "Email Address",
                "Years of Experience",
                "Current Job Title",
                "Websites Developed",
                "Apps Made",
                "Key Skills (comma-separated)");

        Assert.assertEquals(labellist, expectedLabelList,"Labels are not correct");

    }
    @Test(groups = {"smoke"})
    public void ProfileStatistics_TC004(){


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
        System.out.println(buttonenables);
        Assert.assertEquals(enables, expectedenables,"input feilds are not enabled");
        Assert.assertEquals(yrs, expectedyrsenable,"Yrs experience is not enabled");
        Assert.assertEquals(buttonenables, expectedButtons,"buttons are not enabled(measure and createport");

    }

    @Test(groups = {"smoke"})
    public void ProfileStatistics_TC005(){
        logger.info("Starting ProfileStatistics_TC005()");
        List<String> placeholder=homepage.getPlaceholders();
        List<String> expected_placeholders=Arrays.asList(
                "Enter your full name",
                "Enter your email",
                "e.g. Frontend Developer",
                "Enter number of websites",
                "Enter number of apps",
                "e.g. JavaScript, React, Node.js"
        );
        List<String> options=homepage.getExpdropdown();
        String Experience=options.get(0);

        Assert.assertEquals(placeholder,expected_placeholders,"place holder mismatch");
        Assert.assertEquals(Experience,"Select experience","experience mismatch");

    }
    @Test(groups = {"smoke"})
    public void ProfileStatistics_TC006(){
        Assert.assertEquals(homepage.measureText(), "Measure Profile","measure button label mismatch");
        Assert.assertEquals(homepage.createText(), "Create Portfolio","Create port label mismatch");
        logger.info("Test Passed");

    }

}

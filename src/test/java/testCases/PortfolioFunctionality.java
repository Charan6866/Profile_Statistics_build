package testCases;


import org.testng.Assert;
import org.testng.annotations.Test;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.testng.Assert.fail;


public class PortfolioFunctionality extends  BaseTest {


    @Test(groups = {"regression"})
    public void portFunctionality() {
        logger.info("Starting the checking of complete portfolio page functionality");
        int exp=20;
        homepage.setName("Yash");
        homepage.setEmail("example@gmail.com");
        if(exp>=10){
            homepage.setYearsofExperience("10");
        }
        homepage.setTitle("Senior delivery head");
        homepage.setWebsites("20");
        homepage.setApp("25");
        homepage.setSkills("Javafullstack,python,machinelearning,cloud");
        homepage.clickCreate();
        String url = portfolioPage.getBacktoProfileHref();
        try {
            portfolioPage.clickbutton();
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("HEAD");
            connection.connect();
            int responseCode = connection.getResponseCode();
            Assert.assertTrue(responseCode < 400);
            logger.info("All test cases related to the portfolio functionality are passed");
        }
        catch (Exception e) {
            Assert.fail();
            logger.error("Test Failed:"+e.getMessage());

        }
        finally {
            logger.info("Test Case finished");
        }


    }

}


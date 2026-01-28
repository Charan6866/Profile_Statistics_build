package testCases;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import DataProvider.Dataprovider;
import utils.ExcelUtils;

import java.io.IOException;
import java.time.Duration;

public class ProfileStatisticsFunctionality extends BaseTest {
    String Excelpath;
    String sheetName;
    public void setForm(String name, String email, String experience, String jobTitle, String websitesDeveloped, String appMade, String skills) {
        homepage.setName(name);
        homepage.setEmail(email);
        homepage.setYearsofExperience(experience);
        homepage.setTitle(jobTitle);
        homepage.setWebsites(websitesDeveloped);
        homepage.setApp(appMade);
        homepage.setSkills(skills);
        homepage.clickMeasure();
    }
    @Test(priority = 0, dataProvider = "sampleData", dataProviderClass = Dataprovider.class, groups = {"regression"})
    public void testMeasurePortfolioButtonTitle(int rowindex, String name, String email, String experience, String jobTitle, String websitesDeveloped, String appMade, String skills, String expected) throws InterruptedException, IOException {
        Excelpath = properties.getProperty("Excelpath");
        sheetName = properties.getProperty("sheetName1");
        logger.info("***** Starting Test Cases  ****");
        System.out.println(name + " " + email + " " + experience + " " + jobTitle + " " + websitesDeveloped + " " + appMade + " " + skills);
        driver.navigate().refresh();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.body.style.zoom = '50%';");
        logger.info("Entering user details");
        setForm(name, email, experience, jobTitle, websitesDeveloped, appMade, skills);
        logger.info("Validating the profile highlights");
        Datacheck(expected, rowindex);
    }
    public void Datacheck(String expect, int rowIndex) throws IOException {
        if (waitAndAcceptAlert()) {
            String alertText = getAlertText();
            ExcelUtils.setCellData(Excelpath, sheetName, rowIndex, 8, alertText);
            ExcelUtils.setCellData(Excelpath, sheetName, rowIndex, 9, "Pass");
            ExcelUtils.fillGreenColor(Excelpath, sheetName, rowIndex, 9);
            Assert.assertTrue(true, "Validation alert at row" + rowIndex + " : " + alertText);
        } else {
            String actual = homepage.getProfileHighlights();
            String expectval = expect;

            try {
                Assert.assertEquals(actual, expectval, "Profile Highlights doesn't match");
                ExcelUtils.setCellData(Excelpath, sheetName, rowIndex, 8, actual);
                ExcelUtils.setCellData(Excelpath, sheetName, rowIndex, 9, "Pass");
                ExcelUtils.fillGreenColor(Excelpath, sheetName, rowIndex, 9);
            } catch (AssertionError e) {
                ExcelUtils.setCellData(Excelpath, sheetName, rowIndex, 8, actual);
                ExcelUtils.setCellData(Excelpath, sheetName, rowIndex, 9, "Fail");
                ExcelUtils.fillRedColor(Excelpath, sheetName, rowIndex, 9);
                throw e;
            }
        }
    }
    public boolean waitAndAcceptAlert() {
        try {
            wait = new WebDriverWait(driver, Duration.ofSeconds(2));
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public String getAlertText(){
        try{
            wait=new WebDriverWait(driver,Duration.ofSeconds(2));
            Alert alert=wait.until(ExpectedConditions.alertIsPresent());

            String text = alert.getText();
            alert.accept();
            return text;
        } catch (Exception e) {
            return null;
        }
    }
}




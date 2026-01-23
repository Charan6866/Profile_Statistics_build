package testCases;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import DataProvider.Dataprovider;

import java.util.Arrays;
import java.util.List;

public class PortifoliopageUiTest extends BaseTest{
SoftAssert sa=new SoftAssert();
String Excelpath;
String sheetName;

public void setForm(String name, String email, String experience, String jobTitle, String websitesDeveloped, String appMade, String skills){
        homepage.setName(name);
        homepage.setEmail(email);
        homepage.setYearsofExperience(experience);
        homepage.setTitle(jobTitle);
        homepage.setWebsites(websitesDeveloped);
        homepage.setApp(appMade);
        homepage.setSkills(skills);
        homepage.clickCreate();
    }
    @Test(priority = 0, dataProvider = "dataforportfolio", dataProviderClass = Dataprovider.class, groups = {"smoke"})
    public void testCreatePortfolioButtonTitle(String name, String email, String experience, String jobTitle, String websitesDeveloped, String appMade, String skills) throws InterruptedException {
        Excelpath = properties.getProperty("Excelpath");
        sheetName = properties.getProperty("sheetName2");
    System.out.println(name+" "+email+" "+experience+" "+jobTitle+" "+websitesDeveloped+" "+appMade+" "+skills);
        driver.navigate().refresh();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.body.style.zoom = '67%';");
        setForm(name, email, experience, jobTitle, websitesDeveloped, appMade, skills);
        logger.info("Starting the UI testing of portfolio page");
        Portpagetitle();
        Pageurl();
        PageheaderTitle();
        Pageheadersubtitle();
        Avatar(name);
        fullNamecheck(name);
        jobTitelcheck(jobTitle);
        emailCheck(email);
        experienceStats(experience);
        experienceStatsHeader();
        websitesCheck(websitesDeveloped);
        appsCheck(appMade);
        skillsHeader(skills);
        skillsCheck(skills);
        profileHighlightsHeader();
        //profileHighlightsText(expected);
        experienceBadge(experience);

        driver.navigate().back();
    }
public void Portpagetitle(){
    logger.info("Sarting  Portpagetitle test");
    try{
  Assert.assertEquals(driver.getTitle(),"DevProfile | Portfolio");
  logger.info("Test passed");
    }
    catch (Exception e){
        logger.error("Test Failed:"+e.getMessage());
    }
    finally {
        logger.info("Test Cases completed");
    }
}
public void Pageurl(){
    logger.info("Starting Pageurl test");
    try {
        Assert.assertEquals(driver.getCurrentUrl(), "https://dev-profile-eight.vercel.app/portfolio/portfolio.html");
        logger.info("Test passed");
    }
    catch (Exception e){
        logger.error("Test Failed:"+e.getMessage());
    }
    finally {
        logger.info("Test Cases completed");
    }
}
public  void PageheaderTitle()
{
    logger.info("Starting PageheaderTitle test");
    try {
        Assert.assertEquals(portfolioPage.getHeadingText(), "DevProfile");
        logger.info("Test passed");
    }
    catch (Exception e){
        logger.error("Test Failed:"+e.getMessage());
    }
    finally {
        logger.info("Test Cases completed");
    }
}
public void Pageheadersubtitle(){
    logger.info("Stating Pageheadersubtitle test");
    try {
        Assert.assertEquals(portfolioPage.getSubtitleText(), "Your Professional Portfolio");
        logger.info("Test passed");
    }
    catch (Exception e){
        logger.error("Test Failed:"+e.getMessage());
    }
    finally {
        logger.info("Test Cases completed");
    }
}
public void Avatar(String fullName){
    logger.info("Starting Avatar Test");
    String[] namesplit=fullName.split(" ");
    String avatar1="";
    if(namesplit.length>1)
    {
        avatar1=Character.toString(namesplit[0].charAt(0))+Character.toString(namesplit[1].charAt(0));
    } else if (namesplit.length==1) {
        avatar1=Character.toString(namesplit[0].charAt(0));
    }
    try {
        Assert.assertEquals(portfolioPage.getProfileAvatarAltText(), avatar1);
        logger.info("Test passed");
    }
    catch (Exception e){
        logger.error("Test Failed:"+e.getMessage());
    }
    finally {
        logger.info("Test Cases completed");
    }
}
public void fullNamecheck(String fullName){
    logger.info("Starting fullNamecheck");
    try {
        Assert.assertEquals(portfolioPage.getProfileNameText(), fullName);
        logger.info("Test Passed");
    }
    catch (Exception e){
        logger.error("Test Failed:"+e.getMessage());
    }
    finally {
        logger.info("Test Cases completed");
    }
}
public void jobTitelcheck(String jobRole){
    logger.info("Starting jobTitelcheck");
    try {
        Assert.assertEquals(portfolioPage.getProfileTitleText(), jobRole);
        logger.info("Test Passed");
    }
    catch (Exception e){
        logger.error("Test Failed:"+e.getMessage());
    }
    finally {
        logger.info("Test Cases completed");
    }
}
public void emailCheck(String email){
    logger.info("Starting emailCheck");
    try {
        Assert.assertEquals(portfolioPage.getProfileEmailText(), email);
        logger.info("Test Passed");
    }
    catch (Exception e){
        logger.error("Test Failed:"+e.getMessage());
    }
    finally {
        logger.info("Test Cases completed");
    }

}
public void experienceStats(String experience){
    logger.info("Starting experienceStats");
    try {
        Assert.assertEquals(portfolioPage.getYearsOfExperienceValue(), experience);
        logger.info("Test Passed");
    }
    catch (Exception e){
        logger.error("Test Failed:"+e.getMessage());
    }
    finally {
        logger.info("Test Cases completed");
    }
}
public void experienceStatsHeader(){
    logger.info("Starting experienceStatsHeader");
    try {
        Assert.assertEquals(portfolioPage.getYearsOfExperienceLabel(), "Years Experience");
        logger.info("Test passed");
    }
    catch (Exception e){
        logger.error("Test Failed:"+e.getMessage());
    }
    finally {
        logger.info("Test Cases completed");
    }
}
public void websitesCheck(String websites){
    logger.info("Starting websitesCheck");
    try{
    sa.assertEquals(portfolioPage.getWebsitesDevelopedValue(),websites);
    Assert.assertEquals(portfolioPage.getWebsitesDevelopedLabel(),"Websites Developed");
        logger.info("Test passed");
    }
    catch (Exception e){
        logger.error("Test Failed:"+e.getMessage());
    }
    finally {
        logger.info("Test Cases completed");
    }
}

public void appsCheck(String apps){
    logger.info("Starting appsCheck");
    try{
    Assert.assertEquals(portfolioPage.getAppsDevelopedValue(),apps);
    Assert.assertEquals(portfolioPage.getAppsDevelopedLabel(),"Apps Created");
        logger.info("Test passed");
    }
    catch (Exception e){
        logger.error("Test Failed:"+e.getMessage());
    }
    finally {
        logger.info("Test Cases completed");
    }
}

public void skillsHeader(String skill){
    logger.info("Starting skillsHeader");
    if(!skill.trim().isEmpty()){
        try{

     Assert.assertEquals(portfolioPage.getSkillsSectionHeaderText(),"Skills");
            logger.info("Test passed");
        }
        catch (Exception e){
            logger.error("Test Failed:"+e.getMessage());
        }
        finally {
            logger.info("Test Cases completed");
        }
    }
}
public void skillsCheck(String skills){
    logger.info("Starting skillsCheck");
    if(!skills.trim().isEmpty()){
        String[] stringsplit=skills.split(",");
        List<String> stringList= Arrays.stream(stringsplit).toList();
        System.out.println(stringList);
        try{
        Assert.assertEquals(portfolioPage.getSkillSetTexts(),stringList);
            logger.info("Test passed");
        }
        catch (Exception e){
            logger.error("Test Failed:"+e.getMessage());
        }
        finally {
            logger.info("Test Cases completed");
        }
    }
}
public void profileHighlightsHeader(){
    logger.info("Starting profileHighlightsHeader");
    try {
        Assert.assertEquals(portfolioPage.getProfileHighlightsSectionHeaderText(),"Profile Highlights");
        logger.info("Test passed");
    }
    catch (Exception e){
        logger.error("Test Failed:"+e.getMessage());
    }
    finally {
        logger.info("Test Cases completed");
    }

}
//public void profileHighlightsText(String expected){
//    logger.info("Starting profileHighlightsText");
//    try{
//    Assert.assertEquals(portfolioPage.getProfileHighlightsText(),expected);
//        logger.info("Test passed");
//    }
//    catch (Exception e){
//        logger.error("Test Failed:"+e.getMessage());
//    }
//    finally {
//        logger.info("Test Cases completed");
//    }
//}
public void experienceBadge(String Experience) {
    logger.info("Starting experienceBadge");
    String expectedOutput;
    int exp = Integer.parseInt(Experience);
    if (exp < 2) {
        expectedOutput = "Beginner";
    } else if (exp <= 4) {
        expectedOutput = "Intermediate";
    } else if (exp <= 7) {
        expectedOutput = "Advanced";
    } else {
        expectedOutput = "Expert";
    }
    try{
    Assert.assertEquals(portfolioPage.getExperienceBadgeText(), expectedOutput);
        logger.info("Test passed");
    }
    catch (Exception e){
        logger.error("Test Failed:"+e.getMessage());
    }
    finally {
        logger.info("Test Cases completed");
    }
}
}

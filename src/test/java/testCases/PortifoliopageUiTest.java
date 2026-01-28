package testCases;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import DataProvider.Dataprovider;

import java.util.Arrays;
import java.util.List;

public class PortifoliopageUiTest extends BaseTest{

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

        Assert.assertEquals(driver.getTitle(),"DevProfile | Portfolio","Title doesn't match");

    }
    public void Pageurl(){

        Assert.assertEquals(driver.getCurrentUrl(), "https://dev-profile-eight.vercel.app/portfolio/portfolio.html","portfolio url mismatch or url not correct");

    }
    public  void PageheaderTitle()
    {


        Assert.assertEquals(portfolioPage.getHeadingText(), "DevProfile","page header mismatch");

    }
    public void Pageheadersubtitle(){


        Assert.assertEquals(portfolioPage.getSubtitleText(), "Your Professional Portfolio","page header subtitle mismatch");

    }
    public void Avatar(String fullName){

        String[] namesplit=fullName.split(" ");
        String avatar1="";
        if(namesplit.length>1)
        {
            avatar1=""+namesplit[0].charAt(0)+namesplit[1].charAt(0);
        } else if (namesplit.length==1) {
            avatar1=""+namesplit[0].charAt(0);
        }

        Assert.assertEquals(portfolioPage.getProfileAvatarAltText(), avatar1,"avatar is not correct");
    }
    public void fullNamecheck(String fullName){
        Assert.assertEquals(portfolioPage.getProfileNameText(), fullName,"fullName is not as given");
    }
    public void jobTitelcheck(String jobRole){
        Assert.assertEquals(portfolioPage.getProfileTitleText(), jobRole,"jobrole is not as given");
    }
    public void emailCheck(String email){
        Assert.assertEquals(portfolioPage.getProfileEmailText(), email,"email is not as given");
    }
    public void experienceStats(String experience){
        Assert.assertEquals(portfolioPage.getYearsOfExperienceValue(), experience,"experience is not as given");
    }
    public void experienceStatsHeader(){
        Assert.assertEquals(portfolioPage.getYearsOfExperienceLabel(), "Years Experience","experience status header mismatch");
    }
    public void websitesCheck(String websites){
        Assert.assertEquals(portfolioPage.getWebsitesDevelopedValue(),websites,"websites count is not as given");
        Assert.assertEquals(portfolioPage.getWebsitesDevelopedLabel(),"Websites Developed","websites header mismatch");
    }

    public void appsCheck(String apps){
        Assert.assertEquals(portfolioPage.getAppsDevelopedValue(),apps,"apps count is not as given");
        Assert.assertEquals(portfolioPage.getAppsDevelopedLabel(),"Apps Created","apps header is mismatch");
    }

    public void skillsHeader(String skill) {
        if (!skill.trim().isEmpty()) {
            Assert.assertEquals(portfolioPage.getSkillsSectionHeaderText(), "Skills","skills header mismatch");
        }
    }
    public void skillsCheck(String skills){
        if(!skills.trim().isEmpty()){
            String[] stringsplit=skills.split(",");
            List<String> stringList= Arrays.stream(stringsplit).toList();
            System.out.println(stringList);

            Assert.assertEquals(portfolioPage.getSkillSetTexts(),stringList,"skills are not as given");
        }
    }
    public void profileHighlightsHeader(){
        Assert.assertEquals(portfolioPage.getProfileHighlightsSectionHeaderText(),"Profile Highlights","profile highlights header mismatch");
    }

    public void experienceBadge(String Experience) {
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

        Assert.assertEquals(portfolioPage.getExperienceBadgeText(), expectedOutput,"experience level in profile highlights mismatch");
    }
}

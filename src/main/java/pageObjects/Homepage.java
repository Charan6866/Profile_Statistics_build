package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class Homepage extends Basepage {
    public Homepage(WebDriver driver)
    {
        super(driver); // MANDATORY
    }
    //locators
    @FindBy(xpath="//h1[normalize-space()='DevProfile']")
    WebElement pagetitle;
    @FindBy(xpath="//h2[normalize-space()='Showcase your development journey with style']")
    WebElement pagesubtitle;
    @FindBy(xpath="//input[@id='fullName']")
    WebElement name;
    @FindBy(xpath="//input[@id='email']")
    WebElement email;
    @FindBy(xpath="//select[@id='yearsExperience']")
    WebElement yearsofExperience;
    @FindBy(xpath="//input[@id='jobTitle']")
    WebElement jobtitle;
    @FindBy(xpath="//input[@id='websitesDeveloped']")
    WebElement websites;
    @FindBy(xpath = "//input[@id='appsMade']")
    WebElement apps;
    @FindBy(xpath="//input[@id='skills']")
    WebElement skills;
    @FindBy(xpath="//button[@id='measureBtn']")
    WebElement measurebtn;
    @FindBy(xpath="//button[@type='submit']")
    WebElement createport;
    @FindBy(id = "profileHighlight")
    WebElement profileHighlights;
    //placeholder locators
    @FindBy(xpath="//input")
    List<WebElement> Inputs;
    @FindBy(xpath="//label")
    List<WebElement> labels;
    @FindBy(xpath="//buttons")
    List<WebElement> buttons;

//actions
    public String getPagetitle(){
        return pagetitle.getText();
    }
    public  String getSubtitle(){
        return pagesubtitle.getText();
    }

   public void setName(String Name)
   {
       name.sendKeys(Name);
   }
    public void setEmail(String Email)
    {
        email.sendKeys(Email);
    }
    public void setYearsofExperience(String Experience) {
        yearsofExperience.sendKeys(Experience);
    }

    public void setTitle(String Title)
    {
        jobtitle.sendKeys(Title);
    }
    public void setWebsites(String Websites)
    {
        websites.sendKeys(Websites);
    }
    public void setApp(String Apps)
    {
        apps.sendKeys(Apps);
    }
    public void setSkills(String Skills){
       skills.sendKeys(Skills);
    }
    public String measureText(){
        return measurebtn.getText();
    }
    public String createText(){
        return createport.getText();
    }
    public void clickMeasure()
    {
        measurebtn.click();
    }
    public void clickCreate()
    {
        createport.click();
    }
    public String getProfileHighlights(){
        return profileHighlights.getText();
    }
    //enabled actions methods
    public List<Boolean> getEnabled(){
        return Inputs.stream().map(web->web.isEnabled()).toList();
    }
    public Boolean yeardropdwn_Enable(){
        return yearsofExperience.isEnabled();
    }
    public List<Boolean> buttons_enabled(){
        return buttons.stream().map(w->w.isEnabled()).toList();
    }
//placeholder actions
    public List<String> getPlaceholders()
    {
        return Inputs.stream().map(web->web.getAttribute("placeholder")).toList();
    }

    public List<String> getExpdropdown()
    {
        Select select=new Select(yearsofExperience);
        List<String> options =select.getOptions().stream().map(w -> w.getText()).toList();
        return options;
    }
    public List<String> getLabels()
    {
        return labels.stream().map(w->w.getText()).toList();
    }

}

package pageObjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class PortfolioPage extends Basepage{

    public PortfolioPage(WebDriver driver){
       super(driver);
    }

    @FindBy(tagName = "h1")
    private WebElement heading;

    @FindBy(className = "subtitle")
    private WebElement subtitle;

    @FindBy(className = "profile-avatar")
    private WebElement profileAvatar;

    @FindBy(className = "profile-name")
    private WebElement profileName;

    @FindBy(className = "profile-title")
    private WebElement profileTitle;

    @FindBy(className = "profile-email")
    private WebElement profileEmail;

    @FindBy(className = "stat-value")
    private List<WebElement> statusCardValues;

    @FindBy(className = "stat-label")
    private List<WebElement> statusCardLabels;

    @FindBy(xpath = "//div[@id='portfolioContent']/div/div[3]")
    private WebElement skillsSectionHeader;

    @FindBy(className = "skill-tag")
    private List<WebElement> skillSet;

    @FindBy(xpath = "//div[text() = 'Profile Highlights']")
    private WebElement profileHighlightsSectionHeader;

    @FindBy(xpath = "//div[@class = 'profile-highlight']")
    private WebElement profileHighlights;

    @FindBy(xpath = "//div[@class = 'profile-highlight']//span")
    private WebElement experienceBadge;
    @FindBy(xpath="//a[@class='btn btn-back']")
    public WebElement backtoprofile;

    public String getHeadingText() {
        return heading.getText();
    }

    public String getSubtitleText() {
        return subtitle.getText();
    }

    public String getProfileAvatarAltText() {
        return profileAvatar.getText();
    }

    public String getProfileNameText() {
        return profileName.getText();
    }

    public String getProfileTitleText() {
        return profileTitle.getText();
    }

    public String getProfileEmailText() {
        return profileEmail.getText();
    }

    public String getYearsOfExperienceValue() {
        return statusCardValues.get(0).getText();
    }
    public String getYearsOfExperienceLabel() {
        return statusCardLabels.get(0).getText();
    }

    public String getWebsitesDevelopedValue() {
        return statusCardValues.get(1).getText();
    }
    public String getWebsitesDevelopedLabel() {
        return statusCardLabels.get(1).getText();
    }

    public String getAppsDevelopedValue() {
        return statusCardValues.get(2).getText();
    }
    public String getAppsDevelopedLabel() {
        return statusCardLabels.get(2).getText();
    }

    public String getSkillsSectionHeaderText() {
        return skillsSectionHeader.getText();
    }

    public List<String> getSkillSetTexts() {
        return skillSet.stream().map(WebElement::getText).toList();
    }

    public String getProfileHighlightsSectionHeaderText() {
        return profileHighlightsSectionHeader.getText();
    }

    public String getProfileHighlightsText() {
        return profileHighlights.getText();
    }

    public String getExperienceBadgeText() {
        return experienceBadge.getText();
    }
public void clickbutton(){
        backtoprofile.click();
}
    public String getBacktoProfileHref(){
        String href = backtoprofile.getAttribute("href");
        return href;
    }
}



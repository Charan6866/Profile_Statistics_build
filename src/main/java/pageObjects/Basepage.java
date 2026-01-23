package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class Basepage {
    WebDriver driver;
    Basepage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this); // MANDATORY
    }
}

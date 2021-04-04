package pages.locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindAll;

public class LoginPageLocator {

	@FindBy(xpath = "/html[1]/body[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/form[1]/div[1]/div[1]/input[1]")

	public WebElement email;

	@FindBy(xpath = "//input[@id='pass']")

	public WebElement password;

 @FindBy(xpath = "/html[1]/body[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/form[1]/div[2]/button[1]") 
	public WebElement signup;

	@FindBy(xpath = "//a[@id='forgot-password-link']")
	public WebElement forgotenPasword;

	@FindAll({ @FindBy(css = "#signup-button']"), @FindBy(xpath = "//a[@id='signup-button']") })
	public WebElement createnewAccount;
}

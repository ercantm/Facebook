package pages.action;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import base.TestBase;
import pages.locators.LoginPageLocator;

public class LoginPage extends TestBase {
	LoginPageLocator login;
 WebDriver driver=TestBase.driver;
	public LoginPage() {
		this.login = new LoginPageLocator();
		AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver,10 );
		PageFactory.initElements(factory, this.login);
	}

	public void enterEmail(String email) {

		type(login.email, email);
		// login.email.sendKeys(email);
	}

	public void enterPasswords(String pass) {
		type(login.password, pass);
		// login.password.sendKeys( pass);
	}

	public void sigUPFacebook() {
		click(login.signup);
		//login.signup.click();
		
	}
	public void quit() {
		quitBrowser();
	}

}

package testCases;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.TestBase;
import pages.action.LoginPage;
import utilities.TestUtil;

public class LoginPageTest extends TestBase  {

	
	

	@BeforeTest
	public void setUp(){
		
		try {
			TestBase.initConfiguration();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Test(dataProviderClass = TestUtil.class, dataProvider = "dp")
	public void loginPageTest(Hashtable<String, String> data) {
		if (data.get("runmode").equalsIgnoreCase("N")) {

			throw new SkipException("Skipping the test as the Run mode is NO");

		}
		
		LoginPage lp = new LoginPage();
		lp.enterEmail(data.get("email"));
		lp.enterPasswords(data.get("password"));
		lp.sigUPFacebook();
		lp.quit();
		

	}

//	@AfterMethod
//	
//	public void tearDown(){
//		if(TestBase.driver!=null){
//		TestBase.quitBrowser();
//		}
	//}

}

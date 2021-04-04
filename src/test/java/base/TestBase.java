package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.TopMenu;
import utilities.ExcelReader;
import utilities.ExtentManager;
import utilities.TestUtil;

public class TestBase {

	private static final String False = null;
	/*
	 * webdriver propertiresd logs extentreports DP Excel Mail
	 * 
	 * 
	 */
	public static TopMenu menu;
	public static SoftAssert soft = new SoftAssert();
	public static ExtentReports rep = ExtentManager.getInstance();
	public static ExtentTest test;
	public static String browser;
	public static WebDriver driver;
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static ExcelReader excel = new ExcelReader(System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\data.xlsx");
	public static WebDriverWait wait;


	public static void initConfiguration() throws IOException {
//		if (driver == null) {
//	
//	
//		if (System.getenv("browser") != null && (!System.getenv("browser").isEmpty())) {
//			browser = System.getenv("browser");
//
//		}
//
//		else {
//			browser = Constants.browser;
//		}

		

			if(Constants.browser.equals("firefox")) {
		
			WebDriverManager.firefoxdriver().setup();
			FirefoxProfile customProfile = new FirefoxProfile();
			customProfile.setPreference("dom.disable_open_during_load", False);
			driver = new FirefoxDriver((Capabilities) customProfile);
			
			log.debug(" firefox starting");
		}
			else if(Constants.browser.equals("ie")) {
		
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
			WebDriverManager.iedriver().setup();
			// System.setProperty("webdriver.ie.driver",
			// "C:\\Users\\ercan\\Driver\\IEDriver\\IEDriverServer.exe");
			driver = new InternetExplorerDriver(capabilities);
			log.debug(" Internet Explorer  starting");
		}
		else if(Constants.browser.equals("chrome")) {

			WebDriverManager.chromedriver().setup();

			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-popup-blocking");
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			driver = new ChromeDriver(capabilities);

			// System.setProperty("webdriver.chrome.driver",
			// "C:\\Users\\ercan\\Driver\\Chromedriver\\chromedriver.exe");
			// driver = new ChromeDriver();
			log.debug(" Crome is  starting");
		}
		else if(Constants.browser.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			// System.setProperty("webdriver.ei.driver",
			// "C:\\Users\\ercan\\Driver\\Edge\\MicrosoftWebDriver.exe");
			driver = new EdgeDriver();

		}
		else if(Constants.browser.equals("opera")) {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			OperaOptions options = new OperaOptions();
			options.setBinary("C:\\Users\\ercan\\AppData\\Local\\Programs\\Opera\\72.0.3815.18\\Opera.exe");
			capabilities.setCapability(OperaOptions.CAPABILITY, options);
			WebDriverManager.operadriver().setup();
			// System.setProperty("webdriver.opera.driver",
			// "C:\\Users\\ercan\\Driver\\OperaDriver\\operadriver.exe");
			driver = new OperaDriver();

		}
	  driver.get(Constants.testsiteurl);
		log.debug("Test URl is lauching");
		driver.manage().window().maximize();
		log.debug(" windows geting full screan get implemtting implicit wait ");
		driver.manage().timeouts().implicitlyWait((Constants.implicitwait),
				TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 15);

	}
	//}

   public static void quitBrowser(){
		

		driver.quit();
		
	
	log.debug(" Test execustion completed");
	test.log(LogStatus.INFO, " Test execustion completed");
	Reporter.log(" Test execustion completed");
		
	}



	public static void type(WebElement element, String value) {

		element.sendKeys(value);
		log.debug("Typing in an Element : " + element + " entered value as : " + value);
		test.log(LogStatus.INFO, "typing in this  : " + element.toString() + "  enter value as the " + value);
		Reporter.log("typing in this : " + element.toString() + " enter value as the " + value);
	}

	public static void click(WebElement element) {
		element.click();
		test.log(LogStatus.INFO, "clicking  in this  : " + element.toString() + " bottom");
		Reporter.log("clicking  in this  : " + element.toString() + " bottom");
		log.debug("Clicking on an Element : " + element);

	}

	public static void waitVisible(WebElement element) {

		test.log(LogStatus.INFO, "waiting  in this  : " + element.toString() + " web elemet to be vissible ");
		Reporter.log("waiting  in this  : " + element.toString() + " web elemet to be vissible ");

		wait.until(ExpectedConditions.visibilityOf(element));

		test.log(LogStatus.INFO, "waiting for the visibility of   : " + element.toString() + " element ");

	}

	public static void waitClickable(WebElement element) {

		wait.until(ExpectedConditions.elementToBeClickable(element));
		log.debug("waiting Clicking on an Element : " + element);
		test.log(LogStatus.INFO, "waiting  for the clickable  of   : " + element.toString() + " element ");
		Reporter.log("waiting  in this  : " + element.toString() + " web elemet to be clickable  ");
	}

	public static boolean isElementPresent(WebElement element) {
		log.debug("waiting enable of an Element : " + element);
		test.log(LogStatus.INFO, "waiting  in this  : " + element + " elemet to be peresent   ");
		Reporter.log("waiting  in this  : " + element + " elemet to be peresent   ");
		try {

			element.isDisplayed();
			return true;

		} catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
			return false;
		}

	}

	public static void select(WebElement element, String text) {
		test.log(LogStatus.INFO, "selecting  in this  : " + element.toString() + "  enter value as the " + text);
		Reporter.log("selecting in this : " + element.toString() + " enter value as the " + text);
		log.debug("selecting Element : " + element + " choosing  value as : " + text);
		Select select = new Select(element);
		select.selectByVisibleText(text);

	}

	public static void selectbyIndex(WebElement element, int text) {
		test.log(LogStatus.INFO,
				"selecting  in this  : " + element.toString() + "  enter index number  as the " + text);
		Reporter.log("selecting in this : " + element.toString() + " enter index number  as the " + text);
		log.debug("selecting Element : " + element + " choosing  value as : " + text);
		Select select = new Select(element);
		select.selectByIndex(text);

	}

	public static void selectbyValue(WebElement element, String text) {
		test.log(LogStatus.INFO,
				"selecting  in this  : " + element.toString() + "  enter index number  as the " + text);
		Reporter.log("selecting in this : " + element.toString() + " enter index number  as the " + text);
		log.debug("selecting Element : " + element + " choosing  value as : " + text);
		Select select = new Select(element);
		select.selectByValue(text);

	}

	public static void softAssert(String actual, String expected) {

		test.log(LogStatus.INFO, "typing in this  : " + expected + actual + " soft checking make sure they are same ");
		Reporter.log("typing in this  : " + expected + actual + " soft checking make sure they are same ");

		soft.assertEquals(expected, actual);
		Reporter.log("<br>");
		Reporter.log("Click to see Screenshot");
		Reporter.log("verification of these  " + expected + " and " + actual + "  failed and reason is ");
		Reporter.log("<a target=\"_blank\" href=" + TestUtil.screenshotName + ">Screenshot</a>");

		Reporter.log("<br>");
		Reporter.log("<a target=\"_blank\" href=" + TestUtil.screenshotName + "><img src=" + TestUtil.screenshotName
				+ " height=200 width=200></img></a>");
		// Extent Report
		test.log(LogStatus.SKIP, "verification of these  " + expected + " and " + actual + "  failed"
				+ " and   Failled with this exception:   ");
		test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotName));
		log.info(expected + " and " + actual + " is not mattching  ");

	}

	public static void verifyEquals(String actual, String expected) throws IOException {

		test.log(LogStatus.INFO, "typing in this  : " + expected + actual + " soft checking make sure they are same ");
		Reporter.log("typing in this  : " + expected + actual + " soft checking make sure they are same ");
		try {
			Assert.assertEquals(expected, actual);

		} catch (Throwable t) {
			TestUtil.captureScreenshot();
			// reportrNG
			Reporter.log("<br>");
			Reporter.log("Click to see Screenshot");
			Reporter.log("verification of these  " + expected + " and " + actual + "  failed and reason is "
					+ t.getMessage());
			Reporter.log("<a target=\"_blank\" href=" + TestUtil.screenshotName + ">Screenshot</a>");

			Reporter.log("<br>");
			Reporter.log("<a target=\"_blank\" href=" + TestUtil.screenshotName + "><img src=" + TestUtil.screenshotName
					+ " height=200 width=200></img></a>");
			// Extent Report
			test.log(LogStatus.SKIP, "verification of these  " + expected + " and " + actual + "  failed"
					+ " and   Failled with this exception:   " + t.getMessage());
			test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotName));
			log.info(expected + " and " + actual + " is not mattching  ");
		}

	}

}
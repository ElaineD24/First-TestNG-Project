package firsttestngpackage;

//import java.sql.Time;
import java.util.ArrayList;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
//import org.testng.SkipException;
import org.testng.annotations.*;

public class FirstTestNGFile {
	
	public String facebookUrl = "https://www.facebook.com/";
	String driverPath = "C:\\Users\\wenyued\\Desktop\\chromedriver.exe";
	public WebDriver driver;
	public String expected = null;
	public String actual = null;
	
	@BeforeTest
	public void launchBrowser() {
		System.out.println("Launching Google chrome browser...");
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		driver.get(facebookUrl);
		
	}
	
	@BeforeMethod
	public void verifyFacebookHomepageTitle() {
		
		String expectedTitle = "Facebook - Log In or Sign Up";
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle);
	}
	
	@Test (description="Verifies that the location for retreiving password exists.", enabled = true, priority = 1)
	public void forgotAccount() throws Exception {
		driver.findElement(By.linkText("Forgot account?")).click();
		expected = "Forgot Password | Can't Log In | Facebook";
		actual = driver.getTitle();
		System.out.println("forgotAccount: " + actual);
		Assert.assertEquals(actual, expected);
	}
	
	@Test(description="Verifies that the location for retreiving 'read terms' exists.", enabled = true, priority = 0)
	public void readTerms() throws Exception {
		//click on URL -> opens a new page
		driver.findElement(By.linkText("Terms")).click();
		
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		
		System.out.println(driver.getCurrentUrl());
		expected = "Terms of Service";
		actual = driver.getTitle();
		System.out.println("readTerm: " + actual);
		Assert.assertEquals(actual.toString(), expected);
	}
	
	@Test(description="Verifies that the location for retreiving 'create a page' exists.", priority = 2)
	public void createAPage() throws Exception {
		driver.findElement(By.linkText("Create a Page")).click();
		expected = "Facebook";
		actual = driver.getTitle();
		System.out.println("createPage: " + actual);
		Assert.assertEquals(actual, expected);
	}
	
	@AfterMethod
	public void backToHomepage() {
		driver.findElement(By.className("fb_logo")).click();
	}
	
	@AfterTest
	public void closeBrowser() {
		driver.close();
		delay2sec();
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(0));
		driver.close();
	}
	
	private static void delay2sec() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

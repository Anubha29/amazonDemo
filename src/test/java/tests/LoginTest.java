package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import junit.framework.Assert;
import pages.LoginPage;
import util.WebUtil;

@SuppressWarnings("deprecation")

public class LoginTest {

	public static WebDriver driver;

	@BeforeSuite
	public void initialize() {
		driver = WebUtil.getBrowser("chrome","https://www.amazon.in/");
		PageFactory.initElements(driver, LoginPage.class);
	}

	@AfterSuite
	public void cleanup() {
		driver.quit();
	}

	@Test(priority=1)
	public void loginTest() {
		
		String welcome=LoginPage.loginUser("9773909702","Test@1234");
		Assert.assertEquals("Hello, GoJek", welcome);
	}

	@Test(priority=2)
	public void logOut(){
		System.out.println(LoginPage.logoutUser());
	}	
	
}

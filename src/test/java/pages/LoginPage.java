package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import tests.LoginTest;
import util.WebUtil;

public class LoginPage {

	@FindBy(how = How.ID, using = "nav-link-accountList")
	private static WebElement loginButton;
	
	@FindBy(how = How.ID, using = "ap_email")
	private static WebElement email;
	
	@FindBy(how = How.ID, using = "continue")
	private static WebElement continueButton;
	
	@FindBy(how = How.ID, using = "ap_password")
	private static WebElement password;
	
	@FindBy(how = How.ID, using = "signInSubmit")
	private static WebElement signIn;
	
	@FindBy(how = How.XPATH, using = "//*[@id=\"nav-link-accountList\"]/*[@class=\"nav-line-1\"]")
	private static WebElement welcomeUser;
	
	@FindBy(how = How.XPATH, using = "//*[text()='Account & Lists']/*[@class=\"nav-icon nav-arrow\"]")
	private static WebElement acctListOptions;
	
	@FindBy(how = How.XPATH, using = "//span[@class=\"nav-text\"]")
	private static WebElement signOut;
	
	public static String loginUser(String user,String password) {
		loginButton.click();
		email.clear();
		email.sendKeys(user);
		continueButton.click();
		LoginPage.password.clear();
		LoginPage.password.sendKeys(password);
		signIn.click();
		return welcomeUser.getText();
	}

	public static String logoutUser() {
		WebUtil.mouseHover(LoginTest.driver, acctListOptions);
		signOut.click();
		return welcomeUser.getText();
	}

}

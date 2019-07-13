package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import tests.ShoppingTest;
import util.WebUtil;

public class ShoppingPage {

	@FindBy(how = How.XPATH, using = "//*[text()='Category']/span[@class=\"nav-icon nav-arrow\"]")
	private static WebElement categoryOptions;

	@FindBy(how = How.XPATH, using = "//*[text()='TV, Appliances, Electronics']")
	private static WebElement electronics;

	@FindBy(how = How.XPATH, using = "//*[text()='Headphones']")
	private static WebElement headphones;
	
	@FindBy(how = How.ID, using = "s-result-count")
	private static WebElement resultCount;
	
	@FindBy(how = How.XPATH, using = "//*[@id=\"result_0\" and contains(@class,'s-result-item')]//img")
	private static WebElement firstResult;

	@FindBy(how = How.ID, using = "a-autoid-0-announce")
	private static WebElement addToCart;

	@FindBy(how = How.ID, using = "nav-cart-count")
	private static WebElement cartValue;
	
	@FindBy(how = How.ID, using = "searchDropdownBox")
	private static WebElement searchCategory;

	@FindBy(how = How.ID, using = "twotabsearchtextbox")
	private static WebElement searchBox;
	
	@FindBy(how = How.XPATH, using = "//*[@class=\"nav-input\" and @type=\"submit\"]")
	private static WebElement searchButton;

	@FindBy(how = How.XPATH, using = "//*[@data-index=\"1\"]//a[contains(@class,'a-link-normal a-text-normal')]")
	private static WebElement secondResult;

	@FindBy(how = How.XPATH, using = "//*[@id=\"quantity\" and @class=\"a-native-dropdown\"]")
	private static WebElement quantity;

	@FindBy(how = How.ID, using = "add-to-cart-button")
	private static WebElement addProductToCart;

	@FindBy(how = How.ID, using = "productTitle")
	private static WebElement productTitle;

	@FindBy(how = How.XPATH, using = "//*[@class=\"nav-cart-icon nav-sprite\"]")
	private static WebElement shoppingCart;

	@FindBy(how = How.XPATH, using = "//*[contains(@name,'submit.delete') and contains(@aria-label,'Headphone')]")
	private static WebElement deleteHeadphone;

	@FindBy(how = How.XPATH, using = "//*[@name=\"proceedToCheckout\" and @type=\"submit\"]")
	private static WebElement checkout;
	
	@FindBy(how = How.XPATH, using = "//*[@class=\"a-icon a-icon-dropdown\"]")
	private static WebElement cartQuantity;
	
	@FindBy(how = How.ID, using = "dropdown1_0")
	private static WebElement quantityOne;

	public static String shopByCategory() {
		WebUtil.mouseHover(ShoppingTest.driver, categoryOptions);
		WebUtil.mouseHover(ShoppingTest.driver, electronics);
		headphones.click();
		new WebDriverWait(ShoppingTest.driver, 10).until(ExpectedConditions.visibilityOf(resultCount));
		firstResult.click();
//		ShoppingTest.driver.findElement(By.xpath("//*[@id=\"result_0\" and contains(@class,'s-result-item')]//a"))
//				.click();
		if (productTitle.getText().contains("Headphone")) {
			new WebDriverWait(ShoppingTest.driver, 10).until(ExpectedConditions.visibilityOf(addToCart));
			addToCart.click();
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cartValue.getText();
	}

	public static void searchResult(String searchProduct) {
		Select category=new Select(searchCategory);
		category.selectByVisibleText("All Categories");
		searchBox.click();
		searchBox.clear();
		searchBox.sendKeys(searchProduct);
		searchButton.click();
	}
	
	public static String shopBySearch(String searchProduct) {
		searchResult(searchProduct);
		String winHandleBefore = ShoppingTest.driver.getWindowHandle();
		secondResult.click();
		for (String winHandle : ShoppingTest.driver.getWindowHandles()) {
			ShoppingTest.driver.switchTo().window(winHandle);
		}
		 Select quantity = new Select(ShoppingPage.quantity);
		 quantity.selectByVisibleText("2");
		addProductToCart.click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String cartVal=cartValue.getText();
		ShoppingTest.driver.close();
		ShoppingTest.driver.switchTo().window(winHandleBefore);
		return cartVal;
	}

	public static String removeHeadphone() {
		shoppingCart.click();
		deleteHeadphone.click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cartValue.getText();
	}

	public static String changeQuantity() {
		addToCart.click();
		quantityOne.click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cartValue.getText();
	}

	public static void checkout() {
		checkout.click();
	}

}

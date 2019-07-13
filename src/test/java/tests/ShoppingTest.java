package tests;

import java.io.FileReader;
import java.util.List;

import javax.naming.directory.SearchResult;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.LoginPage;
import pages.ShoppingPage;
import util.WebUtil;

public class ShoppingTest {
	public static WebDriver driver;

	@DataProvider(name="searchData")
	public Object[][] searchData(){
		Object[][] search = null;
		JSONParser jsonParser = new JSONParser();
		 try (FileReader reader = new FileReader("src/test/resources/input/search.json"))
	        {
	            Object parser = jsonParser.parse(reader);
	            JSONObject searching = (JSONObject) parser;
	            JSONArray jsonArray = (JSONArray) searching.get("search");
	            search = new Object[jsonArray.size()][1];
	            int i=0;
	            for(Object obj : jsonArray){
	            	search[i][0]=obj;
	            	i++;
	            }
	        }catch(Exception e) {
	        	System.out.println("EXCEPTION::"+e);
	        }
		return search;
	}
	
	@BeforeClass
	public void initialize() {
		driver = WebUtil.getBrowser("chrome","https://www.amazon.in/");
		PageFactory.initElements(driver, LoginPage.class);
		PageFactory.initElements(driver, ShoppingPage.class);
		LoginPage.loginUser("9773909702","Test@1234");
	}

	@AfterClass
	public void cleanup() {
		try {
		LoginPage.logoutUser();
		} finally {
		driver.quit();
		}
	}
	
	@Test(priority=1, dataProvider="searchData")
	public void searchingProduct(String input) {
		ShoppingPage.searchResult(input);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test(priority=2)
	public void addHeadphones() {
		String cartValue=ShoppingPage.shopByCategory();
		Assert.assertEquals("1", cartValue);
	}
	
	@Test(priority=3)
	public void addMacbookPro() {
		String cartValue=ShoppingPage.shopBySearch("Macbook pro");
		Assert.assertEquals("3", cartValue);
	}
	
	@Test(priority=4)
	public void deleteHeadphone() {
		String cartValue=ShoppingPage.removeHeadphone();
		Assert.assertEquals("2", cartValue);
	}
	
	@Test(priority=5)
	public void updateCartQuantity() {
		String cartValue=ShoppingPage.changeQuantity();
		ShoppingPage.checkout();
		Assert.assertEquals("1", cartValue);
	}
	
	

}

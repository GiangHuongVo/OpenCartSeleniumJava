package testcases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import base.Base;
import pages.SearchPage;
import utils.Utilitaire;

public class TestSearch extends Base{
	public WebDriver driver;	
	public SearchPage search;
		
	@BeforeMethod
	public void setup() {		
		driver = initialiserNavigate(prop.getProperty("browserName"));
		search = new SearchPage(driver);
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	// Using datapool to test the products are available or not
	@Test(dataProvider="testData")
	public void testSearch(String productName) {
		try {
			search.searchProduct(productName);
			
			if(search.displayOfValidProduct()) {
				System.out.println("Products: " + productName + " are available");
			}			
		}
		catch (Exception e) {			
			System.out.println("Products: " + productName + " " + search.retreiveNoProductMessageText());	
		}
		
	}
	
	@DataProvider(name="testData")    
	public Object[][] supplyData() throws IOException {        
		Object[][] data = Utilitaire.getDataFromExcel("search");        
		return data;    
	}
}

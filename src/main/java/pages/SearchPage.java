package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
	WebDriver driver;
	
	@FindBy(xpath = "//input[@name='search']")
	private WebElement searchInput;
	
	@FindBy(xpath = "//div[@class='description']//a")
	private WebElement validProduct;

	@FindBy(xpath = "//h2/following-sibling::p")
	private WebElement noProductMessage;

	public SearchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	// Search input
	public void searchProduct(String productName) {
		searchInput.sendKeys(productName);
		searchInput.sendKeys(Keys.ENTER);
		
	}

	// pour retourner le texte du message 

	public String retreiveNoProductMessageText() {

		return noProductMessage.getText();

	}
	
	//une methode pour valider que le produit est affiché

	public boolean displayOfValidProduct() {
		
		return validProduct.isDisplayed();

	}

}

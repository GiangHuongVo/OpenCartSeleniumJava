package pages;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.Utilitaire;
/*
 * Choisir un produit par cliquer sur le nom produit
 * Cliquer sur le bouton "Add to Cart"
 * Le bouton icon cart montre le nombre items et price
 * Cliquer sur l'icon cart sur header pour a allez sur le page commander par cliquer sur view cart
 * Clique sur le bouton "Check out"
 * 
 */

public class CommandePage {
	WebDriver driver;	
	WebDriverWait wait;
	//Product detail locator
	String categoryP1 = "//li[@class='nav-item']/a[contains(text(),'";
	String categoryP2 = "')]";
	String productNameP1="//a[contains(text(), '";
	String productNameP2="')]";
	//Add Cart icon of a product
	@FindBy(id="button-cart")
	private WebElement btnAddCart;
	
	@FindBy(xpath="//input[@name='quantity']")
	private WebElement quanlity;
	
	@FindBy(xpath="//p[contains(text(), 'Your shopping cart is empty!')]")
	private WebElement emptyCart;
	
	@FindBy(xpath="//a/strong[contains(text(), 'View Cart')]")
	private WebElement viewCartIcon;
	
	@FindBy(xpath="//a/strong[contains(text(), 'Checkout')]")
	private WebElement checkoutIcon;
	
	@FindBy(xpath="//h1[text()='Checkout']")
	private WebElement checkoutTitle;
	
	@FindBy(xpath="//h1[text()='Shopping Cart']")
	private WebElement viewCartTitle;
	
	@FindBy(xpath="//i[@class='fa-solid fa-shopping-cart']")
	private WebElement addCartIcon;
	
	@FindBy(xpath="//div[@class='price']/span[@class='price-new']")
	private WebElement productPrice;
	
	@FindBy(xpath="//button[contains(text(), 'item(s)')]")
	private WebElement btnCartItems;
	
	// Variable for button cart on the header with number of items and total price
	String btnCartLocatorP1="//button[text()='";
	String btnCartLocatorP2= "']";
	int numItem = 0;
	double totalPrice = 0;
	
	// Locator of checkout // Locator variables
	
	@FindBy(xpath="//a[contains(text(), 'Checkout')]")
	WebElement btnCheckout;	
	// Form checkout
	@FindBy(xpath="//label[contains(text(), 'Register')]")
	WebElement registerOption;	
	@FindBy(xpath="//label[contains(text(), 'Guest')]")
	WebElement guestOption;	
	
	@FindBy(id="input-firstname")
	WebElement firstNameLocator;
	@FindBy(id="input-lastname")
	WebElement lastNameLocator;	
	@FindBy(id="input-email")
	WebElement emailLocator;	
	@FindBy(id="input-shipping-address-1")
	WebElement shippingAdress;	
	@FindBy(id="input-shipping-city")
	WebElement cityLocator;
	@FindBy(id="input-shipping-postcode")
	WebElement codePostalLocator;
	
	@FindBy(xpath="//select[@id='input-shipping-country']")
	WebElement countryLocator;
	
	String countryOptionP1="//option[contains(text(),'";
	String countryOptionP2="')]";
	
	@FindBy(xpath="//select[@id='input-shipping-zone']")
	WebElement regionLocator;
	
	String zoneOptionP1="//option[contains(text(),'";
	String zoneOptionP2="')]";
	@FindBy(id="input-password")
	WebElement passLocator;
	@FindBy(id="input-newsletter")
	WebElement newsletterLocator;
	@FindBy(id="input-register-agree")
	WebElement pricyPolicyLocator;
	@FindBy(id="button-register")
	WebElement btnContinue;
	
	// Method	
	@FindBy(xpath="//button[@id='button-shipping-methods']")
	WebElement btnShippingChoose;
	@FindBy(id="input-shipping-method-flat-flat")
	WebElement flatOption;	
	@FindBy(id="button-shipping-method")
	WebElement btnContinueShippingForm;
	
	@FindBy(id="input-payment-method")
	WebElement inputPaymentMethod;
	@FindBy(xpath="//button[@id='button-payment-methods']")
	WebElement btnPaymentChoose;	
	@FindBy(id="input-payment-method-cod-cod")
	WebElement paymentOption;	
	@FindBy(id="button-payment-method")
	WebElement btnContinuePaymentForm;
	
	@FindBy(id="button-confirm")
	WebElement btnConfirm;
	
	@FindBy(xpath="//div[@id='content']/h1")
	WebElement orderSuccesMessage;
	
	@FindBy(id="input-shipping-existing")
	WebElement existingAddressOption;
	// Choisir new address pour utilisateur login
	@FindBy(id="input-shipping-new")
	WebElement newAddressOption;
	
	@FindBy(id="input-shipping-firstname")
	WebElement shippingFirstName;
	
	@FindBy(id="input-shipping-lastname")
	WebElement shippingLastName;
	
	public CommandePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void choisirCategory(String categoryName) throws InterruptedException {
		driver.findElement(By.xpath(categoryP1 + categoryName + categoryP2)).click();
		Thread.sleep(3000);
	}
	
	public void productDetail(String productName) throws InterruptedException{ 		
		driver.findElement(By.xpath(productNameP1 + productName + productNameP2)).click();
		Thread.sleep(3000);
		
	}
	
	public void imageProduct(String title) throws InterruptedException{ 		
		driver.findElement(By.xpath("//img[@title='"+ title + "']")).click();
		Thread.sleep(3000);		
	}
	
	public void addCart(String numProduct) throws InterruptedException {
		// clear field and put a number of product
		quanlity.sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), numProduct);	
		Thread.sleep(5000);		
		btnAddCart.click();
		Thread.sleep(2000);
	}
	
	public void goToCheckoutPageByViewCart() throws InterruptedException {
		Thread.sleep(5000);
		btnCartItems.click();				
		viewCartIcon.click();				
	}
	
	public void goToCheckoutPageByCheckout() throws InterruptedException {
		Thread.sleep(5000);
		btnCartItems.click();				
		checkoutIcon.click();				
	}
	
	//if user choose view cart before checkout
		public void goToCheckoutPage() throws InterruptedException {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			// Scrolling down the page till the element is found		
	        js.executeScript("arguments[0].scrollIntoView();", btnCheckout);
	        Thread.sleep(5000);
			btnCheckout.click();
			Thread.sleep(5000);
		}
		
		public String messageSucces() {
			WebElement mess = driver.findElement(By.id("alert"));
			System.out.println(mess.getText());
			return mess.getText();
		}
		
		public void infoFormRegisterOption(String firstName, String lastName, String email, String address, String city, String code, String countryOption, String zoneName, String pass) throws InterruptedException {
			registerOption.click();
			firstNameLocator.sendKeys(firstName);
			lastNameLocator.sendKeys(lastName);
			emailLocator.sendKeys(email);
			shippingAdress.sendKeys(address);
			cityLocator.sendKeys(city);
			codePostalLocator.sendKeys(code);
			Thread.sleep(2000);
			Actions actions = new Actions(driver);
			actions.moveToElement(countryLocator).build().perform();
			countryLocator.click();
			Thread.sleep(8000);
			String optionCountry = countryOptionP1 + countryOption + countryOptionP2;
			
			WebElement countryName = driver.findElement(By.xpath(optionCountry));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			// Scrolling down the page till the element is found		
	        js.executeScript("arguments[0].scrollIntoView();", countryName);
	        Thread.sleep(5000);			
			countryName.click();
			
			Thread.sleep(2000);
			
			js.executeScript("arguments[0].scrollIntoView();", regionLocator);
			Thread.sleep(2000);
			regionLocator.click();
			
			Thread.sleep(2000);
			 
			String optionRegion = zoneOptionP1 + zoneName + zoneOptionP2;
			WebElement regionElement = driver.findElement(By.xpath(optionRegion));
			js.executeScript("arguments[0].scrollIntoView();", regionElement);
			regionElement.click();			
			Thread.sleep(2000);
			
			actions.moveToElement(passLocator).build().perform();
			passLocator.sendKeys(pass);
			
			actions.moveToElement(newsletterLocator).build().perform();
			newsletterLocator.click();
			Thread.sleep(2000);
			actions.moveToElement(pricyPolicyLocator).build().perform();
			pricyPolicyLocator.click();
			Thread.sleep(2000);
			actions.moveToElement(btnContinue).build().perform();
			btnContinue.click();
			Thread.sleep(2000);
			messageSucces();			
		}
		
		public void choirsirShipping() throws InterruptedException {
			Actions actions = new Actions(driver);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			// Scrolling down the page till the element is found
			WebElement shippingMethodField= driver.findElement(By.xpath("//*[@id='checkout-shipping-method']"));
	        js.executeScript("arguments[0].scrollIntoView();", shippingMethodField);
	        Thread.sleep(5000);
	        actions.moveToElement(btnShippingChoose).build().perform();
	        Thread.sleep(4000);
	        
	        btnShippingChoose.click();
		    Thread.sleep(2000);
		    actions.moveToElement(flatOption).build().perform();
		    Thread.sleep(2000);
			flatOption.click();
			Thread.sleep(2000);			
			actions.moveToElement(btnContinueShippingForm).build().perform();
			Thread.sleep(2000);
			btnContinueShippingForm.click();
			Thread.sleep(2000);			
		}
		
		public void choirsirPayment () throws InterruptedException {
			Actions actions = new Actions(driver);						
	        actions.moveToElement(driver.findElement(By.id("input-payment-method"))).build().perform();	        			
	        Thread.sleep(2000);
	        
			actions.moveToElement(btnPaymentChoose).build().perform();
			Thread.sleep(2000);
			btnPaymentChoose.click();
			Thread.sleep(2000);
			
			actions.moveToElement(paymentOption).build().perform();
			Thread.sleep(2000);
			paymentOption.click();
			Thread.sleep(2000);	
			actions.moveToElement(btnContinuePaymentForm).build().perform();
			Thread.sleep(2000);
			btnContinuePaymentForm.click();
			Thread.sleep(2000);			
		}
		
		public void confirmerCommande () throws InterruptedException {
			Actions actions = new Actions(driver);						
			actions.moveToElement(btnConfirm).build().perform();
			Thread.sleep(2000);
			btnConfirm.click();
			Thread.sleep(10000);
		}
		
		public String orderSuccessful() {
			System.out.println("Order Succes: " + orderSuccesMessage.getText());
			return orderSuccesMessage.getText();
		}
		
		public void choisirExistingAddress() throws InterruptedException {
			
			WebElement selectAddress = driver.findElement(By.id("input-shipping-address"));
			selectAddress.click();
			Thread.sleep(2000);
			List<WebElement> optionAddress = driver.findElements(By.xpath("//option"));
			optionAddress.get(1).click();
			Thread.sleep(5000);
			
		}
		
		public void succesUpdatedAddressMessage() throws InterruptedException {
			By messLocator = By.xpath("//*[@id='alert']/div[@class='alert alert-success alert-dismissible' and contains(text(),'Success')]");
			//wait.until(ExpectedConditions.visibilityOf(driver.findElement(messLocator)));
			System.out.println(driver.findElement(messLocator).getText());
			//Close the alert message
			driver.findElement(By.xpath("//button[@class='btn-close']")).click();
			Thread.sleep(5000);
			
		}
		
		public void choisirNewAddressOption() throws InterruptedException {			
			WebElement selectAddress = driver.findElement(By.id("input-shipping-new"));
			selectAddress.click();
			Thread.sleep(2000);
			//wait.until(ExpectedConditions.visibilityOf(shippingFirstName));			
		}
		
		public void formNewAddress(String firstName, String lastName, String address, String city, String code, String countryOption, String zoneName) throws InterruptedException {
			
			shippingFirstName.sendKeys(firstName);
			shippingLastName.sendKeys(lastName);
			
			shippingAdress.sendKeys(address);
			cityLocator.sendKeys(city);
			codePostalLocator.sendKeys(code);
			Thread.sleep(2000);
			Actions actions = new Actions(driver);
			actions.moveToElement(countryLocator).build().perform();
			countryLocator.click();
			Thread.sleep(10000);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			String optionCountry = countryOptionP1 + countryOption + countryOptionP2;			
			WebElement countryName = driver.findElement(By.xpath(optionCountry));			
			// Scrolling down the page till the element is found		
	        js.executeScript("arguments[0].scrollIntoView();", countryName);
	        Thread.sleep(5000);	
	        System.out.println("Test ---------" + countryName.getText());
			countryName.click();						
			Thread.sleep(5000);			
			actions.moveToElement(regionLocator).build().perform();
			Thread.sleep(2000);
			regionLocator.click();
			
			Thread.sleep(2000);
			 
			String optionRegion = zoneOptionP1 + zoneName + zoneOptionP2;
			WebElement regionElement = driver.findElement(By.xpath(optionRegion));
			actions.moveToElement(regionElement).build().perform();
			regionElement.click();			
			Thread.sleep(2000);	
			
			actions.moveToElement(btnContinue).build().perform();
			btnContinue.click();
			Thread.sleep(2000);
			messageSucces();			
		}

}

package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage {
	WebDriver driver;
	// Locator variables
	
	@FindBy(xpath="//a[contains(text(),'Checkout')]")
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
	@FindBy(id="input-shipping-country")
	WebElement country;	
	String countryOptionP1="//option[contains(text(),'";
	String countryOptionP2="')]";
	@FindBy(id="input-shipping-zone")
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
	@FindBy(id="input-shipping-method")
	WebElement shippingMethodInput;
	@FindBy(id="input-payment-method")
	WebElement paymentMethodInput;
	
	@FindBy(id="button-shipping-methods")
	WebElement btnShippingMethod;
	@FindBy(id="button-payment-methods")
	WebElement btnPaymentMethod;
	
	@FindBy(xpath="//button[contains(text(), 'Confirm')]")
	WebElement btnConfirm;
	
	
	
	public CheckoutPage(WebDriver driver) {
		this.driver = driver;
	}
	
	//if user choose view cart before checkout
	public void goToCheckoutPage() {		
		btnCheckout.click();
	}
	
	public void infoFormRegisterOption(String firstName, String lastName, String email, String address, String city, String code, String countryOption, String zoneName, String pass) {
		registerOption.click();
		firstNameLocator.sendKeys(firstName);
		lastNameLocator.sendKeys(lastName);
		emailLocator.sendKeys(email);
		shippingAdress.sendKeys(address);
		cityLocator.sendKeys(city);
		codePostalLocator.sendKeys(code);
		country.click();
		String optionCountry = countryOptionP1 + countryOption + countryOptionP1;
		driver.findElement(By.xpath(optionCountry)).click();
		regionLocator.click();
		String optionRegion = zoneOptionP1 + zoneName + zoneOptionP2;
		driver.findElement(By.xpath(optionRegion)).click();
		passLocator.sendKeys(pass);
		
		newsletterLocator.click();
		btnContinue.click();
		btnContinue.click();
		
	}
	
	

}

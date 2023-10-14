package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	WebDriver driver;
	
	
	@FindBy(xpath = "//span[text()='My Account']")
	private WebElement linkMyAccount;
	
	
	@FindBy(xpath = "//a[@href='http://localhost/opencart/upload/index.php?route=account/login&language=en-gb']")
	private WebElement linkLogin;
	
	
	@FindBy(xpath = "//a[text()='Register']")
	private WebElement linkRegister;
	
	
	
	
	

	public HomePage(WebDriver driver) {

		this.driver = driver;

		PageFactory.initElements(driver, this);
	}
	
	
	public void clickOnMyAccountLink() {

		linkMyAccount.click();

	}
	
	public void clickOnLoginLink() {

		linkLogin.click();

	}	
	
	public void clickOnRegisterLink() {

		linkRegister.click();

	}
	

}

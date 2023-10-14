package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;

public class LoginPage {
	WebDriver driver;

	@FindBy(id="input-email")
	private WebElement emailAdress;

	@FindBy(id="input-password")
	private WebElement passWord;

	@FindBy(xpath="//button[text()='Login']")
	private WebElement loginButton;

	@FindBy(css=".alert-dismissible")
	private WebElement warningLogin;
	@FindBy(xpath="//h2[contains(text(),'My Account')]")
	private WebElement succesLogin;

// constructeur
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// creer les methodes pour les manipulations

	public void saisirEmail(String email) {
		try {
			emailAdress.clear();

			emailAdress.sendKeys(email);
		}
		catch(Exception e) {
			System.out.println("Cannot find locator email" + email);
		}
	}

	public void saisirPassword(String pass) {
		try {
			passWord.clear();
			passWord.sendKeys(pass);
		}
		catch (Exception e) {
			System.out.println("Can not find locator password " +pass );
		}

	}

	public void clickOnLoginButton() {
		loginButton.click();
	}
	
	public String getSuccesLoginMessage() {
		
		String mess = succesLogin.getText();

		return mess;

	}

	public String getErrorLoginMessage() {
		String mess = warningLogin.getText();
		return mess;

	}

	public void login(String email, String pass) {
		emailAdress.clear();
		emailAdress.sendKeys(email);
		passWord.clear();
		passWord.sendKeys(pass);
		loginButton.click();
	}

}

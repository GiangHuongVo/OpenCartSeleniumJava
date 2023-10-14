package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	WebDriver driver ;
	
	public RegisterPage(WebDriver driver) {
		
		this.driver = driver;
		// la classe PageFctory initilise , on doit toujours la jouter dans cette classe 
		PageFactory.initElements(driver, this);
	}

	// creer des webelemts prives qui constituent la page "register"
	
	// utiliser le concept de page factory (reperez chaque element par @FindBy
	@FindBy(id="input-firstname")
	private WebElement firstNameField;
	
	
	@FindBy(id="input-lastname")
	private WebElement lastNameField;
	
	
	@FindBy(id="input-email")
	private WebElement emailField;
	
	
	@FindBy(id="input-password")
	private WebElement passwordField;
	
	
	@FindBy(css="button[type='submit']")
	private WebElement btnContinue;
	
	@FindBy(id="input-newsletter")
	private WebElement subscribeNewsLetter;
	
	
	@FindBy(xpath="//input[@name='agree']")
	private WebElement privacyPolicyField;	
	
	@FindBy(id="error-firstname")
	private WebElement errorFirstName;
	
	@FindBy(id="error-lastname")
	private WebElement errorLastName;
	
	@FindBy(id="error-email")
	private WebElement errorEmailName;
	
	@FindBy(id="error-password")
	private WebElement errorPasswordName;
	
	@FindBy(id="alert")
	private WebElement policyWarning;
	
	@FindBy(xpath="//a[text()='login page']")
	private WebElement loginPageRedirection;
	// saisir le firstname
	
	public void enterFistName(String fname) {
		//firstNameField est un webelement grace a @findby
		firstNameField.sendKeys(fname);
	}
	
	
	public void enterLastName(String lname) {
		lastNameField.sendKeys(lname);
		
	}
	
	public void enterEmail(String email) {
		emailField.sendKeys(email);
		
	}
	
	public void enterPassword(String passw) {
		 passwordField.sendKeys(passw);
		
	}
	
	public void selectSubscribe() {
		
		subscribeNewsLetter.click();	
	}
	

	public void agreePolicy() {
		privacyPolicyField.click();
			
	}
	
	public void clickOnContinueButton() {
		btnContinue.click();
			
	}
	
	public void naviagteToLoginPage() {
		loginPageRedirection.click();
			
	}
	
	// les methodes pour les messages d erreurs 
	
	public String getFirstNameError() {
		System.out.println("First Name error: " + errorFirstName.getText());
		return  errorFirstName.getText();
	}
	
	public String getLastNameError() {
		System.out.println("Last Name error: " + errorLastName.getText());
		return  errorLastName.getText();
	}
	
	public String getEmailError() {
		System.out.println("Email error: " + errorEmailName.getText());
		return  errorEmailName.getText();
	}
	
	public String getPasswordError() {
		System.out.println("Password error: " + errorPasswordName.getText());
		return  errorPasswordName.getText();
		
	}
	public String getPrivacyPolicyError() {
		System.out.println("Privacy Policy error: " + policyWarning.getText());
		return  policyWarning.getText();
		
	}
	
	// creer une methode (preaparation du test) pour la saise de tous les champs obligatoires
	public void registerWithRequiredFields(String fname , String lname , String email, String password) throws InterruptedException {
		firstNameField.sendKeys(fname);
		lastNameField.sendKeys(lname);
		emailField.sendKeys(email);
		passwordField.sendKeys(password);
		Thread.sleep(4000);
		passwordField.sendKeys(Keys.ENTER);
			
		Actions actions = new Actions(driver);
		actions.keyDown(Keys.TAB).click(privacyPolicyField);
		actions.keyDown(Keys.TAB).click(btnContinue);		
	}
	
	public void registerWithAllRequiredFields(String fname , String lname , String email, String password) throws InterruptedException {
		firstNameField.sendKeys(fname);
		lastNameField.sendKeys(lname);
		emailField.sendKeys(email);
		passwordField.sendKeys(password);
		passwordField.sendKeys(Keys.ENTER);
		Thread.sleep(4000);
		Actions actions = new Actions(driver);
		// NewLetter
		actions.keyDown(Keys.TAB).click(subscribeNewsLetter);		
		Thread.sleep(4000);		
		actions.keyDown(Keys.TAB).click(privacyPolicyField);
		actions.keyDown(Keys.TAB).click(btnContinue);
		
	}	
	
	public void registerWithNonPolicy(String fname , String lname , String email, String password) throws InterruptedException {
		firstNameField.sendKeys(fname);
		lastNameField.sendKeys(lname);
		emailField.sendKeys(email);
		passwordField.sendKeys(password);
		Thread.sleep(4000);
		Actions actions = new Actions(driver);
		actions.moveToElement(subscribeNewsLetter).build().perform();
		// NewLetter
		actions.keyDown(Keys.TAB).click(subscribeNewsLetter);		
		Thread.sleep(4000);		
		//actions.keyDown(Keys.TAB).click(privacyPolicyField);
		actions.keyDown(Keys.TAB).click(btnContinue);		
		getPrivacyPolicyError();
	}	


}

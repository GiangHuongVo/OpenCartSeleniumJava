package testcases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import base.Base;
import pages.HomePage;
import pages.LoginPage;
import pages.RegisterPage;

public class TestRegister extends Base{
	public WebDriver driver;
	public LoginPage loginPage;
	public HomePage homePage;
	public RegisterPage resgister;
	
	@BeforeMethod
	public void setup() {
		driver = initialiserNavigate(prop.getProperty("browserName"));		
		homePage = new HomePage(driver);
		homePage.clickOnMyAccountLink();
		homePage.clickOnRegisterLink();
		resgister = new RegisterPage(driver);
	}
	
	@Test
	public void registerTestSucces() throws InterruptedException {
		resgister.registerWithRequiredFields("Lala", "vO", "lola205@mina.cn", "12345");
	}
	
	@Test
	public void registerWithNewLetterSucces() throws InterruptedException {
		resgister.registerWithAllRequiredFields("Lala", "vO", "lola206@mina.cn", "12345");
	}
	
	@Test
	public void registerWithInValidEmail() throws InterruptedException {
		resgister.registerWithRequiredFields("Lala", "vO", "sfsdf", "12345");
		resgister.getEmailError();
	}
	
	@Test
	public void registerWithEmailDejaExist() throws InterruptedException {
		resgister.registerWithRequiredFields("Lala", "vO", "lola200@mina.cn", "12345");
		resgister.getEmailError();
	}
	
	@Test
	public void registerWithEmptyFirstName() throws InterruptedException {
		resgister.registerWithRequiredFields("", "vO", "lola203@mina.cn", "12345");
		resgister.getFirstNameError();
	}
	
	@Test
	public void registerWithAllFieldEmpty() throws InterruptedException {
		resgister.registerWithRequiredFields("", "", "", "");
		resgister.getFirstNameError();
		resgister.getLastNameError();
		resgister.getPasswordError();
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
}

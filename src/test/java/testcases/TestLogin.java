package testcases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import base.Base;
import pages.HomePage;
import pages.LoginPage;
import pages.RegisterPage;
import utils.Utilitaire;

public class TestLogin extends Base{
	// heritage pour avoir acces a la classe Base
	public WebDriver driver;
	public LoginPage loginPage;
	

		// preconditions(s execute avant chaque test)
	@BeforeMethod
	public void setup() {			
		driver = initialiserNavigate(prop.getProperty("browserName"));
		loginPage = new LoginPage(driver);
	}

		// postconditions : s execute apres chaque test)
		@AfterMethod
		public void tearDown() {
			driver.quit();
		}

		@Test
		public void verifyLoginWithValidCredential() throws InterruptedException {						
			loginPage.saisirEmail(propData.getProperty("valideEmail"));
			loginPage.saisirPassword(propData.getProperty("validePassword"));
			loginPage.clickOnLoginButton();
			loginPage.getSuccesLoginMessage();
			Thread.sleep(4000);

		}
		
		@Test
		public void testLoginAccountFromRegister() {
			loginPage.login("lola201@mina.cn", "12345");
		}
		
		@Test
		public void verifyLoginWithNonValidCredential() throws InterruptedException {						
			loginPage.saisirEmail(propData.getProperty("inValideEmail"));
			loginPage.saisirPassword(propData.getProperty("inValidePassword"));
			loginPage.clickOnLoginButton();
			loginPage.getErrorLoginMessage();
			Thread.sleep(4000);

		}	
		
}

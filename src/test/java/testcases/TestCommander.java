package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import base.Base;
import pages.CheckoutPage;
import pages.CommandePage;
import pages.HomePage;
import pages.LoginPage;
import pages.RegisterPage;

public class TestCommander extends Base{
	public WebDriver driver;
	public CommandePage commande;
	public LoginPage login;
	
	
	@BeforeClass
	public void setup() {
		driver = initialiserNavigate(prop.getProperty("browserName"));	
		login = new LoginPage(driver);
		commande = new CommandePage(driver);		
	}
	
	@Test
	public void commanderSuccesNonLogin() throws InterruptedException {
		commande.choisirCategory("Phone");
		Thread.sleep(3000);
		commande.productDetail("HTC Touch HD");
		Thread.sleep(3000);
		commande.addCart("2");
		Thread.sleep(5000);
		commande.goToCheckoutPageByViewCart();
		Thread.sleep(5000);
		commande.goToCheckoutPage();
		Thread.sleep(5000);
		commande.infoFormRegisterOption("Lola", "Mina", "lola59@mina.cn", "13 add", "Montreal", "H1A 3D1", "Canada", "Québec", "12345");
		Thread.sleep(5000);
		commande.choirsirShipping();
		Thread.sleep(5000);
		commande.choirsirPayment();
		Thread.sleep(5000);
		commande.confirmerCommande();
		Thread.sleep(5000);
		commande.orderSuccessful();
		Thread.sleep(3000);
	}
	
	@Test
	public void commanderSuccesAvecLoginExistingAddress() throws InterruptedException {
		login.login("lola53@mina.cn", "12345");
		Thread.sleep(4000);
		commande.choisirCategory("Phone");
		Thread.sleep(5000);
		
		commande.imageProduct("Palm Treo Pro");
		Thread.sleep(3000);
		
		commande.addCart("2");
		Thread.sleep(6000);
		commande.goToCheckoutPageByViewCart();
		Thread.sleep(5000);
		commande.goToCheckoutPage();
		Thread.sleep(5000);
		
		// Avec existing address, pas besoin de choisir option address				
		commande.choisirExistingAddress();
		Thread.sleep(5000);
		// Fermer l'alert message
		commande.succesUpdatedAddressMessage();
		Thread.sleep(3000);
		
		commande.choirsirShipping();
		Thread.sleep(5000);
		
		commande.choirsirPayment();
		Thread.sleep(5000);
		commande.confirmerCommande();
		Thread.sleep(5000);
		commande.orderSuccessful();
		Thread.sleep(3000);
	}
	
	@Test
	public void commanderSuccesAvecLoginNewAddress() throws InterruptedException {
		login.login("lola53@mina.cn", "12345");
		Thread.sleep(3000);
		commande.choisirCategory("Phone");
		Thread.sleep(5000);
		commande.productDetail("iPhone");
		Thread.sleep(3000);
		
		commande.addCart("1");
		Thread.sleep(5000);
		commande.goToCheckoutPageByViewCart();
		Thread.sleep(5000);
		commande.goToCheckoutPage();
		Thread.sleep(5000);
		
		// Avec new address, besoin de choisir option address
		commande.choisirNewAddressOption();
		Thread.sleep(5000);
		// Fill the form new address
		commande.formNewAddress("Lucy", "Kisa", "13 add", "Montreal", "H1A 3D2", "Canada", "Québec");
		Thread.sleep(5000);
		// Method shipping
		commande.choirsirShipping();
		Thread.sleep(5000);
		commande.choirsirPayment();
		Thread.sleep(5000);
		commande.confirmerCommande();
		Thread.sleep(5000);
		commande.orderSuccessful();
		Thread.sleep(3000);
	}
	
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}

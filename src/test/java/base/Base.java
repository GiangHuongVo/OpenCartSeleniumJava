//les tests que nous allons creer vont tous heriter de cette classe de base 
//par consequent ils auront acces aux donnes des fichiers config
package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

// c est la classe qui va contenir les setup et les teardown , les classes communes a tous les tests
// ici on va lire les properties et les utiliser dans un cas de test
public class Base {
	WebDriver driver;
	public Properties prop;
	public Properties propData;
	
	public Base() {		
		prop = new Properties();
		propData = new Properties();
		
		File propFile = new File(System.getProperty("user.dir") + "\\src\\main\\java\\config\\config.properties");
		File dataPropFile = new File(System.getProperty("user.dir") + "\\src\\main\\java\\testData\\testData.properties");
		
		try {
			FileInputStream dataFile = new FileInputStream(propFile);
			prop.load(dataFile);
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		catch (Throwable e) {
			System.out.println(e.getMessage());
		}
		
		try {
			FileInputStream dataFileProp = new FileInputStream(dataPropFile);
			propData.load(dataFileProp);
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		catch (Throwable e) {
			System.out.println(e.getMessage());
		}
	}
	
	public WebDriver initialiserNavigate (String browserName) {
	
		if(browserName.equalsIgnoreCase("chrome")){
			driver = new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("edgedriver")){
			driver = new EdgeDriver();
		}
		else{
			driver = new FirefoxDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(prop.getProperty("qaUrl"));
		
		return driver;
	}
	// Time stamp local
	public static String  genererEmailTimeStamp() {
		Date date = new Date();
		String timeStamp = date.toString().replace(" ", "_").replace(":","_");		
		return "G:" + timeStamp;
	}
}

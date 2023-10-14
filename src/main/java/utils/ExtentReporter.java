package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {
	 // methode pour genrer notre rapport 
		public static ExtentReports generateExtentReport() throws IOException {
			ExtentReports extentReports = new ExtentReports();
			//utiliser la classe File pour manipuler le rapport generé
			// specifier le chemin du rapport qu on va genere ,
			File extentReportFile = new File(System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReports.html");
			
			// cr eation de l objet  sparkReporter qui va personnaliser notre extentReports
			ExtentSparkReporter  sparkReporter = new ExtentSparkReporter(extentReportFile); 
			//on va personnaliser notre rapporteur 
			sparkReporter.config().setTheme(Theme.DARK);
			sparkReporter.config().setReportName("faycal automation report");
			sparkReporter.config().setTimeStampFormat("dd//mm/yyyy hh:mm:ss");
			sparkReporter.config().setDocumentTitle("framework hybride d'automatisation des tests");
			
			//attacher sparkReporter a extentReports
			extentReports.attachReporter(sparkReporter);
			
			// creation d un objet de type Properties pour ajouter des infos utils (url ...)
			Properties prop = new Properties();
			File propFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\config\\config.properties");
			//pour lire a partir du fichier propFile qui est un objet qui stockee le fichier config.properties
			FileInputStream fis = new FileInputStream(propFile) ;
			//charger l'objet prop
			prop.load(fis);
			// recuperer les donnees qu on veut afficher dans notre rapport de test a partir de notre suystem 
			extentReports.setSystemInfo("url de l'application sous test", prop.getProperty("qaUrl"));
			extentReports.setSystemInfo("browser utilié pour nos  test", prop.getProperty("browserName"));
			//os.name donne automatiquement le nom de l os , mais il faut les appeler avec  System.getProperty et pas :prop.getProperty
			extentReports.setSystemInfo("operating system utilié pour nos  test", System.getProperty("os.name"));
			extentReports.setSystemInfo("le realisateur du framnework ", System.getProperty("user.name"));
			extentReports.setSystemInfo("la version de java utilisé", System.getProperty("java.version"));
			
			
			
			
			return extentReports ;
		}
		
		
		// sur ce lien on trouve le exentsparkreporter :https://www.extentreports.com/docs/versions/5/java/index.html
		//on click :getting started -> reporters : ExtentSparkReporter spark = new ExtentSparkReporter("Spark.html");
		
		

}

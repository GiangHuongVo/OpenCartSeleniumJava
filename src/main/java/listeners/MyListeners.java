package listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import utils.ExtentReporter;
import utils.Utilitaire;

// implementer une interface ITestListner 
// on l'ouvre avec ctrl et clique sur ITestListener pour importer les methodes 
//dans le fichiers testng.xml on ajoute la balise <listners>
//faut copier tous ce qu il y a dans le pom et le fichier testng.xml pour les reutiliser dans un autre projet
// pour executer avec maven test : click droit sur le projet , run as : 7 maven test 
public class MyListeners implements ITestListener {
// creer un objet de type ExtentReports 
	ExtentReports extentreport;
	// objet offert par testng
	ExtentTest extentTest;

	@Override
	public void onTestStart(ITestResult result) {
		// System.out.println("test start");
		extentTest = extentreport.createTest(result.getName());
		extentTest.log(Status.INFO, result.getName() + "start executing");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// System.out.println("test success");
		extentTest.log(Status.INFO, result.getName() + "test succesfully executed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// System.out.println("result failure");
		WebDriver driver = null;
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver")
					.get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String destinationScreenShotPatch = Utilitaire.captureScrenShot(driver, result.getName());
		extentTest.addScreenCaptureFromPath(destinationScreenShotPatch);
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.FAIL, result.getName() + "test failed ");

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// System.out.println("test skipped");
		// extentTest = extentreport.createTest(result.getName());
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.INFO, result.getName() + "test skipped");
	}

	@Override
	public void onStart(ITestContext context) {
		// System.out.println("on start");
		try {
			extentreport = ExtentReporter.generateExtentReport();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onFinish(ITestContext context) {
		extentreport.flush();
		String pathOfExrentReport = System
				.getProperty(System.getProperty("user.dir") + "\\test-output\\ExtentReports\\extentReports.html");
		File myReport = new File(pathOfExrentReport);
		try {
			Desktop.getDesktop().browse(myReport.toURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

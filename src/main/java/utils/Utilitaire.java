package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;

public class Utilitaire {
	
	// creeation des utilitaires:

		// une fonction qui utilise le time stamp local pour genener des emails
		// passwords
		// static :pour qu elle soit commune a tous les objets crees
		// cette fonction permet de creer des email en fonction de la date et l heure ,
		// et ce pour avoir des email qui se ressemble pas

		public static String genererEmailAvecTimeStamp() {
			// importer Date depuis java.util
			Date date = new Date();
			// on convertit l objet date en une chaine de caractere
			// on remplace les espaces par _ et les : par _
			String timeStamp = date.toString().replace(" ", "_").replace(":", "_");

			return "nabil" + timeStamp + "@gmail.com";

		}

		// on cree un fichier excel sur le bureau : ninjaTestData ou on va mettre nos
		// donnes
		// on copie notre fichier excel dans le package testData qui se trouve dans
		// src/main/java
		// on s assure qu on a la dependence de l api poi pour manipuler les fichier
		// excel ou pdf

		public static Object[][] getDataFromExcel(String sheetName) throws IOException {
			// specifier le chemin de notre fichier excel
			// notre fichier rend notre fichier excel comme un objet File pour le manipuler
			// on ajoute System.getProperty("user.dir") pour ne pas dependre de l
			// emplacement de ma machine
			File excelFile = new File(System.getProperty("user.dir") + "\\src\\main\\java\\testData\\datapool.xlsx");

			// pour lire donnees on utulise
			FileInputStream fis = new FileInputStream(excelFile);
			// manipuler nos deonnes excel dans 'fis avec l'objet workbook
			// XSSFWorkbook c est une classe de l api poi pour manipuler les fichiers excel
			// workbook est un fichier excel
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			// creer un objet de type sheet
			XSSFSheet sheet = workbook.getSheet(sheetName); // sheetName est l argument de la methode principale

			// on recupere le nombre de ligne et de colonne qui comporte des donnes dans le
			// fichier excel
			int rows = sheet.getLastRowNum();
			int column = sheet.getRow(0).getLastCellNum();
			// creation du tableau
			Object[][] data = new Object[rows][column];
			// remplissage du tableau
			for (int i = 0; i < rows; i++) {
				XSSFRow row = sheet.getRow(i + 1); // i+1 pour eliminer la premiere ligne qui contient les entetes
				for (int j = 0; j < column; j++) {
					XSSFCell cell = row.getCell(j);
					// dans cellType on stocke un string, un char ou boolean
					CellType cellType = cell.getCellType(); // on importe CellType avec poi "ss"
					// notre est de type object parceque peut reoturner string , numric ou boolean
					// donc pour eviter tous ca on met object car il englobe le tous
					// tableau deux dimensions car on a ligne et colonne , la premiere dimenssion
					// concerne les lignes , la deuxieme les colonnes
					switch (cellType) {
					case STRING:
						data[i][j] = cell.getStringCellValue();
						break;
					case NUMERIC:
						data[i][j] = cell.getNumericCellValue();// Integer.toString((int) cell.getNumericCellValue())
						break;
					case BOOLEAN:
						data[i][j] = cell.getBooleanCellValue();
						break;

					}
				}
			}
			// on va faire l appelle de ctte fonction dans Dema.java (on travaillera avec
			// dataprovider
			return data;

		}

		
		public static String captureScrenShot(WebDriver driver, String testName) {

			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			// chemin du dossier ou seront stockes les screenshots , il va creer un dossier
			// ScreenShots et mettre le screenshot dans un fichier testName+".png"
			String destinationScreenShotPath = System.getProperty("user.dir") + "\\ScreenShots\\" + testName + ".png";
			try {
				// copier le screenshot dans le fichier testName+".png
				FileHandler.copy(screenshot, new File(destinationScreenShotPath));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return destinationScreenShotPath;
		}
		
		public static boolean isElementVisible(WebElement locator){
		    return locator.isDisplayed();
		}

}

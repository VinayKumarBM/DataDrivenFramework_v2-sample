package com.ddf.test;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import com.dff.configs.DriverManager;
import com.dff.configs.GlobalVariables;
import com.dff.listeners.TestListener;
import com.dff.reports.ReportManager;
import com.dff.utils.Directory;
import com.dff.utils.ExcelUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

@Listeners({TestListener.class})
public class TestBase implements GlobalVariables{
	protected WebDriverWait wait;

	@BeforeSuite
	public void configurations(ITestContext context) {
		Directory dir = new Directory();
		dir.clearFolder(DOWNLOAD_FOLDER);
		dir.clearFolder(SCREENSHOT_FOLDER);
		dir.clearFolder(ALLURE_RESULTS);
	}

	@BeforeMethod (alwaysRun = true)
	public void setup(Method method) {
		WebDriverManager.chromiumdriver().setup();
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();  
		chromePrefs.put("download.default_directory", DOWNLOAD_FOLDER);  
		ChromeOptions options = new ChromeOptions();  
		options.setExperimentalOption("prefs", chromePrefs);  
		options.addArguments("--disable-notifications");  
		DesiredCapabilities cap = DesiredCapabilities.chrome();  
		cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);  
		cap.setCapability(ChromeOptions.CAPABILITY, options); 
		WebDriver driver = new  ChromeDriver(options);
		driver.get(BASE_URL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, EXPLICIT_WAIT);
		DriverManager.getInstance().setDriver(driver);
		ReportManager.startTest(method.getClass().getName());
	}

	@AfterMethod (alwaysRun = true)
	public void tearDown() {
		DriverManager.getInstance().getDriver().quit();
	}

	public Object[][] getTestData(String className) throws Exception{		
		ExcelUtil excel = new ExcelUtil();
		excel.setExcelFile(DATA_FOLDER + WORKBOOK);		
		int testRow = excel.getNumberOfRows(className);
		Object[][] data = new Object[testRow-1][1];
		for(int i=1; i<testRow; i++) {
			data[i-1][0] = excel.getData(className, i);
		}
		return data;
	}

	protected void pause(int sec) {
		try {
			Thread.sleep(sec*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}	
}

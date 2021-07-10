package com.dff.reports;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.dff.configs.GlobalVariables;

public class ReportManager implements GlobalVariables{
	
	private static final Logger LOG = LogManager.getLogger(ReportManager.class);
	private static ExtentReports extent;
	static Map<Integer, ExtentTest> extentTestMap = new HashMap<Integer, ExtentTest>();

	//Create an extent report instance
	private static ExtentReports createInstance() {
		ExtentSparkReporter htmlReporter = new ExtentSparkReporter(EXTENT_REPORT);
		try {
			htmlReporter.loadXMLConfig(new File(EXTENT_CONFIG));
		} catch (IOException e) {
			LOG.error("Issue while configuring extent report: ", e);
		}
		extent = new ExtentReports();
		extent.setSystemInfo("User: ", System.getProperty("user.name"));	
		extent.setSystemInfo("URL: ", BASE_URL);
		extent.setSystemInfo("Browser: ", "Chrome");
		extent.setSystemInfo("OS: ", System.getProperty("os.name"));
		extent.setSystemInfo("Version: ", System.getProperty("os.version"));		
		extent.attachReporter(htmlReporter); 
		return extent;
	}

	private static ExtentReports getInstance() {
		if (extent == null) {
			createInstance();
		}
		return extent;
	}

	public static synchronized ExtentTest getTest() {
		return (ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId()));
	}

	public static synchronized void endTest() {
		getInstance().flush();
	}

	public static synchronized ExtentTest startTest(String testName) {
		ExtentTest test = getInstance().createTest(testName);
		extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
		return test;
	}
}

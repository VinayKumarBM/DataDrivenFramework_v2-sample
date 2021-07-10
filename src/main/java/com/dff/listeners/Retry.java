package com.dff.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;
import com.dff.configs.GlobalVariables;
import com.dff.utils.ScreenshotUtil;

public class Retry implements IRetryAnalyzer, GlobalVariables {
	private int count  = 0;
	private static int repeats = MAX_TRY; //Run the failed test 2 times

	@Override
	public boolean retry(ITestResult iTestResult) {
		System.out.println("In retry method");
		if (count < repeats) {                           //Check if maxTry count is reached
			count++;                                    //Increase the maxTry count by 1
			ScreenshotUtil.attachScreenshotToReport(iTestResult, Status.INFO);   //ExtentReports fail operations
			return true;                                //Tells TestNG to re-run the test
		}
		return false;
	}
}
package com.ddf.test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.dff.configs.DriverManager;
import com.dff.steps.HomePageSteps;
import com.dff.steps.LoginPageSteps;
import com.dff.steps.OrderPageSteps;
import com.dff.utils.Log;
import com.dff.utils.ScreenshotUtil;

import io.qameta.allure.Description;

public class PDFInvoiceTest extends TestBase{

	@Test(testName = "PDF Invoice Test", description = "Verify the details on the PDF Invoice of an Order")
	@Description("Test Description: To verify the details on the PDF Invoice of an Order")
	public void pdfInvoiceTest() throws Exception {
		WebDriver driver = DriverManager.getInstance().getDriver();
		HomePageSteps homePageSteps = new HomePageSteps(driver);
		homePageSteps.navigateToLoginPage();
		new LoginPageSteps(driver).login(EMAIL, PASSWORD);
		homePageSteps.navigateToUserAccount();
		OrderPageSteps orderPageSteps = new OrderPageSteps(driver);
		orderPageSteps.naviageToDetailsFromMyOrder();
		orderPageSteps.validatePDFInvoice();
		ScreenshotUtil.takeScreenShot(new Object(){}.getClass().getEnclosingMethod().getName());
		Log.info("Validated PDF invoice file from Order Details page");
		homePageSteps.logut();
	}
	
	@Test(testName = "PDF Invoice Test", description = "Verify the details on the PDF Invoice of Downloadable products")
	@Description("Test Description: To verify the details on the PDF Invoice of Downloadable products")
	public void pdfInvoiceOfDownloadableProductsTest() throws Exception {
		WebDriver driver = DriverManager.getInstance().getDriver();
		HomePageSteps homePageSteps = new HomePageSteps(driver);
		homePageSteps.navigateToLoginPage();
		new LoginPageSteps(driver).login(EMAIL, PASSWORD);
		homePageSteps.navigateToUserAccount();
		OrderPageSteps orderPageSteps = new OrderPageSteps(driver);
		orderPageSteps.naviageToDetailsFromDownloadableProducts();
		orderPageSteps.validatePDFInvoice();
		ScreenshotUtil.takeScreenShot(new Object(){}.getClass().getEnclosingMethod().getName());
		Log.info("Validated PDF invoice file from Downloadable products page");
		homePageSteps.logut();
	}
}

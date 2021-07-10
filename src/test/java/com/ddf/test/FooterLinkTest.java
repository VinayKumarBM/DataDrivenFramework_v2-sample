package com.ddf.test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.dff.configs.DriverManager;
import com.dff.steps.HomePageSteps;
import com.dff.utils.Log;

import io.qameta.allure.Description;

public class FooterLinkTest extends TestBase{
	
	@Test(testName = "Footer link Test", description = "To validate the footer links")
	@Description("Test Description: To validate the footer links")
	public void footerLinkTest() throws Exception {
		WebDriver driver = DriverManager.getInstance().getDriver();
		new HomePageSteps(driver).validateFooterlinks();
		Log.info("Validated the footer links");
	}
}

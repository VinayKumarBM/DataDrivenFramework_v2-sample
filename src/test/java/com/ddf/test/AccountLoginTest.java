package com.ddf.test;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.dff.configs.DriverManager;
import com.dff.steps.HomePageSteps;
import com.dff.steps.LoginPageSteps;
import com.dff.utils.Log;
import com.dff.utils.ScreenshotUtil;

import io.qameta.allure.Description;

public class AccountLoginTest extends TestBase{
	
	@Test(dataProvider = "testData", testName = "Account Login Test", description = "Account Login")
	@Description("Test Description: To validate login feature")
	public void accountLoginTest(Map<String, String> map) throws Exception {
		WebDriver driver = DriverManager.getInstance().getDriver();
		Log.info("Test Data: \n"+map);
		new HomePageSteps(driver).navigateToLoginPage();
		LoginPageSteps loginPageSteps = new LoginPageSteps(driver);
		loginPageSteps.enterLoginDetails(map.get("email"), map.get("password"));
		ScreenshotUtil.takeScreenShot(map.get("Test_Case"));
		loginPageSteps.clickLoginButton();
		loginPageSteps.verifyErrorMessage(map.get("message"));
	}
	
	@DataProvider(name = "testData")
	public Object[][] testData() throws Exception{
		return getTestData(this.getClass().getSimpleName());
	}
}

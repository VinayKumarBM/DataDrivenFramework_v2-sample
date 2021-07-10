package com.dff.steps;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import static org.testng.Assert.*;

import com.dff.pages.LoginPage;

import io.qameta.allure.Step;

public class LoginPageSteps {

	private Logger log = LogManager.getLogger(this.getClass().getName());
	private LoginPage loginPage;
	
	public LoginPageSteps(WebDriver driver) {
		this.loginPage = new LoginPage(driver);
	}

	@Step("Enter login credentials: {0} | {1}")
	public void enterLoginDetails(String email, String password) {
		log.info("Entering login details");
		loginPage.enterEmail(email);
		loginPage.enterPassword(password);
	}
	
	@Step("Click on Login button")
	public void clickLoginButton() {
		loginPage.clickLoginButton();
	}
	
	@Step("Verifying the error message for invalid credentials")
	public void verifyErrorMessage(String errorMessage) {
		assertEquals(loginPage.getErrorMessage(), errorMessage, "Error message did not match");
		log.info("Displayed Error Message: "+errorMessage);
	}
	
	@Step("Login into the shop with credentials: {0}/{1}")
	public void login(String email, String password) {
		enterLoginDetails(email, password);
		clickLoginButton();
		assertTrue(loginPage.isUserEmailDisplayed(email), "User login failed");
	}
}

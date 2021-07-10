package com.dff.steps;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.dff.pages.RegisterPage;

import io.qameta.allure.Step;

public class RegisterPageSteps {
	
	private Logger log = LogManager.getLogger(this.getClass().getName());
	private RegisterPage registerPage; 
	
	public RegisterPageSteps(WebDriver driver) {
		this.registerPage = new RegisterPage(driver);
	}
	
	@Step("Enter registration details: {0}, {1}, {2}, {3}, {4}, {5}, {6}, {7}, {8}")
	public String enterRegistrationDetails(String firstName, String lastName, String email, String gender, 
			String password, String confirmPassword) {
		log.info("Entering Registration details");
		registerPage.chooseAGender(gender);
		registerPage.enterFirstName(firstName);
		registerPage.enterLastName(lastName);
		String registeredEmail = String.format(email,System.currentTimeMillis());
		registerPage.enterEmail(registeredEmail );
		registerPage.enterPassword(password);
		registerPage.enterConfirmPassword(confirmPassword);
		return registeredEmail;
	}
	
	@Step("Save registration details")
	public void saveRegistrationDetails() {
		registerPage.clickRegisterButton();
	}
	
	@Step("Verify registration is completed")
	public void verifyRegistrationCompleted(String message) {
		Assert.assertEquals(registerPage.getRgistrationCompletedMessage(), message);
	}
}

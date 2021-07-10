package com.dff.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends BasePage{

	public RegisterPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(name = "FirstName")
	private WebElement firstNameTextbox;

	@FindBy(name = "LastName")
	private WebElement lastNameTextbox;
	
	@FindBy(name = "Email")
	private WebElement emailTextbox;
	
	@FindBy(name = "Password")
	private WebElement passwordTextbox;
	
	@FindBy(name = "ConfirmPassword")
	private WebElement confirmPasswordTextbox;
	
	@FindBy(name = "register-button")
	private WebElement registerButton;
	
	@FindBy(xpath = "//input[@value='Continue']")
	private WebElement continueButton;
	
	@FindBy(className = "result")
	private WebElement registrationCompletedText;
	
	private static final String GENDER_XPATH = "//input[@value='%s']";
	
	public void chooseAGender(String gender) {
		click(String.format(GENDER_XPATH, gender.toUpperCase()));
	}
	
	public void enterFirstName(String firstName) {
		clearAndEnterText(firstNameTextbox, firstName);
	}
	
	public void enterLastName(String lastName) {
		clearAndEnterText(lastNameTextbox, lastName);
	}
	
	public void enterEmail(String email) {
		clearAndEnterText(emailTextbox, email);
	}

	public void enterPassword(String password) {
		clearAndEnterText(passwordTextbox, password);
	}
	
	public void enterConfirmPassword(String confirmPassword) {
		clearAndEnterText(confirmPasswordTextbox, confirmPassword);
	}

	public void clickRegisterButton() {
		registerButton.click();
	}
	
	public void clickContinueButton() {
		continueButton.click();
	}
	
	public String getRgistrationCompletedMessage() {
		return registrationCompletedText.getText();
	}
}

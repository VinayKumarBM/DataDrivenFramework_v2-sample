package com.dff.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(name = "Email")
	private WebElement emailTextbox;
	
	@FindBy(name = "Password")
	private WebElement passwordTextbox;
	
	@FindBy(css = "input[value='Log in']")
	private WebElement loginButton;	
	
	@FindBy(css = "div[class='message-error'] li")
	private WebElement errorMessageText;

	private static final String USER_EMAIL_XPATH = "//*[text()='%s']";
	
	public void enterEmail(String email) {
		clearAndEnterText(emailTextbox, email);
	}
	
	public void enterPassword(String password) {
		clearAndEnterText(passwordTextbox, password);
	}
	
	public void clickLoginButton() {
		loginButton.click();
	}
	
	public String getErrorMessage() {
		return errorMessageText.getText();
	}
	
	public boolean isUserEmailDisplayed(String email) {
		return isElementDisplayed(By.xpath(String.format(USER_EMAIL_XPATH, email)));
	}
}

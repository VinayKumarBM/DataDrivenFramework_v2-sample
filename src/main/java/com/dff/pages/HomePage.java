package com.dff.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
	
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(linkText = "Log in")
	private WebElement loginLink;
	
	@FindBy(linkText = "Register")
	private WebElement  createAccountLink;
	
	@FindBy(linkText = "Log out")
	private WebElement  logoutLink;
	
	@FindBy(className = "account")
	private WebElement  userAccountLink;
	
	@FindBy(className = "ico-wishlist")
	private WebElement  wishlistLink;
	
	@FindBy(css = ".footer-menu-wrapper ul>li>a")
	private List<WebElement>  footerLinks;
		
	public void clickCreateAccountLink() {
		createAccountLink.click();
	}

	public void clickLoginLink() {
		loginLink.click();
	}
	
	public void clickLogoutLink() {
		logoutLink.click();
	}

	public boolean hasUserLoggedOut() {
		return isElementDisplayed(loginLink);
	}
	
	public String getLoggedInUserEmail() {
		return userAccountLink.getText();
	}
	
	public void clickUserAccountLink() {
		userAccountLink.click();
	}
	
	public int getNumberOfFooterLinks() {
		return footerLinks.size();
	}
	
	public String getFooterLinkText(int index) {
		return footerLinks.get(index).getText();
	}
	
	public String getFooterLink(int index) {
		return footerLinks.get(index).getAttribute("href");
	}
}

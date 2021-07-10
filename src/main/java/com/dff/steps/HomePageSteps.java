package com.dff.steps;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import com.dff.pages.HomePage;

import io.qameta.allure.Step;

public class HomePageSteps {

	private Logger log = LogManager.getLogger(this.getClass().getName());
	private HomePage home;

	public HomePageSteps(WebDriver driver) {
		this.home = new HomePage(driver);
	}

	@Step("Navigate to login page")
	public void navigateToLoginPage() {
		home.clickLoginLink();
	}

	@Step("Navigate to create account page")
	public void navigateToCreateAccount() {
		home.clickCreateAccountLink();
	}

	@Step("Navigate to user account page")
	public void navigateToUserAccount() {
		home.clickUserAccountLink();
	}

	@Step("Logout of the shop")
	public void logut() {	
		home.clickLogoutLink();
		assertTrue(home.hasUserLoggedOut(), "User was not logged out");
		log.info("Logged out of the Shop" );
	}

	@Step("Verify user {0} is logged in")
	public void verifyLoggedInUser(String email) {
		assertEquals(home.getLoggedInUserEmail(), email, "Logged in user name did not match");
		log.info("Logged into shop as "+email );
	}

	@Step("Validate footer links")
	public void validateFooterlinks() {
		SoftAssert softAssert = new SoftAssert();
		for(int i=0; i<home.getNumberOfFooterLinks(); i++) {
			softAssert.assertFalse(isLinkBroken(home.getFooterLink(i)), home.getFooterLinkText(i)+" link - "+home.getFooterLink(i)+" is broken");
		}		
		softAssert.assertAll();
		log.info("Successfully validated all the URL in the footer");
	}

	private boolean isLinkBroken(String url) {
		HttpURLConnection huc = null;
		if(url != null){		
			try {
				huc = (HttpURLConnection)(new URL(url).openConnection());
				huc.connect();
				int respCode = huc.getResponseCode();
				if(respCode > 199 && respCode < 400){
					return false;
				}			
			} catch (MalformedURLException e) {
				log.error("Malformed URL Exception: ", e);
			} catch (IOException e) {
				log.error("IO Exception: ", e);
			}
		}
		return true;
	}
}
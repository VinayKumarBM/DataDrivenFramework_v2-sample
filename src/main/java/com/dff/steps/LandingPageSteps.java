package com.dff.steps;

import static org.testng.Assert.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.dff.pages.LandingPage;

import io.qameta.allure.Step;

public class LandingPageSteps {

	private Logger log = LogManager.getLogger(this.getClass().getName());
	private LandingPage plp;

	public LandingPageSteps(WebDriver driver) {
		this.plp = new LandingPage(driver);
	}

	@Step("Search product: {0}")
	public void searchProduct(String product) {
		plp.enterProductToSearch(product);
		plp.clickSearchButton();
		assertTrue(plp.isAdvanceSearchDisplayed(), "Advance Search option was not displayed");
		log.info("Searched for product: "+product);
	}

	@Step("Validate search result")
	public void validateSearchResults(String product) {
		plp.getProdutResult().stream().forEach(prod -> assertTrue(prod.toLowerCase().contains(product.toLowerCase()), 
				"Search did fetched "+prod+" which did not contain "+product));
	}

	@Step("Navigate to shopping cart")
	public void navigateToCart() {
		plp.clickCartLink();
	}
	
	@Step("Add product to cart")
	public void addProductToCart() {
		plp.clickOnAddToCartButton();
	}

	@Step("Validate added to cart success message: {0}")
	public void validateAddToCartSuccess(String message) {
		assertEquals(plp.getAddToCartSuccessMessage(), message, "Product was not added to cart");
		closeAddToCartSuccessBanner();
	}

	public void closeAddToCartSuccessBanner() {
		plp.closeSuccessBanner();
		plp.clickHeaderLogo();
	}
}
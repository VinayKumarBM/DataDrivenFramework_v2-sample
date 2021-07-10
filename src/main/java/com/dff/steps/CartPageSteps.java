package com.dff.steps;

import static org.testng.Assert.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.dff.pages.CartPage;

import io.qameta.allure.Step;

public class CartPageSteps {

	private Logger log = LogManager.getLogger(this.getClass().getName());
	private CartPage cartPage;

	public CartPageSteps(WebDriver driver) {
		cartPage = new CartPage(driver);
	}

	@Step("Remove all the products in cart")
	public void removeAllProducts() {
		if(!cartPage.isCartQuantityZero()) {
			cartPage.selectItemToRemove();
			cartPage.clickOnUpdateCartButton();
			cartPage.waitForPageLoad();
		}		
		assertTrue(cartPage.isCartEmpty(), "Cart was not empty");
		assertTrue(cartPage.isCartQuantityZero(), "Cart quantity was not '0'");
		log.info("Cart is empty");
	}

	@Step("Validate Empty cart message: {0}")
	public void validateEmptyCart(String message) {
		assertEquals(cartPage.getEmptyCartMessage(), message, "Cart empty message did not match");
	}

	@Step("Update product quantity by {0}")
	public void updateQuantity(String quantity) {
		float subTotal = cartPage.getSubTotal();
		log.info("Sub-Total before updating quantity: "+subTotal);
		cartPage.updateQuantity(quantity);
		cartPage.clickOnUpdateCartButton();
		cartPage.waitForPageLoad();
		float total = subTotal*Integer.valueOf(quantity);
		log.info("Calulation : "+total);
		subTotal = cartPage.getSubTotal();
		log.info("Sub-Total after updating quantity: "+subTotal);
		assertEquals(subTotal, total, "Sub-Total was not updated correctly");
		total = cartPage.getTotal();
		log.info("Total after updating quantity: "+total);
		assertEquals(total, subTotal, "Total did not match with Sub-Total");
	}	

	@Step("Validate Terms & Conditions")
	public void validateTerms(String heading, String body) {
		cartPage.clickReadTerms();
		String window = cartPage.switchToNewWindow();
		assertEquals(cartPage.getTermsHeading(), heading, "Terms Heading did not match");
		assertEquals(cartPage.getTermsBody(), body, "Terms body did not match");
		cartPage.closeAndSwitchToParentWindow(window);
		log.info("Read Terms & Conditions");
	}

	@Step("Verify shipping estimate details")
	public void validateShippingEstimate(String country, String state, String zip) {
		cartPage.selectCountry(country);
		cartPage.selectState(state);
		cartPage.enterZipCode(zip);
		cartPage.clickEstimateButton();
		assertTrue(cartPage.isEstimateDisplayed(), "Shipping Estimates was not displayed");
		log.info("Validated shipping estimate");
	}

	@Step("Validate Terms & Conditions warning message")
	public void validateTermsWarningMessage(String tcHeading, String tcWarning) {
		cartPage.clickCheckoutButton();
		assertEquals(cartPage.getTermsWarningHeading(), tcHeading, " T&C warning heading did not match");
		assertEquals(cartPage.getTermsWarning(), tcWarning, " T&C warning message did not match");
		cartPage.closeTermsWarning();
		log.info("Verified T&C warning message");
	}

	@Step("Accept T&C and Checkout product")
	public void acceptTermsAndCheckout() {
		cartPage.acceptTerms();
		cartPage.clickCheckoutButton();
		log.info("Done Checking out product");
	}

}

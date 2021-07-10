package com.dff.steps;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.dff.pages.CheckoutPage;

import io.qameta.allure.Step;

public class CheckoutPageSteps {
	
	private Logger log = LogManager.getLogger(this.getClass().getName());
	private CheckoutPage checkoutPage;
	private final String IN_STORE = "STORE";
	private final String NOT_APPLICABLE = "NA";
	private final String CREDIT_CARD = "Credit Card";
	private final String PURCHASE_ORDER = "Purchase Order";
	
	public CheckoutPageSteps(WebDriver driver) {
		checkoutPage = new CheckoutPage(driver);
	}

	@Step("Select a first Billing Address")
	public void selectFirstBillingAddress() {
		checkoutPage.selectFirstBillingAddress(0);
		checkoutPage.continueToShippingAddress();
		log.info("Continuing checkout by selecting billing address");
	}
	
	@Step("Select a Shipping Address and Method")
	public void selectShippingAddressAndMethod(String shippingMethod) {
		if(shippingMethod.equalsIgnoreCase(IN_STORE)) {
			checkoutPage.pickFromStore();
			checkoutPage.continueToShippingMethod();
		} else if(!shippingMethod.equalsIgnoreCase(NOT_APPLICABLE)){
			checkoutPage.selectShippingAddressSameAsBillingAddress(0);
			checkoutPage.continueToShippingMethod();
			checkoutPage.chooseShippingMethod(shippingMethod);
			checkoutPage.continueToPaymentMethod();
		}
		log.info("Continuing checkout by selecting shipping address and method of shipping");
	}
	
	@Step("Select payment Method as {0}")
	public void selectPaymentMethod(String paymentMethod, String cardType, String holderName, 
			String cardNumber, String expirationDate, String code, String poNumber) {
		checkoutPage.choosePaymentMethod(paymentMethod);
		checkoutPage.continueToPaymentInfo();
		if(paymentMethod.equalsIgnoreCase(CREDIT_CARD)) {
			checkoutPage.selectCC(cardType);
			checkoutPage.enterCardHolderName(holderName);
			checkoutPage.enterCardNumber(cardNumber);
			String[] expDetails = expirationDate.split("-");
			checkoutPage.selectExpirationMonth(expDetails[0]);
			checkoutPage.selectExpirationYear(expDetails[1]);
			checkoutPage.enterCardCode(code);
		} else if(paymentMethod.equalsIgnoreCase(PURCHASE_ORDER)) {
			checkoutPage.enterPONumber(poNumber);
		}
		checkoutPage.continueToConfirmOrder();
		log.info("Confirming the order with payment method as "+paymentMethod);
	}
	
	@Step("Confirm the order")
	public String confirmOrder() {
		checkoutPage.clickConfirmOrder();
		String orderNumber = checkoutPage.getOrderNumber();
		log.info("Confirmed Order ID: "+orderNumber);
		checkoutPage.viewOrderDetails();
		return orderNumber;
	}
	
}

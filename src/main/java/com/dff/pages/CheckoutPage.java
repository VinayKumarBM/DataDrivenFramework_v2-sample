package com.dff.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage extends BasePage {

	public CheckoutPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(name = "billing_address_id")
	private WebElement billingAddressDropdown;
	
	@FindBy(xpath = "//input[contains(@onclick,'Billing.save')]")
	private WebElement continueBillingAddressButton;
	
	@FindBy(id="shipping-address-select")
	private WebElement shippingAddressDropdown;	
	
	@FindBy(id="PickUpInStore")
	private WebElement pickUpInStoreCheckbox;

	@FindBy(xpath = "//input[contains(@onclick,'Shipping.save')]")
	private WebElement continueShippingAddressButton;
	
	@FindBy(xpath = "//input[contains(@onclick,'ShippingMethod.save')]")
	private WebElement continueShippingMethodButton;
	
	@FindBy(xpath = "//input[contains(@onclick,'PaymentMethod.save')]")
	private WebElement continuePaymentMethodButton;
	
	@FindBy(id="CardholderName")
	private WebElement cardholderNameTextbox;
	
	@FindBy(id="CreditCardType")
	private WebElement creditCardTypeDropdown;
	
	@FindBy(id="CardNumber")
	private WebElement cardNumberTextbox;
	
	@FindBy(id="ExpireMonth")
	private WebElement expireMonthDropdown;
	
	@FindBy(id="ExpireYear")
	private WebElement expireYearDropdown;
	
	@FindBy(id="CardCode")
	private WebElement cardCodeTextbox;
	
	@FindBy(id="PurchaseOrderNumber")
	private WebElement purchaseOrderNumberTextbox;
	
	@FindBy(xpath = "//input[contains(@onclick,'PaymentInfo.save')]")
	private WebElement continuePaymentInfoButton;
	
	@FindBy(css = ".confirm-order-next-step-button")
	private WebElement confirmOrderButton;
	
	@FindBy(css = ".details>li")
	private WebElement orderNumberText;
	
	@FindBy(linkText = "Click here for order details.")
	private WebElement orderDetailsLink;
	
	private static final String SHIPPING_METHOD_XPATH = "//input[contains(@value,'%s')]";
	private static final String PAYMENT_METHOD_XPATH = "//label[contains(text(),'%s')]/../input";
	
	public String selectFirstBillingAddress(int index) {
		return selectByIndex(billingAddressDropdown, index);
	}

	public void continueToShippingAddress() {
		continueBillingAddressButton.click();
	}
	
	public String selectShippingAddress(String text) {
		return selectByText(billingAddressDropdown, text);
	}
	
	public String selectShippingAddressSameAsBillingAddress(int index) {
		return selectByIndex(billingAddressDropdown, index);
	}
	
	public void pickFromStore() {
		pickUpInStoreCheckbox.click();
	}
		
	public void continueToShippingMethod() {
		continueShippingAddressButton.click();
	}
	
	public void chooseShippingMethod(String method) {
		click(String.format(SHIPPING_METHOD_XPATH, method));
	}
	
	public void continueToPaymentMethod() {
		continueShippingMethodButton.click();
	}
	
	public void choosePaymentMethod(String method) {
		click(String.format(PAYMENT_METHOD_XPATH, method));
	}
	
	public void selectCC(String cardType) {
		selectByText(creditCardTypeDropdown, cardType);
	}
	
	public void enterCardHolderName(String name) {
		cardholderNameTextbox.sendKeys(name);
	}
	
	public void enterCardNumber(String number) {
		cardNumberTextbox.sendKeys(number);
	}
	
	public void selectExpirationMonth(String month) {
		selectByText(expireMonthDropdown, month);
	}
	
	public void selectExpirationYear(String year) {
		selectByText(expireYearDropdown, year);
	}
	
	public void enterCardCode(String code) {
		cardCodeTextbox.sendKeys(code);
	}
	
	public void enterPONumber(String code) {
		purchaseOrderNumberTextbox.sendKeys(code);
	}
	
	public void continueToPaymentInfo() {
		continuePaymentMethodButton.click();
		pause(1);
	}
	
	public void continueToConfirmOrder() {
		continuePaymentInfoButton.click();
	}
	
	public void clickConfirmOrder() {
		confirmOrderButton.click();
	}
	
	public String getOrderNumber() {
		return getOrderNumber(orderNumberText);
	}
	
	public void viewOrderDetails() {
		orderDetailsLink.click();
	}
}

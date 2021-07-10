package com.dff.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OrderDetailsPage extends BasePage {

	public OrderDetailsPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(className = "order-number")
	private WebElement orderNumberTextbox;
	
	@FindBy(css = ".order-total>strong")
	private WebElement orderTotalText;
	
	@FindBy(css = ".order-details>span:nth-child(2)")
	private WebElement orderDetailsStatusText;
	
	@FindBy(css = ".cart-total-right strong")
	private WebElement cartOrderTotal;	
	
	@FindBy(className = "payment-method")
	private WebElement paymentMethodText;
	
	@FindBy(className = "shipping-method")
	private WebElement shippingMethodText;
	
	@FindBy(css = "td.a-center.quantity")
	private WebElement quantityText;
	
	@FindBy(css = ".re-order-button")
	private WebElement reorderButton;

	@FindBy(css = ".pdf-order-button")
	private WebElement pdfInvoiceButton;
	
	// My account - Orders 
	@FindBy(css = ".order-item>.title>strong")
	private WebElement myOrderNumberTextbox;
	
	@FindBy(css = ".order-item>.info>li")
	private WebElement myOrderStatusTextbox;
	
	@FindBy(css = ".order-item>.info>li:nth-child(3)")
	private WebElement myOrderTotalTextbox;
	
	@FindBy(css = ".order-details-button")
	private WebElement myOrderDetailsButton;
	
	@FindBy(linkText = "Orders")
	private WebElement myOrdersLink;
	
	// My account - Downloadable products
	@FindBy(linkText = "Downloadable products")
	private WebElement downloadableProductsLink;
	
	@FindBy(css = "td.order>a")
	private WebElement downloadableProductOrderLink;
	
	public String getOrderNumber() {
		return getOrderNumber(orderNumberTextbox);
	}
	
	public String getPaymentMethod() {
		return paymentMethodText.getText().trim();
	}
	
	public void clickReorderButton() {
		reorderButton.click();
	}
	
	public void clickPDFInvoiceButton() {
		pdfInvoiceButton.click();
	}
	
	public String getShippingMethod() {
		return shippingMethodText.getText();
	}
	
	public String getQuantity() {
		return quantityText.getText().trim();
	}
	
	public String getOrderTotal() {
		return orderTotalText.getText().trim();
	}
	
	public String getCartTotal() {
		return cartOrderTotal.getText().trim();
	}
	
	public void clickOnOrderDetailsButton() {
		myOrderDetailsButton.click();
	}
	
	public String getMyOrderNumber() {
		return getOrderNumber(myOrderNumberTextbox);
	}
	
	public String getMyOrderStatus() {
		return myOrderStatusTextbox.getText();
	}
	
	public String getMyOrderTotal() {
		return myOrderTotalTextbox.getText();
	}

	public String getOrderDetailsStatus() {
		return orderDetailsStatusText.getText();
	}
	
	public void clickMyOrdersLinks() {
		myOrdersLink.click();
	}
	
	public void clickDownloadableProductsLinks() {
		downloadableProductsLink.click();
	}
	
	public void clickOrderNumberLink() {
		downloadableProductOrderLink.click();
	}
	
	public String getDownloadableProductOrderNumber() {
		return downloadableProductOrderLink.getText();
	}
}

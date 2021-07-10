package com.dff.steps;

import static org.testng.Assert.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.dff.pages.OrderDetailsPage;
import com.dff.utils.PDFUtil;

import io.qameta.allure.Step;

public class OrderPageSteps {

	private Logger log = LogManager.getLogger(this.getClass().getName());
	private OrderDetailsPage orderDetailsPage;
	private final String NOT_APPLICABLE = "NA";
	private final String FILE_NAME = "order_%s.pdf";
	
	public OrderPageSteps(WebDriver driver) {
		this.orderDetailsPage = new OrderDetailsPage(driver);
	}

	@Step("Verify details of Order: {3}")
	public void verifyOrderDetails(String quantity, String shippingMethod, String paymentMethod, String orderNumber) {
		assertEquals(orderDetailsPage.getCartTotal(),orderDetailsPage.getOrderTotal(), "Total on the Order Details page did not match");
		assertEquals(orderDetailsPage.getQuantity(), quantity, "Quantity did not match");
		if(!shippingMethod.equalsIgnoreCase(NOT_APPLICABLE)) {
			assertTrue(orderDetailsPage.getShippingMethod().contains(shippingMethod), "Shipping Method did not match");
		}
		assertTrue(orderDetailsPage.getPaymentMethod().contains(paymentMethod), "Payment Method did not match");
		assertEquals(orderDetailsPage.getOrderNumber(), orderNumber, "Order Number did not match");
		log.info("Verifed details of Order: "+orderNumber);
	}
	
	@Step("Navigate to Order Details from My Orders")
	public void naviageToDetailsFromMyOrder() {
		orderDetailsPage.clickMyOrdersLinks();
		String orderNumber = orderDetailsPage.getMyOrderNumber();
		String orderStatus = orderDetailsPage.getMyOrderStatus();
		String orderTotal = orderDetailsPage.getMyOrderTotal();
		orderDetailsPage.clickOnOrderDetailsButton();
		
		assertEquals(orderDetailsPage.getOrderDetailsStatus().toLowerCase(), orderStatus.toLowerCase(), "Order Status did not match");
		assertEquals(orderDetailsPage.getOrderNumber(), orderNumber, "Order Number did not match");
		assertTrue(orderTotal.contains(orderDetailsPage.getOrderTotal()), "Order Total did not match");
	}
	
	@Step("Navigate to Order Details from Downloadable products")
	public void naviageToDetailsFromDownloadableProducts() {
		orderDetailsPage.clickDownloadableProductsLinks();
		String orderNumber = orderDetailsPage.getDownloadableProductOrderNumber();
		orderDetailsPage.clickOrderNumberLink();		
		assertEquals(orderDetailsPage.getOrderNumber(), orderNumber, "Order Number did not match");
	}
	
	@Step("Validate PDF Invoice generated")
	public void validatePDFInvoice() throws Exception {
		String orderNumber = orderDetailsPage.getOrderNumber();
		orderDetailsPage.clickPDFInvoiceButton();
		String pdfContent = PDFUtil.read(String.format(FILE_NAME, orderNumber));
		assertNotNull(pdfContent, "Did not get PDF content");
		assertTrue(pdfContent.contains(orderDetailsPage.getCartTotal()), "Total on the Order Details page did not match");
		assertTrue(pdfContent.contains(orderDetailsPage.getQuantity()), "Quantity did not match");
		assertTrue(pdfContent.contains(orderDetailsPage.getPaymentMethod()), "Payment Method did not match");
		assertTrue(pdfContent.contains(orderNumber), "Order Number did not match");
		log.info("Verifed PDF Invoice of Order: "+orderNumber);
	}
}
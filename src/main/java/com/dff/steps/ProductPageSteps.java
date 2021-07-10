package com.dff.steps;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.dff.pages.ProductPage;

import io.qameta.allure.Step;

public class ProductPageSteps {

	private Logger log = LogManager.getLogger(this.getClass().getName());
	private ProductPage productPage;
	private final String PRODUCT_TYPE = "JEWELRY";

	public ProductPageSteps(WebDriver driver) {
		this.productPage = new ProductPage(driver);
	}

	@Step("Enter details for Gift Card")
	public void enterGiftCardDetails(String recipientName, String recipientEmail, String senderName, 
			String senderEmail, String message) {
		productPage.enterRecipientName(recipientName);
		productPage.enterSenderName(senderName);
		if(productPage.isRecipientEmailDisplayed()) {
			productPage.enterRecipientEmail(recipientEmail);
			productPage.enterSenderEmail(senderEmail);
		}
		productPage.enterMessage(message);
		log.info("Entered the Gift Card details");
	}

	@Step("Update product quantity to {0}")
	public void updateQuantity(String quantity) {
		productPage.enterQuantity(quantity);
		log.info("Updated the Product quantity to "+quantity);
	}

	@Step("Add product to Cart")
	public void addProductToCart() {
		productPage.clickAddToCartButton();
	}

	@Step("Enter details of {0}")
	public void enterProductDetails(String productType, String size, String color, String length, String pendant) {
		productPage.selectSize(size);
		if(productType.equalsIgnoreCase(PRODUCT_TYPE)) {
			productPage.enterLenght(length);
			productPage.choosePendant(pendant);
		} else if(productPage.isColorOptionDisplayed(color)) {
			productPage.chooseColor(color);	
		}
		log.info("Entered the details of "+productType);
	}
	
	@Step("Enter computer specifications")
	public void enterComputerSpecification(String processor, String ram, String hdd, String os, String softwares) {
		productPage.chooseHDD(hdd);
		if(productPage.isProcessorRadioButtonDisplayed(processor)) {
			productPage.chooseProcessor(processor);
		}else {
			productPage.selectProcessor(processor);
		}
		if(productPage.isRAMRadioButtonDisplayed(ram)) {
			productPage.chooseRAM(ram);
		}else {
			productPage.selectRAM(ram);
		}
		if(os!=null && !os.isEmpty() && !os.equalsIgnoreCase("")) {
			productPage.chooseOS(os);
		}
		for(String software : softwares.split("\\|")) {
			productPage.chooseSoftware(software);
		}
		log.info("Entered the specifications of computer");
	}
}

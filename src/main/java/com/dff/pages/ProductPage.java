package com.dff.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends BasePage {

	public ProductPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(css = ".add-to-cart-button")
	private WebElement addToCartButton;
	
	@FindBy(css = ".qty-input")
	private WebElement quantityTextbox;
	
	@FindBy(css = "[value='Add to wishlist']")
	private WebElement addToWishlistButton;	
	
	@FindBy(css = ".email-a-friend-button")
	private WebElement emailFriendButton;

	@FindBy(className = "recipient-name")
	private WebElement recipientNameTextbox;
	
	@FindBy(className = "sender-name")
	private WebElement senderNameTextbox;
	
	@FindBy(className = "message")
	private WebElement messageTextarea;
	
	@FindBy(className = "recipient-email")
	private WebElement recipientEmailTextbox;
	
	@FindBy(className = "sender-email")
	private WebElement senderEmailTextbox;
	
	@FindBy(linkText = "Add your review")
	private WebElement addReviewLink;
	
	@FindBy(xpath = "//select[contains(@id,'product_attribute')]")
	private WebElement sizeDropdown;
	
	@FindBy(xpath = "//input[contains(@id,'product_attribute')][@type='text']")
	private WebElement lengthTextbox;
	
	@FindBy(xpath = "//label[contains(text(),'Processor')]/../following-sibling::dd[1]//select")
	private WebElement processorDropdown;
	
	@FindBy(xpath = "//label[contains(text(),'RAM')]/../following-sibling::dd[1]//select")
	private WebElement ramDropdown;
		
	private static final String COLOR_XPATH = "//*[@class='color-container'][@title='%s']";
	private static final String PENDANT_XPATH = "//label[contains(text(),'%s')]/../input";
	private static final String PROCESSOR_XPATH = "//label[contains(text(),'Processor')]/../following-sibling::dd[1]//label[contains(text(),'%s')]";
	private static final String RAM_XPATH = "//label[contains(text(),'RAM')]/../following-sibling::dd[1]//label[contains(text(),'%s')]";
	private static final String HDD_XPATH = "//label[contains(text(),'HDD')]/../following-sibling::dd[1]//label[contains(text(),'%s')]";
	private static final String SOFTWARE_XPATH = "//label[contains(text(),'%s')]/preceding-sibling::input[@type='checkbox']";
	private static final String OS_XPATH = "//label[contains(text(),'OS')]/../following-sibling::dd[1]//label[contains(text(),'%s')]";
	
	public void enterQuantity(String quantity) {
		clearAndEnterText(quantityTextbox, quantity);
	}
	
	public void enterRecipientName(String name) {
		clearAndEnterText(recipientNameTextbox, name);
	}
	
	public boolean isRecipientEmailDisplayed() {
		return isElementDisplayed(recipientEmailTextbox);
	}
	
	public void enterRecipientEmail(String email) {
		clearAndEnterText(recipientEmailTextbox, email);
	}
	
	public void enterSenderName(String name) {
		clearAndEnterText(senderNameTextbox, name);
	}
	
	public void enterSenderEmail(String email) {
		clearAndEnterText(senderEmailTextbox, email);
	}
	
	public void enterMessage(String message) {
		clearAndEnterText(messageTextarea, message);
	}
	
	public void clickAddToCartButton() {
		addToCartButton.click();
	}
	
	public void selectSize(String size) {
		selectByText(sizeDropdown, size);
	}
	
	public void enterLenght(String length) {
		lengthTextbox.sendKeys(length);
	}
	
	public boolean isColorOptionDisplayed(String color) {
		return isElementDisplayed(By.xpath(String.format(COLOR_XPATH, color)));
	}
	
	public void chooseColor(String color) {
		click(String.format(COLOR_XPATH, color));
	}
	
	public void choosePendant(String pendant) {
		click(String.format(PENDANT_XPATH, pendant));
	}
	
	public boolean isProcessorRadioButtonDisplayed(String processor) {
		return isElementDisplayed(By.xpath(String.format(PROCESSOR_XPATH, processor)));
	}
	
	public boolean isRAMRadioButtonDisplayed(String ram) {
		return isElementDisplayed(By.xpath(String.format(RAM_XPATH, ram)));
	}
	
	public void chooseProcessor(String processor) {
		click(String.format(PROCESSOR_XPATH, processor));
	}
	
	public void chooseRAM(String ram) {
		click(String.format(RAM_XPATH, ram));
	}
	
	public void chooseHDD(String hdd) {
		click(String.format(HDD_XPATH, hdd));
	}

	public void chooseSoftware(String software) {
		checkWhenUnchecked(By.xpath(String.format(SOFTWARE_XPATH, software)));
	}
	
	public void chooseOS(String os) {
		click(String.format(OS_XPATH, os));
	}

	public void selectProcessor(String processor) {
		selectByText(processorDropdown, processor);
	}

	public void selectRAM(String ram) {
		selectByText(ramDropdown, ram);
	}
}

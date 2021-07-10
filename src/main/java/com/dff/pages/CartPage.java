package com.dff.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage{

	public CartPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(name="updatecart")
	private WebElement updateCartButton;
	
	@FindBy(name="removefromcart")
	private List<WebElement> removeCheckbox;
	
	@FindBy(css = ".qty-input")
	private List<WebElement> quantityTextbox;
	
	@FindBy(css = "span.read")
	private WebElement readTermsLink;

	@FindBy(id="termsofservice")
	private WebElement termsCheckbox;
	
	@FindBy(id = "checkout")
	private WebElement checkoutButton;
	
	@FindBy(css = ".page-title>h1")
	private WebElement conditionsHeadingText;
	
	@FindBy(css = ".page-body>p")
	private WebElement conditionsBodyText;

	@FindBy(css = "span.product-price")
	private WebElement subTotalText;
	
	@FindBy(css = ".order-total")
	private WebElement totalText;
	
	@FindBy(css = ".order-summary-content")
	private WebElement emptyCartMessage;
	
	@FindBy(css = ".active-step")
	private WebElement emptyCartInfo;
	
	@FindBy(id="CountryId")
	private WebElement countryDropdown;
	
	@FindBy(id="StateProvinceId")
	private WebElement stateDropdown;
	
	@FindBy(id="ZipPostalCode")
	private WebElement zipTextbox;
	
	@FindBy(name="estimateshipping")
	private WebElement estimateshippingButton;
	
	@FindBy(className = "shipping-results")
	private WebElement shippingEstimateText;
	
	@FindBy(id="terms-of-service-warning-box")
	private WebElement termsWarningText;
	
	@FindBy(css = ".ui-dialog-titlebar-close")
	private WebElement closeTermsWarningButton;
	
	@FindBy(className = "ui-dialog-title")
	private WebElement termsWarningHeadingText;
	
	private final By CART_QUANTITY_BY_XPATH  = By.xpath("//span[@class='cart-qty'][text()='(0)']");
	
	public void clickOnUpdateCartButton() {
		updateCartButton.click();
	}
	
	public void clickOnCheckoutButton() {
		checkoutButton.click();
	}
	
	public void clickOnTermOfService() {
		termsCheckbox.click();
	}
	
	public void selectItemToRemove() {
		removeCheckbox.stream().forEach(ele -> ele.click());
	}
	
	public void updateQuantity(String quantity) {
		quantityTextbox.stream().forEach(ele -> clearAndEnterText(ele, quantity));
	}
	
	public void clickReadTerms() {
		readTermsLink.click();
	}
	
	public String getTermsHeading() {
		return conditionsHeadingText.getText();
	}
	
	public String getTermsBody() {
		return conditionsBodyText.getText();
	}
	
	public float getTotal() {
		return Float.parseFloat(totalText.getText().trim());
	}
	
	public float getSubTotal() {
		return Float.parseFloat(subTotalText.getText().trim());
	}
	
	public boolean isCartEmpty() {
		return isElementDisplayed(emptyCartInfo);
	}
	
	public boolean isCartQuantityZero() {
		return isElementDisplayed(CART_QUANTITY_BY_XPATH);
	}
	
	public String getEmptyCartMessage() {
		return emptyCartMessage.getText().trim();
	}
	
	public void selectCountry(String country) {
		selectByText(countryDropdown, country);
	}
	
	public void selectState(String state) {
		selectByText(stateDropdown, state);
	}
	
	public void enterZipCode(String zip) {
		zipTextbox.sendKeys(zip);
	}
	
	public void clickEstimateButton() {
		estimateshippingButton.click();
	}
	
	public boolean isEstimateDisplayed() {
		return isElementDisplayed(shippingEstimateText);
	}
	
	public void acceptTerms() {
		termsCheckbox.click();
	}
	
	public void clickCheckoutButton() {
		checkoutButton.click();
	}
	
	public void closeTermsWarning() {
		closeTermsWarningButton.click();
	}
	
	public String getTermsWarning() {
		return termsWarningText.getText();
	}
	
	public String getTermsWarningHeading() {
		return termsWarningHeadingText.getText();
	}
}

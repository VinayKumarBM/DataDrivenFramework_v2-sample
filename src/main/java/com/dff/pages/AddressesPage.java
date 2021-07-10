package com.dff.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddressesPage extends BasePage {

	public AddressesPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(linkText = "Addresses")
	private WebElement addressesLink;
	
	@FindBy(css = ".add-address-button")
	private WebElement newAddressButton;
	
	@FindBy(id="Address_FirstName")
	private WebElement firstNameTextbox;	
	
	@FindBy(id="Address_LastName")
	private WebElement lastNameTextbox;

	@FindBy(id="Address_Email")
	private WebElement emailTextbox;	
	
	@FindBy(id="Address_Company")
	private WebElement compayTextbox;	
	
	@FindBy(id="Address_CountryId")
	private WebElement countryDropdown;	
	
	@FindBy(id="Address_StateProvinceId")
	private WebElement stateDropdown;	
	
	@FindBy(id="Address_City")
	private WebElement cityTextbox;	
	
	@FindBy(id="Address_Address1")
	private WebElement addressLine1Textbox;	
	
	@FindBy(id="Address_Address2")
	private WebElement addressLine2Textbox;	
	
	@FindBy(id="Address_ZipPostalCode")
	private WebElement zipCodeTextbox;	
	
	@FindBy(id="Address_PhoneNumber")
	private WebElement phoneNumberTextbox;	

	@FindBy(id="Address_FaxNumber")
	private WebElement faxNumberTextbox;	
	
	@FindBy(css = ".save-address-button")
	private WebElement saveButton;	
	
	private final String EMAIL_INFO_XPATH = "//li[contains(.,'%s')]";
	private final String EDIT_BUTTON_XPATH = "//li[contains(.,'%s')]/../..//*[@value='Edit']";
	private final String DELETE_BUTTON_XPATH = "//li[contains(.,'%s')]/../..//*[@value='Delete']";
	
	public void clickAddressLink() {
		addressesLink.click();
	}
	
	public void clickAddNewAddressButton() {
		newAddressButton.click();
	}
	
	public void enterFirstName(String firstName) {
		clearAndEnterText(firstNameTextbox, firstName);
	}
	
	public void enterLastName(String lastName) {
		clearAndEnterText(lastNameTextbox, lastName);
	}
	
	public void enterEmail(String email) {
		clearAndEnterText(emailTextbox, email);
	}
	
	public void enterCompany(String company) {
		clearAndEnterText(compayTextbox, company);
	}	
	
	public void selectCountry(String country) {
		selectByText(countryDropdown, country);
	}
	
	public void selectState(String state) {
		selectByText(stateDropdown, state);
	}
	
	public void enterCity(String city) {
		clearAndEnterText(cityTextbox, city);
	}
	
	public void enterAddressLine1(String address1) {
		clearAndEnterText(addressLine1Textbox, address1);
	}
	
	public void enterAddressLine2(String address2) {
		clearAndEnterText(addressLine2Textbox, address2);
	}
	
	public void enterZipCode(String zip) {
		clearAndEnterText(zipCodeTextbox, zip);
	}
	
	public void enterPhoneNumber(String phoneNumber) {
		clearAndEnterText(phoneNumberTextbox, phoneNumber);
	}
	
	public void enterFaxNumber(String faxNumber) {
		clearAndEnterText(faxNumberTextbox, faxNumber);
	}
	
	public boolean isUserEmailDisplayed(String email) {
		return isElementDisplayed(By.xpath(String.format(EMAIL_INFO_XPATH, email)));
	}
	
	public void clickSaveButton() {
		saveButton.click();
	}
	
	public void clickEditButton(String email) {
		click(String.format(EDIT_BUTTON_XPATH, email));
	}
	
	public void clickDeleteButton(String email) {
		click(String.format(DELETE_BUTTON_XPATH, email));
	}
}

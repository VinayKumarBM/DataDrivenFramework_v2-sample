package com.dff.steps;

import static org.testng.Assert.assertTrue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.dff.pages.AddressesPage;

import io.qameta.allure.Step;

public class AddressesPageSteps {
	
	private Logger log = LogManager.getLogger(this.getClass().getName());
	private AddressesPage addressesPage;
	
	public AddressesPageSteps(WebDriver driver) {
		addressesPage = new AddressesPage(driver);
	}

	@Step("Navigate to address page")
	public void navigateToAddress() {
		addressesPage.clickAddressLink();
		log.info("Navigated to address page");
	}
	
	@Step("Add a New Address")
	public void clickAddNewAddress() {
		addressesPage.clickAddNewAddressButton();
		log.info("Clicked on Add New address button");
	}
	
	@Step("Save address and verify the saved address with email {0}")
	public void saveAddressAndVerify(String email) {
		addressesPage.clickSaveButton();
		log.info("Saved address details");
		assertTrue(addressesPage.isUserEmailDisplayed(email), "Address was not saved");
		log.info("Address with email "+email+" was saved");
	}
	
	@Step("Enter address details of {0} {1}")
	public String enterAddressDetails(String firstName, String lastName, String emailAddress, String company, String country, 
			String state, String city, String addressLine1, String addressLine2, String zip, String phone, String fax) {
		addressesPage.enterFirstName(firstName);
		addressesPage.enterLastName(lastName);
		String email = String.format(emailAddress,System.currentTimeMillis());
		addressesPage.enterEmail(email);
		addressesPage.enterCompany(company);
		addressesPage.selectCountry(country);
		addressesPage.enterCity(city);
		addressesPage.enterAddressLine1(addressLine1);
		addressesPage.enterAddressLine2(addressLine2);
		addressesPage.enterZipCode(zip);
		addressesPage.enterPhoneNumber(phone);
		addressesPage.enterFaxNumber(fax);
		addressesPage.selectState(state);
		log.info("Entered address details of "+firstName+" "+lastName);
		return email;
	}
	
	@Step("Delete address with email {0}")
	public void deleteAddress(String email) {
		addressesPage.clickDeleteButton(email);
		addressesPage.acceptAlert();
		assertTrue(!addressesPage.isUserEmailDisplayed(email), "Address was not deleted");
		log.info("Address with email "+email+" was delted");
	}
	
	@Step("Update address with email {0}")
	public void editAddress(String email) {
		addressesPage.clickEditButton(email);
		log.info("Editing address with email "+email);
	}
	
	@Step("Update email {0}, phone {1} and fax {2} deails of address")
	public String updateAddressDetails(String emailAddress, String phone, String fax) {
		String email = String.format(emailAddress,System.currentTimeMillis());
		addressesPage.enterEmail(email);
		addressesPage.enterPhoneNumber(phone);
		addressesPage.enterFaxNumber(fax);
		log.info("Updated email "+email+", phone "+phone+" and fax "+fax+" deails of address");
		return email;
	}

}

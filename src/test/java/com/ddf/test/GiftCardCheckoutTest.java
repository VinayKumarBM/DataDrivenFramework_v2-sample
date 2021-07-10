package com.ddf.test;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.dff.configs.DriverManager;
import com.dff.steps.CartPageSteps;
import com.dff.steps.CheckoutPageSteps;
import com.dff.steps.HomePageSteps;
import com.dff.steps.LandingPageSteps;
import com.dff.steps.LoginPageSteps;
import com.dff.steps.OrderPageSteps;
import com.dff.steps.ProductPageSteps;
import com.dff.utils.Log;
import com.dff.utils.ScreenshotUtil;

import io.qameta.allure.Description;

public class GiftCardCheckoutTest extends TestBase{

	@Test(dataProvider = "testData", testName = "Gift Card Checkout Test", description = "To checkout a Gift card")
	@Description("Test Description: To checkout a Gift Card")
	public void giftCheckoutTest(Map<String, String> map) throws Exception {
		WebDriver driver = DriverManager.getInstance().getDriver();
		Log.info("Test Data: \n"+map);
		new HomePageSteps(driver).navigateToLoginPage();
		new LoginPageSteps(driver).login(map.get("email"), map.get("password"));

		CartPageSteps cartPageSteps = new CartPageSteps(driver);
		LandingPageSteps plpSteps = new LandingPageSteps(driver);
		CheckoutPageSteps checkoutPageSteps = new CheckoutPageSteps(driver);
		ProductPageSteps productPageSteps	= new ProductPageSteps(driver);
		
		plpSteps.navigateToCart();
		cartPageSteps.removeAllProducts();
		plpSteps.searchProduct(map.get("product"));
		plpSteps.addProductToCart();
		productPageSteps.enterGiftCardDetails(map.get("recipientName"), map.get("recipientEmail"), map.get("senderName"), 
				map.get("senderEmail"), map.get("message"));
		productPageSteps.updateQuantity(map.get("quantity"));
		ScreenshotUtil.takeScreenShot(map.get("Test_Case"));
		productPageSteps.addProductToCart();
		plpSteps.closeAddToCartSuccessBanner();		
		plpSteps.navigateToCart();		
		cartPageSteps.acceptTermsAndCheckout();		
		checkoutPageSteps.selectFirstBillingAddress();
		checkoutPageSteps.selectShippingAddressAndMethod(map.get("shippingMethod"));
		checkoutPageSteps.selectPaymentMethod(map.get("paymentMethod"), map.get("cardType"), map.get("holderName"), 
				map.get("cardNumber"), map.get("expirationDate"), map.get("code"), map.get("poNumber"));
		String orderNumber = checkoutPageSteps.confirmOrder();
		
		new OrderPageSteps(driver).verifyOrderDetails(map.get("quantity"), map.get("shippingMethod"), map.get("paymentMethod"), orderNumber);
		ScreenshotUtil.takeScreenShot(map.get("Test_Case"));
		new HomePageSteps(driver).logut();
	}

	@DataProvider(name = "testData")
	public Object[][] testData() throws Exception{
		return getTestData(this.getClass().getSimpleName());
	}
}

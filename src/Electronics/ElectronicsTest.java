package Electronics;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import utilities.Utility;

import java.time.Instant;

public class ElectronicsTest extends Utility {

    String baseUrl = "https://demowebshop.tricentis.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    public void selectMenu(String menu) {
        clickOnElement(By.linkText(menu));
    }

    @Test
    public void verifyUserShouldNavigateToCellPhonesPageSuccessfully() {

        // Mouse Hover on the “ELECTRONICS” Tab
        mouseHoverToElement(By.xpath("//ul[@class='top-menu']//a[normalize-space()='Electronics']"));

        // Mouse Hover on the “Cell phones” and click
        mouseHoverToElementAndClick(By.xpath("//a[contains(text(), 'Cell phones')]"));

        //Verify the text “Cell phones”
        Assert.assertEquals("Cell phones text not matched!", "Cell phones",
                getTextFromElement(By.xpath("//h1[normalize-space()='Cell phones']")));

    }

    @Test
    public void verifyThatTheProductAddedSuccessfullyAndPlaceOrderSuccessfully() throws InterruptedException {

        Instant timestamp = Instant.now();
        String email = "Primetest"+timestamp+"@prime.com";

        // Mouse Hover on the “ELECTRONICS” Tab
        mouseHoverToElement(By.xpath("//ul[@class='top-menu']//a[normalize-space()='Electronics']"));

        // Mouse Hover on the “Cell phones” and click
        mouseHoverToElementAndClick(By.xpath("//a[contains(text(), 'Cell phones')]"));

        //Verify the text “Cell phones”
        Assert.assertEquals("Cell phones text not matched!", "Cell phones",
                getTextFromElement(By.xpath("//h1[normalize-space()='Cell phones']")));

        // Select View as option ‘List’
        selectByVisibleTextDropdown(By.xpath("//select[@id='products-viewmode']"), "List");

        // Click on the product name “Smartphone” link
        clickOnElement(By.linkText("Smartphone"));

        // Verify the text Smartphone
        Assert.assertEquals("Smartphone text not matched!", "Smartphone",
                getTextFromElement(By.xpath("//h1[normalize-space()='Smartphone']")));


        //Verify the price “100.00”
        Assert.assertEquals("Price mismatch!", "100.00",
                getTextFromElement(By.xpath("//span[@class='price-value-43']")));

        // Change the quantity to 2
        sendTextToElementWithClearText(By.xpath("//input[@id='addtocart_43_EnteredQuantity']"), "2");

        // Click on the “Add to cart” tab
        clickOnElement(By.xpath("//input[@id='add-to-cart-button-43']"));

        // Verify the success message
        Assert.assertTrue("Success message mismatch!",
                getTextFromElement(By.xpath("//p[@class='content']")).contains("The product has been added to your shopping cart"));

        // After that close the bar by clicking on the cross button.
        clickOnElement(By.xpath("//span[@title='Close']"));

        // Then MouseHover on "Shopping cart" and click on the "Go to cart" button.
        mouseHoverToElement(By.xpath("//span[contains(text(),'Shopping cart')]"));
        clickOnElement(By.xpath("//input[@value='Go to cart']"));

        // Verify the message "Shopping cart"
        Assert.assertEquals("Cart title mismatch!", "Shopping cart",
                getTextFromElement(By.xpath("//h1[normalize-space()='Shopping cart']")));

        /*Thread.sleep(2000);
        // Verify the quantity is 2
        Assert.assertEquals("quantity is not matched!", "2",
                getTextFromElement(By.xpath("//td[@class='qty nobr']")));*/

        // Verify the Total “200.00”
        Assert.assertEquals("Total price mismatch!", "200.00",
                getTextFromElement(By.xpath("//span[@class='product-subtotal']")));

        // Agree to the terms and conditions
        clickOnElement(By.id("termsofservice"));

        // Click on "Checkout"
        clickOnElement(By.xpath("//button[@id='checkout']"));

        // Verify the text "Welcome, Please Sign In!"
        Assert.assertEquals("Sign-in title mismatch!", "Welcome, Please Sign In!",
                getTextFromElement(By.xpath("//h1")));

        // Click on the “Register” tab
        clickOnElement(By.xpath("//input[@value='Register']"));

        // Verify the text “Register”
        Assert.assertEquals("Register title mismatch!", "Register",
                getTextFromElement(By.xpath("//h1")));

        // Select the Male radio button
        clickOnElement(By.id("gender-male"));
        // Enter the First name
        sendTextToElement(By.id("FirstName"), "Prime");
        // Enter the Last name
        sendTextToElement(By.id("LastName"), "Test");
        // Enter the Email
        sendTextToElement(By.id("Email"), email.replace("-","").replace(":",""));
        // Enter the Password
        sendTextToElement(By.id("Password"), "Prime@123");
        // Enter the Confirm password
        sendTextToElement(By.id("ConfirmPassword"), "Prime@123");

        // Click on the “Register” button
        clickOnElement(By.id("register-button"));

        // Verify the message “Your registration completed”
        Assert.assertEquals("Register completed title mismatch!", "Your registration completed",
                getTextFromElement(By.xpath("//div[@class='result']")));

        // Click on the “Continue” button
        clickOnElement(By.xpath("//input[@value='Continue']"));

        // Verify the text “Shopping cart”
        Assert.assertEquals("Shopping cart title mismatch!", "Shopping cart",
                getTextFromElement(By.xpath("//h1")));

        // click on the checkbox “I agree with the terms of service
        clickOnElement(By.id("termsofservice"));

        // Click on the “Checkout”
        clickOnElement(By.xpath("//button[@id='checkout']"));

       /* selectByVisibleTextDropdown(By.id("//select[@id='billing-address-select']"),
                "New Address");*/

        // fill in billing details
        selectByVisibleTextDropdown(By.id("BillingNewAddress_CountryId"), "United Kingdom");
        sendTextToElement(By.id("BillingNewAddress_City"), "London");
        sendTextToElement(By.id("BillingNewAddress_Address1"), "test");
        sendTextToElement(By.id("BillingNewAddress_ZipPostalCode"), "HA07HG");
        sendTextToElement(By.id("BillingNewAddress_PhoneNumber"), "0757765434");

        // Click "Continue"
        clickOnElement(By.xpath("//input[@onclick='Billing.save()']"));

        // Click "Continue"
        clickOnElement(By.xpath("//input[@onclick='Shipping.save()']"));

        // Complete shipping and payment

        // select 2 day air
        clickOnElement(By.id("shippingoption_2"));

        //click on continue
        clickOnElement(By.xpath("//input[@class='button-1 shipping-method-next-step-button']"));

        // click on credit card
        clickOnElement(By.id("paymentmethod_2"));

        // Click on the “Continue” button
        clickOnElement(By.xpath("//input[@class='button-1 payment-method-next-step-button']"));

        // Select the “Visa” From Select credit card dropdown
        selectByVisibleTextDropdown(By.id("CreditCardType"), "Master card");
        // Enter the Cardholder's name
        sendTextToElement(By.id("CardholderName"), "Alexis Runolfsdottir");
        // Enter the Card number
        sendTextToElement(By.id("CardNumber"), "5451628062666699");
        // Select the Expiration date using the select class
        selectByVisibleTextDropdown(By.id("ExpireMonth"), "08");
        selectByVisibleTextDropdown(By.id("ExpireYear"), "2025");
        // Enter the Card code
        sendTextToElement(By.id("CardCode"), "031");

        // Click on the “Continue” button
        clickOnElement(By.xpath("//input[@class='button-1 payment-info-next-step-button']"));

        // Verify the “Payment Method” is “Credit Card”
        Assert.assertEquals("Payment method is not match!", "Credit Card",
                getTextFromElement(By.xpath("//li[@class='payment-method']")));

        // Verify the “Shipping Method” is “2nd Day Air”
        Assert.assertEquals("Shipping Method is not matched!", "2nd Day Air",
                getTextFromElement(By.xpath("//li[@class='shipping-method']")));

        // Verify the Total is “200.00”
        Assert.assertEquals("Total price mismatch!", "200.00",
                getTextFromElement(By.xpath("//span[@class='product-subtotal']")));

        // Click on the “Confirm” button
        clickOnElement(By.xpath("//input[@value='Confirm']"));

        // Verify the Text “Thank You”
        Assert.assertEquals("Thank you message mismatch!", "Thank you",
                getTextFromElement(By.xpath("//h1[normalize-space()='Thank you']")));

        // Verify the message “Your order has been successfully processed!”
        Assert.assertEquals("order successfully message not matched!", "Your order has been successfully processed!",
                getTextFromElement(By.xpath("//strong[normalize-space()='Your order has been successfully processed!']")));

        // Click on the “Continue” button
        clickOnElement(By.xpath("//input[@class='button-2 order-completed-continue-button']"));

        // Verify the text “Welcome to our store”
        Assert.assertEquals("Welcome message mismatch!", "Welcome to our store",
                getTextFromElement(By.xpath("//h2[normalize-space()='Welcome to our store']")));
        // Click on the “Logout” link
        clickOnElement(By.xpath("//a[normalize-space()='Log out']"));

        // Verify the URL is “https://demowebshop.tricentis.com/
        Assert.assertTrue("Url is not match!",
                driver.getCurrentUrl().contains("https://demowebshop.tricentis.com"));

    }

    @After
    public void tearDown() {
        closeBrowser();
    }
}

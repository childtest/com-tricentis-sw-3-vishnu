package computer;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestSuite extends Utility {

    String baseUrl = "https://demowebshop.tricentis.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    public void selectMenu(String menu) {
        clickOnElement(By.linkText(menu));
    }


    @Test
    public void verifyProductArrangeInAlphaBaticalOrder() {
        // Click on the "Computers" menu
        clickOnElement(By.linkText("Computers"));

        // Click on the "Desktops" submenu
        clickOnElement(By.linkText("Desktops"));

        //  Select Sort By option "Name: Z to A"
        selectByVisibleTextDropdown(By.id("products-orderby"), "Name: Z to A");

        //  Verify the products are arranged in descending order
        List<String> actualProductNames = new ArrayList<>();
        for (WebElement product : getWebElements(By.xpath("//h2[@class='product-title']/a"))) {
            actualProductNames.add(product.getText());
        }

        // Create a sorted copy of the product names in descending order
        List<String> expectedProductNames = new ArrayList<>(actualProductNames);
        Collections.sort(expectedProductNames, Collections.reverseOrder());

        // Assert that the product names are sorted correctly
        Assert.assertEquals("Products are not sorted in descending order!", expectedProductNames, actualProductNames);
    }

    @Test
    public void verifyProductAddedToShoppingCartSuccessFully() throws InterruptedException {
        // Click on the "Computers" menu
        clickOnElement(By.linkText("Computers"));

        // Click on the "Desktops" submenu
        clickOnElement(By.linkText("Desktops"));

        //  Select Sort By option "Name: Z to A"
        selectByVisibleTextDropdown(By.id("products-orderby"), "Name: Z to A");


        // Click on the "Add To Cart" button of the product name ‘Build your own computer’
        clickOnElement(By.linkText("Build your own computer"));

        // Verify the Text "Build your own computer"
        Assert.assertEquals("Product title mismatch!", "Build your own computer",
                getTextFromElement(By.xpath("//h1[normalize-space()='Build your own computer']")));

        //  Customize the product
        selectByVisibleTextDropdown(By.id("product_attribute_16_5_4"), "2.2 GHz Intel Pentium Dual-Core E2200");
        selectByVisibleTextDropdown(By.id("product_attribute_16_6_5"), "8GB [+60.00]");
        clickOnElement(By.xpath("//input[@id='product_attribute_16_3_6_19']"));   // HDD: 400 GB [+100.00]
        clickOnElement(By.id("product_attribute_16_4_7_21"));// OS: Windows 10 [+60.00]

        // Select additional software
        if (getWebElements(By.id("product_attribute_16_8_8_22")).isEmpty())
            clickOnElement(By.id("product_attribute_16_8_8_22")); // Microsoft Office [+50.00]

        clickOnElement(By.id("product_attribute_16_8_8_24"));  // Total Commander [+5.00]

        // Verify the price "1200.00"
        Assert.assertEquals("Price mismatch!", "1200.00",
                getTextFromElement(By.xpath("//span[@class='price-value-16']")));

        // Click on the "Add to cart" button
        clickOnElement(By.id("add-to-cart-button-16"));

        // Verify the success message
        Assert.assertTrue("Success message mismatch!",
                getTextFromElement(By.xpath("//p[@class='content']")).contains("The product has been added to your shopping cart"));

        //  Close the green bar
        clickOnElement(By.xpath("//span[@title='Close']"));

        // Navigate to the cart
        mouseHoverToElement(By.xpath("//span[contains(text(),'Shopping cart')]"));
        clickOnElement(By.xpath("//input[@value='Go to cart']"));

        // Verify the text "Shopping cart"
        Assert.assertEquals("Cart title mismatch!", "Shopping cart",
                getTextFromElement(By.xpath("//h1[normalize-space()='Shopping cart']")));

        //  Change the quantity to "2" and update the cart
        sendTextToElementWithClearText(By.xpath("//input[contains(@name,'itemquantity')]"), "2");
        clickOnElement(By.xpath("//input[@name='updatecart']"));

        //  Verify the total "2,950.00"
        Assert.assertEquals("Total price mismatch!", "2950.00",
                getTextFromElement(By.xpath("//span[@class='product-subtotal']")));

        // Agree to the terms and conditions
        clickOnElement(By.id("termsofservice"));

        // Click on "Checkout"
        clickOnElement(By.xpath("//button[@id='checkout']"));

        // Verify the text "Welcome, Please Sign In!"
        Assert.assertEquals("Sign-in title mismatch!","Welcome, Please Sign In!",
                getTextFromElement(By.xpath("//h1")));

        // Click on "Checkout as Guest"
       clickOnElement(By.xpath("//input[@value='Checkout as Guest']"));

       // fill in billing details
        sendTextToElement(By.id("BillingNewAddress_FirstName"), "prime");
        sendTextToElement(By.id("BillingNewAddress_LastName"), "test");
        sendTextToElement(By.id("BillingNewAddress_Email"), "primetest@prime.com");
        sendTextToElement(By.id("BillingNewAddress_Company"), "prime test");
        selectByVisibleTextDropdown(By.id("BillingNewAddress_CountryId") , "United Kingdom");
        Thread.sleep(1000);
        selectByVisibleTextDropdown(By.id("BillingNewAddress_StateProvinceId") , "Other (Non US)");
        sendTextToElement(By.id("BillingNewAddress_City"), "London");
        sendTextToElement(By.id("BillingNewAddress_Address1"), "test");
        sendTextToElement(By.id("BillingNewAddress_Address2"), "prime");
        sendTextToElement(By.id("BillingNewAddress_ZipPostalCode"), "HA07HG");
        sendTextToElement(By.id("BillingNewAddress_PhoneNumber"), "0757765434");
        sendTextToElement(By.id("BillingNewAddress_FaxNumber"), "076876756");

        // Click "Continue"
        clickOnElement(By.xpath("//input[@onclick='Billing.save()']"));

        // Click "Continue"
        clickOnElement(By.xpath("//input[@onclick='Shipping.save()']"));

        // Complete shipping and payment

        // select next day air
        clickOnElement(By.id("shippingoption_1"));

        //click on continue
        clickOnElement(By.xpath("//input[@class='button-1 shipping-method-next-step-button']"));

        // click on credit card
        clickOnElement(By.id("paymentmethod_2"));

        // click on continue
        clickOnElement(By.xpath("//input[@class='button-1 payment-method-next-step-button']"));

        // Enter credit card details

        selectByVisibleTextDropdown(By.id("CreditCardType"), "Master card");
        sendTextToElement(By.id("CardholderName"), "Alexis Runolfsdottir");
        sendTextToElement(By.id("CardNumber"), "5451628062666699");
        selectByVisibleTextDropdown(By.id("ExpireMonth"), "08");
        selectByVisibleTextDropdown(By.id("ExpireYear"), "2025");
        sendTextToElement(By.id("CardCode"), "031");

        // click on continue
        clickOnElement(By.xpath("//input[@class='button-1 payment-info-next-step-button']"));

        // Verify “Payment Method” is Credit Card
        Assert.assertEquals("Payment method is not match!","Credit Card",
               getTextFromElement(By.xpath("//li[@class='payment-method']")) );

        // Verify “Shipping Method” is “Next Day Air”
        Assert.assertEquals("Shipping Method is not matched!","Next Day Air",
                getTextFromElement(By.xpath("//li[@class='shipping-method']")));

        // Verify Total is “2,950.00”
        Assert.assertEquals("Total price mismatch!", "2950.00",
                getTextFromElement(By.xpath("//span[@class='product-subtotal']")));

        //Click on the “Confirm” button
        clickOnElement(By.xpath("//input[@value='Confirm']"));

        // Verify the text "Thank You"
        Assert.assertEquals("Thank you message mismatch!","Thank you",
                getTextFromElement(By.xpath("//h1[normalize-space()='Thank you']")));

        // Verify the text "Your order has been successfully processed!"
        Assert.assertEquals("order successfully message not matched!","Your order has been successfully processed!",
                getTextFromElement(By.xpath("//strong[normalize-space()='Your order has been successfully processed!']")));

        // click on continue
        clickOnElement(By.xpath("//input[@class='button-2 order-completed-continue-button']"));

        // Verify the text "Welcome to our store"
        Assert.assertEquals("Welcome message mismatch!","Welcome to our store",
                getTextFromElement(By.xpath("//h2[normalize-space()='Welcome to our store']")));

    }


    @After
    public void tearDown() {
        closeBrowser();
    }

}

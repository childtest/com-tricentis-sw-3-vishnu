package homepage;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import utilities.Utility;

public class TopMenuTest extends Utility {

    String baseUrl = "https://demowebshop.tricentis.com/";

    @Before
    public void setUp(){
        openBrowser(baseUrl);
    }
    public void selectMenu(String menu){
        clickOnElement(By.linkText(menu));
    }


    @Test
    public void verifyPageNavigation(){

        // click on all desktop
        selectMenu("Computers");

        // Verify desktop text
        Assert.assertEquals("Computers text is not matched!","Computers",
                getTextFromElement(By.xpath("//h1[normalize-space()='Computers']")));

    }

    @After
    public void tearDown(){
        //closeBrowser();
    }
}

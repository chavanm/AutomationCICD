package MaheshChavan.test;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import MaheshChavan.TestComponent.BaseTest;
import MaheshChavan.TestComponent.Retry;
import MaheshChavan.pageobjects.CartPage;
import MaheshChavan.pageobjects.CheckoutPage;
import MaheshChavan.pageobjects.ConfirmationPage;
import MaheshChavan.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest
{

	
	@Test(groups = {"ErrorHandling"}, retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException
	{
 
		String productName = "ZARA COAT 3";     
        ProductCatalogue productcatalogue =  landingpage.LoginApplication("chavanm33@gmail.com", "Chavanm@13");
        Assert.assertEquals("Incorrect email or password.", landingpage.getErrorMessage());

	}
	
	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException
	{
 
		String productName = "ZARA COAT 3";     
        ProductCatalogue productcatalogue =  landingpage.LoginApplication("chavanm33@gmail.com", "Chavanm@123");
             
        List<WebElement> products =  productcatalogue.getProductList(); //get the list of products
        productcatalogue.addProductToCart(productName);
        CartPage cartPage =  productcatalogue.goToCartPage();
         
        Boolean match = cartPage.VerifyProductDisplay(productName);
        Assert.assertTrue(match); //all assertion can go inside test case. // we can't write assertion in java folder.() //this page is validation //validations cannot go in page object files.
        
            
	}
	
	
}

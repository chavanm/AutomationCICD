package MaheshChavan.stepdefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import MaheshChavan.TestComponent.BaseTest;
import MaheshChavan.pageobjects.CartPage;
import MaheshChavan.pageobjects.CheckoutPage;
import MaheshChavan.pageobjects.ConfirmationPage;
import MaheshChavan.pageobjects.LandingPage;
import MaheshChavan.pageobjects.ProductCatalogue;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepdefinitionImpl  extends BaseTest
{
	 public LandingPage landingpage;
	 public ProductCatalogue productcatalogue;
	 public  ConfirmationPage confirmationPage;
	 
     @Given("I landed on Ecommerce Page")
     public void  I_landed_on_Ecommerce_Page() throws IOException
     {
    	landingpage = launchApplication(); //this method returns landingpage object.
     }
     
     @Given ( "^Logged in with username (.+)  and password (.+)$")  //(.+) - represents any character or any value //"^ $ "  this say that a regular expression.
     public void logged_in_username_and_password(String username, String password)
     {
          productcatalogue =  landingpage.LoginApplication(username,password);

     }
     @When ("^I add product (.+) to cart$")
     public void i_add_product_to_cart(String productName) throws InterruptedException
     {
    	 List<WebElement> products =  productcatalogue.getProductList(); 
         productcatalogue.addProductToCart(productName);
     }
     
     @When("^checkout (.+) and submit the order$")
     public void checkout_submit_order(String productName)
     {
    	 CartPage cartPage =  productcatalogue.goToCartPage();
         
         Boolean match = cartPage.VerifyProductDisplay(productName);
         Assert.assertTrue(match);
         CheckoutPage checkoutpage =  cartPage.goToCheckout();
         
         checkoutpage.selectCountry("india");
          confirmationPage = checkoutpage.submitOrder();
     }

     //Then  "THANKYOU FOR THE ORDER."  message is displayed  on ConfirmationPage.
     @Then("{string} message is displayed  on ConfirmationPage.")
     public void message_displayed_confirmationPage(String string)
     {
    	 String confirmMessage = confirmationPage.getConfirmationMessage();
         Assert.assertTrue(confirmMessage.equalsIgnoreCase(string)); 
     }

     
}

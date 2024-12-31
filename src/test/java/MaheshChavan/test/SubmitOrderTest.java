package MaheshChavan.test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import MaheshChavan.TestComponent.BaseTest;
import MaheshChavan.pageobjects.CartPage;
import MaheshChavan.pageobjects.CheckoutPage;
import MaheshChavan.pageobjects.ConfirmationPage;
import MaheshChavan.pageobjects.OrderPage;
import MaheshChavan.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest
{
	
	String productName = "ZARA COAT 3";     

	
	@Test(dataProvider = "getData", groups = {"Purchase"})
	public void submitorder(HashMap<String, String> input) throws IOException, InterruptedException
	{
 
        ProductCatalogue productcatalogue =  landingpage.LoginApplication(input.get("email"), input.get("password"));
             
        List<WebElement> products =  productcatalogue.getProductList(); //get the list of products
        productcatalogue.addProductToCart(input.get("product"));
        CartPage cartPage =  productcatalogue.goToCartPage();
         
        Boolean match = cartPage.VerifyProductDisplay(input.get("product"));
        Assert.assertTrue(match); //all assertion can go inside test case. // we can't write assertion in java folder.() //this page is validation //validations cannot go in page object files.
        CheckoutPage checkoutpage =  cartPage.goToCheckout();
        
        checkoutpage.selectCountry("india");
        ConfirmationPage confirmationPage = checkoutpage.submitOrder();
        
        String confirmMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));   
            
      /*  driver.findElement(By.cssSelector(".totalRow Button")).click();

        Actions a = new Actions(driver);
        a.sendKeys(driver.findElement(By.cssSelector("[placeholder = 'Select Country']")), "india").build().perform();

        
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-item")));
        
        driver.findElement(By.cssSelector(".ta-results button:last-of-type")).click();
        driver.findElement(By.cssSelector(".btnn")).click();
        
        
        String confirmedmessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertTrue(confirmedmessage.equalsIgnoreCase("Thankyou for the order."));   
        driver.close(); */
       
       

	} 
	
	@Test(dependsOnMethods = {"submitorder"})
	public void orderHistory()
	{
		
		ProductCatalogue productCatalogue = landingpage.LoginApplication("chavanm33@gmail.com", "Chavanm@123");	
	    OrderPage orderpage = productCatalogue.goToOrderPage();
	    Assert.assertTrue(orderpage.VerifyOrderDisplay(productName));
	   
	}

	
	
	//hashmap -  we use hashmap when we have n number of data sets we dont send the all parameters to the submitorder method.
	//hashmap send value - <key,value> pair.
	
	@DataProvider //give the data what needed for
	public Object[][] getData() throws IOException
	{
		
		/*
		 * HashMap<String, String> map = new HashMap<String, String>(); map.put("email",
		 * "chavanm33@gmail.com"); map.put("password", "Chavanm@123");
		 * map.put("product", "ZARA COAT 3");
		 * 
		 * HashMap<String, String> map1 = new HashMap<String, String>();
		 * map1.put("email", "maheshchavan9271@gmail.com"); map1.put("password",
		 * "Chavanm@123"); map1.put("product", "ADIDAS ORIGINAL");
		 */
		
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + "\\src\\test\\java\\MaheshChavan\\data\\PurchaseOrder.json"); 
		
		return new Object[][] {{data.get(0)}, {data.get(1)}};
		 
	} 
	
	/*@DataProvider  //first try this one if we have few data sets value this method is useful
	public Object[][] getData()
	{
	
		return new Object[][] {{"chavanm33@gmail.com","Chavanm@123","ZARA COAT 3"}, {"Shetty@gmail.com","Iamking@000","ADIDAS ORIGINAL"}}; //{} - is one data set value //Object is parent data type of all this(int, string) and its generic data type which accepts any kind of data type.

	 
	}*/

}

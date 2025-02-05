package MaheshChavan.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import MaheshChavan.AbstractComponent.AbstractComponent;

public class CartPage extends AbstractComponent
{
	 WebDriver driver;
	 
	@FindBy(css = ".totalRow button")
	WebElement checkoutEle;
	
	@FindBy(css = ".cartSection h3")
	private List<WebElement> productTitles;
	
	public CartPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public Boolean VerifyProductDisplay(String productName)
	{
		boolean match = productTitles.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
	    return match;
	}
	
	public CheckoutPage  goToCheckout()
	{ 
			
			checkoutEle.click();
	       
		   return new CheckoutPage(driver);
			
		
	}


}

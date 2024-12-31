package MaheshChavan.AbstractComponent;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import MaheshChavan.pageobjects.CartPage;
import MaheshChavan.pageobjects.OrderPage;

public class AbstractComponent
{
	
	WebDriver driver;

	public AbstractComponent(WebDriver driver)
	{
		
		this.driver = driver; // this driver coming from child class. //every child need to serve to parent - every child has to give that driver.
	    PageFactory.initElements(driver, this);
	
	}
   
	@FindBy(css ="[routerlink*='cart']")
	WebElement cartHeader;
	
	@FindBy(css ="[routerlink*='myorders']")
	WebElement orderHeader;
	
	public void waitForElementToAppear(By findBy)  
	{
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy)); // that findBy has to come here so that from
																			// your page object, it will come.
	}
	
	public void waitForWebElementToAppear(WebElement findBy)  
	{
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(findBy));
		
	}
	
	public CartPage goToCartPage()
	{
        cartHeader.click();
        CartPage cartPage = new CartPage(driver); //this is another class object 
        return cartPage;
	}
	
	public OrderPage goToOrderPage()
	{
		orderHeader.click();
        OrderPage orderpage = new OrderPage(driver); //this is another class object 
        return orderpage;
	}
	 
	public void waitForElementToDisappear(WebElement ele) throws InterruptedException //there is driver. thats why we use WebElement. refer below wait commented code.
	{
		
		Thread.sleep(1000);
		/*
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		//wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating")))); 
		wait.until(ExpectedConditions.invisibilityOf(ele));
        */
		
	 }

}

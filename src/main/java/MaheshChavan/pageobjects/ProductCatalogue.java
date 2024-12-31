package MaheshChavan.pageobjects;
//page object only should have actions.

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver; 
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import MaheshChavan.AbstractComponent.AbstractComponent;

public class ProductCatalogue extends AbstractComponent
{
	
    WebDriver driver;  //create local variable.
    
    public ProductCatalogue(WebDriver driver)//this driver scope is this method only  //constructor //this method will execute first. //this driver is coming to standaloneTest.java class.
    {   
    	//initialization   
    	super(driver);
    	
    	this.driver= driver; //this will assign locale variable.
    	PageFactory.initElements(driver, this); //this will trigger initializing all the element.
    	
    }
    
   // List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

    
	@FindBy(css = ".mb-3") //construction of this will be triggered when you call(pageFactory.initElements(driver, this)) this method. and this method expects two argument. 
	List<WebElement> products; //this will find  no. of element.
	
	@FindBy(css = ".ng-animating")
	WebElement spinner;
	
    By productBy =  By.cssSelector(".mb-3");//this not driver. so you cannot write page factory
    By addToCart = By.cssSelector(".card-body Button:last-of-type"); //this not driver. so you cannot write page factory
    By toastMessage =  By.cssSelector("#toast-container");//this not drive r. so you cannot write page factory
    
	public  List<WebElement> getProductList()
	{
		waitForElementToAppear(productBy);
	    return products;	
	}

	public WebElement getProductByName(String productName)
	{
		
		WebElement prod = products.stream().filter(product->
        product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null); //if match get first element or else return null. 
	    return prod;
	    
	}
	
	public void addProductToCart(String productName) throws InterruptedException
	{
		
		WebElement prod = getProductByName(productName); 
		prod.findElement(addToCart).click(); //can you apply page factory within WebElement.findElement
		                                    //Ans - no it is not possible.
		
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(spinner);
		//wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating")))); //because this driver. we use pagefactory.
	
	}



	
	
	
	
	
	
	
}

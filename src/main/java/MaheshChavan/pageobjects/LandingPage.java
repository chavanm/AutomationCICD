package MaheshChavan.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import MaheshChavan.AbstractComponent.AbstractComponent;

public class LandingPage extends AbstractComponent
{
    WebDriver driver;  //create local variable.
    
    public LandingPage(WebDriver driver)//this driver scope is this method only  //constructor //this method will execute first. //this driver is coming to standaloneTest.java class.
    {            
    	super(driver);
    	//initialization
    	this.driver= driver; //this will assign locale variable.
    	PageFactory.initElements(driver, this); //this will trigger initializing all the element.
    	
    }
    
	//WebElement userEmail = driver.findElement(By.id("userEmail"));
	//pagefactory  - so using that you can reduce the syntax of creating your web element.
	
	@FindBy(id = "userEmail") //construction of this will be triggered when you call(pageFactory.initElements(driver, this)) this method. and this method expects two argument. 
	WebElement userEmail;
	
	@FindBy(id= "userPassword")
	WebElement passwordEle;
	
	@FindBy(id = "login")
	WebElement submit;
	
	@FindBy(css = "[class*='flyInOut']")
	WebElement errorMessage;   //div[@class='ng-tns-c4-2 ng-star-inserted ng-trigger ng-trigger-flyInOut ngx-toastr toast-error']
	
	public ProductCatalogue LoginApplication(String email, String password) //action method
	{
		
		userEmail.sendKeys(email);
		passwordEle.sendKeys(password);
		submit.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
		
	}
	
	public String getErrorMessage()
	{
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
		
	}
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	
	
}

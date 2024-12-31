package MaheshChavan.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import MaheshChavan.AbstractComponent.AbstractComponent;

public class CheckoutPage extends AbstractComponent
{
 
	WebDriver driver;
	public CheckoutPage(WebDriver driver)  //create constructor that gives life of the driver.
	{
		super(driver);
		this.driver = driver;
        PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".btnn")
	WebElement submit;
	
	@FindBy(css = "[placeholder = 'Select Country']")
	WebElement country;
	
	@FindBy(css = ".ta-results button:last-of-type")
	WebElement selectCountry;

	// driver.findElement(By.cssSelector(".ta-results button:last-of-type")).click();
	
	By results = By.cssSelector(".ta-item");
    
	public void selectCountry(String countryName )
	{
		Actions a = new Actions(driver);
        a.sendKeys(country, countryName).build().perform();
        waitForElementToAppear(By.cssSelector(".ta-item"));
        selectCountry.click();

	}
	
	public ConfirmationPage submitOrder()
	{
		submit.click();
	    return new ConfirmationPage(driver);
	}

}


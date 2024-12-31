package MaheshChavan.test;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import MaheshChavan.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandaloneTest {

	public static void main(String[] args)
	{

		String productName = "ZARA COAT 3";
        WebDriverManager.chromedriver().setup(); //chromedriver will automatically downloaded into your system based upon chrome browser version
        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/client");
        
        LandingPage landingpage =  new LandingPage(driver); // sending driver(argument) to Landingpage class.
         
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.findElement(By.id("userEmail")).sendKeys("chavanm33@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Chavanm@123");
        driver.findElement(By.id("login")).click();
        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

        WebElement prod = products.stream().filter(product->
        product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null); //if match get first element or else return null.

        prod.findElement(By.cssSelector(".card-body Button:last-of-type")).click(); //first parent class(.card-body) then child tag(Button) after (:last-of-type) when we have multiple button then it selects last element.

       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
       wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
        //ng-animating 
       //  wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));// this method eating lot of time so, getting performance issue     //or
        
       wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
       driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
        
       List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
       boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
       Assert.assertTrue(match);
       
       driver.findElement(By.cssSelector(".totalRow Button")).click();
       
       Actions a = new Actions(driver);
       a.sendKeys(driver.findElement(By.cssSelector("[placeholder = 'Select Country']")), "india").build().perform();

       wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-item")));
       
       driver.findElement(By.cssSelector(".ta-results button:last-of-type")).click();
       driver.findElement(By.cssSelector(".btnn")).click();
       
       String confirmedmessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
       Assert.assertTrue(confirmedmessage.equalsIgnoreCase("Thankyou for the order."));   
       driver.close();
       

	}

}

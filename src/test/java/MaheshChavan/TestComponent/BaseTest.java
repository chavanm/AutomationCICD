package MaheshChavan.TestComponent;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import MaheshChavan.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest
{
    
    public WebDriver driver;
    public LandingPage landingpage;
	
    public WebDriver initializeDriver() throws IOException 
	{
    	
    	//properties class - 
    	
    	Properties prop = new Properties(); //java package
    	//System.getProperty("user.dir") - this will give you project path.
    	FileInputStream fis  = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\MaheshChavan\\Resources\\GlobalData.properties"); // which can convert your file into input stream object.
    	prop.load(fis); //file that needs to be sent as a input stream //load method here is expecting to send InputStream object.
    	
    	//this will give right to the maven to provide browser if not then it gets in global property. //you can decide global parameters on cmd also
    	String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser"); //java ternary

      // prop.getProperty("browser");
    	
    	if(browserName.contains("chrome"))
    	{
    		 ChromeOptions options =new ChromeOptions();
		     WebDriverManager.chromedriver().setup(); //chromedriver will automatically downloaded into your system based upon chrome browser version
	         if(browserName.contains("headless"))
	         { 
		        options.addArguments("headless");//run in headless mode.
	         }
	         driver = new ChromeDriver(options);
	        driver.manage().window().setSize(new Dimension(1440,900));
	         
	    }
    	else if(browserName.equalsIgnoreCase("firfox"))
    			{
    		         //firefox
    		           System.setProperty("webdriver.gecko.driver", "C:\\Users\\mahesh\\Desktop\\Driver\\firefox driver\\geckodriver.exe");
    		           driver = new FirefoxDriver();
    			}
    	else if(browserName.equalsIgnoreCase("edge"))
    			{
    		          //edge
    		          System.setProperty("webdriver.edge.driver", "C:\\Users\\mahesh\\Desktop\\Driver\\Edge driver\\msedgedriver.exe");
    		          driver = new EdgeDriver();
    			}
    	
    	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	        driver.manage().window().maximize();
	        return driver;
	        
	}
    
    //we create here so, we can use anywhere in the framework.
    public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
    {
    	
    	//in java there in one method which will read file that means if you just pass JSON file.
    	//it will scan the entire content of your JSON and convert that into one string variable .
    	String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir")+  "\\src\\test\\java\\MaheshChavan\\data\\PurchaseOrder.json"), StandardCharsets.UTF_8); //also write the encoding format this(StandardCharsets.UTF_8)  //read json to string"
    	
    	//string to HashMap - new dependency using jackson Databind
    	ObjectMapper mapper = new ObjectMapper();
    	List<HashMap<String,String>> data =	mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>()  //first read the string and convert to HashMap. //all hashmap put in the List
    	{});
    		return data;
    	//{map. map}
    		
    }
     
    public String getScreenshot(String testCaseName, WebDriver driver) throws IOException //this driver is coming from the listener level.
	{
		
	    TakesScreenshot	ts= (TakesScreenshot)driver; //where does driver have life.
	    File source =  ts.getScreenshotAs(OutputType.FILE); //we just need in the file format so that we want to store in our local system.
	    File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
	    FileUtils.copyFile(source, file);
	    return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
	   
	}
    
    
    @BeforeMethod(alwaysRun = true)
    public LandingPage launchApplication() throws IOException
    {
    	
        driver = initializeDriver();
        landingpage =  new LandingPage(driver); //sending driver(argument) to Landingpage class.
        landingpage.goTo();
        return landingpage;
        
    } 
    
    @AfterMethod(alwaysRun = true)
    public void tearDown()
    {
    	driver.close();
    }
}

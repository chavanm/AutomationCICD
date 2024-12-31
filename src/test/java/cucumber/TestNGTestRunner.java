package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests; //wrapper class
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/cucumber", glue ="MaheshChavan.stepdefinitions" , monochrome = true, plugin= {"html:target/cucumber.html"})
public class TestNGTestRunner extends AbstractTestNGCucumberTests 
{

}

package MaheshChavan.Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

// we need to setup metadata like creating extent,spark,reporter ,class,object ,setting a report name, document title 
public class ExtentReporterNG 
{
      public static ExtentReports getReportObject()
      { 
    	  
    	  String path = System.getProperty("user.dir") + "\\reports\\index.html"; // first part give project path until extent report + index.html this we have extent report.
  		ExtentSparkReporter reporter  = new ExtentSparkReporter(path);
  		reporter.config().setReportName("Web Automation Results");
  		reporter.config().setDocumentTitle("Test results");
  		
  		//using this extent object we are going to create entries for each and every test in the report.
  		ExtentReports extent = new ExtentReports();
  		extent.attachReporter(reporter);
  		extent.setSystemInfo("Tester", "Mahesh Chavan");
  		//how to create entry for particular test and whatever result is there it'll show in the HTML report.
  		extent.createTest(path);
  		return extent;
  		//you have 100 test cases do you go and write this line in a 100 test saying that first do this reporting. No, there should be smart way of handling it so for that there is concept called TestNG Listeners.
  		
  		
      }
}

package MaheshChavan.TestComponent;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import MaheshChavan.Resources.ExtentReporterNG;

//ItestListener this is one of the interface provided by testng
public class Listeners extends BaseTest implements ITestListener // we are inheriting all methods present in the BaseTest.
{
	ExtentTest test;
	ExtentReports extent  = ExtentReporterNG.getReportObject();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();  //make this object thread safe - means no matter even if you run concurrently each object creation have its own thread so it won't interrupt the other overriding variable.	
	
	@Override
	public void onTestStart(ITestResult result) //result variable hold the all information about the test.
	{
  		test = extent.createTest(result.getMethod().getMethodName()); //we have to push this object in to ThreadLocal
        extentTest.set(test); //set is a method which will set your object into this ThreadLocal  //if you think those are not thread safe
        //unique thread id(ErrorValidationsTest) -> test 
	}

	@Override
	public void onTestSuccess(ITestResult result) 
	{
		extentTest.get().log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) 
	{
		
		extentTest.get().fail(result.getThrowable()); //get will extract it //getThrowable() - print that error message in the report.
		//step-1- take screenshot failed test
		//step-2- attach to the report.
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance()); //getTestClass - where method is method ,getRealClass - get the actual real class , getField - get the field of driver that driver is using field is part of class
		} catch (Exception e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String filepath = null;
		try 
		{
			
			filepath = getScreenshot(result.getMethod().getMethodName(), driver);
		
		} catch (IOException e) {

			e.printStackTrace();
		
		}
		extentTest.get().addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
	
	}

	
	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onStart(ITestContext context) 
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void onFinish(ITestContext context) 
	{ 
        extent.flush(); //if you miss that then it wont generate report.
	}

}

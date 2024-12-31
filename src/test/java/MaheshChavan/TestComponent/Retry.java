package MaheshChavan.TestComponent;
//implement TestNG retry mechanism to rerun the failed flaky tests in the application.

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer 
{
    int count = 0;
    int maxTry =1;
	@Override
	public boolean retry(ITestResult result) 
	{
		if(count<maxTry)
		{
			count++;
			return true;
		}
		return false;
	}

}

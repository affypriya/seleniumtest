/**
 * 
 */
package selenium.ui.framework.listeners;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.LogStatus;

import selenium.ui.framework.base.BasePage;
import selenium.ui.framework.reports.ReportManager;
import selenium.ui.framework.reports.TestManager;

import org.openqa.selenium.TakesScreenshot;

/**
 * @author priya
 *
 */
public class TestListener extends BasePage implements ITestListener {

	WebDriver webDriver;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.testng.ITestListener#onTestStart(org.testng.ITestResult)
	 */
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("I am in onTestStart Method" + result.getMethod().getMethodName() + " start");
		TestManager.startTest(result.getMethod().getMethodName(), "");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.testng.ITestListener#onTestSuccess(org.testng.ITestResult)
	 */
	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("I am in onTestSuccess Method" + result.getMethod().getMethodName() + " succeed");
		Object testClass = result.getInstance();
		String base64Screenshot = ((BasePage) testClass).getBase64Screenshot();
		try {
			TestManager.getTest().log(LogStatus.PASS, "Test Passed",
					TestManager.getTest().addBase64ScreenShot(base64Screenshot));
			((BasePage) testClass).getDataTable().entrySet().forEach(entry -> {
				TestManager.getTest().log(LogStatus.PASS, entry.getKey(), entry.getValue());
			});
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.testng.ITestListener#onTestFailure(org.testng.ITestResult)
	 */
	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("I am in onTestFailure Method" + result.getMethod().getMethodName() + " failed");
		Object testClass = result.getInstance();
		String base64Screenshot = ((BasePage) testClass).getBase64Screenshot();

		/*String base64Screenshot = "data:image/png;base64,"
				+ ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BASE64);*/

		TestManager.getTest().log(LogStatus.FAIL, "Test Failed",
				TestManager.getTest().addBase64ScreenShot(base64Screenshot));

		Throwable th = result.getThrowable();
		String desc = "";
		if (th != null) {
			desc = th.getMessage();
			System.out.println(th.getMessage());
			result.setThrowable(null);
		}
		TestManager.getTest().log(LogStatus.FAIL, "Test Exception", th);

		((BasePage) testClass).getDataTable().entrySet().forEach(entry -> {
			TestManager.getTest().log(LogStatus.FAIL, entry.getKey(), entry.getValue());
		});

/*		if (webDriver != null) {
			webDriver.quit();
		}*/
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.testng.ITestListener#onTestSkipped(org.testng.ITestResult)
	 */
	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("I am in onTestSkipped Method" + getTestMethodName(result) + " skipped");
		TestManager.getTest().log(LogStatus.SKIP, "Test Skipped");
	}

	/**
	 * @param result
	 * @return
	 */
	private String getTestMethodName(ITestResult result) {
		// TODO Auto-generated method stub
		return result.getMethod().getConstructorOrMethod().getName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.testng.ITestListener#onTestFailedButWithinSuccessPercentage(org.testng.
	 * ITestResult)
	 */
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("test failed but it is in defined success ratio " + getTestMethodName(result));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.testng.ITestListener#onStart(org.testng.ITestContext)
	 */
	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ReportManager.name = context.getName();
		System.out.println("I am in OnStart Method" + context.getName());
		context.setAttribute("WebDriver", getDriver());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.testng.ITestListener#onFinish(org.testng.ITestContext)
	 */
	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		System.out.println("I am in onFinish Method" + context.getName());
/*		ITestNGMethod[] allmethod = context.getAllTestMethods();
		for (ITestNGMethod met : allmethod) {
			Object testClass = met.getInstance();
			if (testClass != null) {
				webDriver = ((BasePage) testClass).getDriver();
				if (webDriver != null) {
					try {
						webDriver.quit();
					} catch (Exception e) {

					}
				}
			}
		}*/
		TestManager.endTest();
		ReportManager.getReporter().flush();
	}

}

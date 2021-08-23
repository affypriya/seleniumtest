/**
 * 
 */
package selenium.ui.framework.reports;

import java.util.HashMap;
import java.util.Map;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

/**
 * @author priya
 *
 */
public class TestManager {
	static Map extentMap = new HashMap();
	static ExtentReports extent = ReportManager.getReporter();
	
	public static synchronized ExtentTest getTest() {
		return (ExtentTest) extentMap.get((int)(long) (Thread.currentThread().getId()));
	}
	
	public static synchronized void endTest() {
		extent.endTest((ExtentTest) extentMap.get((int)(long) (Thread.currentThread().getId())));
	}
	
	public static synchronized ExtentTest startTest(String testName, String desc) {
		ExtentTest test = extent.startTest(testName, desc);
		extentMap.put((int)(long) (Thread.currentThread().getId()), test);
		return test;
	}
}

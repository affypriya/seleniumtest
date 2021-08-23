/**
 * 
 */
package sample.tests.kickoff.vzw;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.testng.ITest;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.internal.BaseTestMethod;

import selenium.ui.framework.base.BasePage;
import selenium.ui.framework.reports.TestManager;
import selenium.ui.framework.utils.TestCases;
import selenium.ui.framework.utils.TestDataProvider;



/**
 * @author priya
 *
 */
public class VzwMainTest extends BasePage implements ITest, TestCases {
	public static final Logger log = Logger.getLogger(VzwMainTest.class.getName());

	@Factory(dataProviderClass=TestDataProvider.class , dataProvider= "testData")
	VzwMainTest(Map<String,String> table){
		setDataTable(table);
	}
	
	@BeforeMethod(alwaysRun = true)
	public void setTestName(ITestResult result) {
		try {
			BaseTestMethod baseTestMethod = (BaseTestMethod) result.getMethod();
			Field f = baseTestMethod.getClass().getSuperclass().getDeclaredField("m_methodName");
			f.setAccessible(true);
			f.set(baseTestMethod, getDataTable().get("Serial_No") + "_" + getDataTable().get("TestCase_Name"));
		} catch (Exception e) {
			Reporter.log("Exception: " + e.getMessage());
		}
	}
	
	@Test
	public void runMainTest() throws Throwable {
		try {
			TestManager.getTest().setDescription("TestCase Name:" + getDataTable().get("TestCase_Name"));
			WebDriver driver = intiateBrowser();
			
		}catch (Exception e) {
			Reporter.log("Exception: " + e.getMessage());
			throw e;
		}
	}
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see selenium.ui.utils.TestCases#testSeq()
	 */

	public Integer testSeq() {
		// TODO Auto-generated method stub
		return Integer.parseInt(getDataTable().get("Serial_No"));
	}
	
	public Integer getSheetIndex() {
		return Integer.parseInt(getDataTable().get("sheetIndex"));
	}

	@AfterMethod(alwaysRun = true)
	public void setResultTestName(ITestResult result) {
		try {
			BaseTestMethod baseTestMethod = (BaseTestMethod) result.getMethod();
			Field f = baseTestMethod.getClass().getSuperclass().getDeclaredField("m_methodName");
			f.setAccessible(true);
			f.set(baseTestMethod, getDataTable().get("Serial_No") + "_" + getDataTable().get("TestCase_Name"));
		} catch (Exception e) {
			Reporter.log("Exception: " + e.getMessage());
		}
	}
}

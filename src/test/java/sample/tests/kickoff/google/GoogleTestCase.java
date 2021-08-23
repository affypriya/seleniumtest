/**
 * 
 */
package sample.tests.kickoff.google;

import java.util.Map;

import org.openqa.selenium.WebDriver;

import selenium.ui.pages.google.GoogleMainPage;



/**
 * @author priya
 *
 */
public class GoogleTestCase {
	GoogleMainPage gmp;
	Map<String,String> testData;
	WebDriver driver;
	public GoogleTestCase(Map<String,String> testData, WebDriver driver) {
		this.testData = testData;
		this.driver = driver;
	}
	
	public void validateMainPage() {
		gmp = new GoogleMainPage(testData, driver);
		gmp.assertTitle("Google");
		gmp.isGoogle_search_btn_displayed();
		gmp.isLucky_btn_displayed();
		gmp.checkAllLinksDisplayed();
	}
	
	
}

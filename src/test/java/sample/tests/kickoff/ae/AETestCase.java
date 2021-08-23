/**
 * 
 */
package sample.tests.kickoff.ae;

import static org.testng.Assert.assertTrue;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;

import selenium.ui.pages.ae.mainpage.AELandingPageObj;


/**
 * @author priya
 *
 */
public class AETestCase {
	AELandingPageObj mlp;
	Map<String, String> testData;
	WebDriver driver;

	public AETestCase(Map<String, String> testData, WebDriver driver) {
		this.testData = testData;
		this.driver = driver;
		if (mlp == null) {
			mlp = new AELandingPageObj(testData, driver);
		}
	}

	public void rejectcookie() {
		mlp.rejectCookie();
	}
	

	public void validateMainPageTitle() {
		String links = testData.get("Links");
		if(StringUtils.isAllBlank(links)) {
			mlp.assertTitle(testData.get("PageTitle"));
		} else {
			mlp.clickValidateLink(testData.get("Links"), testData.get("PageTitle"));
		}		
	}

	
}

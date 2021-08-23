/**
 * 
 */
package selenium.ui.pages.ae.mainpage;

import static org.testng.Assert.assertTrue;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.logging.Logger;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.asserts.SoftAssert;

import selenium.ui.framework.base.BasePage;
import selenium.ui.framework.functions.SeleniumFunctions;

/**
 * @author priya
 *
 */
public class AELandingPageObj extends BasePage {
	public static final Logger log = Logger.getLogger(AELandingPageObj.class.getName());
	Map<String, String> testData;
	WebDriver driver;

	public AELandingPageObj(Map<String, String> testData, WebDriver driver) {
		this.testData = testData;
		this.driver = driver;
		AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 5);
		PageFactory.initElements(factory, this);
	}

	@FindBy(xpath = "//button[contains(@class,'reject')]")
	private WebElement reject_cookie;

	public static final String ae_logo = "//a[contains(@title,'American Eagle')]";	
	public static final String aerie_logo = "//a[contains(@title,'Aerie')]";
	public static final String checkout = "//a[contains(@href,'cart')]";
	public static final String cms_shop_women = "//a[text()='Shop Women']";
	public static final String cms_shop_men = "//a[text()='Shop Men']";
	public static final String cms_shop_tailgate = "//a[text()='Shop Tailgate']";
	public static final String cms_shop_clearance = "//a[text()='Shop Clearance']";

	
	/**
	 * 
	 */
	public void rejectCookie() {
		reject_cookie.click();
	}

	
	/**
	 * @param title
	 */
	public void assertTitle(String title) {
		assertTrue(driver.getTitle().trim().contains(title.trim()));
	}

	/**
	 * @param linkTest
	 */
	public void clickValidateLink(String linkTest, String pageTitle) {
		// TODO Auto-generated method stub
		String elementName;
		try {
			elementName = SeleniumFunctions.getMappedWE(linkTest, this);
			SeleniumFunctions.clickElement(driver, elementName);
			SeleniumFunctions.waitForPageToLoad(3);
			assertTitle(pageTitle);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

/**
 * 
 */
package selenium.ui.pages.google;

import static org.testng.Assert.assertTrue;

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
public class GoogleMainPage extends BasePage {
	public static final Logger log = Logger.getLogger(GoogleMainPage.class.getName());
	Map<String,String> testData;
	WebDriver driver;
	public GoogleMainPage(Map<String,String> testData,WebDriver driver) {
		this.testData = testData;
		this.driver = driver;
		AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 5);
		PageFactory.initElements(factory, this);
		setDataTable(testData);
		setDriver(driver);
	}

	@FindBy(xpath="//input[@title='Search']")
	private WebElement google_search_textbox;
	
	@FindBy(xpath="//input[@value='Google Search' and not (ancestor::div[contains(@style,'display:none')])]")
	private WebElement google_search_button;
	
	@FindBy(xpath="//input[contains(@value,'Lucky') and not (ancestor::div[contains(@style,'display:none')])]")
	private WebElement google_lucky_button;
	
	@FindBy(linkText="Gmail")
	private WebElement gmail_link;
	
	@FindBy(linkText="About")
	private WebElement about_link;
	
	@FindBy(linkText="Store")
	private WebElement store_link;
	
	@FindBy(linkText="Images")
	private WebElement images_link;
	
	@FindBy(linkText="Advertising")
	private WebElement advertising_link;
	
	@FindBy(linkText="Business")
	private WebElement business_link;
	
	@FindBy(linkText="  How Search works ")
	private WebElement how_link;
	
	@FindBy(linkText="Privacy")
	private WebElement privacy_link;
	
	@FindBy(linkText="Terms")
	private WebElement terms_link;
	
	@FindBy(linkText="Settings")
	private WebElement settings_link;
	
	@FindBy(xpath="//a[@title='Google apps']")
	private WebElement apps_link;
	
	public void enterText(String searchTxt) {
		SeleniumFunctions.enterText(driver, google_search_textbox, searchTxt);
		google_search_textbox.sendKeys(Keys.ENTER);
	}
	
	public void assertTitle(String title) {
		assertTrue(driver.getTitle().trim().contains(title));
	}
	
	public void isGoogle_search_btn_displayed() {
		assertTrue(google_search_button.isDisplayed());
	}
	
	public void isLucky_btn_displayed() {
		assertTrue(google_lucky_button.isDisplayed());
	}
	
	public void isGmail_link_displayed() {
		assertTrue(gmail_link.isDisplayed());
	}
	
	public void checkAllLinksDisplayed() {
		SoftAssert sa = new SoftAssert();
		
		sa.assertTrue(gmail_link.isDisplayed(),"failed gmail_link");
		sa.assertTrue(about_link.isDisplayed(),"failed about_link");
		sa.assertTrue(store_link.isDisplayed(),"failed store_link");
		sa.assertTrue(images_link.isDisplayed(),"failed images_link");
		sa.assertTrue(advertising_link.isDisplayed(),"failed advertising_link");
		sa.assertTrue(business_link.isDisplayed(),"failed business_link");
		//sa.assertTrue(how_link.isDisplayed(),"failed how_link");
		sa.assertTrue(privacy_link.isDisplayed(),"failed privacy_link");
		sa.assertTrue(terms_link.isDisplayed(),"failed terms_link");
		sa.assertTrue(settings_link.isDisplayed(),"failed settings_link");
		sa.assertTrue(apps_link.isDisplayed(),"failed apps_link");
		sa.assertAll();
	}
}

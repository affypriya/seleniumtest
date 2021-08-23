/**
 * 
 */
package selenium.ui.framework.browser;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * @author priya
 *
 */
public class BrowserHandler {

	public WebDriver intiateBrowser(WebDriver driver, Properties prop, Map<String, String> dataTable,
			String grid) throws MalformedURLException {

		if (StringUtils.isNotBlank(System.getenv("GRID"))
				&& !StringUtils.equalsIgnoreCase(System.getenv("GRID"), "LOCAL")) {
			grid = System.getenv("WORKSTATION");
			driver = (RemoteWebDriver) initiateBrowserFromGrid(driver,
					prop.getProperty("BROWSER", dataTable.get("browser")), grid);
		} else {
			driver = intiateLocalBrowser(driver, dataTable.get("Browser"));
		}
		return driver;

	}

	/**
	 * @param driver
	 * @param property
	 * @param grid
	 * @return
	 * @throws MalformedURLException 
	 */
	private  WebDriver initiateBrowserFromGrid(WebDriver driver, String property, String grid) throws MalformedURLException {
		// TODO Auto-generated method stub
		if (driver == null) {
			if(!StringUtils.isEmpty(grid)) {
				String gridURL = "http://"+grid + ":4444/wd/hub";
				driver = new RemoteWebDriver(new URL(gridURL),getChromeOptions());
			}
		}
		
		return driver;
	}

	/**
	 * @param driver
	 * @param property
	 * @return
	 */
	private  WebDriver intiateLocalBrowser(WebDriver driver, String browser) {
		// TODO Auto-generated method stub
		if (driver == null) {
			driver = new ChromeDriver(getChromeOptions());
		}
		return driver;
	}

	/**
	 * @param browser
	 * @param driver
	 * @return
	 */
	private  WebDriver getDriver(String browser, WebDriver driver) {
		// TODO Auto-generated method stub

		switch (browser.toLowerCase()) {
		case "ie":
			return new InternetExplorerDriver();

		case "chrome":
			return new ChromeDriver(getChromeOptions());

		case "firefox":
			return new FirefoxDriver();

		case "phantomjs":
			return null;
		default :
			return new ChromeDriver(getChromeOptions());
		}

	}

	/**
	 * @return
	 */
	private  ChromeOptions getChromeOptions() {
		// TODO Auto-generated method stub
		HashMap<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_setting_values.notifications", 1);
		LoggingPreferences logs = new LoggingPreferences();
		logs.enable(LogType.DRIVER, Level.ALL);
		System.setProperty("webdriver.chrome.driver", System.getenv("USERPROFILE")+ "\\driver\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("useAutomationExtension", false);
		options.addArguments("start-maximized");
		options.setAcceptInsecureCerts(true);
		options.addArguments("--disable-notifications");
		options.setCapability("requireWindowFocus", true);
		options.setCapability("goog:loggingPrefs", logs);
		options.addArguments("force-device-scale-factor=1.75");
		options.addArguments("high-dpi-support=1.75");
		options.addArguments("use-fake-ui-for-media-stream");
		options.setExperimentalOption("prefs", prefs);
		options.setExperimentalOption("excludeSwitches",Collections.singletonList("enable-automation"));
		options.addArguments("--js-flags=--expose-gc");  
		options.addArguments("--enable-precise-memory-info"); 
		options.addArguments("--disable-popup-blocking");
		options.addArguments("--disable-default-apps"); 
		return options;
		
	}
	
	public  DesiredCapabilities getChromeDesiredCapabilities() {
		HashMap<String, Object> prefs = new HashMap<String, Object>();
		DesiredCapabilities cap = new DesiredCapabilities();
		prefs.put("profile.default_content_setting_values.notifications", 2);
		cap = DesiredCapabilities.chrome();
		System.setProperty("webdriver.chrome.driver", System.getenv("USERPROFILE")+ "\\seleniumGrid\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("useAutomationExtension", false);
		options.setExperimentalOption("prefs", prefs);
		options.addArguments("start-maximized");
		options.setAcceptInsecureCerts(true);
		options.addArguments("--disable-notifications");
		options.addArguments("use-fake-ui-for-media-stream");
		cap.setCapability(ChromeOptions.CAPABILITY, options);
		return cap;
	}

}

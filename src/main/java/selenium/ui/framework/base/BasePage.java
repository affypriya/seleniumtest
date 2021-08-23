/**
 * 
 */
package selenium.ui.framework.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;

import selenium.ui.framework.browser.BrowserHandler;

/**
 * @author priya
 *
 */
public class BasePage {

	public WebDriver driver;
	private Map<String, String> dataTable;
	private  Map<String, String> loginInfo = new HashMap<String, String>();
	private Properties prop;
	private String grid = "";
	static Logger log = Logger.getLogger(BasePage.class);
	private  String applnName = "";
	private  String sheetName = "";
	String base64Screenshot;

	@BeforeClass
	public void setup() throws IOException {
		prop = new Properties();
		FileInputStream dataProp = new FileInputStream(
				System.getProperty("user.dir") + "/src/test/resources/config/config.properties");
		prop.load(dataProp);
		loginInfo = gatherLoginInfo();
	}

	/**
	 * @return
	 * @throws IOException
	 */

	private Map<String, String> gatherLoginInfo() {
		// TODO Auto-generated method stub
		try {
			String filePath = System.getenv("USERPROFILE") + "/login/login.properties";
			Path path = FileSystems.getDefault().getPath(filePath);
			return Files.lines(path)
					.collect(Collectors.toMap(u -> u.toString().split("=")[0], p -> p.toString().split("=")[1]));
		} catch (Exception e) {

		}
		return null;
	}

	public WebDriver getDriver() {
		return driver;
	}
	
	public void setDriver(WebDriver wd) {
		this.driver = wd;
	}

	public WebDriver intiateBrowser() throws MalformedURLException {
		String env = dataTable.get("AppName");
		String url = prop.getProperty(env);
		dataTable.put("url", url);
		if (driver == null) {
			driver = new BrowserHandler().intiateBrowser(driver, prop, dataTable, grid);
		}
		driver.navigate().to(url);
		return driver;
	}

	/**
	 * @return the dataTable
	 */
	public Map<String, String> getDataTable() {
		return dataTable;
	}

	/**
	 * @param dataTable the dataTable to set
	 */
	public void setDataTable(Map<String, String> dataTable) {
		this.dataTable = dataTable;
	}

	/**
	 * @return the loginInfo
	 */
	public  Map<String, String> getLoginInfo() {
		return loginInfo;
	}

	/**
	 * @param loginInfo the loginInfo to set
	 */
	public  void setLoginInfo(Map<String, String> loginInfo) {
		this.loginInfo = loginInfo;
	}

	/**
	 * @return the prop
	 */
	public Properties getProp() {
		return prop;
	}

	/**
	 * @param prop the prop to set
	 */
	public void setProp(Properties prop) {
		this.prop = prop;
	}

	/**
	 * @return the grid
	 */
	public String getGrid() {
		return grid;
	}

	/**
	 * @param grid the grid to set
	 */
	public void setGrid(String grid) {
		this.grid = grid;
	}

	/**
	 * @return the applnName
	 */
	public  String getApplnName() {
		return applnName;
	}

	/**
	 * @param applnName the applnName to set
	 */
	public  void setApplnName(String applnName) {
		this.applnName = applnName;
	}

	/**
	 * @return the sheetName
	 */
	public  String getSheetName() {
		return sheetName;
	}

	/**
	 * @param sheetName the sheetName to set
	 */
	public  void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public String getTestName() {
		return "Sheet Name:" + getDataTable().get("sheetName") + "_TestCase Name:"
				+ getDataTable().get("TestCase_Name");
	}

	/**
	 * @return the base64Screenshot
	 */
	public String getBase64Screenshot() {
		return base64Screenshot;
	}

	/**
	 * @param base64Screenshot the base64Screenshot to set
	 */
	public void setBase64Screenshot(String base64Screenshot) {
		this.base64Screenshot = base64Screenshot;
	}
}

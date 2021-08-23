/**
 * 
 */
package selenium.ui.framework.functions;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotSelectableException;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;



/**
 * @author priya
 *
 */
public class SeleniumFunctions {

	public static final int timeOut = 10;
	private Actions actions;
	static Logger log = Logger.getLogger(SeleniumFunctions.class);
	public static Actions builder;

	public static String[] getActionStepArray(String actions) {
		return StringUtils.split(actions, "#");
	}

	public static String[] getActionStepArray(String actions, String symbol) {
		return StringUtils.split(actions, symbol);
	}

	public static void printInfo(String info) {
		log.info("-------------------------------------------------");
		log.info(info);
		log.info("-------------------------------------------------");
	}

	public static String generateRandomAplhaNumericString(int len) {
		StringBuilder sb = new StringBuilder();
		String chars = "abcdefghijklmnopqrstuvzxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		for (int i = 0; i < len; i++) {
			int index = (int) (Math.random() * chars.length());
			sb.append(chars.charAt(index));
		}
		return sb.toString();
	}

	public static String generateRandomAplhaString(int len) {
		StringBuilder sb = new StringBuilder();
		String chars = "abcdefghijklmnopqrstuvzxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		for (int i = 0; i < len; i++) {
			int index = (int) (Math.random() * chars.length());
			sb.append(chars.charAt(index));
		}
		return sb.toString();
	}

	public static String generateRandomNumericString(int len) {
		StringBuilder sb = new StringBuilder();
		String chars = "0123456789";
		for (int i = 0; i < len; i++) {
			int index = (int) (Math.random() * chars.length());
			sb.append(chars.charAt(index));
		}
		return sb.toString();
	}

	public static String generateRandomDate() {
		GregorianCalendar gc = new GregorianCalendar();
		int year = 1990 + (int) Math.round(Math.random() * (Calendar.getInstance().get(Calendar.YEAR) - 1990));
		gc.set(gc.YEAR, year);
		int dayOfYear = 1 + (int) Math.round(Math.random() * (gc.getActualMaximum(gc.DAY_OF_YEAR) - 1));
		gc.set(gc.DAY_OF_YEAR, dayOfYear);
		SimpleDateFormat fmt = new SimpleDateFormat("MM/dd/yyyy");
		fmt.setCalendar(gc);
		String dateFormatted = fmt.format(gc.getTime());
		return dateFormatted;
	}

	public static int getRandomNumber(int min, int max) {
		int random = (int) (Math.random() * max + min);
		return random;
	}

	public static String generateRandomFutureDate(int months) {
		int ranMonth = SeleniumFunctions.getRandomNumber(1, months);
		LocalDate startDate = LocalDate.now();
		LocalDate date;
		date = startDate.plusMonths(ranMonth);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		String formatDateTime = date.format(formatter);
		return formatDateTime;
	}

	public static boolean checkFutureDate(String date) {
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		try {
			LocalDate enteredDate = LocalDate.parse(date, format);
			if (enteredDate.isAfter(currentDate)) {
				return true;
			}
		} catch (DateTimeParseException e) {

		}
		return false;
	}

	public static boolean checkOlderThan(String dob, int limit) {
		LocalDate currentDate = LocalDate.now();
		int currentYr = currentDate.getYear();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		try {
			LocalDate enteredDate = LocalDate.parse(dob, format);
			int enteredYr = enteredDate.getYear();
			if (currentYr - enteredYr >= limit) {
				return true;
			}
		} catch (DateTimeParseException e) {

		}
		return false;
	}

	public static boolean checkDateFormat(String date) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		try {
			LocalDate enteredDate = LocalDate.parse(date, format);
			return true;
		} catch (DateTimeParseException e) {
			return false;
		}
	}

	public static boolean containsNumberSpecialCharater(String s) {
		return (s == null) ? false : s.matches("[^A-Za-z0-9]");
	}

	public static boolean containsSpecialCharater(String s) {
		return (s == null) ? false : s.matches("[^A-Za-z]");
	}

	public static boolean isExpiredDate(String expDate) {
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		try {
			LocalDate date = LocalDate.parse(expDate, format);
			if (currentDate.isAfter(date)) {
				return true;
			}
		} catch (DateTimeParseException e) {

		}
		return false;
	}

	public static boolean ifYoungerOrOlder(String dob, int min, int max) {
		LocalDate currentDate = LocalDate.now();
		int currentYr = currentDate.getYear();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		try {
			LocalDate enteredDate = LocalDate.parse(dob, format);
			int enteredYr = enteredDate.getYear();
			if (currentYr - enteredYr >= max || currentYr - enteredYr <= 14) {
				return true;
			}
		} catch (DateTimeParseException e) {

		}
		return false;
	}

	public static boolean isElementPresent(WebDriver driver, WebElement ele) {
		try {
			fluentWaitForElement(driver, ele);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public static void enterText(WebDriver driver, WebElement ele, String txt) {
		String txtVal = txt == null ? "" : txt;
		if (isElementPresent(driver, ele) && ele.isEnabled()) {
			ele.clear();
			ele.sendKeys(txtVal);
		} else if (!ele.isEnabled()) {
			fluentWaitClickable(driver, ele, 2);
			ele.clear();
			ele.sendKeys(txtVal);
		} else {
			Assert.fail("Failed looking for textField for textField element .... " + ele.toString());
		}
	}

	public static void enterText(WebDriver driver, String eleString, String txt) {
		String txtVal = txt == null ? "" : txt;
		WebElement ele = getWebElement(driver, eleString);
		if (isElementPresent(driver, ele) && ele.isEnabled()) {
			ele.clear();
			ele.sendKeys(txtVal);
		} else if (!ele.isEnabled()) {
			fluentWaitClickable(driver, ele, 2);
			ele.clear();
			ele.sendKeys(txtVal);
		} else {
			Assert.fail("Failed looking for textField for textField element .... " + ele.toString());
		}
	}

	public static void clickElement(WebDriver driver, WebElement ele) {
		try {
			if (isElementPresent(driver, ele)) {
				fluentWaitClickable(driver, ele, 10);
			}
			ele.click();
		} catch (Exception e) {
			jsClick(driver, ele);
		}
	}

	public static void clickElement(WebDriver driver, String eleStr) {
		WebElement we = getWebElement(driver, eleStr);
		try {
			if (isElementPresent(driver, we)) {
				fluentWaitClickable(driver, we, 10);
			}
			we.click();
		} catch (Exception e) {
			jsClick(driver, we);
		}
	}

	/**
	 * @param driver
	 * @param ele
	 */
	public static void jsClick(WebDriver driver, WebElement ele) {
		// TODO Auto-generated method stub
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", ele);
	}

	public static void jsClick(WebDriver driver, String xpath) {
		// TODO Auto-generated method stub
		WebElement we = getWebElement(driver, xpath);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", we);
	}

	public static void setDropDownByVisibleText(WebDriver driver, WebElement ele, String txt) {
		if (isElementPresent(driver, ele)) {
			Select dropdown = new Select(ele);
			dropdown.selectByVisibleText(txt);
		} else {
			Assert.fail("Failed looking for " + txt + " in dropdown");
		}
	}

	public static String getDropDownByVisibleText(WebDriver driver, WebElement ele) {
		String text = "";
		if (isElementPresent(driver, ele)) {
			Select dropdown = new Select(ele);
			WebElement option = dropdown.getFirstSelectedOption();
			text = option.getText();
		} else {
			Assert.fail("Failed to get text from drop down");
		}
		return text;
	}

	public static String getDropDownByVisibleText(WebDriver driver, String xpath) {
		WebElement ele = getWebElement(driver, xpath);
		String text = "";
		if (isElementPresent(driver, ele)) {
			Select dropdown = new Select(ele);
			WebElement option = dropdown.getFirstSelectedOption();
			text = option.getText();
		} else {
			Assert.fail("Failed to get text from drop down");
		}
		return text;
	}

	public static String getDropDownByValue(WebDriver driver, String xpath) {
		WebElement ele = getWebElement(driver, xpath);
		String text = "";
		if (isElementPresent(driver, ele)) {
			Select dropdown = new Select(ele);
			WebElement option = dropdown.getFirstSelectedOption();
			text = option.getAttribute("value");
		} else {
			Assert.fail("Failed to get text from drop down");
		}
		return text;
	}

	public static String getDropDownByValue(WebDriver driver, WebElement ele) {
		String text = "";
		if (isElementPresent(driver, ele)) {
			Select dropdown = new Select(ele);
			WebElement option = dropdown.getFirstSelectedOption();
			text = option.getAttribute("value");
		} else {
			Assert.fail("Failed to get text from drop down");
		}
		return text;
	}

	public static void setDropDownByValue(WebDriver driver, WebElement ele, String value) {

		if (value != null) {
			if (isElementPresent(driver, ele)) {
				Select dropdown = new Select(ele);
				dropdown.selectByValue(value);
			} else {
				Assert.fail("Failed looking for " + value + " in dropdown");
			}
		} else {
			Assert.fail("Value is null");
		}
	}

	public static void setDropDownByIndex(WebDriver driver, WebElement ele, int index) {
		if (isElementPresent(driver, ele)) {
			Select dropdown = new Select(ele);
			dropdown.selectByIndex(index);
		} else {
			Assert.fail("Failed looking for index : " + index + " in dropdown");
		}
	}

	public static void setDropDownByVisibleText(WebDriver driver, String xpath, String txt) {
		WebElement ele = getWebElement(driver, xpath);
		if (isElementPresent(driver, ele)) {
			Select dropdown = new Select(ele);
			dropdown.selectByVisibleText(txt);
		} else {
			Assert.fail("Failed looking for " + txt + " in dropdown");
		}
	}

	public static void setDropDownByValue(WebDriver driver, String xpath, String value) {
		WebElement ele = getWebElement(driver, xpath);
		if (value != null) {
			if (isElementPresent(driver, ele)) {
				Select dropdown = new Select(ele);
				dropdown.selectByValue(value);
			} else {
				Assert.fail("Failed looking for " + value + " in dropdown");
			}
		} else {
			Assert.fail("Value is null");
		}
	}

	public static void setDropDownByIndex(WebDriver driver, String xpath, int index) {
		WebElement ele = getWebElement(driver, xpath);
		if (isElementPresent(driver, ele)) {
			Select dropdown = new Select(ele);
			dropdown.selectByIndex(index);
		} else {
			Assert.fail("Failed looking for index : " + index + " in dropdown");
		}
	}

	public static String getElementText(WebDriver driver, WebElement ele) {
		if (isElementPresent(driver, ele)) {
			return ele.getText();
		}
		return "";
	}

	public static String getElementText(WebDriver driver, String xpath) {
		if (isElementPresent(driver, xpath)) {
			return getWebElement(driver, xpath).getText();
		}
		return "";
	}

	public static String getElementValue(WebDriver driver, WebElement ele) {
		if (isElementPresent(driver, ele)) {
			return ele.getAttribute("value");
		}
		return "";
	}

	public static String getAttributeValue(WebDriver driver, String xpath, String attribute) {
		if (isElementPresent(driver, xpath)) {
			return getWebElement(driver, xpath).getAttribute(attribute);
		}
		return "";
	}

	public static String getAttributeValue(WebDriver driver, WebElement ele, String attribute) {
		if (isElementPresent(driver, ele)) {
			return ele.getAttribute(attribute);
		}
		return "";
	}

	public static List<String> getElementsText(WebDriver driver, List<WebElement> eleList) {

		List<String> txtList = new ArrayList<String>();
		if (isElementPresent(driver, eleList)) {
			for (WebElement we : eleList) {
				txtList.add(we.getText());
			}
		}
		return txtList;
	}

	public static boolean checkTitle(WebDriver driver, String title) throws NoSuchElementException {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.titleContains(title));

	}

	public static WebElement fluentWaitVisibility(WebDriver driver, WebElement ele) {
		try {
			FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(timeOut, TimeUnit.SECONDS)
					.pollingEvery(5, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class);
			return wait.until(ExpectedConditions.visibilityOf(ele));
		} catch (Exception e) {
			return null;
		}
	}

	public static List<WebElement> fluentWaitVisibility(WebDriver driver, List<WebElement> eleList) {
		try {
			FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(timeOut, TimeUnit.SECONDS)
					.pollingEvery(5, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class);
			return wait.until(ExpectedConditions.visibilityOfAllElements(eleList));
		} catch (Exception e) {
			return null;
		}
	}

	public static WebElement fluentWaitVisibility(WebDriver driver, WebElement ele, int secs) {
		try {
			FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(secs, TimeUnit.SECONDS)
					.pollingEvery(5, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class);
			return wait.until(ExpectedConditions.visibilityOf(ele));
		} catch (Exception e) {
			return null;
		}
	}

	public static List<WebElement> fluentWaitVisibility(WebDriver driver, List<WebElement> eleList, int secs) {
		try {
			FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(secs, TimeUnit.SECONDS)
					.pollingEvery(5, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class);
			return wait.until(ExpectedConditions.visibilityOfAllElements(eleList));
		} catch (Exception e) {
			return null;
		}
	}

	public static void fluentWait(WebDriver driver, String xpath) {
		try {
			FluentWait<WebDriver> wait = new WebDriverWait(driver, timeOut);
			wait.withTimeout(timeOut, TimeUnit.SECONDS).pollingEvery(5, TimeUnit.MILLISECONDS)
					.ignoring(NoSuchElementException.class).ignoring(ElementNotVisibleException.class)
					.ignoring(StaleElementReferenceException.class);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		} catch (Exception e) {

		}
	}

	/**
	 * @param driver
	 * @param ele
	 * @param secs
	 */
	public static void fluentWaitClickable(WebDriver driver, WebElement ele) {
		// TODO Auto-generated method stub
		try {
			FluentWait<WebDriver> wait = new WebDriverWait(driver, timeOut);
			wait.withTimeout(timeOut, TimeUnit.SECONDS).pollingEvery(5, TimeUnit.MILLISECONDS)
					.ignoring(NoSuchElementException.class).ignoring(ElementNotVisibleException.class)
					.ignoring(StaleElementReferenceException.class);
			wait.until(ExpectedConditions.elementToBeClickable(ele));
		} catch (Exception e) {

		}
	}

	/**
	 * @param driver
	 * @param ele
	 * @param secs
	 */
	public static void fluentWaitClickable(WebDriver driver, String xpath) {
		// TODO Auto-generated method stub
		try {
			FluentWait<WebDriver> wait = new WebDriverWait(driver, timeOut);
			wait.withTimeout(timeOut, TimeUnit.SECONDS).pollingEvery(5, TimeUnit.MILLISECONDS)
					.ignoring(NoSuchElementException.class).ignoring(ElementNotVisibleException.class)
					.ignoring(StaleElementReferenceException.class);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
		} catch (Exception e) {

		}
	}

	public static void fluentWaitEnabled(WebDriver driver, WebElement ele) {
		// TODO Auto-generated method stub
		try {
			FluentWait<WebDriver> wait = new WebDriverWait(driver, timeOut);
			wait.withTimeout(timeOut, TimeUnit.SECONDS).pollingEvery(5, TimeUnit.MILLISECONDS)
					.ignoring(NoSuchElementException.class).ignoring(ElementNotVisibleException.class)
					.ignoring(StaleElementReferenceException.class);
			wait.until(ExpectedConditions.elementSelectionStateToBe(ele, ele.isEnabled()));
		} catch (Exception e) {

		}
	}

	public static void fluentWaitEnabled(WebDriver driver, WebElement ele, int secs) {
		// TODO Auto-generated method stub
		try {
			FluentWait<WebDriver> wait = new WebDriverWait(driver, secs);
			wait.withTimeout(secs, TimeUnit.SECONDS).pollingEvery(5, TimeUnit.MILLISECONDS)
					.ignoring(NoSuchElementException.class).ignoring(ElementNotVisibleException.class)
					.ignoring(StaleElementReferenceException.class);
			wait.until(ExpectedConditions.elementSelectionStateToBe(ele, ele.isEnabled()));
		} catch (Exception e) {

		}
	}

	public static void fluentWaitEnabled(WebDriver driver, String xpath, int secs) {
		// TODO Auto-generated method stub
		try {
			FluentWait<WebDriver> wait = new WebDriverWait(driver, secs);
			wait.withTimeout(secs, TimeUnit.SECONDS).pollingEvery(5, TimeUnit.MILLISECONDS)
					.ignoring(NoSuchElementException.class).ignoring(ElementNotVisibleException.class)
					.ignoring(StaleElementReferenceException.class);
			wait.until(ExpectedConditions.elementSelectionStateToBe(By.xpath(xpath),
					getWebElement(driver, xpath).isEnabled()));
		} catch (Exception e) {

		}
	}

	/**
	 * @param driver
	 * @param ele
	 * @param secs
	 */
	public static void fluentWaitClickable(WebDriver driver, WebElement ele, int secs) {
		// TODO Auto-generated method stub
		try {
			FluentWait<WebDriver> wait = new WebDriverWait(driver, secs);
			wait.withTimeout(secs, TimeUnit.SECONDS).pollingEvery(5, TimeUnit.MILLISECONDS)
					.ignoring(NoSuchElementException.class).ignoring(ElementNotVisibleException.class)
					.ignoring(StaleElementReferenceException.class);
			wait.until(ExpectedConditions.elementToBeClickable(ele));
		} catch (Exception e) {

		}
	}

	/**
	 * @param driver
	 * @param ele
	 * @param secs
	 */
	public static void fluentWaitClickable(WebDriver driver, String xpath, int secs) {
		// TODO Auto-generated method stub
		try {
			FluentWait<WebDriver> wait = new WebDriverWait(driver, secs);
			wait.withTimeout(secs, TimeUnit.SECONDS).pollingEvery(5, TimeUnit.MILLISECONDS)
					.ignoring(NoSuchElementException.class).ignoring(ElementNotVisibleException.class)
					.ignoring(StaleElementReferenceException.class);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
		} catch (Exception e) {

		}
	}

	public static void fluentWaitForElementToBeSelected(WebDriver driver, WebElement ele) {
		try {
			FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(timeOut, TimeUnit.SECONDS)
					.pollingEvery(3, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class);
			wait.until(ExpectedConditions.elementSelectionStateToBe(ele, ele.isSelected()));
		} catch (Exception e) {

		}
	}

	/**
	 * @param driver
	 * @param ele
	 */
	private static void fluentWaitForElement(WebDriver driver, WebElement ele) {
		// TODO Auto-generated method stub
		try {
			FluentWait<WebDriver> wait = new WebDriverWait(driver, timeOut);
			wait.withTimeout(timeOut, TimeUnit.SECONDS).pollingEvery(5, TimeUnit.MILLISECONDS)
					.ignoring(NoSuchElementException.class).ignoring(ElementNotVisibleException.class)
					.ignoring(StaleElementReferenceException.class);
			wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver driver) {
					return ele;
				}
			});
		} catch (Exception e) {

		}
	}

	/**
	 * @param driver
	 * @param xpath
	 */
	private static void fluentWaitForElement(WebDriver driver, String xpath) {
		// TODO Auto-generated method stub
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeOut);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		} catch (Exception e) {

		}
	}

	public static void clickElement(WebDriver driver, WebElement ele, String successMsg, String failureMsg) {

		boolean found = false;
		final long startTime = System.currentTimeMillis();

		while ((System.currentTimeMillis() - startTime) < 100000) {
			try {
				scrollToElement(driver, ele);
				fluentWaitForElementToBeSelected(driver, ele);
				ele.click();
				found = true;
				break;
			} catch (NoSuchElementException e1) {
				log.error(failureMsg + "Error : No such Element present");
				log.error(ExceptionUtils.getStackTrace(e1));
			} catch (ElementNotVisibleException e2) {
				log.error(failureMsg + "Error : Element Not Visible");
				log.error(ExceptionUtils.getStackTrace(e2));
			} catch (StaleElementReferenceException e3) {
				log.error(failureMsg + "Error : Stale Element Reference Exception");
				log.error(ExceptionUtils.getStackTrace(e3));
			} catch (ElementNotSelectableException e4) {
				log.error(failureMsg + "Error : Element Not Selectable Exception");
				log.error(ExceptionUtils.getStackTrace(e4));
			} catch (ElementClickInterceptedException e5) {
				log.error(failureMsg + "Error : Element Click Intercepted Exception");
				log.error(ExceptionUtils.getStackTrace(e5));
			} catch (Exception e) {
				log.error(failureMsg + "Error :  Exception");
				log.error(ExceptionUtils.getStackTrace(e));
			}
		}

		long endTime = System.currentTimeMillis();
		long totalTime = endTime = startTime;

		if (found) {
			log.info("Found element after waiting for" + totalTime + "milliseconds");
			log.info(successMsg);
		} else {
			log.info("Unable to find element after waiting for" + totalTime + "milliseconds");
			log.info(failureMsg);
		}
	}

	/**
	 * @param driver
	 * @param eleString
	 * @return
	 */
	public static WebElement getWebElement(WebDriver driver, String xpath) {
		// TODO Auto-generated method stub
		fluentWaitForElement(driver, xpath);
		WebElement we = driver.findElement(By.xpath(xpath));
		return we;
	}

	public static List<WebElement> getWebElements(WebDriver driver, String xpath) {
		fluentWaitForElement(driver, xpath);
		List<WebElement> weList = driver.findElements(By.xpath(xpath));
		return weList;
	}

	public static WebElement findWebElement(WebDriver driver, WebElement rootEle, String xpath) {
		WebElement we = rootEle.findElement(By.xpath(xpath));
		return we;
	}

	public static String getWebElementTxt(WebDriver driver, WebElement ele) {
		// TODO Auto-generated method stub
		fluentWaitForElement(driver, ele);
		return ele.getText();
	}

	public static void scrollToElement(WebDriver driver, WebElement ele) {
		int x = ele.getLocation().getX();
		int y = ele.getLocation().getY();

		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("window.scrollTo(" + x + "," + (y - 100) + ")");
	}

	public void enterKeyStoke(WebDriver driver) {
		actions = new Actions(driver);
		actions.sendKeys(Keys.ENTER).perform();
	}

	public void tabKey(WebDriver driver) {
		actions = new Actions(driver);
		actions.sendKeys(Keys.TAB).perform();
	}

	public static boolean isNotElementStale(WebDriver driver, WebElement ele) {
		try {
			return ele.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * @param driver
	 * @param xpath
	 * @return
	 */
	private static boolean isElementPresent(WebDriver driver, String xpath) {
		// TODO Auto-generated method stub
		try {
			WebElement ele = null;
			WebDriverWait wait = new WebDriverWait(driver, timeOut);
			ele = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
			return ele.isDisplayed() && ele.isEnabled();
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * @param driver
	 * @param eleList
	 * @return
	 */
	private static boolean isElementPresent(WebDriver driver, List<WebElement> eleList) {
		// TODO Auto-generated method stub
		try {
			List<WebElement> ele = null;
			WebDriverWait wait = new WebDriverWait(driver, timeOut);
			ele = wait.until(ExpectedConditions.visibilityOfAllElements(eleList));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean dismissAlerts(WebDriver driver) {
		try {
			Alert alert = driver.switchTo().alert();
			alert.dismiss();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static String getElementAttribute(WebDriver driver, WebElement ele, String attribute) {
		try {
			return ele.getAttribute(attribute).trim();
		} catch (Exception e) {
			return "";
		}
	}

	public static boolean isElementDisplayed(WebDriver driver, WebElement ele) {
		try {
			return ele.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean isElementsDisplayed(WebDriver driver, List<WebElement> eleList) {
		if (eleList != null && eleList.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isElementEnabled(WebDriver driver, WebElement ele) {
		try {
			return ele.isEnabled();
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean isElementDisabled(WebDriver driver, WebElement ele) {
		try {
			return ele.isEnabled();
		} catch (Exception e) {
			return true;
		}
	}

	public static boolean isRadioSelected(WebDriver driver, WebElement ele) {
		return ele.isSelected();
	}

	public static boolean isElementSelected(WebDriver driver, WebElement ele) {
		return ele.isSelected();
	}

	public WebElement getWebElement(WebDriver driver, final String idStr, final String idBy) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(timeOut, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class);
		return wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				if (StringUtils.equalsIgnoreCase(idBy, "xpath")) {
					return driver.findElement(By.xpath(idStr));
				} else if (StringUtils.equalsIgnoreCase(idBy, "id")) {
					return driver.findElement(By.id(idStr));
				} else if (StringUtils.equalsIgnoreCase(idBy, "name")) {
					return driver.findElement(By.name(idStr));
				} else {
					return null;
				}
			}
		});
	}

	public List<WebElement> getWebElements(WebDriver driver, final String idStr, final String idBy) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(timeOut, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class);
		return wait.until(new Function<WebDriver, List<WebElement>>() {
			public List<WebElement> apply(WebDriver driver) {
				if (StringUtils.equalsIgnoreCase(idBy, "xpath")) {
					return driver.findElements(By.xpath(idStr));
				} else if (StringUtils.equalsIgnoreCase(idBy, "id")) {
					return driver.findElements(By.id(idStr));
				} else if (StringUtils.equalsIgnoreCase(idBy, "name")) {
					return driver.findElements(By.name(idStr));
				} else {
					return null;
				}
			}
		});
	}

	public static void actionMouseOver(WebDriver driver, WebElement ele) {
		builder = getBuilder(driver);
		builder.moveToElement(ele).perform();
		;
	}

	public static void actionMouseOver(WebDriver driver, String xpath) {
		builder = getBuilder(driver);
		WebElement we = getWebElement(driver, xpath);
		builder.moveToElement(we).perform();
	}

	/**
	 * @param driver
	 * @return
	 */
	private static Actions getBuilder(WebDriver driver) {
		// TODO Auto-generated method stub
		if (builder == null) {
			return new Actions(driver);
		}
		return builder;
	}

	public static void actionClick(WebDriver driver, WebElement ele) {
		builder = getBuilder(driver);
		builder.moveToElement(ele).click().perform();
		;
	}

	public static void actionClick(WebDriver driver, String xpath) {
		builder = getBuilder(driver);
		WebElement we = getWebElement(driver, xpath);
		builder.moveToElement(we).click().perform();
		;
	}

	public static void waitForPageToLoad(int i) {
		try {
			Thread.sleep(i * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static String getXpath(WebElement ele) {
		String str = ele.toString();
		String[] listString = null;
		if (str.contains("xpath")) {
			listString = str.split("xpath:");
		} else if (str.contains("id")) {
			listString = str.split("id:");
		}
		String last = listString[1].trim();
		return last.substring(0, last.length() - 1);
	}

	public static By convertWebElementToByReference(WebElement ele) {
		By byLocator = null;
		String eleDesc = ele.toString();

		String elementTypeAndValue[] = (eleDesc.substring(eleDesc.lastIndexOf("-> ") + 3, eleDesc.lastIndexOf("]")))
				.split(":");

		switch (elementTypeAndValue[0].trim()) {
		case "id":
			byLocator = By.id(elementTypeAndValue[1].trim());
			break;
		case "xpath":
			byLocator = By.xpath(elementTypeAndValue[1].trim());
			break;
		case "link text":
			byLocator = By.linkText(elementTypeAndValue[1].trim());
			break;
		case "tag name":
			byLocator = By.tagName(elementTypeAndValue[1].trim());
			break;
		case "class":
			byLocator = By.className(elementTypeAndValue[1].trim());
			break;
		case "partial link text":
			byLocator = By.partialLinkText(elementTypeAndValue[1].trim());
			break;
		case "name":
			byLocator = By.name(elementTypeAndValue[1].trim());
			break;
		case "css selector":
			byLocator = By.cssSelector(elementTypeAndValue[1].trim());
			break;
		default:
			throw new RuntimeException("Invalid locator type: " + elementTypeAndValue[0].trim());
		}
		return byLocator;
	}


	/**
	 * @param linkTest
	 * @param obj
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static String getMappedWE(String linkTest, Object obj)
			throws IllegalArgumentException, IllegalAccessException {
		// TODO Auto-generated method stub
		try {
			for (Field field : obj.getClass().getFields()) {
				if (field.getName().equals(linkTest)) {
					return field.get(obj).toString();
				}
			}
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
}

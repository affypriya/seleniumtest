/**
 * 
 */
package selenium.ui.framework.sikulirobo;

import java.util.Iterator;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import selenium.ui.framework.functions.SeleniumFunctions;



/**
 * @author priya
 *
 */
public class AuthenticateUsingSikuli {

	
	public static void pivLogin(Map<String,String> loginInfo) {
		SeleniumFunctions.waitForPageToLoad(5);
		
		Runnable runnable = () -> {
			try {
				Screen s = new Screen(0);
				SelectCertificate(s);
				enterPin(loginInfo.get("PIV"),s);
			} catch (Exception e) {
				
			}
		};
		Thread th = new Thread(runnable);
		th.start();
	}
	
	public static void hashLogin(Map<String,String> loginInfo, WebDriver driver) {
		SeleniumFunctions.waitForPageToLoad(5);
		
		Runnable runnable = () -> {
			try {
				Screen s = new Screen(0);
				cancelCertificate(loginInfo,driver,s);
			} catch (Exception e) {
				
			}
		};
		Thread th = new Thread(runnable);
		th.start();
	}

	/**
	 * @param loginInfo
	 * @param driver
	 * @param s
	 * @throws FindFailed 
	 */
	private static void cancelCertificate(Map<String, String> loginInfo, WebDriver driver, Screen s) throws FindFailed {
		// TODO Auto-generated method stub
		Pattern cancel_button = new Pattern(System.getProperty("user.dir") + "\\src\\main\\resources\\"+"cancel_button.PNG");
		Pattern certi = new Pattern(System.getProperty("user.dir") + "\\src\\main\\resources\\"+"certi.PNG");
		
		if(s.exists(certi) == null && s.exists(cancel_button) == null) {
			Iterator<Match> chromeMatch = s.findAll(System.getProperty("user.dir") + "\\src\\main\\resources\\"+"chrome.PNG");
			while (chromeMatch.hasNext() && s.exists(cancel_button) == null) {
				chromeMatch.next().click();
			}
		}
		s.wait(cancel_button,5);
		s.click(cancel_button);
	}

	/**
	 * @param string
	 * @param s
	 * @throws FindFailed 
	 */
	private static void enterPin(String string, Screen s) throws FindFailed {
		// TODO Auto-generated method stub
		Pattern pin = new Pattern(System.getProperty("user.dir")+"\\src\\main\\resources\\"+"pin.PNG");
		s.wait(pin,5);
		
	}

	/**
	 * @param s
	 * @throws FindFailed 
	 */
	private static void SelectCertificate(Screen s) throws FindFailed {
		// TODO Auto-generated method stub
		Pattern ok_button = new Pattern(System.getProperty("user.dir") + "\\src\\main\\resources\\"+"ok_button.PNG");
		Pattern certi = new Pattern(System.getProperty("user.dir") + "\\src\\main\\resources\\"+"certi.PNG");
		
		if(s.exists(certi) == null && s.exists(ok_button) == null) {
			Iterator<Match> chromeMatch = s.findAll(System.getProperty("user.dir") + "\\src\\main\\resources\\"+"chrome.PNG");
			while (chromeMatch.hasNext() && s.exists(ok_button) == null) {
				chromeMatch.next().click();
			}
		}
		s.wait(ok_button,5);
		s.click(ok_button);
	}
}

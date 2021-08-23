/**
 * 
 */
package selenium.ui.framework.sikulirobo;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.sikuli.script.Screen;

import selenium.ui.framework.functions.SeleniumFunctions;



/**
 * @author priya
 *
 */
public class AuthenticateUsingRobot {
	public static Robot robo;

	static {
		try {
			robo = new Robot();
		} catch (AWTException ae) {
			ae.printStackTrace();
		}
	}

	public static void pivLogin(Map<String, String> loginInfo) {
		SeleniumFunctions.waitForPageToLoad(5);

		Runnable runnable = () -> {
			try {
				SelectCertificate();
				enterPin(loginInfo.get("PIV"));
			} catch (Exception e) {

			}
		};
		Thread th = new Thread(runnable);
		th.start();
	}

	public static void hashLogin(Map<String, String> loginInfo, WebDriver driver) {
		SeleniumFunctions.waitForPageToLoad(5);

		Runnable runnable = () -> {
			try {
				cancelCertificate(loginInfo, driver);
			} catch (Exception e) {

			}
		};
		Thread th = new Thread(runnable);
		th.start();
	}

	/**
	 * @param loginInfo
	 * @param driver
	 */
	private static void cancelCertificate(Map<String, String> loginInfo, WebDriver driver) {
		// TODO Auto-generated method stub
		robo.keyPress(KeyEvent.VK_ESCAPE);
		robo.keyRelease(KeyEvent.VK_ESCAPE);
	}

	/**
	 * @param string
	 */
	private static void enterPin(String pin) {
		// TODO Auto-generated method stub
		char[] digits = pin.toCharArray();

		for (int i = 0; i < digits.length; i++) {
			switch (digits[i]) {
			case '0':
				robo.keyPress(KeyEvent.VK_0);
				break;
			case '1':
				robo.keyPress(KeyEvent.VK_1);
				break;
			case '2':
				robo.keyPress(KeyEvent.VK_2);
				break;
			case '3':
				robo.keyPress(KeyEvent.VK_3);
				break;
			case '4':
				robo.keyPress(KeyEvent.VK_4);
				break;
			case '5':
				robo.keyPress(KeyEvent.VK_5);
				break;
			case '6':
				robo.keyPress(KeyEvent.VK_6);
				break;
			case '7':
				robo.keyPress(KeyEvent.VK_7);
				break;
			case '8':
				robo.keyPress(KeyEvent.VK_8);
				break;
			case '9':
				robo.keyPress(KeyEvent.VK_9);
				break;
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
		}
		robo.keyPress(KeyEvent.VK_ENTER);
		robo.keyRelease(KeyEvent.VK_ENTER);
	}

	/**
	 * 
	 */
	private static void SelectCertificate() {
		// TODO Auto-generated method stub
		robo.keyPress(KeyEvent.VK_ENTER);
		robo.keyRelease(KeyEvent.VK_ENTER);
	}
}

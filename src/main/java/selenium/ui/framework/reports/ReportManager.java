/**
 * 
 */
package selenium.ui.framework.reports;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.relevantcodes.extentreports.ExtentReports;

/**
 * @author priya
 *
 */
public class ReportManager {

	public static String name;
	private static ExtentReports extent;

	public static synchronized  ExtentReports getReporter() {

		if (extent == null) {
			String workingDir = System.getProperty("user.dir");
			String timeStamp = new SimpleDateFormat("YYYY_MM_dd__hh_mm_ss").format(new Date());
			extent = new ExtentReports(workingDir + "\\target\\reports\\" + name + "_result_" + timeStamp + ".html");
		}
		return extent;

	}
}

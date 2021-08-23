/**
 * 
 */
package selenium.ui.framework.utils;
import java.util.Comparator;

import org.testng.IMethodInstance;

/**
 * @author priya
 *
 */
public class SerialNoComarator implements Comparator<IMethodInstance> {

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(IMethodInstance o1, IMethodInstance o2) {
		// TODO Auto-generated method stub
		TestCases o1Test = (TestCases) o1.getInstance();
		TestCases o2Test = (TestCases) o2.getInstance();
		return o1Test.testSeq().compareTo(o2Test.testSeq());
	}

}

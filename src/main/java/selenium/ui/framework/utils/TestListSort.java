/**
 * 
 */
package selenium.ui.framework.utils;

import java.util.Collections;
import java.util.List;

import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;

/**
 * @author priya
 *
 */
public class TestListSort implements IMethodInterceptor{

	/* (non-Javadoc)
	 * @see org.testng.IMethodInterceptor#intercept(java.util.List, org.testng.ITestContext)
	 */
	@Override
	public List<IMethodInstance> intercept(List<IMethodInstance> methods, ITestContext context) {
		// TODO Auto-generated method stub
		Collections.sort(methods,new SerialNoComarator());
		return methods;
	}

}

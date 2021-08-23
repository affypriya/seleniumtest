/**
 * 
 */
package selenium.ui.framework.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

import selenium.ui.framework.base.BasePage;



/**
 * @author priya
 *
 */
public class TestDataProvider extends BasePage{

	@DataProvider()
	public  Object[][] testData(ITestContext iTestContext){
		return returnTestDataProviderInput(getTestData(iTestContext));
		
	}

	/**
	 * @param testData
	 * @return
	 */
	private  Object[][] returnTestDataProviderInput(ArrayList<Map<String,String>> testData) {
		// TODO Auto-generated method stub
		Object[][] data = new Object[testData.size()][1];
		for(int i = 0; i <testData.size(); i++) {
			HashMap<String,String> table = new HashMap<String,String>(testData.get(i));
			data[i][0] = table;
		}
		return data;
	}

	/**
	 * @param iTestContext
	 * @return
	 */
	private  ArrayList<Map<String,String>> getTestData(ITestContext iTestContext) {
		// TODO Auto-generated method stub
		String work_book = iTestContext.getCurrentXmlTest().getName();
		String fileName = FilenameUtils.normalize(System.getProperty("user.dir")+ "/src/test/resources/testdata/" + work_book + ".xlsx");
		Xls_Reader xls = new Xls_Reader(fileName);
		int noOfSheets = xls.getWorkbook().getNumberOfSheets();
		
		return readTestData(work_book,"Yes",xls,iTestContext);
	}

	/**
	 * @param work_book
	 * @param string
	 * @param xls
	 * @param iTestContext
	 * @return
	 */
	private  ArrayList<Map<String, String>> readTestData(String work_book, String runMode, Xls_Reader xls,
			ITestContext iTestContext) {
		// TODO Auto-generated method stub
		int noOfSheets = xls.getWorkbook().getNumberOfSheets();
		String sheetName = "";
		ArrayList<Map<String, String>> data  = new ArrayList<Map<String, String>>();
		for(int i =0; i< noOfSheets ; i++) {
			sheetName = xls.getSheetName(i);
			int sheetIndex = i;
			ArrayList<Integer> noOfIterations = getNoOfIterations(runMode,xls,sheetName);
			data = getData(xls,sheetName,noOfIterations,data,sheetIndex);
		}
		return data;
	}

	/**
	 * @param xls
	 * @param sheetName
	 * @param noOfIterations
	 * @param data
	 * @param sheetIndex
	 * @return
	 */
	private  ArrayList<Map<String, String>> getData(Xls_Reader xls, String sheetName,
			ArrayList<Integer> noOfIterations, ArrayList<Map<String, String>> data, int sheetIndex) {
		// TODO Auto-generated method stub
		for(int i=0; i<noOfIterations.size();i++) {
			Map<String, String> table = new HashMap<String, String>();
			table.put("sheetName", sheetName);
			table.put("sheetIndex", String.valueOf(sheetIndex));
			for(int cols=0;cols<100;cols++) {
				String cellData = (String) xls.getCellData(sheetName, cols, noOfIterations.get(i));
				String colName = String.valueOf(xls.getCellData(sheetName, cols, 1));
				if(colName == null || StringUtils.isEmpty(colName)) {
					break;
				}
				table.put(colName,cellData);
			}
			data.add(table);
		}
		return data;
	}

	/**
	 * @param runMode
	 * @param xls
	 * @param sheetName
	 * @return
	 */
	private  ArrayList<Integer> getNoOfIterations(String runMode, Xls_Reader xls, String sheetName) {
		// TODO Auto-generated method stub
		ArrayList<Integer> noOfIterations = new ArrayList<Integer>();
		int rows = xls.getRowCount(sheetName);
		for (int rNum = 2; rNum <=rows ;rNum++) {
			if(runMode.equalsIgnoreCase(xls.getCellData(sheetName, "RunMode", rNum))) {
				noOfIterations.add(rNum);
			}			
		}
		return noOfIterations;
	}
}

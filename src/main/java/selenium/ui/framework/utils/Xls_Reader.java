/**
 * 
 */
package selenium.ui.framework.utils;

import java.io.FileInputStream;
import java.util.Calendar;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 * @author priya
 *
 */
public class Xls_Reader {
	public String path;
	public FileInputStream fis = null;
	public XSSFWorkbook workbook = null;
	public XSSFSheet sheet = null;
	public XSSFRow row = null;
	public XSSFCell cell = null;

	public Xls_Reader(String fileName) {
		this.path = fileName;
		try {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheetAt(0);
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * @return the workbook
	 */
	public XSSFWorkbook getWorkbook() {
		return workbook;
	}

	/**
	 * @param workbook the workbook to set
	 */
	public void setWorkbook(XSSFWorkbook workbook) {
		this.workbook = workbook;
	}

	/**
	 * @return the sheet
	 */
	public XSSFSheet getSheet() {
		return sheet;
	}

	/**
	 * @param sheet the sheet to set
	 */
	public void setSheet(XSSFSheet sheet) {
		this.sheet = sheet;
	}

	/**
	 * @return the row
	 */
	public XSSFRow getRow() {
		return row;
	}

	/**
	 * @param row the row to set
	 */
	public void setRow(XSSFRow row) {
		this.row = row;
	}

	/**
	 * @return the cell
	 */
	public XSSFCell getCell() {
		return cell;
	}

	/**
	 * @param cell the cell to set
	 */
	public void setCell(XSSFCell cell) {
		this.cell = cell;
	}

	public int getRowCount(String sheetName) {
		int index = workbook.getSheetIndex(sheetName);
		if (index == -1) {
			return 0;
		} else {
			sheet = workbook.getSheetAt(index);
			int num = sheet.getLastRowNum() + 1;
			return num;
		}
	}

	public String getSheetName(int index) {
		XSSFSheet sheet = workbook.getSheetAt(index);
		return sheet.getSheetName();
	}

	public String getCellData(String sheetName, String colName, int rNum) {

		if (rNum <= 0)
			return "";

		int index = workbook.getSheetIndex(sheetName);
		int colNum = -1;
		if (index == -1) {
			return "";
		}
		sheet = workbook.getSheetAt(index);
		row = sheet.getRow(0);

		for (int i = 0; i < row.getLastCellNum(); i++) {
			if (row.getCell(i) != null && row.getCell(i).getStringCellValue() != null
					&& row.getCell(i).getStringCellValue().trim().equalsIgnoreCase(colName.trim())) {
				colNum = i;
			}
		}

		if (colNum == -1)
			return "";

		sheet = workbook.getSheetAt(index);
		row = sheet.getRow(rNum - 1);
		if (row == null)
			return "";

		cell = row.getCell(colNum);
		if (cell == null)
			return "";

		if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
			return cell.getStringCellValue();
		} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC || cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
			String cellText = String.valueOf(cell.getNumericCellValue());
			if (HSSFDateUtil.isCellDateFormatted(cell)) {
				double date = cell.getNumericCellValue();
				Calendar cal = Calendar.getInstance();
				cal.setTime(HSSFDateUtil.getJavaDate(date));
				cellText = (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
				cellText = cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.MONTH) + 1 + "/" + cellText;
			}
			return cellText;
		} else if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
			return "";
		} else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(cell.getBooleanCellValue());
		} else {
			return "";
		}

	}

	public String getCellData(String sheetName, int colNum, int rNum) {
		if (rNum <= 0)
			return "";

		int index = workbook.getSheetIndex(sheetName);

		if (index == -1) {
			return "";
		}
		sheet = workbook.getSheetAt(index);
		row = sheet.getRow(rNum - 1);

		if (row == null)
			return "";

		cell = row.getCell(colNum);
		if (cell == null)
			return "";

		if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
			return cell.getStringCellValue();
		} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC || cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
			String cellText = String.valueOf(cell.getNumericCellValue());
			if (HSSFDateUtil.isCellDateFormatted(cell)) {
				double date = cell.getNumericCellValue();
				Calendar cal = Calendar.getInstance();
				cal.setTime(HSSFDateUtil.getJavaDate(date));
				cellText = (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
				cellText = cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.MONTH) + 1 + "/" + cellText;
			}
			return cellText;
		} else if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
			return "";
		} else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(cell.getBooleanCellValue());
		} else {
			return "";
		}
	}

}

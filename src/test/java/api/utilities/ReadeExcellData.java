package api.utilities;

import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class ReadeExcellData {

	public static FileInputStream fileInput;
	public static XSSFWorkbook workBook;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	public static XSSFCell cell;

	public static File filePath;
	public static String rootPath = System.getProperty("user.dir");

//	public ReadeExcellData(String filePath) {
//		this.filePath = filePath;
//	}

	public static int getRowCount(String sheetName) throws Exception {

		filePath = new File(rootPath + ".\\TestData\\TestDataSheet.xlsx");
		fileInput = new FileInputStream(filePath);
		workBook = new XSSFWorkbook(fileInput);
		sheet = workBook.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum();
		workBook.close();
		System.out.println("No of Row Count : " + rowCount);
		return rowCount;
	}

	public static int getColumnCount(String sheetName, int rowNumber) throws Exception {
		fileInput = new FileInputStream(filePath);
		workBook = new XSSFWorkbook(fileInput);
		sheet = workBook.getSheet(sheetName);
		row = sheet.getRow(rowNumber);
		int cellCount = row.getLastCellNum();
		workBook.close();
		System.out.println("Cell Count :  " + cellCount);
		return cellCount;

	}

	public static String getCellData(String sheetName, int rowNum, int colname) throws Exception {
		fileInput = new FileInputStream(filePath);
		workBook = new XSSFWorkbook(fileInput);
		sheet = workBook.getSheet(sheetName);
		row = sheet.getRow(rowNum);
		cell = row.getCell(colname);

		DataFormatter formater = new DataFormatter();

		String data;

		/*
		 * DataFormatter contains methods for formatting the value stored in a Cell.
		 * This can be useful for reports and GUI presentations when you need to display
		 * data exactly as it appears in Excel.
		 */
		try {

			data = formater.formatCellValue(cell);

		} catch (Exception e) {
			// TODO: handle exception
			data = "";
		}
		workBook.close();
		fileInput.close();
		return data;
	}

	@Test
	public static void testData() 
	{
//		getCellData("ReadAndWrite", "TC_001", "API_URL");

	}

}

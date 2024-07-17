package ExcelUtilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class ReadExcell {

//	public static HashMap<String, String> mapData;
	public static File filePath;
	public static FileInputStream fileInput;
	public static XSSFWorkbook workBook;
	public static XSSFSheet currentSheet;
	public static Iterator<Row> ItRows;
	public static Iterator<Cell> itCell;
	public static Row row;
	public static Cell cell;
//	public String key;
	public static String value;

	public static ArrayList<String> getExcellRowData(String SheetName, String testCaseID) throws IOException {

		ArrayList<String> arrayList = null;

		File filePath = new File(".\\TestData\\TestDataSheet.xlsx");
		fileInput = new FileInputStream(filePath);
		workBook = new XSSFWorkbook(fileInput);
		int noOfSheets = workBook.getNumberOfSheets();

		try {
			arrayList = new ArrayList<String>();

			for (int i = 0; i < noOfSheets; i++) {
				if (workBook.getSheetName(i).equalsIgnoreCase(SheetName)) {
					currentSheet = workBook.getSheetAt(i);
					int noOfRows = currentSheet.getPhysicalNumberOfRows();
					System.err.println(" No Of Rows in a Sheet :  " + noOfRows);
					ItRows = currentSheet.iterator();
					row = ItRows.next();
					int noOfColumns = row.getPhysicalNumberOfCells();
					System.out.println(" No Of Column in the Row : " + noOfColumns);
					itCell = row.cellIterator();

					/*
					 * Gets the Desired sheetname
					 */

					int k = 0;
					int column = 0;
					while (itCell.hasNext()) {
						cell = itCell.next();
						if (cell.getStringCellValue().equalsIgnoreCase("TestCaseID")) { // Header column name
							column = k;
							break;
						}
						k++;
					}

//				System.out.println(column);

					/*
					 * get the TestcaseID row
					 * 
					 */

					while (ItRows.hasNext()) {
						row = ItRows.next();
						if (row.getCell(column).getStringCellValue().equalsIgnoreCase(testCaseID)) {

							/*
							 * After we got our test case pull the all test data from cells
							 */

							itCell = row.cellIterator();
							while (itCell.hasNext()) {
								cell = itCell.next();
								if (cell.getCellType() == CellType.STRING) {
									value = cell.getStringCellValue();
									arrayList.add(value);
								} else {
									value = NumberToTextConverter.toText(cell.getNumericCellValue());
									arrayList.add(value);

								}
							}
						}

					}

				}
				workBook.close();
				fileInput.close();

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arrayList;

	}

	public static String getCellData(String sheetName, String TestCaseID, String columName) {
		ArrayList<String> arrayData = null;
		String data = null;

		try {
			arrayData = getExcellRowData(sheetName, TestCaseID);
			ItRows = currentSheet.iterator();
			row = ItRows.next();
			itCell = row.iterator();

			int k = 0;
			int column = 0;

			while (itCell.hasNext()) {
				cell = itCell.next();

				if (cell.getStringCellValue().equalsIgnoreCase(columName)) {
					column = k;
					break;
				}
				k++;
			}
			data = arrayData.get(column); // desired column name
//			System.err.println(" Cell header data " + data);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return data;
	}

//	@Test
//	public void testExcellData() throws IOException {
//		String prin = getCellData("ReadAndWrite", "TC_001", "Request");
//
//		System.out.println(" Expectted column  values : " + prin);
//
//	}

}

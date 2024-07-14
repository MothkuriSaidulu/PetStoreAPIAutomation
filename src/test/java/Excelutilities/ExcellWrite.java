package Excelutilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class ExcellWrite {

	public static FileInputStream fis;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;

	public static void writeDataIntoCell(String sheetname, String testflow, String colName, String value) {

		/*
		 * Steps: 
		 * 1. Workbook location path. 
		 * 2. Identify the working sheet. 
		 * 3. Get the Count of No of physical rows in that sheet. 
		 * 4. Get the No Of Columns that row. 
		 * 5. Get the Desired TestcaseID Row in sheet based on column of Header.
		 * 6. Get the Desired ColumnName in sheet based on Header of sheet column. 
		 * 7. Once we got the TestcasesID Row and Expected Column header the send the value
		 */
		int getNoOfRows = 0;
		int noOfCells = 0;
		
		try {
			/*
			 * 1. Workbook location path. 
			 * 2. Identify the working sheet.
			 * 3. Get the Count of No of physical rows in that sheet. 
		    * 4. Get the No Of Columns that row. 
			 */
			fis = new FileInputStream("./TestData/TestDataSheet.xlsx");
			workbook = new XSSFWorkbook(fis);
			int noOFsheet = workbook.getNumberOfSheets();

			// based on row header

			for (int i = 0; i < noOFsheet; i++) {
				if (workbook.getSheetAt(i).getSheetName().equalsIgnoreCase(sheetname)) {
					sheet = workbook.getSheetAt(i);

					getNoOfRows = sheet.getPhysicalNumberOfRows();
					System.out.println("No of Rows in the sheet : " + getNoOfRows);
					
					Iterator<Row> itRow = sheet.iterator();
					Row row = itRow.next();
					noOfCells = row.getPhysicalNumberOfCells();
					System.out.println(" No of cells in a row " + noOfCells);

					break;
				}

			}

			
//			Row row = sheet.getRow(0); // 1st row for header
//
//			int rowNum = sheet.getPhysicalNumberOfRows();
//			int cellNum = row.getPhysicalNumberOfCells();
//			System.out.println("No of Rows in the sheet : " + rowNum);
//			System.out.println(" No of cells in a row " + cellNum);

			
			/*
			 * 5. Get the Desired TestcaseID Row in sheet based on column of Header.
			 */
			int expectedRowNumber = 0;
			for (int i = 0; i < getNoOfRows; i++) {

				if (sheet.getRow(i).getCell(0).toString().equalsIgnoreCase(testflow)) {

					expectedRowNumber = i;

					break;

				}

			}
			
			/*
			 * 6. Get the Desired ColumnName in sheet based on Header of sheet column. 
			 */
			
			Row firstRow = sheet.getRow(0);
			int expectedColumnNumber = 0;
			for (int i = 0; i < noOfCells; i++) {

				if ((firstRow.getCell(i).toString()).equals(colName)) {

					expectedColumnNumber = i;

					break;

				}

			}
			
			/*
			 *  7. Once we got the TestcasesID Row and Expected Column header the send the value
			 */
			
			System.out.println(" Value   " + value);
			sheet.getRow(expectedRowNumber).getCell(expectedColumnNumber).setCellValue(value);
			fis.close();

			FileOutputStream fileOutputStream = new FileOutputStream("./TestData/TestDataSheet.xlsx");

			workbook.write(fileOutputStream);

			workbook.close();

			fileOutputStream.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void named() {
		writeDataIntoCell("WriteSheet", "TC01", "LastName", "Saida");

	}

}

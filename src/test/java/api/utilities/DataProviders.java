package api.utilities;

import java.io.File;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviders {

//	public String rootPath = System.getProperty("user.dir");

	/*
	 * This Method will get all data from Excell sheet
	 */
	@DataProvider(name = "Data")
	public static String[][] getAllData() throws Exception {

//		String filePath = ("./TestData/TestDataSheet.xlsx");
//		ReadeExcellData excell = new ReadeExcellData();

		int rowCount = ReadeExcellData.getRowCount("TestData");
		int colCount = ReadeExcellData.getColumnCount("TestData", 1);

		/*
		 * ceate the two dimentional array to story excell sheet
		 */
		String apiData[][] = new String[rowCount][colCount];

		for (int i = 1; i <= rowCount; i++) {

			for (int j = 0; j < colCount; j++) {
				apiData[i - 1][j] = ReadeExcellData.getCellData("TestData", i, j);

			}
		}

		return apiData;

	}

	/*
	 * This method will get all users from excell sheet
	 */

	@DataProvider(name = "userNames")
	public String[] getUserNames() throws Exception {
//		String filePath = ("./TestData/TestDataSheet.xlsx");
//		ReadeExcellData excell = new ReadeExcellData();
		
		int rowNum = ReadeExcellData.getRowCount("TestData");
		String apiData[] = new String[rowNum];

		for (int i = 0; i <= rowNum; i++) {
			apiData[i - 1] = ReadeExcellData.getCellData("TestData", i, 1);

		}
		return apiData;
	}

}

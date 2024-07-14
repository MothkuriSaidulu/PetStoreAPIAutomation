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
	public String[][] getAllData() throws Exception {

		String filePath = ("E:\\RestAssuredProject\\PetStoreAPIAutomation\\TestData\\TestDataSheet.xlsx");
		ExcellData excell = new ExcellData(filePath);

		int rowCount = excell.getRowCount("TestData");
		int colCount = excell.getColumnCount("TestData", 1);

		/*
		 * ceate the two dimentional array to story excell sheet
		 */
		String apiData[][] = new String[rowCount][colCount];

		for (int i = 1; i <= rowCount; i++) {

			for (int j = 0; j < colCount; j++) {
				apiData[i - 1][j] = excell.getCellData("TestData", i, j);

			}
		}

		return apiData;

	}

	/*
	 * This method will get all users from excell sheet
	 */

	@DataProvider(name = "userNames")
	public String[] getUserNames() throws Exception {
		String filePath = ("E:\\RestAssuredProject\\PetStoreAPIAutomation\\TestData\\TestDataSheet.xlsx");
		ExcellData excell = new ExcellData(filePath);
		int rowNum = excell.getRowCount("TestData");
		String apiData[] = new String[rowNum];

		for (int i = 0; i <= rowNum; i++) {
			apiData[i - 1] = excell.getCellData("TestData", i, 1);

		}
		return apiData;
	}

}

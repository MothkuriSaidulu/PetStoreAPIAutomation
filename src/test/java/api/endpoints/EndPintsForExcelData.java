package api.endpoints;

import ExcelUtilities.ReadExcell;
import ExcelUtilities.WriteExcell;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class EndPintsForExcelData {

	@SuppressWarnings("unused")
	public static void getDataFromExcell() {
		String get_url = ReadExcell.getCellData("ReadAndWrite", "TC_002", "API_URL");
		String put_url = ReadExcell.getCellData("ReadAndWrite", "TC_003", "API_URL");

	}

	public static Response PostUserDetails() {
		String post_url = ReadExcell.getCellData("ReadAndWrite", "TC_002", "API_URL");
		String requestbody = ReadExcell.getCellData("ReadAndWrite", "TC_002", "Requestbody");

		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(requestbody).when()
				.post(post_url);

		String responseBody = response.asString();
		int statusCode = response.getStatusCode();

		WriteExcell.writeDataIntoCell("ReadAndWrite", "TC_002", "Responsebody", responseBody);
		WriteExcell.writeDataIntoCell("ReadAndWrite", "TC_002", "Status_Code", String.valueOf(statusCode));

		return response;
	}

}

package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.EndPintsForExcelData;
import io.restassured.response.Response;

public class Test_04_TestWithExcellData 
{
	
	@Test
	public void createUserDetails() 
	{
		Response response = EndPintsForExcelData.PostUserDetails();
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		
	}
	
	

}

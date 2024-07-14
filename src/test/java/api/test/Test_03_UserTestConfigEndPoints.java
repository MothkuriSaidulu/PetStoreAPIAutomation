package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.EndPointsFromConfig;
import api.endpoints.UserEndPoints;
import api.payload.UserPOJO;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class Test_03_UserTestConfigEndPoints {



	public UserPOJO userPayload;

	// data provider and from which class it is coming from that we need to mention

	@Test(priority = 0, dataProvider = "Data", dataProviderClass = DataProviders.class)
	public void testPostUsers(String userID, String userName, String fname, String lname, String userEmail, String pwd,
			String phone) {
		userPayload = new UserPOJO();

		userPayload = new UserPOJO();
		userPayload.setId(Integer.parseInt(userID));
		userPayload.setUsername(userName);
		userPayload.setFirstName(fname);
		userPayload.setLastName(lname);
		userPayload.setFirstName(userEmail);
		userPayload.setEmail(userEmail);
		userPayload.setPassword(pwd);
		userPayload.setPhone(phone);

		Response response = EndPointsFromConfig.createUser(userPayload);
		Assert.assertEquals(response.getStatusCode(), 200);

	}

//	@Test(priority = 1)
	public void getUserDetails()
	{
		Response getUserResp = EndPointsFromConfig.getUserDetails(this.userPayload.getUsername());

		getUserResp.then().log().all();
		
		Assert.assertEquals(getUserResp.getStatusCode(), 200);
	}
	
//	@Test(priority = 1, dataProvider = "userNames", dataProviderClass = DataProviders.class)
	public void deleteUser( String usn ) 
	{
		Response deletResponse = EndPointsFromConfig.deleteUserDetails(usn);
		deletResponse.then().log().all();
		
		Assert.assertEquals(deletResponse.getStatusCode(), 200);
	
	}
	
	

	
}

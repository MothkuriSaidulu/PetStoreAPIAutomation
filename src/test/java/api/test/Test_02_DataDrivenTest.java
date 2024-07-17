package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.UserPOJO;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class Test_02_DataDrivenTest {

	public UserPOJO userPayload;

	// data provider and from which class it is coming from that we need to mention

	@Test(priority = 0, dataProvider = "Data", dataProviderClass = DataProviders.class)
	public void testPostUsers(String userID, String userName, String fname, String lname, String userEmail, String pwd,
			String phone) {
		System.out.print(" ");
		System.err.println("********** Post user request details started **********");

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

		Response response = UserEndPoints.createUser(userPayload);
		Assert.assertEquals(response.getStatusCode(), 200);

	}

	@Test(priority = 1)
	public void getUserDetails() {
		System.out.print(" ");

		System.err.println("********** Get user request details started **********");
		Response getUserResp = UserEndPoints.getUserDetails(this.userPayload.getUsername());

		getUserResp.then().log().all();

		Assert.assertEquals(getUserResp.getStatusCode(), 200);
		System.err.println("********** Get user request details Ended **********");

	}

	
//	@Test(priority = 1, dataProvider = "userNames", dataProviderClass = DataProviders.class)
	public void deleteUser(String usn) {
		System.out.print(" ");

		Response deletResponse = UserEndPoints.deleteUserDetails(usn);
		deletResponse.then().log().all();

		Assert.assertEquals(deletResponse.getStatusCode(), 200);

	}

}

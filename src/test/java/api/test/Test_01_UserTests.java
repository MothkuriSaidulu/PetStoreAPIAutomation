package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.UserPOJO;
import io.restassured.response.Response;

public class Test_01_UserTests {

	public Faker faker;
	public UserPOJO userPayload;
	public Logger log;

//	public static final Logger logger = LogManager.getLogger(Test_01_UserTests.class);

	/*
	 * Below method will generate the Test data by using Faker class in java
	 */

	@BeforeClass
	public void setupData() {
//		logger = LogManager.getLogger(UserTests.class);
		/*
		 * By using Faker we are generating random test data for testing
		 */
//		logger = LogManager.getLogger(this.getClass());

		faker = new Faker();
		userPayload = new UserPOJO();
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());

	}

	/*
	 * Send POST Request
	 */
	@Test(priority = 1)
	public void testPostUser() {

//		logger.info(" ******* Create Users ******* ");

		Response response = UserEndPoints.createUser(userPayload);
		response.then().log().all();

		System.out.println("Response Code : " + response.getStatusCode());
//		Validate the data
		Assert.assertEquals(response.getStatusCode(), 200);
//		logger.info(" *******  Users Created ******* ");

	}

	/*
	 * Send Get request
	 */

	@Test(priority = 2)
	public void getTestUser() {
//		logger.info(" ******* Getting Users details ******* ");

		Response response = UserEndPoints.getUserDetails(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}

	/*
	 * Lets update some data in user details
	 */
//	@Test(priority = 3)
	public void updateUserDetails() {
//		logger.info(" ******* Updating Users ******* ");

		String usn = this.userPayload.getUsername();
		String FFName = this.userPayload.getFirstName();
		String LName = this.userPayload.getFirstName();

		System.out.println("User name : " + usn);
		System.out.println("1st First name : " + FFName);
		System.out.println("1st Last name : " + FFName);

		faker = new Faker();
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());

		String secondFirstName = userPayload.getFirstName();
		String secondLastName = userPayload.getLastName();

		System.out.println(" second First Name : " + secondFirstName);
		System.out.println("second Last Name" + secondLastName);
//		String usn = this.userPayload.getUsername();
		System.out.println("User name : " + usn);

		Response reafterUpdate = UserEndPoints.updateUserDetails(this.userPayload.getUsername(), userPayload);
		System.out.println(this.userPayload.getUsername());

		reafterUpdate.then().log().body();

		/*
		 * validate the response
		 */
		Assert.assertEquals(reafterUpdate.getStatusCode(), 200);

		/*
		 * Ltes check updated user details
		 */

		Response getResponse = UserEndPoints.getUserDetails(this.userPayload.getUsername());
		getResponse.then().log().all();
		Assert.assertEquals(getResponse.getStatusCode(), 200);

//		logger.info(" ******* User Details Updated ******* ");

	}

	public void deleteUser() {
//		logger.info(" ******* Deleting Users Details******* ");

		Response response = UserEndPoints.deleteUserDetails(this.userPayload.getUsername());
		response.then().log().all();

	}

}

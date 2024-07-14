package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import api.payload.UserPOJO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints {

	/*
	 * this class created for perform CRUD operations Create , Retrieve , Update,
	 * Delete request to the API.
	 */

	
	/*
	 * Ceate uers by calling POST Request
	 */
	public static Response createUser(UserPOJO payload) {

		Response responce = given().contentType(ContentType.JSON) // accept content type as Json
				.accept(ContentType.JSON).body(payload) // to send data

				.when().post(Routes.post_url); // ths will responce

		return responce;

	}

	/*
	 * Get User details by calling Get request
	 */
	public static Response getUserDetails(String user) {
		Response responce = given().pathParam("username", user) // username --> this path parameter which is same as route
															// urls

				.when().get(Routes.get_url); // this will gives responce
		System.out.println(" base url "+Routes.get_url);
		return responce;

	}
	
	
	/*
	 * Updating the user details by calling PUT request
	 * Payload : test data whic we are creating by using pojo class
	 */
	
	public static Response updateUserDetails(String user, UserPOJO payload) 
	{
		Response responce = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("username", user)
			.body(payload)
		.when()
			.put(Routes.put_url);
			
		return  responce;	
	}
	

	/*
	 * Delete the user by calling Delete request
	 */
	public static Response deleteUserDetails(String user) 
	{
		
		Response responce = given()
			.pathParam("username", user)
		.when()
			.delete(Routes.delete_url);
		
		return responce;
	
	
	}
	
}

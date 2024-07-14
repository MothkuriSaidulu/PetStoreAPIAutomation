package api.endpoints;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;

import api.payload.UserPOJO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class EndPointsFromConfig {
	public static Properties prop;
	/*
	 * this class created for perform CRUD operations Create , Retrieve , Update,
	 * Delete request to the API.
	 */

	/*
	 * Ceate uers by calling POST Request
	 */

	public static ResourceBundle getUrl() {
		/*
		 * ResourceBundle java class to get location of src/test/resource folder and give the file name
		 */
		ResourceBundle routes = ResourceBundle.getBundle("config");
		return routes;

	}

	public static Properties initiate_properties() {

		try {
			File filePath = new File(".\\src\\test\\resources\\config.properties");
			FileInputStream iputStream = new FileInputStream(filePath);
			prop = new Properties();
//			InputStream ip = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties");
			prop.load(iputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
	}

	public static Response createUser(UserPOJO payload) {

//		String config_post_url = initiate_properties().getProperty("post_url");

		
		String config_post_url = getUrl().getString("post_url");

		Response responce = given().contentType(ContentType.JSON) // accept content type as Json
				.accept(ContentType.JSON).body(payload) // to send data

				.when().post(config_post_url); // ths will responce

		return responce;

	}

	/*
	 * Get User details by calling Get request
	 */
	public static Response getUserDetails(String user) {
		String get_url = getUrl().getString("get_url");

		Response responce = given().pathParam("username", user) // username --> this path parameter which is same as
																// route
				// urls

				.when().get(get_url); // this will gives responce

		return responce;

	}

	/*
	 * Updating the user details by calling PUT request Payload : test data whic we
	 * are creating by using pojo class
	 */

	public static Response updateUserDetails(String user, UserPOJO payload) {

		String put_url = getUrl().getString("put_url");

		Response responce = given().contentType(ContentType.JSON).accept(ContentType.JSON).pathParam("username", user)
				.body(payload).when().put(put_url);

		return responce;
	}

	/*
	 * Delete the user by calling Delete request
	 */
	public static Response deleteUserDetails(String user) {

		String delete_url = getUrl().getString("delete_url");

		Response responce = given().pathParam("username", user).when().delete(delete_url);

		return responce;

	}

}

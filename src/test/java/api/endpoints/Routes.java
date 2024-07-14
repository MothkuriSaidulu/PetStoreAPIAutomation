package api.endpoints;

public class Routes {
	/*
	 * Swagger URL:  https://petstore.swagger.io
	 * Create User POST : https://petstore.swagger.io/v2/user
	 * Get user GET: https://petstore.swagger.io/v2/user/{username}
	 * Update User PUT: https://petstore.swagger.io/v2/{username}
	 * Delete User Delete: https://petstore.swagger.io/v2/{username}
	 * 
	 */
	
//	 below are the ulrs using for USER Module
	public static String base_url = "https://petstore.swagger.io/v2/";  //https://petstore.swagger.io/v2/user/
	
	public static String post_url = base_url + "user/";  // path parameter --> user
	
	public static String get_url = base_url + "user/{username}/";  //
	
	public static String put_url = base_url + "{username}/";
	
	public static String delete_url = base_url + "{username}/";
	
	
	
//	Store Module
	
	/*
	 * Put her Store Module url's 
	 */
	

	
	
	
}

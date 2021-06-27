package com.orwrites.testcases;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC001_Get_Request {
	
	

	@Test
	public void getPersonDetails() {
		
		
		// Specify the base URL to the RESTful web service
		RestAssured.baseURI = "https://reqres.in/api/users";

		// RequestSpecification is a interface
		// Get the RequestSpecification of the request that you want to sent to
		// the server. The server is specified by the BaseURI that we have
		// specified in the above step
		RequestSpecification httprequest = RestAssured.given();

		// Response is an interface
		// Make a request to the server by specifying the method Type and the
		// method URL. This will return the Response from the server. Store the
		// response in a variable
		Response response = httprequest.request(Method.GET, "/2");

		// Now let us print the body of the message to see what response we have
		// recieved from the server
		String responseBody = response.getBody().asString();
		System.out.println("The response body is :" + responseBody);

		// Status code validation
		int statusCode = response.getStatusCode();
		System.out.println("The status code is :" + statusCode);
		Assert.assertEquals(statusCode, 200);

		// status line verification
		String statusLine = response.statusLine();
		System.out.println("The status line is :" + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");

		// JSON column/fields validation
		Assert.assertEquals(responseBody.contains("Janet"), true);

		JsonPath jsonpath = response.jsonPath();

		System.out.println(jsonpath.get("id"));
		System.out.println(jsonpath.get("email"));
		System.out.println(jsonpath.get("first_name"));
		System.out.println(jsonpath.get("last_name"));
		System.out.println(jsonpath.get("avatar"));

	}

	/*
	 * @Test public void getwetherDetails() { // Specify base URI
	 * RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
	 * 
	 * // Request object RequestSpecification httpRequest = RestAssured.given();
	 * 
	 * // Response object Response response = httpRequest.request(Method.GET,
	 * "/Hyderabad");
	 * 
	 * // print response in console window
	 * 
	 * String responseBody = response.getBody().asString();
	 * System.out.println("Response Body is:" + responseBody);
	 * 
	 * 
	 * // status code validation int statusCode = response.getStatusCode();
	 * System.out.println("Status code is: " + statusCode);
	 * Assert.assertEquals(statusCode, 200);
	 * 
	 * 
	 * // status line verification String statusLine = response.getStatusLine();
	 * System.out.println("Status line is:" + statusLine);
	 * Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	 * 
	 * }
	 */

}

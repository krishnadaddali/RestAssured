package com.orwrites.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TC006_Get_Request_Autherization {

	@Test
	public void auth() {

		// Specify base URI
		RestAssured.baseURI = "https://reqres.in/api/users";
		
		// Basic authentication
		PreemptiveBasicAuthScheme authscheme = new PreemptiveBasicAuthScheme();
		authscheme.setUserName("userName");
		authscheme.setPassword("pwd");
		
		RestAssured.authentication = authscheme;
		
		// Request object
		RequestSpecification httprequest = RestAssured.given();
		
		//Response object
		Response response = httprequest.request(Method.GET,"/1");
		
		// print response in console window
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is:" + responseBody);
		
		// status line verification
		String statusLine = response.statusLine();
		System.out.println("The status line is :" + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
		

		}

	

}

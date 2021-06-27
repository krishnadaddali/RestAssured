package com.orwrites.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC003_Get_Request_GoogleMapAllGeaders {

	@Test
	public void googleMapTest() {

		// Specify base URI
		RestAssured.baseURI = "https://maps.googleapis.com";

		// Request object
		RequestSpecification httpRequest = RestAssured.given();

		// Response object
		Response response = httpRequest.request(Method.GET,
				"/search?q=near+me+salon+shop&sxsrf=ALeKk01CLe9w6zC3ct_hMHImbpUAhIFg2w%3A1624725132227&ei=jFbXYIqoDZOt5NoPqbiqiAM&oq=near+me+saloo&gs_lcp=Cgdnd3Mtd2l6EAMYADIHCCMQsQIQJzIHCAAQyQMQCjIECAAQCjIECAAQCjIECAAQCjIECAAQCjIICAAQFhAKEB4yCAgAEBYQChAeMggIABAWEAoQHjIGCAAQFhAeOgcIABBHELADOgoIABCwAxDJAxBDOggIABCSAxCwAzoECCMQJzoCCAA6BQgAEMkDSgQIQRgAUJ-JB1iLjwdgzJsHaANwAngAgAHLAYgB3QWSAQUzLjIuMZgBAKABAaoBB2d3cy13aXrIAQrAAQE&sclient=gws-wiz");

		// print response in console window
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is:" + responseBody);

		Headers allHeaders = response.headers();
		
		System.out.println(" ***********************");

		for (Header header : allHeaders) {

			System.out.println(header.getName() + " : " + header.getValue());

		}

		System.out.println(" ***********************");

		// Capture details of headers from response
		String contentType = response.header("Content-Type");
		System.out.println(" The Content-Type is : " + contentType);
		
		// Check the headers(contentType) in postman
		Assert.assertEquals(contentType, "text/html; charset=UTF-8"); 
																	
		String contentTypeOptions = response.header("X-Content-Type-Options");
		System.out.println("The X-Content-Type-Options is : " + contentTypeOptions);
		Assert.assertEquals(contentTypeOptions, "nosniff");

	}
}

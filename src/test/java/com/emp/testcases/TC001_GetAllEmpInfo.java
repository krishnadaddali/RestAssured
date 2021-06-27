/******************************************************
 
Test Name:Get all employees data
URI: http://dummy.restapiexample.com/api/v1/employees
Request Type: GET
Request Payload(Body): NA

********* Validations **********
Status Code : 200
Status Line : HTTP/1.1 200 OK
Content Type : text/html; charset=UTF-8
Server Type :  nginx/1.14.1
Content Encoding : gzip
Content Length <800

 *********************************************************/


package com.emp.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.emp.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC001_GetAllEmpInfo extends TestBase {

	@BeforeClass
	public void getEmployeesInfo() throws InterruptedException {

		logger.info(" ******* Started TC001_Get_All_Emp's_Info ******");

		RestAssured.baseURI = "https://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET, "employees");

		Thread.sleep(3);

	}

	@Test
	public void checkResponseBody() {

		logger.info(" ******* Checking Response Body ******");

		String responseBody = response.getBody().asString();
		logger.info("responseBody --> " + responseBody);
		Assert.assertTrue(responseBody != null);

	}

	@Test
	public void checkStatusCode() {

		logger.info(" ******* Checking Status Code ******");

		int statusCode = response.getStatusCode();
		logger.info("responseBody --> " + statusCode);
		Assert.assertEquals(statusCode, 200);

	}

	@Test
	public void checkResponseTime() {

		logger.info(" ******* Checking Response Time ******");

		long responseTime = response.getTime();
		logger.info("Response Time is --> " + responseTime);

		if (responseTime > 2000)
			logger.info(" Response time is greater than 2000");

		Assert.assertTrue(responseTime < 2000);

	}
	
	@Test
	public void statusLine(){
		
		logger.info(" ******* Checking Status Line ******");
		
		String statusLine = response.statusLine();
		logger.info("Status Line is --> " + statusLine );
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
		
	}
	
	@Test
	public void checkContentType(){
		
		logger.info(" ******* Checking Content Type ******");
		
		String contentType = response.contentType();
		logger.info(" ContentType is --> " + contentType );
		Assert.assertEquals(contentType, "application/json");
		
	}
	
	@Test
	public void checkServerType(){
		
		logger.info(" ******* Checking Server Type ******");
		
		String serverType = response.header("Server");
		logger.info(" ContentType is --> " + serverType );
		Assert.assertEquals(serverType, "cloudflare");
		
	}
	
	@Test
	public void checkContentEncoding(){
		
		logger.info(" ******* Checking Content Encoding ******");
		
		String contentEncoding = response.header("content-Encoding");
		logger.info(" Content Encoding is --> " + contentEncoding );
		Assert.assertEquals(contentEncoding, "gzip");
		
	}
	
	@Test
	public void checkContentLength(){
		
		logger.info(" ******* Checking Content Length ******");
		
		String contentLength = response.header("content-Length");
		logger.info(" Content Length is --> " + contentLength );
		
		if(Integer.parseInt(contentLength)<100)
			logger.info(" Content Length is less than 100 ");
		
		Assert.assertTrue(Integer.parseInt(contentLength)>100);
	}
	
	@Test
	public void checkCookies(){
		
		logger.info(" ******* Checking Cookies ******");
		
		String checkCookies = response.getCookie("PHPSESSID");
		logger.info(" Check Cookies is --> " + checkCookies );
	//	Assert.assertEquals(checkCookies, "gzip");
		
	}
	
	@AfterClass
	public void tearDown(){
		
		logger.info(" ******* Finshed TC001_GetAllEmpInfo ******");
		
	}

}

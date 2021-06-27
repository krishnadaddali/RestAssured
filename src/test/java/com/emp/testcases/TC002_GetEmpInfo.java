/******************************************************
 
Test Name:Get a single employee data
URI: http://dummy.restapiexample.com/api/v1/employee/{id}
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

public class TC002_GetEmpInfo extends TestBase {

	@BeforeClass
	public void getEmpInfo() throws InterruptedException {

		logger.info(" ******* Started TC002_Get_Emp_Info ******");
								
		RestAssured.baseURI = "https://reqres.in/api/users";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET, "/1");

		Thread.sleep(3000);

	}

	@Test
	public void checkResponseBody() {

		String responseBody = response.getBody().asString();
		System.out.println("The reponse body is : " + responseBody);
		Assert.assertEquals(responseBody.contains(empID), true);

	}

	@Test
	public void checkStatusCode() {

		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);

	}

	@Test
	public void checkResponseTime() {

		long responseTime = response.getTime();
		Assert.assertTrue(responseTime < 2000);

	}

	@Test
	public void statusLine() {

		String statusLine = response.statusLine();
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");

	}

	@Test
	public void checkContentType() {

		String contentType = response.contentType();
		Assert.assertEquals(contentType, "application/json; charset=utf-8");

	}

	@Test
	public void checkServerType() {

		String serverType = response.header("Server");
		Assert.assertEquals(serverType, "cloudflare");

	}

	//@Test
	public void checkContentLength() {
		
		String contentLength = response.header("content-Length");
		System.out.println("***************************" + contentLength);
		Assert.assertTrue(Integer.parseInt(contentLength) < 1500);

	}

	@AfterClass
	public void tearDown() {

		logger.info(" ******* Finshed TC002_GetEmpInfo ******");

	}

}

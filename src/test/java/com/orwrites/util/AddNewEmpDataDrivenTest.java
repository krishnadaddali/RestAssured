package com.orwrites.util;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AddNewEmpDataDrivenTest {

	@Test(dataProvider = "empdataprovider")

	public void addEmp(String name, String sal, String age) {

		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";

		// Request object
		RequestSpecification httpRequest = RestAssured.given();

		// Here we create data which we can send along with the post request
		JSONObject requestParam = new JSONObject();

		requestParam.put("name", "name"); // Cast
		requestParam.put("salary", "sal");
		requestParam.put("age", "age");

		// Add a header stating the Request body is a JSON
		httpRequest.header("Content-Type", "application/json");

		// attach above data to the request
		httpRequest.body(requestParam.toJSONString());

		// Response object
		Response response = httpRequest.request(Method.POST, "/create");

		// capture response body to perform validations
		String responseBody = response.getBody().asString();

		Assert.assertEquals(responseBody.contains("name"), true);
		Assert.assertEquals(responseBody.contains("sal"), true);
		Assert.assertEquals(responseBody.contains("age"), true);

		int statusCode = response.getStatusCode();
		System.out.println(statusCode);
		Assert.assertEquals(200, statusCode);

	}

	@DataProvider(name = "empdataprovider")
	String[][] getEmpData() throws IOException {

		String path = System.getProperty("user.dir") + "/src/test/java/com/orwrites/util/LoginDataa.xlsx";

		int rownum = XLcellUtil.getRowCount(path, "Sheet1");
		int colcount = XLcellUtil.getCellCount(path, "Sheet1", 1);

		String logindata[][] = new String[rownum][colcount];

		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < colcount; j++) {
				logindata[i - 1][j] = XLcellUtil.getCellData(path, "Sheet1", i, j);// 1
																					// 0
			}

		}
		return logindata;
	}

}

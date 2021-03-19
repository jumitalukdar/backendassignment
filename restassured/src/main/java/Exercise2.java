package main.java;

import static io.restassured.RestAssured.given;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utility.Constant;
import utility.TestData;

public class Exercise2 {
	@Test
	public void coinMarketInfo() {
	Response res=given()
	.header("X-CMC_PRO_API_KEY", Constant.KEY)
		.baseUri(Constant.BASEURL)
	.when().queryParam("id", "1027").queryParam("aux", "urls,logo,description,tags,platform,date_added")
		.get(Constant.CURRENCYINFOURL)
	.then()
		.statusCode(200)
		.statusLine("HTTP/1.1 200 OK").extract().response();
	
	JsonPath path=res.jsonPath();
	String doc= path.getString("data.1027.logo");
	Assert.assertEquals(path.get("data.1027. logo"),TestData.logoPathUrl);
	Assert.assertEquals(path.get("data.1027. symbol"),TestData.symbol);
	Assert.assertEquals(path.get("data.1027. date_added"),TestData.dateAdded);
	Assert.assertEquals(path.get("data.1027. tags[0]"),"mineable");
	Assert.assertNull(path.get("data.1027.plateform"));
	System.out.println("docis "+ doc);
	System.out.println(res.asString());
}
}


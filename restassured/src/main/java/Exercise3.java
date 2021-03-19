package main.java;

import static io.restassured.RestAssured.given;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utility.Constant;

public class Exercise3 {

	@Test
	public void Retrieve10currenices() {
		Response res = given().header("X-CMC_PRO_API_KEY", Constant.KEY)
				.baseUri(Constant.BASEURL).when().queryParam("id", "1,2,3,4,5,6,7,8,9,10")
				.queryParam("aux", "urls,logo,description,tags,platform,date_added").get("/v1/cryptocurrency/info")
				.then().statusCode(200).statusLine("HTTP/1.1 200 OK").extract().response();
		JsonPath path = res.jsonPath();
		String resbody = res.getBody().asString();
		System.out.println(resbody);
		List<String> idwithmineable = path.getList("data.findAll {i-> i.tags=='mineable'}.id");
		for (Object id : idwithmineable) {
			System.out.println(id.toString());
		}
	}
}

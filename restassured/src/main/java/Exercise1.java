package main.java;

import static org.hamcrest.Matchers.*;

import java.util.List;

import static io.restassured.RestAssured.*;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utility.Constant;

public class Exercise1 {
	static List<String> idlist;

	@Test
	public void getIdFromMapCall() {
		Response res = given().header("X-CMC_PRO_API_KEY", "7f989e84-2864-4e2d-a5d7-618db09fb1f6")
				.baseUri(Constant.BASEURL)
				.when().get(Constant.MAPURL)
				.then().statusCode(200).statusLine("HTTP/1.1 200 OK").extract().response();
		JsonPath path = res.jsonPath();
		idlist = path.getList("data.findAll {i-> i.name=='Bitcoin'|| i.name=='Tether'||i.name=='Ethereum'}.id");
		System.out.println("id are");
		for (Object id : idlist) {
			System.out.println(id.toString());
		}

	}

	@Test
	public void priceconversion() {
		for (Object id : idlist) {
			Response res = given().header("X-CMC_PRO_API_KEY", "7f989e84-2864-4e2d-a5d7-618db09fb1f6")
					.baseUri(Constant.BASEURL)
					.when().queryParam("id", id).queryParam("amount", 87.5).queryParam("convert", "BOB")
					.get(Constant.PRICECONVERSIONURL)
					.then().statusCode(200).statusLine("HTTP/1.1 200 OK").extract().response();
			System.out.println(res.asString());
		}
	}
}

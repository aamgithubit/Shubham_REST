package a_GET_API_BDD;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class pathParameter {
	
	@Test
	public void pathParam() {

		baseURI = "https://gorest.co.in";
given().log().all()
				.header("Authorization", "Bearer 9857c8f69677407f12fcdbf8cc74373f63bbd43d750f12601d5751d315b41f89")
				.pathParam("userId", 8137705)
				.when().get("/public/v2/users/{userId}")
				.then().assertThat().statusCode(200);
		
		
	}

}

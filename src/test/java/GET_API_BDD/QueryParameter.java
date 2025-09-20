package GET_API_BDD;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueryParameter {
	
	@Test
	public void queryParam() {

		baseURI = "https://gorest.co.in";

		 given().log().all()
				.header("Authorization", "Bearer 9857c8f69677407f12fcdbf8cc74373f63bbd43d750f12601d5751d315b41f89")
				.queryParam("name", "Vidhur Devar")
				.queryParam("status", "inactive")
				.when().get("/public/v2/users")
				.then().assertThat().statusCode(200);
		
		
	}
	
	@Test
	public void queryParamWithHashmap() {
		
		Map<String,String> queryParam = new HashMap<String,String>();
		
		queryParam.put("name", "Kamala Mishra Esq.");
		queryParam.put("status", "inactive");

		


		baseURI = "https://gorest.co.in";

		Response response = given()
				.header("Authorization", "Bearer 9857c8f69677407f12fcdbf8cc74373f63bbd43d750f12601d5751d315b41f89")
				.queryParams(queryParam)
				.when().get("/public/v2/users");
		
		response.prettyPrint();
		
		
	}


}

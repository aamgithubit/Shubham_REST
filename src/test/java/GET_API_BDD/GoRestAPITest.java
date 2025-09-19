package GET_API_BDD;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;


public class GoRestAPITest {
	
	@Test
	public void getSingleUser() {
		
		baseURI = "https://gorest.co.in";

		Response response = given()
		      .header("Authorization", "Bearer 9857c8f69677407f12fcdbf8cc74373f63bbd43d750f12601d5751d315b41f89")
		.when().log().all()
		      .get("/public/v2/users/8137778");
		
		System.out.println("status code = " + response.statusCode());
		
		Assert.assertEquals(response.statusCode(), 200);
		
		System.out.println("status line = " + response.statusLine());
		
		Assert.assertEquals(response.statusLine(), "HTTP/1.1 200 OK");

		
		//to print whole response
		response.prettyPrint();
		
		//get particular header
		System.out.println("Content type = "+ response.getHeader("Content-Type"));
		
		
		//fetch json response body
		JsonPath js = response.jsonPath();
		
		//get user ID // getInt method gets any interger value from response
		int userID = js.getInt("id");
		
		System.out.println("User ID is = " + userID);	
		
		//get name 
		String name = js.getString("name");
		
		System.out.println("User name is = " + name);	
		
		String mail = js.get("email");
		
		System.out.println("Mail id is = " + mail);
		
	}

}

package a_GET_API_BDD;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class GoRestBDD {
	
	@Test
	public void getSingleUser() {
		
		baseURI = "https://gorest.co.in";
		
		given().log().all()
		      .header("Authorization", "Bearer 9857c8f69677407f12fcdbf8cc74373f63bbd43d750f12601d5751d315b41f89")
		.when().log().all()
		      .get("/public/v2/users")
		.then().log().all()   
		      .assertThat().statusCode(200)
		      .and().statusLine("HTTP/1.1 200 OK")
		      .and().contentType(ContentType.JSON);
		
		
	}
	
	@Test
	public void authTest() {
		
		baseURI = "https://gorest.co.in";
		
		given().log().all()
		      .header("Authorization", "Bearer 857c8f69677407f12fcdbf8cc74373f63bbd43d750f12601d5751d315b41f89")
		.when().log().all()
		      .get("/public/v2/users")
		.then().log().all()   
		      .assertThat().statusCode(401)
		      .and().statusLine("HTTP/1.1 401 Unauthorized");
		
		
	}

}

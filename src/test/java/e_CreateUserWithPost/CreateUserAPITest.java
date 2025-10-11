package e_CreateUserWithPost;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class CreateUserAPITest {
	
	public String getRandomEmailid() {
		return "apiautomation"+System.currentTimeMillis()+"@opencart.com";
	}
	
	
	
	@Test
	public void createAUserTest() throws IOException {
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		String emailId = getRandomEmailid();
		
		//convert the json file content to string:
		String rawJson = new String(Files.readAllBytes(Paths.get("./src/test/resources/json/titanic-parquet.json")));
		String updatedJson = rawJson.replace("bb@c.com", emailId);		
		
		System.out.println("----------------------POST CALL ----------------------");

		//1. post: create a user
		int userId = given().log().all()
			.contentType(ContentType.JSON)
			.header("Authorization", "Bearer 2ebc0848e7ee50e3a22a64a2344f0fb2b2495de58571b52f8e114f66684fdeb3")
			.body(updatedJson)
	    .when()
	    	.post("/public/v2/users")
	    .then().log().all()
	    	.assertThat()
	    		.statusCode(201)
	    			.extract()
	    				.path("id");
		
		System.out.println("user id: "+ userId);
		
		
		System.out.println("----------------------GET CALL ----------------------");
		
		//2. get a user by using the same userid 
		given().log().all()
			.header("Authorization", "Bearer 2ebc0848e7ee50e3a22a64a2344f0fb2b2495de58571b52f8e114f66684fdeb3")
		.when()
			.get("/public/v2/users/"+userId)
		.then().log().all()
			.assertThat()
				.statusCode(200)
				.and()
					.body("id", equalTo(userId))
					.body("name", equalTo("Narendra Embranthiri"))
					.body("email", equalTo(emailId));				
	}
	
}

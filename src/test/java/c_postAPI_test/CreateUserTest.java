package c_postAPI_test;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class CreateUserTest {
	
	public String randomEmail() {
		return "S"+ System.currentTimeMillis()+"@gmail.com";
	}
	
	@Test
	public void createUserWithJsonStringTest() {
		
		baseURI = "https://gorest.co.in";
		
		given()
		      .contentType(ContentType.JSON)
		      .header("Authorization", "Bearer 9857c8f69677407f12fcdbf8cc74373f63bbd43d750f12601d5751d315b41f89")
		  	.body("{\"name\":\"Narendra11 Embranthiri\",\"email\":\""+ randomEmail() +"\",\"gender\":\"female\",\"status\":\"active\"}")
	    .when()
	           .post("/public/v2/users")
	    .then().log().all()
	           .assertThat()
	           .statusCode(201);
		
	}
	
	@Test
	public void createUserWithJsonFileTest() {
		
		baseURI = "https://gorest.co.in";
		
		given()
		      .contentType(ContentType.JSON)
		      .header("Authorization", "Bearer 2ebc0848e7ee50e3a22a64a2344f0fb2b2495de58571b52f8e114f66684fdeb3")
		  	.body(new File("./src/test/resources/json/titanic-parquet.json"))

	    .when()
	           .post("/public/v2/users")
	    .then().log().all()
	           .assertThat()
	           .statusCode(201);
		
	}
	
	@Test
	public void createUserWithJsonFileWithEmailReplacementTest() throws IOException {
		
		baseURI = "https://gorest.co.in";
		
		String emailId = randomEmail();
		
		//convert the json file content to string:
		String rawJson = new String(Files.readAllBytes(Paths.get("./src/test/resources/json/titanic-parquet.json")));
		String updatedJson = rawJson.replace("bb@c.com", emailId);		
		
		int userId = given().log().all()
			.contentType(ContentType.JSON)
			.header("Authorization", "Bearer d0bf1714ac04c10dd2982e009d2dffe694a8e0b53af518cb7370e41e046a72f6")
			.body(updatedJson)
	    .when()
	    	.post("/public/v2/users")
	    .then().log().all()
	    	.assertThat()
	    		.statusCode(201)
	    			.extract()
	    				.path("id");
		System.out.println("user id: "+ userId);
	}

}

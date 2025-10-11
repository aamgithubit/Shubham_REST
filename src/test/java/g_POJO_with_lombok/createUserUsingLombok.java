package g_POJO_with_lombok;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class createUserUsingLombok {
	
	public String getRandomEmailid() {
		return "apiautomation"+System.currentTimeMillis()+"@opencart.com";
	}
	
	
	@Test
	public void createUserTest() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		String emailId = getRandomEmailid();
		
		//create object of UserLombok POJO class:
		POJOlombok user = new POJOlombok("Karim", emailId, "male", "active");
						
		int userId = given().log().all()
			.contentType(ContentType.JSON)
			.header("Authorization", "Bearer d0bf1714ac04c10dd2982e009d2dffe694a8e0b53af518cb7370e41e046a72f6")
			.body(user) //auto serialization: java object to json using jackson databind lib
		.when()
			.post("/public/v2/users")
	    .then().log().all()
	    	.assertThat()
	    		.statusCode(201)
	    			.extract()
	    				.path("id");
		
		System.out.println("user id : " + userId);
		
		System.out.println("----------------------GET CALL ----------------------");
		
		//2. get a user by using the same userid 
		given().log().all()
			.header("Authorization", "Bearer d0bf1714ac04c10dd2982e009d2dffe694a8e0b53af518cb7370e41e046a72f6")
		.when()
			.get("/public/v2/users/"+userId)
		.then().log().all()
			.assertThat()
				.statusCode(200)
				.and()
					.body("id", equalTo(userId))
					.body("name", equalTo(user.getName()))
					.body("status", equalTo(user.getStatus()))
					.body("email", equalTo(emailId));
		
	}

}

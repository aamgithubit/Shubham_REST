package j_DeserilizationWithGETCALL;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetUserAPITest {
	
	
	public String getRandomEmailid() {
		return "apiautomation"+System.currentTimeMillis()+"@opencart.com";
	}
	
	
	@Test
	public void getASingleUesrTest() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		String emailId = getRandomEmailid();
		
		//create object of UserLombok POJO class:
		UserLombok user = new UserLombok(null, "Karim", emailId, "male", "active");
										
		Integer userId = given().log().all()
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
		Response response = given().log().all()
			.header("Authorization", "Bearer d0bf1714ac04c10dd2982e009d2dffe694a8e0b53af518cb7370e41e046a72f6")
		.when()
			.get("/public/v2/users/"+userId);
		
		response.prettyPrint();
		
		//de-serialization : JSON response ---> POJO
		//using JAckson ---> ObjectMapper 
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			UserLombok userRes = mapper.readValue(response.getBody().asString(), UserLombok.class);
			//it will create a new UserLombok class object. 
			System.out.println(userRes);
			
			//UserLombok
			//(id=8174717, 
			//name=Karim, 
			//email=apiautomation1759895929171@opencart.com, 
			//gender=male, 
			//status=active)
			
			
			Assert.assertEquals(userId, userRes.getId());

			Assert.assertEquals(userRes.getName(), user.getName());
			Assert.assertEquals(userRes.getStatus(), user.getStatus());
			Assert.assertEquals(userRes.getGender(), user.getGender());
			Assert.assertEquals(userRes.getEmail(), user.getEmail());
			
			
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
	}
}
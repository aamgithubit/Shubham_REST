package GET_API_BDD;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class ContactsAPItest {
	
	@Test
	public void getAllContactsTest() {
		
		baseURI = "https://thinking-tester-contact-list.herokuapp.com";
		
		given().log().all()
			.header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2ODk2MzA1NjEyM2ViMTAwMTVhNzRmMmMiLCJpYXQiOjE3NTg3ODcwNDl9.nc5DNK1hzZbmH9Qu17O_o_xk_TryMooxUsk0G_Zu3Xg")
		.when()
			.get("/contacts")
		.then().log().all()
			.assertThat()
				.statusCode(200)
					.and()
						.contentType(ContentType.JSON);
	}
	
	
	@Test
	public void getAuthErrorTest() {
		
		RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com";
		
		given().log().all()
			.header("Authorization", "Bearer --naveen")
		.when()
			.get("/contacts")
		.then().log().all()
			.assertThat()
				.statusCode(401);
					
	}
	
	
	@Test
	public void getAuthErrorMessageTest() {
		
		RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com";
		
		String errorMessg = given().log().all()
			.header("Authorization", "Bearer --naveen")
		.when()
			.get("/contacts")
		.then().log().all()
			.extract()
				.path("error");
		
		System.out.println("error Message : "+ errorMessg);
		Assert.assertEquals(errorMessg, "Please authenticate.");
					
	}

}

package GET_API_NON_BDD;

import static io.restassured.RestAssured.*;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetContactsAPI {

	@Test
	public void getContactsTest() {

		baseURI = "https://thinking-tester-contact-list.herokuapp.com";

		RequestSpecification req = given();
		
		req.header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2ODk2MzA1NjEyM2ViMTAwMTVhNzRmMmMiLCJpYXQiOjE3NTg3ODcwNDl9.nc5DNK1hzZbmH9Qu17O_o_xk_TryMooxUsk0G_Zu3Xg");
		
		Response res = req.get("/contacts");
		
		System.out.println(res.statusCode());
		System.out.println(res.statusLine());
		
		res.prettyPrint();
		
		//get list of headers
		
		List<Header> headers = res.headers().asList();
		
		for(Header e : headers) {
			
			System.out.println(e.getName()+ "  =  " +e.getValue() );
		}
	}

}

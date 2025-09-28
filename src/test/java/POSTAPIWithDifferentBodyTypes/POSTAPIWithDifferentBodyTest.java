package POSTAPIWithDifferentBodyTypes;


import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;


public class POSTAPIWithDifferentBodyTest {

	
	@Test
	public void bodyWithTextTest() {
		
		RestAssured.baseURI = "https://postman-echo.com";
		
		String payload = "Hi This is My Plain Code";
		
		given().log().all()
			.contentType(ContentType.TEXT)
			.body(payload)
		.when()
			.post("/post")
		.then().log().all()
			.assertThat()
				.statusCode(200);
	}
	
	
	
	@Test
	public void bodyWithJavaScriptTest() {
		
		RestAssured.baseURI = "https://postman-echo.com";
		
		String payload = "<script>\n"
				+ "    function myFunction() {\n"
				+ "        document.getElementById(\"demo\").innerHTML = \"Paragraph changed.\"\n"
				+ "    }\n"
				+ "</script>";
		
		given().log().all()
			.contentType("application/javascript;charset=utf-8")
			.body(payload)
		.when()
			.post("/post")
		.then().log().all()
			.assertThat()
				.statusCode(200);
	}
	
	
	@Test
	public void bodyWithHTMLTest() {
		
		RestAssured.baseURI = "https://postman-echo.com";
		
		String payload = "<html>\n"
				+ "<body>\n"
				+ "<h2>Demo JavaScript in Body</h2>\n"
				+ "<p id=\"demo\">A Paragraph.</p>\n"
				+ "<button type=\"button\" onclick=\"myFunction()\">Try it</button>\n"
				+ "</body>\n"
				+ "</html>";
		
		given().log().all()
			.contentType(ContentType.HTML)
			.body(payload)
		.when()
			.post("/post")
		.then().log().all()
			.assertThat()
				.statusCode(200);
	}
	
	
	@Test
	public void bodyWithXMLTest() {
		
		RestAssured.baseURI = "https://postman-echo.com";
		
		String payload = "<dependency>\n"
				+ "    <groupId>io.rest-assured</groupId>\n"
				+ "    <artifactId>rest-assured</artifactId>\n"
				+ "    <version>5.5.6</version>\n"
				+ "</dependency>";
		
		given().log().all()
			.contentType("application/xml;charset=utf-8")
			.body(payload)
		.when()
			.post("/post")
		.then().log().all()
			.assertThat()
				.statusCode(200);
	}
	
	
	public String getRandomEmailid() {
		return "apiautomation"+System.currentTimeMillis()+"@opencart.com";
	}
	
	@Test
	public void bodyWithJSONTest() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		String emailId = getRandomEmailid();
		
		given().log().all()
			.contentType(ContentType.JSON)
			.header("Authorization", "Bearer d0bf1714ac04c10dd2982e009d2dffe694a8e0b53af518cb7370e41e046a72f6")
			.body("{\n"
					+ "    \"name\": \"Api Automation\",\n"
					+ "    \"gender\": \"male\",\n"
					+ "    \"email\": \""+emailId+"\",\n"
					+ "    \"status\": \"active\"    \n"
					+ "}")
	    .when()
	    	.post("/public/v2/users")
	    .then().log().all()
	    	.assertThat()
	    		.statusCode(201);
	}
	
	
	@Test
	public void bodyWithFormDataMultipPartTest() {
		
		RestAssured.baseURI = "https://postman-echo.com";
		
		given().log().all()
			.contentType(ContentType.MULTIPART)
			.multiPart("name", "Tom")
			.multiPart("resume", new File("./src/test/resources/json/CV Shubhamsing Bayas.pdf"))
			.multiPart("profilepic", new File("./src/test/resources/json/s.jpg"))

		.when()
			.post("/post")
		.then().log().all()
			.assertThat()
				.statusCode(200);
	}
	
	
	
	@Test
	public void bodyWithSinglePDFFileTest() {
		
		RestAssured.baseURI = "https://postman-echo.com";
		
		given().log().all()
			.contentType("application/pdf")
			.body(new File("./src/test/resources/json/CV Shubhamsing Bayas.pdf"))
			
		.when()
			.post("/post")
		.then().log().all()
			.assertThat()
				.statusCode(200);
	}
	
	
	@Test
	public void bodyWithSingleImagePNGFileTest() {
		
		RestAssured.baseURI = "https://postman-echo.com";
		
		given().log().all()
			.contentType("image/jpg")
			.body(new File("./src/test/resources/json/s.jpg"))
			
		.when()
			.post("/post")
		.then().log().all()
			.assertThat()
				.statusCode(200);
	}
	
	
	//csv/xlsx
	@Test
	public void bodyWithSingleCSVFileTest() {
		
		RestAssured.baseURI = "https://postman-echo.com";
		
		given().log().all()
			.contentType("text/csv")
			.body(new File("./src/test/resources/json/size.csv"))
			
		.when()
			.post("/post")
		.then().log().all()
			.assertThat()
				.statusCode(200);
	}
	
	
	@Test
	public void bodyWithSingleXLSxFileTest() {
		
		RestAssured.baseURI = "https://postman-echo.com";
		
		given().log().all()
			.contentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
			.body(new File("./src/test/resources/json/QA EXCEL.xlsx"))
			
		.when()
			.post("/post")
		.then().log().all()
			.assertThat()
				.statusCode(200);
	}
	
	
	

}


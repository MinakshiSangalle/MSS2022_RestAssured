package Minakshi1_restassured;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import static io.restassured.RestAssured.given;

import java.time.LocalDateTime;

public class Patch_reference {

	public static void main(String[] args) {
		// Declare base URI and request body variables
		String BaseURI="https://reqres.in/";
		String requestbody="{\r\n"
				+ "    \"name\": \"morpheus\",\r\n"
				+ "    \"job\": \"zion resident\"\r\n"
				+ "}";

		// Configure baseURI
				RestAssured.baseURI=BaseURI;
						
				//Parse the request body and its parameters
				JsonPath jspreq=new JsonPath(requestbody);
				String req_name=jspreq.getString("name");
				String req_job=jspreq.getString("job");
				
				//Fetch response status code and response body
			
		int statusCode=given().header("Content-type","application/json").body(requestbody).when().patch("api/users/2").then().extract().response().statusCode();
		System.out.println(statusCode);
				
		String responsebody=given().header("Content-type","application/json").body(requestbody).when().patch("api/users/2").then().extract().response().asString();
		System.out.println(responsebody);
				
				//Parse response body and its parameters
				JsonPath jspres=new JsonPath(responsebody);
				String res_name=jspres.getString("name");
				String res_job=jspres.getString("job");
				String res_updatedAt=jspres.getString("updatedAt");
				
				String trim_date=res_updatedAt.substring(0,10);
				
				//generate date
			   LocalDateTime date=LocalDateTime.now();
			   String exp_date=date.toString().substring(0,10);
			
		    	//Validate the response
			   
			   Assert.assertEquals(statusCode,200);
			   Assert.assertEquals(req_name, res_name);
			   Assert.assertEquals(req_job, res_job);
			  Assert.assertEquals(trim_date, exp_date);
		
		

	}

}

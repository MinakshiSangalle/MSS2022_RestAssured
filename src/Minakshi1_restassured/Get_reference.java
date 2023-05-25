package Minakshi1_restassured;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

import org.testng.Assert;

import io.restassured.path.json.JsonPath;
public class Get_reference {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//declare baseURI
		RestAssured.baseURI="https:/reqres.in/";
		//configure 
		int statusCode=given().header("Content-type","application/json").when().get("api/users?page=2").then().extract().response().statusCode();
		String responseBody=given().header("Content-type","application/json").when().get("api/users?page=2").then().extract().response().asString();
		System.out.println(responseBody);
//parse responseBody
		JsonPath jsp=new JsonPath(responseBody);
		int datasize = jsp.getList("data").size();
		//assert the total count objects inside the array
		Assert.assertEquals(datasize, 6);
		
		//validate each object in data array
		for(int i=0;i<datasize;i++) {
		String id=jsp.getString("data["+i+"].id");
		String email=jsp.getString("data["+i+"].email");
		String first_name=jsp.getString("data["+i+"].first_name");
		String last_name=jsp.getString("data["+i+"].last_name");
		String avatar=jsp.getString("data["+i+"].avatar");
		Assert.assertNotNull(id);
		Assert.assertNotNull(email);
		Assert.assertNotNull(first_name);
		Assert.assertNotNull(last_name);
		Assert.assertNotNull(avatar);
		Assert.assertTrue(Integer.parseInt(id)>=7 && Integer.parseInt(id)<=12);
		Assert.assertTrue(email.contains("@reqres.In"));
		}
		



}
}
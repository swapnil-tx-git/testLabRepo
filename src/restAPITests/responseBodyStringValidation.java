package restAPITests;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;

public class responseBodyStringValidation {
	
	static String baseURI = "https://maps.googleapis.com/maps";
	static String resource = "/api/place/nearbysearch/json";
	
	static JsonPath jsonResponse;

	public static void main(String[] args) {
		
			addDeletePlace();
		
	}
	
	public static void addDeletePlace() {
		
		System.out.println("Start of function addDeletePlace");
		RestAssured.baseURI = "http://216.10.245.166";
		
		
		Response res = 
				
		given().
				param("location", "33.8670522,151.1957362").
				param("radius", "500").
				param("key", "AIzaSyBl3BTIvoV9rACllity139BnWlG3lJ9ttE").
				//param("type", "restaurant").
				//param("keyword", "cruise").
		when().
				get(resource).
		then().
				assertThat().statusCode(200).
				and().contentType(ContentType.JSON).
				//and().body("status", equalTo("OK")).
				extract().response();
		
		System.out.println("Response String: \n"+res.asString());
		
		jsonResponse = new JsonPath(res.asString());
		
		String responseStatus = jsonResponse.get("status");
		System.out.println("Status of response: "+responseStatus);
		
		System.out.println("Ënd of function addDeletePlace");
		
	}
	
	

}

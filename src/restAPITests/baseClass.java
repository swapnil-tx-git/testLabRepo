package restAPITests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;



//import org.testng.annotations.Test;



public class baseClass {
	
	static String baseURI = "https://maps.googleapis.com/maps";
	static String resource = "/api/place/nearbysearch/json";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 
		 https://maps.googleapis.com/maps
		 /api/place/nearbysearch/json?
		  location=-33.8670522,151.1957362&radius=500&type=restaurant&keyword=cruise&key=AIzaSyBl3BTIvoV9rACllity139BnWlG3lJ9ttE
		 
		 
		*/
		
		//setAndGetAPI();
		setAndPostAPI();
		
		
		
	} 
	
	
	static void setAndGetAPI() {
		
		System.out.println("Start of function setAndGetAPI");
		RestAssured.baseURI = baseURI;
		
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
				and().header("Server", "scaffolding on HTTPServer2").
				and().header("X-Frame-Options", "SAMEORIGIN");
		
				//and().body("results[0].place_id", equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM")).
				//and().body("results[0].name", equalTo("Sydney"));
				//and().body("results[0].geometry.location.lat", equalTo("-33.86882"));
				//and().body("results[1].vicinity", equalTo("80 Pyrmont Street, Pyrmont"));
		
		System.out.println("Ënd of function setAndGetAPI");
		
	}
	
	@Test
	static void setAndPostAPI() {
		
		System.out.println("Start of function setAndPostAPI");
		
		String bodyDataJson = ""
				+ "{\r\n" + 
				"\r\n" + 
				"    \"location\":{\r\n" + 
				"\r\n" + 
				"        \"lat\" : -38.383494,\r\n" + 
				"\r\n" + 
				"        \"lng\" : 33.427362\r\n" + 
				"\r\n" + 
				"    },\r\n" + 
				"\r\n" + 
				"    \"accuracy\":50,\r\n" + 
				"\r\n" + 
				"    \"name\":\"Frontline house\",\r\n" + 
				"\r\n" + 
				"    \"phone_number\":\"(+91) 983 893 3937\",\r\n" + 
				"\r\n" + 
				"    \"address\" : \"29, side layout, cohen 09\",\r\n" + 
				"\r\n" + 
				"    \"types\": [\"shoe park\",\"shop\"],\r\n" + 
				"\r\n" + 
				"    \"website\" : \"http://google.com\",\r\n" + 
				"\r\n" + 
				"    \"language\" : \"French-IN\"\r\n" + 
				"\r\n" + 
				"}\r\n" + 
				"";
		
		RestAssured.baseURI = "http://216.10.245.166";
		
		given().
				queryParam("key", "qaclick123").
				body(bodyDataJson).
		when().
				post("/maps/api/place/add/json"	).
		then().
				assertThat().statusCode(200);

		System.out.println("Ënd of function setAndPostAPI");	
	}

}

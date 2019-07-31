package restAPITests;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;


import files.Resources;
import files.Payloads;

public class e2EResponseValidation {
	
	String baseURI;
	String resource;
	public static Properties prop;
	String apiConfig = "C:\\Users\\Swapnil\\eclipse-workspace-testlab\\RestAPISpace\\src\\files\\apiconfig.properties";
	
	JsonPath jsonResponse;
	XmlPath xmlResponse;
	
	String bodyDataAddPlace = Payloads.addPlacePayLoad();
	String bodyDataDeletePlace = Payloads.deletePlacePayLoad();
	
	String bodyXMLDataAddPlace = Payloads.addPlaceXMLPayLoad();
	String bodyXMLDataDeletePlace = Payloads.deletePlaceXMLPayLoad();
	
	
	
	@BeforeTest
	public void environmentConfig() throws IOException {
		
		FileInputStream fis = new FileInputStream(apiConfig);
		prop = new Properties();
		prop.load(fis);
		
	}
	
	
	public String addPlaceAPITest() {
		
		System.out.println("\nstart of function addPlaceAPITest\n");
		RestAssured.baseURI = prop.getProperty("baseURI");
		
		Response res = 
				
		given().
				queryParam("key", prop.getProperty("qaClickKey")).
				body(bodyDataAddPlace).log().all().
		when().
				post(Resources.addPlacePostData()).
		then().
				assertThat().statusCode(200).
				and().contentType(ContentType.JSON).log().body().
				extract().response();
		
		System.out.println("Response String: \n"+res.asString());
		
		jsonResponse = new JsonPath(res.asString());
		
		String responseStatus = jsonResponse.get("status");
		String place_id = jsonResponse.get("place_id");
		System.out.println("Status of response: "+responseStatus);
		System.out.println("Place ID: "+place_id);
		
		System.out.println("\nend of function addPlaceAPITest\n");
		return place_id;
	}
	
	//@Test
	public void addPlaceXMLAPITest() {
		
		System.out.println("\nstart of function addPlaceXMLAPITest\n");
		RestAssured.baseURI = prop.getProperty("baseURI");
		
		Response res = 
				
		given().
				queryParam("key", prop.getProperty("qaClickKey")).
				body(bodyXMLDataAddPlace).
		when().
				post(Resources.addPlacePostXMLData()).
		then().
				assertThat().statusCode(200).
				and().contentType(ContentType.XML).
				extract().response();
		
		System.out.println("Response String: \n"+res.asString());
		
		String xmlRes = res.asString();
		xmlResponse = new XmlPath(xmlRes);
		
		String responseStatus = xmlResponse.get("response.status").toString();
		String place_id = xmlResponse.get("response.place_id").toString();
		System.out.println("Status of response: "+responseStatus);
		System.out.println("Place ID: "+place_id);
//		
		System.out.println("\nend of function addPlaceXMLAPITest\n");
//		return place_id;
	}
	
	@Test
	public void deletePlaceAPITest(){
		
		System.out.println("\nstart of function deletePlaceAPITest\n");
		
		String placeToDelete = addPlaceAPITest();
		RestAssured.baseURI = prop.getProperty("baseURI");
		
		String deletePlace = bodyDataDeletePlace.replace("insertPlaceHere", placeToDelete);
		System.out.println("Place ID to Delete - Delete API Body: "+deletePlace);
		
		Response res = 
				
		given().
				queryParam("key", prop.getProperty("qaClickKey")).
				body(bodyDataDeletePlace).log().all().
		when().
				post(Resources.deletePlacePostData()).
		then().
				assertThat().statusCode(200).
				and().contentType(ContentType.JSON).log().body().
				extract().response();
		
		System.out.println("Response String: \n"+res.asString());
		
		if(res.asString().equals(null) || res.asString().equals("")) {
			System.out.println("No Response Body Returned.");
		}
		else {
		jsonResponse = new JsonPath(res.asString());		
		String place_id = jsonResponse.get("place_id");
		System.out.println("Place ID: "+place_id);
		}
		
		System.out.println("\nend of function deletePlaceAPITest\n");		
	}
}

package restAPITests;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import files.APIUtils;

public class jsonResponseTest {
	
	@Test
	public void playingWithJsonResponse() {
		
		System.out.println("Start of function playingWithJsonResponse");
		RestAssured.baseURI = "https://reqres.in";
		
		Response res = 
		
		given().
				param("page", "2").
				//param("location", "33.8670522,151.1957362").
				//param("radius", "1500").
				//param("type", "restaurant").
				//param("keyword", "cruise").
				//param("key", "AIzaSyBl3BTIvoV9rACllity139BnWlG3lJ9ttE").
		when().
				get("/api/users?page=2").
		then().
				assertThat().statusCode(200).
				and().contentType(ContentType.JSON).
				extract().response();
		
				//and().header("Server", "scaffolding on HTTPServer2").
				//and().header("X-Frame-Options", "SAMEORIGIN").extract().response();
		
				//and().body("results[0].place_id", equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM")).
				//and().body("results[0].name", equalTo("Sydney"));
				//and().body("results[0].geometry.location.lat", equalTo("-33.86882"));
				//and().body("results[1].vicinity", equalTo("80 Pyrmont Street, Pyrmont"));
		
		JsonPath jRes = APIUtils.rawDatatoJsonParser(res);
		
		String response = jRes.prettyPrint();
		
		int dataCount = jRes.get("data.size()");
		System.out.println("data values Returned on search: "+dataCount);
			
		for(int i=0; i<dataCount; i++) {
			System.out.println("Details for record "+(i+1)+" : ");
			System.out.println("Name: "+jRes.get("data["+i+"].first_name")+" "+jRes.get("data["+i+"].last_name"));
			System.out.println("Email: "+jRes.get("data["+i+"].email"));
			System.out.println();
		}
		System.out.println("Start of function playingWithJsonResponse");
	}

}

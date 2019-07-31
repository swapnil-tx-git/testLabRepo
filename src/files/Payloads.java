package files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Payloads {
	
	public static String addPlaceXMLPayLoad() {
		
		String postData = null;
		String path = "C:\\Users\\Swapnil\\eclipse-workspace-testlab\\RestAPISpace\\src\\files\\addPlacesPostXMLBody.xml";
		
		try {
			postData = GenerateStringFromResource(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Issue with XML body.");
			e.printStackTrace();
		}
		return postData;
	}
	
	public static String deletePlaceXMLPayLoad() {
		
		
		return "";
	}
	
	public static String GenerateStringFromResource(String path) throws IOException {
		
		return new String(Files.readAllBytes(Paths.get(path)));
		
	}
	
	public static String addPlacePayLoad() {
		
		String payload = ""
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
		return payload;
	}

public static String deletePlacePayLoad() {
		
		String payload = "{\r\n" + 
				"    \"place_id\":\"insertPlaceHere\"           //(This value comes from Add place response)\r\n" + 
				"}";
		return payload;
	}

public static String addBookPayload(String isbn, String aisle) {
	
	String payload = "{\r\n\r\n\"name\":\"Streets of Bangalore\",\r\n\"isbn\":\""+isbn+"\",\r\n\"aisle\":\""+aisle+"\",\r\n\"author\":\"Swapnil T\"\r\n}";
	return payload;
}
}

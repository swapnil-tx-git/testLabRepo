package files;

import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class APIUtils {
	
	public static XmlPath rawDatatoXMLParser(Response res) {
		
		String xmlRes = res.asString();
		XmlPath xmlResponse = new XmlPath(xmlRes);
		
		return xmlResponse;
	}
	
	public static JsonPath rawDatatoJsonParser(Response res) {
		
		String jsonRes = res.asString();
		JsonPath jsonResponse = new JsonPath(jsonRes);
		
		return jsonResponse;
	}
	
	public static Object rawDataParser(Response res, String bodyType) {
		
		if(bodyType.equalsIgnoreCase("xml")) {
			String xmlRes = res.asString();
			XmlPath xmlResponse = new XmlPath(xmlRes);
			
			return xmlResponse;
		}
		else if(bodyType.equalsIgnoreCase("json")) {
			String jsonRes = res.asString();
			JsonPath jsonResponse = new JsonPath(jsonRes);
			
			return jsonResponse;
		}
		else {
			System.out.println("Invalid body format.");
			return null;
		}
	}
	
	

}

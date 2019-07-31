package files;

import restAPITests.e2EResponseValidation;

public class Resources {
	
	public static String addPlacePostData() {
		
		String res = e2EResponseValidation.prop.getProperty("addPlaceAPIResource");
		System.out.println("Resource Name for add place: "+res);
		return res;
	} 
	
public static String deletePlacePostData() {
		
	    String res = e2EResponseValidation.prop.getProperty("deletePlaceAPIResource");
		System.out.println("Resource Name for add place: "+res);
		return res;
	}

public static String addPlacePostXMLData() {
	
	String res = e2EResponseValidation.prop.getProperty("addPlaceAPIResourceXML");
	System.out.println("Resource Name for add place: "+res);
	return res;
}

}

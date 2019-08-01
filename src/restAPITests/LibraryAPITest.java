package restAPITests;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.APIUtils;
import files.Payloads;
import files.Resources;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class LibraryAPITest {
	
	Properties prop;
	
	String autoGenId;
	String libConfig = "C:\\Users\\Swapnil\\eclipse-workspace-testlab\\RestAPISpace\\src\\files\\libconfig.properties";
	String libRepo = "C:\\Users\\Swapnil\\eclipse-workspace-testlab\\RestAPISpace\\src\\files\\libRepo.properties";
	String jsonPayloadPath = "C:\\Users\\Swapnil\\eclipse-workspace-testlab\\RestAPISpace\\src\\files\\addBookPostJsonPayloadFile.json";
	String libTransaction = null;
	String body = null; 
	static int bookSet = 0;
	
	@BeforeTest
	public void environmentConfig() throws IOException {
		
		FileInputStream fis = new FileInputStream(libConfig);
		prop = new Properties();
		prop.load(fis);
		
		System.out.println("Dev Colab through Git deployment");
	}
	
	@AfterTest
	public void tearDownTests() {
		
		prop.clear();
		System.out.println("Tests teardown completed");
	}
	
	@Test
	public void addBookLibraryAPITest(/*String isbn, String aisle*/) throws FileNotFoundException, IOException {
		
		System.out.println("\nstart of function addBookLibraryAPITest\n");
		
		autoGenId = bookIDUtil();
		body = Payloads.addBookPayload(autoGenId, "299");
		
		
		RestAssured.baseURI = "http://216.10.245.166";
		
		Response res = 
				
		given().
				header("Content-Type", "application/json").
				body(body).log().all().
		when().
				post("/Library/Addbook.php").
		then().
				assertThat().statusCode(200).
				and().contentType(ContentType.JSON).log().body().
				extract().response();
		
		System.out.println("Response String: \n"+res.asString());
		
		JsonPath jsonResponse = APIUtils.rawDatatoJsonParser(res);
		
		
		String message = jsonResponse.get("Msg");
		String bookID = jsonResponse.get("ID");
		
		System.out.println("Response Message: "+message);
		System.out.println("Book ID: "+bookID);
		
		System.out.println("\nend of function addBookLibraryAPITest\n");
		
	}
	
	@Test(dataProvider = "BooksData")
	public void addBooksSetLibraryAPITest(String isbn, String aisle) throws FileNotFoundException, IOException {
		
		System.out.println("\nstart of function addBooksSetLibraryAPITest\n");
		bookSet++;
		System.out.println("Adding Book "+bookSet+" to Books set in Library.");
		
		//autoGenId = isbn;
		body = Payloads.addBookPayload(isbn, aisle);
		
		
		RestAssured.baseURI = "http://216.10.245.166";
		
		Response res = 
				
		given().
				header("Content-Type", "application/json").
				body(body).log().all().
		when().
				post("/Library/Addbook.php").
		then().
				assertThat().statusCode(200).
				and().contentType(ContentType.JSON).log().body().
				extract().response();
		
		System.out.println("Response String: \n"+res.asString());
		
		JsonPath jsonResponse = APIUtils.rawDatatoJsonParser(res);
		
		
		String message = jsonResponse.get("Msg");
		String bookID = jsonResponse.get("ID");
		
		System.out.println("Response Message: "+message);
		System.out.println("Book ID: "+bookID);
		
		System.out.println("\nend of function addBooksSetLibraryAPITest\n");
		
	}
	
	@DataProvider(name="BooksData")
	public Object bookData() throws FileNotFoundException, IOException {
		
		Object books[][] = new Object[3][2];
		
		books[0][0] = bookIDUtil();
		books[0][1] = "DPA287";
		books[1][0] = bookIDUtil();
		books[1][1] = "DPA288";
		books[2][0] = bookIDUtil();
		books[2][1] = "DPA289";
		
		return books;
		
	}
	
	@Test
	public void staticJsonAddBookTest() throws IOException, IOException{
		
		System.out.println("\nstart of function staticJsonAddBookTest\n");
		
		autoGenId = bookIDUtil();
		body = Payloads.GenerateStringFromResource(jsonPayloadPath).replace("newisbn", autoGenId);
		System.out.println("Body Retreived from static json file: \n"+body);
		
		RestAssured.baseURI = "http://216.10.245.166";
		
		Response res = 
				
		given().
				header("Content-Type", "application/json").
				body(body).log().all().
		when().
				post("/Library/Addbook.php").
		then().
				assertThat().statusCode(200).
				and().contentType(ContentType.JSON).log().body().
				extract().response();
		
		System.out.println("Response String: \n"+res.asString());
		
		JsonPath jsonResponse = APIUtils.rawDatatoJsonParser(res);
		
		
		String message = jsonResponse.get("Msg");
		String bookID = jsonResponse.get("ID");
		
		System.out.println("Response Message: "+message);
		System.out.println("Book ID: "+bookID);
		
		System.out.println("\nend of function staticJsonAddBookTest\n");
		
	}
	
	//Create Delete Book Function
	
	
	public String bookIDUtil() throws FileNotFoundException, IOException {
		
		String bookId = prop.getProperty("bookid");
		int bId = Integer.parseInt(bookId);
		
		bId = bId+1;
		prop.setProperty("bookid", Integer.toString(bId));		
		prop.store(new FileOutputStream(libConfig), null);
		System.out.println(prop.getProperty("bookid"));
		
		String newId = "L"+Integer.toString(bId);
		System.out.println("New book ISBM generated: "+newId);
		
		return newId;
	}
	
	//To Do
	public void updateBooksInLibrary(String bkId) throws IOException {
		
		Properties repoProp;
		FileOutputStream repoFis = new FileOutputStream(libRepo);
		repoProp = new Properties();
		
		//repoProp.load(repoFis);
		
		if(libTransaction.equals(null)) {
			System.out.println("No update to repository. Please check payload data/response.");
		}
		else if(libTransaction.equalsIgnoreCase("addbook")) {
			
			//repoProp.setProperty(, value)
			//repoProp.store(new FileOutputStream(libRepo), null);
			//System.out.println(prop.getProperty("bookid"));
		}
		else if(libTransaction.equalsIgnoreCase("deletebook")) {
			
		}
	}

}

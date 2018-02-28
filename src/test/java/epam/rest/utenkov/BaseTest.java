package epam.rest.utenkov;

import static io.restassured.RestAssured.given;

import java.util.List;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.uncommons.reportng.HTMLReporter;

import io.restassured.RestAssured;
import io.restassured.response.Response;

@Listeners({HTMLReporter.class})
public class BaseTest {
	
	private static final Logger LOG = Logger.getLogger(BaseTest.class);
	public String auth = "bearer 1dc17a5e-e186-42bb-99b7-a119e0d1271b";
	public String dashboardID = "";
	public List<String> launchList = null;
	public static final int REQUIRED_TOTAL = 138;
	
	@BeforeTest
	public void setBasicData(){
		
		RestAssured.baseURI = "https://rp.epam.com/";
		RestAssured.basePath = "api/v1/vyacheslav_utenkov_personal";
	}
	/**
	@Test(priority = 0)
	public void testCodeResponse(){
		LOG.info("start mathod [testCodeResponse]");
				Assert.assertEquals(200, 200);
		LOG.assertLog(true, "seegss");
		LOG.info("end mathod [testCodeResponse]");
	}
	*/
	@Test(priority = 0)
	public void testCodeResponse(){
		LOG.info("start mathod [testCodeResponse]");
		Response res = given().header("Authorization", auth).
				get("/launch").andReturn();
				Assert.assertEquals(res.getStatusCode(), 200);
		LOG.assertLog(true, "seegss");
		LOG.info("end mathod [testCodeResponse]");
	}
}

/**
 * 
  <dependency>
  		<groupId>com.epam.reportportal</groupId>
  		<artifactId>agent-java-testng</artifactId>
  		<version>3.0.0</version>
	</dependency>
	<dependency>
 		 <groupId>com.epam.reportportal</groupId>
  		 <artifactId>logger-java-log4j</artifactId>
  		 <version>3.0.0</version>
	</dependency>
 * 
 * 
 * 
 * **/

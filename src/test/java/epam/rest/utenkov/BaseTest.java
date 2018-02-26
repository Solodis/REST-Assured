package epam.rest.utenkov;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.uncommons.reportng.HTMLReporter;

import epam.rest.utenkov.bo.Dashboard;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

@Listeners({HTMLReporter.class})
public class BaseTest {
	
	private static final Logger LOG = Logger.getLogger(BaseTest.class);
	public String auth = "bearer d27b3373-c8f7-401a-bd7d-cbd13c8cfa0d";
	public String dashboardID = "";
	public List<String> launchList = null;
	public static final int REQUIRED_TOTAL = 138;
	
	@BeforeTest
	public void setBasicData(){
		
		RestAssured.baseURI = "https://rp.epam.com/";
		RestAssured.basePath = "api/v1/vyacheslav_utenkov_personal";
	}
	
	@Test(priority = 0)
	public void testCodeResponse(){
		LOG.info("start mathod [testCodeResponse]");
				Assert.assertEquals(200, 200);
		LOG.assertLog(true, "seegss");
		LOG.info("end mathod [testCodeResponse]");
	}
	/**
	@Test(priority = 0)
	public void testCodeResponse(){
		LOG.info("start mathod [testCodeResponse]");
		Response res = given().header("Authorization", auth).
				get("/launch").andReturn();
				Assert.assertEquals(res.getStatusCode(), 200);
		LOG.assertLog(true, "seegss");
		LOG.info("end mathod [testCodeResponse]");
	}
	
	@Test()
	public void addDashboard(){
		Dashboard dash = new Dashboard("NoName", "JustForTest", "false");
		LOG.info("Dashboard created: " + dash);
		dashboardID = given().header("Authorization", auth)
			.body(dash)
		.when()
			.contentType("application/json")
			.post("/dashboard")
		.then()
			.assertThat()
			.statusCode(201)
			.extract()
			.response()
			.path("id");
		
		LOG.info("Dushboard ID: " + dashboardID);
	}
	
	@Test()
	public void receivingOfDashboards(){
		LOG.info("\n********************\nstart mathod [receivingOfDashboards]\n********************");
		 
		 given()
		 	.header("Authorization", auth)
		.when()
			.contentType(ContentType.JSON)
			.get("/dashboard")
		.then().assertThat().statusCode(200).extract().response()
			.andReturn();
		LOG.info("");
		LOG.info("end mathod [receivingOfDashboards]");
	}
	
	
	@Test()
	public void allLaunchs(){
		LOG.info("start mathod [allLaunchs]");
		Response res = 
		given()
			.header("Authorization", auth)
		.when()
			.get("/launch")
		.then()
			.contentType(ContentType.JSON)
			.body("content[0].owner", equalTo("vyacheslav_utenkov"))
			.assertThat()
			.statusCode(200)
			.extract()
			.response();
		
		List <String> listOfValues = new ArrayList<String>();
		int i = 0;
		int numOfFailedLaunches = 0;
		while(res.path("content["+ i +"].statistics.executions.total") != null){
			int totalValue = Integer.parseInt(res.path("content["+ i +"].statistics.executions.total").toString());
			if(totalValue > 20){
				listOfValues.add(res.path("content["+ i +"].id").toString() + "\n Total: " + totalValue);
				if(!(res.path("content["+ i +"].status")).equals("FAILD")){
					numOfFailedLaunches++;
				}
			}
			i++;
		}
		
		for (String string : listOfValues) {
			LOG.info(string);
		}

		LOG.info("Num Of Failed Launches: " + numOfFailedLaunches + "\n=============================" );
		LOG.info("end mathod [allLaunchs]");
	}
	
	@Test(dependsOnMethods="addDashboard")
	public void testPutRequest(){
		LOG.info("start mathod [testPutRequest]");
		LOG.info("ID: " + dashboardID);
		Dashboard dash = new Dashboard("NewModrnNoName", "JustForModernPutTest", "false");
	     given()
		.header("Authorization", auth)
		.body(dash)
		.when()
		.contentType(ContentType.JSON)
		.put("/dashboard/{dashboardID}", dashboardID)
		.then()
		.log()
		.all()
		.assertThat().statusCode(200); 
	     LOG.info("end mathod [testPutRequest]");
	}
	
	@Test(dependsOnMethods="testPutRequest")
	public void testDeleteRequest(){
		LOG.info("start mathod [testDeleteRequest]");
		int expectedCode = 200;
		Response res = given().headers("Authorization", auth)
		.when()
		.contentType(ContentType.JSON)
		.delete("/dashboard/{DashboardID}", dashboardID)
		.then().assertThat().statusCode(200).extract().response();
		if(res.statusCode() == expectedCode){
			LOG.info("INFO: Dasboard " + dashboardID + " is deleted");
		}
		LOG.info("end mathod [testDeleteRequest]");
	}
	
	
	@Test(priority = 6)
	public void allLaunchsSecondTest(){
		LOG.info("start mathod [allLaunchsSecondTest]");
		Response res = 
		given()
			.header("Authorization", auth)
		.when()
			.get("/launch")
		.then()
			.contentType(ContentType.JSON)
			.body("content[0].owner", equalTo("vyacheslav_utenkov"))
			.assertThat()
			.statusCode(200)
			.extract()
			.response();
		int i = 0;
		while(res.path("content[" + i + "].statistics.executions.total") != null) {
			int gettingResult = Integer.parseInt(res.path("content[" + i + "].statistics.executions.total").toString());
			if(gettingResult == REQUIRED_TOTAL || gettingResult == 20) {
				LOG.info(res.path("content[" + i + "].id"));
			}
			i++;
		}
		LOG.info("end mathod [allLaunchsSecondTest]");
	}
	*/
	
}

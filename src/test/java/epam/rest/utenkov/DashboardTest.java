package epam.rest.utenkov;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

import org.apache.log4j.Logger;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.uncommons.reportng.HTMLReporter;

import epam.rest.utenkov.bo.Dashboard;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

@Listeners({HTMLReporter.class})
public class DashboardTest extends BaseTest{
	
	private static final Logger LOG = Logger.getLogger(DashboardTest.class);
	private static final String ACTUAL_NAME = "RestAssuredProject";
	private static final String ACTUAL_OWNER = "vyacheslav_utenkov";
	
	/**
	 * The method is adding a new dashboard to the ReportPortal 
	 */
	@Test(dependsOnGroups="Launches")
	public void addDashboard(){
		Dashboard dash = new Dashboard("NoName", "JustForTest", "false");
		LOG.info("Dashboard created: " + dash);
		dashboardID = given().header("Authorization", auth)
			.body(dash)
		.when()
			.contentType("application/json")
			.post("/dashboard")
		.then()
		/**
		 * Checking that time of execution is less then 700 mls
		 */
			.time(lessThan(700l))
			.assertThat()
			.statusCode(201)
			.extract()
			.response()
			.path("id");
		
		LOG.info("Dushboard ID: " + dashboardID);
	}
	
	@Test(dependsOnMethods="testPutRequest")
	public void receivingOfDashboards(){
		LOG.info("\n********************\nstart mathod [receivingOfDashboards]\n********************");
		 
		 given()
		 	.header("Authorization", auth)
		.when()
			.contentType(ContentType.JSON)
			.get("/dashboard")
		.then().assertThat().statusCode(200).extract().response()
			.andReturn();
		LOG.info("end mathod [receivingOfDashboards]");
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
	
	@Test (dependsOnMethods = "testPutRequest")
	public void checkDashboardFields() {
		 LOG.info("\nStart method [checkDashboardFields]");
		Response res = given()
				.header("Authorization", auth)
				.when().contentType(ContentType.JSON)
				.get("/dashboard")
				.andReturn();
		String owner = res.getBody().path("owner[1]").toString();
		String name = res.getBody().path("name[1]").toString();
		assertEquals(owner, ACTUAL_OWNER);
		assertFalse(!owner.equals(ACTUAL_OWNER));
		assertEquals(name, ACTUAL_NAME);
		LOG.info("end method [checkDashboardFields]\n");
	}
	
	@Test(dependsOnMethods="testPutRequest", alwaysRun = true)
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
	
	
}

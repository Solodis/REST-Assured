package epam.rest.utenkov;

import static io.restassured.RestAssured.given;

import java.util.List;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

// @Listeners({HTMLReporter.class})
// @Listeners({ HTMLReporter.class, ReportPortalTestNGListener.class })
public class BaseTest {

	private static final Logger LOG = Logger.getLogger(BaseTest.class);
	public String auth = "bearer b2e90482-142b-4034-89ac-008aa5e13181";
	public String dashboardID = "";
	public List<String> launchList = null;
	public static final int REQUIRED_TOTAL = 138;

	@BeforeTest
	public void setBasicData() {

		RestAssured.baseURI = "https://rp.epam.com/";
		RestAssured.basePath = "api/v1/vyacheslav_utenkov_personal";
	}

	/**
	 * @Test(priority = 0) public void testCodeResponse(){ LOG.info("start
	 *                mathod [testCodeResponse]"); Assert.assertEquals(200,
	 *                200); LOG.assertLog(true, "seegss"); LOG.info("end mathod
	 *                [testCodeResponse]"); }
	 */
	@Test(priority = 0)
	public void testCodeResponse() {
		LOG.info("start mathod [testCodeResponse]");
		Response res = given().header("Authorization", auth).get("/launch").andReturn();
		Assert.assertEquals(res.getStatusCode(), 200);
		LOG.assertLog(true, "seegss");
		LOG.info("end mathod [testCodeResponse]");
	}
}

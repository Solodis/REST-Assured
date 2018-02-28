package epam.rest.utenkov;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import epam.rest.utenkov.bo.Launch;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class LaunchTest extends BaseTest{
	
	private static final Logger LOG = Logger.getLogger(LaunchTest.class);
	List<Launch> launches = new ArrayList<Launch>();
	@Test(groups = "Launches", dataProvider="dp")
	public void allLaunchs(String value){
		
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
			if(totalValue > 0){
				listOfValues.add(res.path("content["+ i +"].id").toString() + "\n Total: " + totalValue);
				if(res.path("content["+ i +"].status").toString().equals(value)){
					numOfFailedLaunches++;
				}
			}
			i++;
		}
		for (String string : listOfValues) {
			LOG.info(string);
		}

		LOG.info("Num Of " + value + " Launches: " + numOfFailedLaunches + "\n===============================================" );
		LOG.info("end mathod [allLaunchs]");
	}
	
	
	@Test(groups = "Launches")
	public void allLaunchsSecondTest(){
		LOG.info("start mathod [allLaunchsSecondTest]");
		String owner;
		String description;
		String id;
		String name;
		String status;
		HashMap<String, String> executions;
		
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
			LOG.info("[block while] " + res.path("content[" + i + "].statistics.executions.total").toString());
			owner = res.path("content[" + i + "].owner").toString();
			description = res.path("content[" + i + "].description").toString();
			id = res.path("content[" + i + "].id").toString();
			name = res.path("content[" + i + "].name").toString();
			status = res.path("content[" + i + "].status").toString();
			executions = new HashMap<String, String>();
			executions.put("Total", res.path("content[" + i + "].statistics.executions.total").toString()); 
			executions.put("Passed", res.path("content[" + i + "].statistics.executions.passed").toString());
			executions.put("Failed", res.path("content[" + i + "].statistics.executions.failed").toString());
			executions.put("Skipped", res.path("content[" + i + "].statistics.executions.skipped").toString());
			launches.add(new Launch(owner, description, id, name, status, executions));
			i++;
		}
		//launches.stream().forEach(System.out::println);
		LOG.info("end mathod [allLaunchsSecondTest]");
	}
	
	@DataProvider(name = "dp")
	public static Object[][] provideStatus(){
		ResourceBundle status = ResourceBundle.getBundle("statuses");
		return  new Object[][]{
				new Object[] {status.getString("Status.FAILED")},
				new Object[] {status.getString("Status.PASSED")},
				new Object[] {status.getString("Status.DEFOULT")}
		};
	}
}

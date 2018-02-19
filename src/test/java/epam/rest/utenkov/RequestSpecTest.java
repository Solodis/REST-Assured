package epam.rest.utenkov;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class RequestSpecTest {
	
	private String auth = "bearer d35338a1-0ee8-45a5-b5f8-bc9b8484961c";
	private String projectName = "vyacheslav_utenkov_personal";
	RequestSpecification rspec;
	RequestSpecBuilder build;

	@BeforeClass
	public void requestSpec() {

		build = new RequestSpecBuilder();
		build.setBaseUri("https://rp.epam.com");
		build.setBasePath("/api/v1/{project_name}/launch");
		build.addParam("page.page", "1");
		build.addParam("page.size", "50");

		rspec = build.build();
		

	}

	@Test
	public void testGetLaunches() {
	}
}

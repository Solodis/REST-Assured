package epam.rest.utenkov;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class ResponseSpecTest {
	private String auth = "bearer d35338a1-0ee8-45a5-b5f8-bc9b8484961c";
	private String projectName = "vyacheslav_utenkov_personal";
	ResponseSpecBuilder builder;
	ResponseSpecification rspec;

	@BeforeClass
	public void requestSpec() {

		RestAssured.baseURI = "https://rp.epam.com";
		RestAssured.basePath = "/api/v1/" + projectName;

		RequestSpecification request;
		builder.expectContentType(ContentType.JSON);
		builder.expectStatusCode(200);

		rspec = builder.build();
	}

	@Test
	public void testStatusCodeRestAssured() {

		 given()
		.header("Authorization", auth)
		.param("page.page", "1")
		.param("page.size", "50")
		.when()
		.get("/launch")
		.then();
	}
}

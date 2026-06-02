package base;

import backend.constants.HttpStatus;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;

import static org.hamcrest.Matchers.*;

public class BaseTest {
    protected RequestSpecification requestSpec;
    protected ResponseSpecification responseSpec;

    @BeforeClass
    public void setup() {
        String baseURI = "https://think-and-get-it-production.up.railway.app/api/v1";

        requestSpec = new RequestSpecBuilder()
                .setBaseUri(baseURI)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();

        responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(HttpStatus.OK.code())
                .expectBody("success", equalTo(true))
                .build();
    }
}

package spec;

import backend.configManager.ConfigManager;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.Matchers.equalTo;

public class SpecBuilder {

    public static RequestSpecification getRequestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(ConfigManager.get("baseUrl"))
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }

    public static ResponseSpecification getResponseSpec() {
        return new ResponseSpecBuilder()
                .expectBody("success", equalTo(true))
                .build();
    }

    public static RequestSpecification getMultipartSpec(){
        return new RequestSpecBuilder()
                .setBaseUri(ConfigManager.get("baseUrl"))
                .setContentType(ContentType.MULTIPART)
                .log(LogDetail.URI)
                .build();
}
}

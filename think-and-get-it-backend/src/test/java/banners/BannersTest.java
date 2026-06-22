package banners;

import backend.constants.HttpStatus;
import backend.constants.ResponseMessages;
import backend.constants.searchConstants.SearchTestData;
import backend.implementFlow.BannersFlow;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ResponseAssertions;
import static spec.SpecBuilder.getRequestSpec;

public class BannersTest {
    @Test
    public void getActiveBannersSuccessfully() {
        RequestSpecification spec = getRequestSpec();
        Response response = new BannersFlow().getActiveBanners(spec);
        ResponseAssertions.assertSuccess(response, HttpStatus.OK.code(), ResponseMessages.ME_SUCCESS);
        Assert.assertTrue(response.jsonPath().getList("data").size() > 0, ResponseMessages.BANNERS_NO_ACTIVE);
        System.out.println("Response Body: " + response.asString());
    }

    @Test
    public void getActiveBannersPassWithInvalidAuth() {
        RequestSpecification spec = getRequestSpec();
        spec.header("Authorization", SearchTestData.INVALID_TOKEN);

        Response response = new BannersFlow().getActiveBanners(spec);
        ResponseAssertions.assertSuccess(response, HttpStatus.OK.code(), ResponseMessages.ME_SUCCESS);
        System.out.println("Response Body: " + response.asString());
    }

}

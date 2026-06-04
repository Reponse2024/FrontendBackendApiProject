package categories;

import backend.constants.HttpStatus;
import backend.constants.ResponsePaths;
import backend.constants.categoriesConstants.CategoryResponseMessages;
import backend.implementFlow.CategoryFlow;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static spec.SpecBuilder.getRequestSpec;

public class GetCategoryBySlugTest {
    @Test
    public void getCategoryBySlug() {
        Response response = new CategoryFlow().getCategoryBySlug(getRequestSpec());

        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK.code());
        Assert.assertTrue(response.jsonPath().getBoolean(ResponsePaths.SUCCESS));
        Assert.assertEquals(response.jsonPath().getString(ResponsePaths.MESSAGE), CategoryResponseMessages.CATEGORY_MESSAGE);
    }

}

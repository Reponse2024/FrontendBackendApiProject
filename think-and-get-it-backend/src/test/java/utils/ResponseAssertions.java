package utils;

import backend.constants.HttpStatus;
import backend.constants.ResponsePaths;
import io.restassured.response.Response;
import org.testng.Assert;

public class ResponseAssertions {

    public static void assertSuccess(Response response, int expectedStatus, String expectedMessage) {
        Assert.assertEquals(response.getStatusCode(), expectedStatus);
        Assert.assertTrue(response.jsonPath().getBoolean(ResponsePaths.SUCCESS));
        Assert.assertEquals(response.jsonPath().getString(ResponsePaths.MESSAGE), expectedMessage);
    }

    public static void assertFailure(Response response, int expectedStatus, String expectedMessage) {
        Assert.assertEquals(response.getStatusCode(), expectedStatus);
        Assert.assertFalse(response.jsonPath().getBoolean(ResponsePaths.SUCCESS));
        Assert.assertEquals(response.jsonPath().getString(ResponsePaths.MESSAGE), expectedMessage);
    }
    //Assertions specific for the Cart
    public static void assertPresence(Response response, String path) {
        Assert.assertNotNull(response.jsonPath().getString(path));
        }
    }

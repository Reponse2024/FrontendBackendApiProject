package reviews;

import backend.constants.HttpStatus;
import backend.constants.ResponseMessages;
import backend.constants.reviewsConstants.ReviewPayload;
import backend.constants.reviewsConstants.ReviewsTestData;
import backend.implementFlow.ReviewFlow;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import spec.SpecBuilder;
import utils.ResponseAssertions;

public class ReviewTests {

    private final ReviewFlow reviewFlow = new ReviewFlow();

    @Test
    public void getReviewsSuccessfully() {
        Response response = reviewFlow.getReviews(SpecBuilder.getRequestSpec(), 1, "newest");
        ResponseAssertions.assertSuccess(response, HttpStatus.OK.code(), ResponseMessages.ME_SUCCESS);
    }
    @Test
    public void submitReviewSuccessfully() {
        ReviewPayload review = new ReviewPayload(
                ReviewsTestData.DEFAULT_RATING,
                ReviewsTestData.DEFAULT_TITLE,
                ReviewsTestData.DEFAULT_BODY
        );

        Response response = reviewFlow.submitReview(SpecBuilder.getRequestSpec(), review);
        ResponseAssertions.assertSuccess(response, HttpStatus.CREATED.code(), ResponseMessages.REVIEW_SUBMITTED);
    }
    @Test
    public void submitReviewFailsWithInvalidData() {
        ReviewPayload review = new ReviewPayload(0, "", "");
        Response response = reviewFlow.submitReview(SpecBuilder.getRequestSpec(), review);
        ResponseAssertions.assertFailure(response, HttpStatus.BAD_REQUEST.code(), ResponseMessages.RECORD_NOT_FOUND);
    }
}

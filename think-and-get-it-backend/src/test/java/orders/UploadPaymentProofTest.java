package orders;

import backend.constants.HttpStatus;
import backend.constants.ResponseMessages;
import backend.implementFlow.OrderFlow;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.ResponseAssertions;
import static spec.SpecBuilder.getMultipartSpec;
import static spec.SpecBuilder.getRequestSpec;

public class UploadPaymentProofTest {
    @Test
    public void uploadPaymentProofSuccessfully() {
        Response response = new OrderFlow().uploadPaymentProof(getMultipartSpec());
        ResponseAssertions.assertSuccess(response, HttpStatus.OK.code(), ResponseMessages.PAYMENT_PROOF_UPLOADED);
    }
    @Test
    public void uploadPaymentProofFailsWithoutFile() {
        Response response = new OrderFlow().uploadPaymentProofWithoutFile(getRequestSpec());
        ResponseAssertions.assertFailure(response, HttpStatus.BAD_REQUEST.code(), ResponseMessages.NO_FILE_UPLOADED);
    }

}

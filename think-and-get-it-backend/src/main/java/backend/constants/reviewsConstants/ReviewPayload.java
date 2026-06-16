package backend.constants.reviewsConstants;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewPayload {
    private int rating;
    private String title;
    private String body;
}

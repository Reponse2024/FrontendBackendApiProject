package backend.constants;

public enum HttpStatus {
    OK(200, "Request succeeded"),
    CREATED(201, "Resource created successfully"),
    BAD_REQUEST(400, "Invalid or expired token"),
    UNAUTHORIZED(401, "Not authenticated. Please log in."),
    FORBIDDEN(403, "You do not have permission for this action"),
    NOT_FOUND(404, "Resource not found"),
    INTERNAL_SERVER_ERROR(500, "Unexpected server error"),
    CONFLICT(409, "Conflict - resource already exists"),
    SERVICE_UNAVAILABLE(503, "Service temporarily unavailable");

    private final int code;
    private final String message;

    HttpStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }
    public int code() {
        return code;
    }
    public String message() {
        return message;
    }
}

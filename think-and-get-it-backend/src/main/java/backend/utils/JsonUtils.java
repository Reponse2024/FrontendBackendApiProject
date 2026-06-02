package backend.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static JsonNode parse(String json) {
        try {
            return mapper.readTree(json);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse JSON", e);
        }
    }
}

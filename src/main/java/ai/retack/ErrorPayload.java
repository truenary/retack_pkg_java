package ai.retack;

public class ErrorPayload {
    private final String title;
    private final String stackTrace;
    private final UserContext userContext;

    public ErrorPayload(String title, String stackTrace, UserContext userContext) {
        this.title = title;
        this.stackTrace = stackTrace;
        this.userContext = userContext;
    }

    public String toJson() {
        return "{" +
                "\"title\":\"" + escapeJson(title) + "\"," +
                "\"stack_trace\":\"" + escapeJson(stackTrace) + "\"," +
                "\"user_context\":{" +
                "\"userId\":\"" + escapeJson(userContext.getUserId()) + "\"," +
                "\"sessionId\":\"" + escapeJson(userContext.getSessionId()) + "\"" +
                "}" +
                "}";
    }

    private String escapeJson(String value) {
        if (value == null) {
            return "";
        }
        return value.replace("\"", "\\\"").replace("\n", "\\n").replace("\r", "\\r");
    }
}

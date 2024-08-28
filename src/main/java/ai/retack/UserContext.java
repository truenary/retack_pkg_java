package ai.retack;

public class UserContext {
    private final String userId;
    private final String sessionId;

    public UserContext(String userId, String sessionId) {
        this.userId = userId;
        this.sessionId = sessionId;
    }

    public String getUserId() {
        return userId;
    }

    public String getSessionId() {
        return sessionId;
    }
}

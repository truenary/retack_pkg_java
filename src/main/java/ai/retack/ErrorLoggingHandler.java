package ai.retack;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ErrorLoggingHandler extends Handler {
    private final String envKey;

    public ErrorLoggingHandler(String envKey) {
        this.envKey = envKey;
    }

    @Override
    public void publish(LogRecord record) {
        if (record.getLevel().intValue() >= Level.SEVERE.intValue() && record.getThrown() != null) {
            String errorTitle = record.getMessage();
            Throwable throwable = record.getThrown();
            UserContext userContext = new UserContext("defaultUser", "defaultSession");

            String stackTrace = getStackTraceAsString(throwable);
            ErrorPayload payload = new ErrorPayload(errorTitle, stackTrace, userContext);
            sendError(payload);
        }
    }

    private String getStackTraceAsString(Throwable throwable) {
        StringBuilder result = new StringBuilder();
        for (StackTraceElement element : throwable.getStackTrace()) {
            result.append(element.toString()).append("\n");
        }
        return result.toString();
    }

    private void sendError(ErrorPayload payload) {
        try {
            URL url = new URL("https://api.retack.ai/observe/error-log/");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("ENV-KEY", envKey);
            connection.setDoOutput(true);

            OutputStream os = connection.getOutputStream();
            os.write(payload.toJson().getBytes());
            os.flush();
            os.close();

            int responseCode = connection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Failed to send error: HTTP error code : " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void flush() {
        // No need to implement
    }

    @Override
    public void close() throws SecurityException {
        // No need to implement
    }
}

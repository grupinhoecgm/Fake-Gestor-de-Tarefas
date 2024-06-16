import java.time.LocalDateTime;
import com.fasterxml.jackson.databind.JsonNode;

public class Task {
    private String id;
    private String description;
    private String priority;
    private LocalDateTime completionDateTime;
    private String performanceState;
    private JsonNode payload;
    

    public Task(String id, String description, String priority, JsonNode payload) {
        this.id = id;
        this.description = description;
        this.priority = priority;
        this.performanceState = "Pending";
        this.payload = payload;
    }

    // Getters and Setters

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getPriority() {
        return priority;
    }

    public LocalDateTime getCompletionDateTime() {
        return completionDateTime;
    }

    public JsonNode getPayload () {
        return payload;
    }
    public void setCompletionDateTime(LocalDateTime completionDateTime) {
        this.completionDateTime = completionDateTime;
    }

    public String getPerformanceState() {
        return performanceState;
    }

    public void setPerformanceState(String performanceState) {
        this.performanceState = performanceState;
    }



    public void setPayload(JsonNode payload) {
        this.payload = payload;
    }
}
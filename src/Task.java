import java.time.LocalDateTime;

public class Task {
    private String id;
    private String description;
    private String priority;
    private LocalDateTime completionDateTime;
    private String performanceState;
    private String payload;

    public Task(String id, String description, String priority) {
        this.id = id;
        this.description = description;
        this.priority = priority;
        this.performanceState = "Pending";
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

    public void setCompletionDateTime(LocalDateTime completionDateTime) {
        this.completionDateTime = completionDateTime;
    }

    public String getPerformanceState() {
        return performanceState;
    }

    public void setPerformanceState(String performanceState) {
        this.performanceState = performanceState;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }
}
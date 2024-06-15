import java.util.ArrayList;
import java.util.List;

public class TaskCollection {
    private List<Task> tasks;

    public TaskCollection() {
        tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task getTaskById(String id) {
        for (Task task : tasks) {
            if (task.getId().equals(id)) {
                return task;
            }
        }
        return null;
    }

    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }
}

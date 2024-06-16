import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.*;

public class TaskManager {
    private Queue<Task> highPriorityTasks;
    private Stack<Task> lowPriorityTasks;
    private List<Task> completedTasks;
    private TaskCollection taskCollection;

    public TaskManager() {
        highPriorityTasks = new LinkedList<>();
        lowPriorityTasks = new Stack<>();
        completedTasks = new ArrayList<>();
        taskCollection = new TaskCollection();
    }

    public void addTask(Task task) {
        taskCollection.addTask(task);
        if (task.getPriority().equalsIgnoreCase("high")) {
            highPriorityTasks.add(task);
        } else {
            lowPriorityTasks.push(task);
        }
    }

    public Task processTask() {
        Task taskToProcess = null;
        if (!highPriorityTasks.isEmpty()) {
            taskToProcess = highPriorityTasks.poll();
        } else if (!lowPriorityTasks.isEmpty()) {
            taskToProcess = lowPriorityTasks.pop();
        }

        if (taskToProcess != null) {
            taskToProcess.setCompletionDateTime(LocalDateTime.now());
            taskToProcess.setPerformanceState("Success");
            completedTasks.add(taskToProcess);
        }

        return taskToProcess;
    }

    public void editTask(String id, String newState) {
        Task task = taskCollection.getTaskById(id);
        if (task != null) {
            task.setPerformanceState(newState);
            if (newState.equals("success") || newState.equals("insucess")) {
                completedTasks.add(task);
                if (task.getPriority().equalsIgnoreCase("high")) {
                    highPriorityTasks.remove(task);
                } else {
                    lowPriorityTasks.remove(task);
                }
            }
        }
    }

    public Task searchTaskById(String id) {
        return taskCollection.getTaskById(id);
    }

    public List<Task> listAllTasks() {
        return taskCollection.getAllTasks();
    }

    public List<Task> listPendingTasks() {
        List<Task> pendingTasks = new ArrayList<>();
        pendingTasks.addAll(highPriorityTasks);
        pendingTasks.addAll(lowPriorityTasks);
        return pendingTasks;
    }

    public List<Task> listCompletedTasks() {
        return new ArrayList<>(completedTasks);
    }

    public void generateReport(String filePath) {
        // Implementation to generate a report in a text file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                for (Task task : completedTasks) {
                    if ("Success".equals(task.getPerformanceState())) {
                        writer.write("Task ID: " + task.getId() + "\n");
                        writer.write("Description: " + task.getDescription() + "\n");
                        writer.write("Priority: " + task.getPriority() + "\n");
                        writer.write("Completion Date/Time: " + task.getCompletionDateTime() + "\n");
                        writer.write("Payload: " + task.getPayload() + "\n");
                        writer.write("Performance State: " + task.getPerformanceState() + "\n");
                        writer.write("\n");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public void saveState(String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(highPriorityTasks);
            oos.writeObject(lowPriorityTasks);
            oos.writeObject(completedTasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void loadState(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            highPriorityTasks = (Queue<Task>) ois.readObject();
            lowPriorityTasks = (Stack<Task>) ois.readObject();
            completedTasks = (List<Task>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
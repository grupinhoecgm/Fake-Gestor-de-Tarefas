import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Add Task");
            System.out.println("2. Process Task");
            System.out.println("3. Search Task by ID");
            System.out.println("4. List All Tasks");
            System.out.println("5. List Pending Tasks");
            System.out.println("6. List Completed Tasks");
            System.out.println("7. Generate Report");
            System.out.println("8. Save State");
            System.out.println("9. Load State");
            System.out.println("10. Edit Task");
            System.out.println("11. Exit");
            System.out.println("Escolha a opção:");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Enter description:");
                    String description = scanner.nextLine();
                    System.out.println("Enter priority (high/low):");
                    String priority = scanner.nextLine();
                    System.out.println("Enter task ID:");
                    String id = scanner.nextLine();
                    Task task = new Task(id, description, priority);
                    taskManager.addTask(task);
                    break;
                case 2:
                    Task processedTask = taskManager.processTask();
                    if (processedTask != null) {
                        System.out.println("Processed Task: " + processedTask.getId());
                    } else {
                        System.out.println("No tasks to process.");
                    }
                    break;
                case 3:
                    System.out.println("Enter Task ID:");
                    String taskId = scanner.nextLine();
                    Task searchedTask = taskManager.searchTaskById(taskId);
                    if (searchedTask != null) {
                        System.out.println("Found Task: " + searchedTask.getDescription());
                    } else {
                        System.out.println("Task not found.");
                    }
                    break;
                case 4:
                    System.out.println("All Tasks:");
                    for (Task t : taskManager.listAllTasks()) {
                        System.out.println(t.getDescription());
                    }
                    break;
                case 5:
                    System.out.println("Pending Tasks:");
                    for (Task t : taskManager.listPendingTasks()) {
                        System.out.println(t.getDescription());
                    }
                    break;
                case 6:
                    System.out.println("Completed Tasks:");
                    for (Task t : taskManager.listCompletedTasks()) {
                        System.out.println(t.getDescription());
                    }
                    break;
                case 7:
                    System.out.println("Enter file path for report:");
                    String reportPath = scanner.nextLine();
                    // Adicionar a extensão .txt se não estiver presente
                    if (!reportPath.endsWith(".txt")) {
                        reportPath += ".txt";
                    }
                    taskManager.generateReport(reportPath);
                    break;
                case 8:
                    System.out.println("Enter file path to save state:");
                    String savePath = scanner.nextLine();
                    taskManager.saveState(savePath);
                    break;
                case 9:
                    System.out.println("Enter file name to load state:");
                    String loadFileName = scanner.nextLine();
                    taskManager.loadState(loadFileName);
                    break;
                    case 10:
                    System.out.println("Enter Task ID to edit:");
                    String editTaskId = scanner.nextLine();
                    System.out.println("Enter new status (success/insucess):");
                    String newStatus = scanner.nextLine();
                    taskManager.editTask(editTaskId, newStatus);
                    break;
                case 11:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
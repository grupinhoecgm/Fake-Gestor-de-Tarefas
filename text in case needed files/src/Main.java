import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("");
            System.out.println("--------------- MENU ---------------");
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
            System.out.println("------------------------------------ \n");
            System.out.print("Escolha a opção: ");
            System.out.print("");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Inserir o ID da Tarefa
                    System.out.println("Enter task ID:");
                    String id = scanner.nextLine();
                    // Inserir a descrição da Tarefa
                    System.out.println("Enter description:");
                    String description = scanner.nextLine();
                    // Inserir a prioridade da Tarefa
                    String priority;
                    while (true) {
                        System.out.print("Enter Priority (high/low): ");
                        priority = scanner.nextLine().toLowerCase();
                        if (priority.equals("high") || priority.equals("low")) {
                            break;
                        } else {
                            System.out.println("Invalid priority. Please enter 'high' or 'low'.");
                        }
                    }
                    Task task = new Task(id, description, priority, null);
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
                    String reportFileName = scanner.nextLine();
                    // Caminho predefinido para a pasta do relatório
                    String reportDirectoryPath = "./ReportsFolder/";
                    // Adicionar a extensão .txt se não estiver presente
                    if (!reportFileName.endsWith(".txt")) {
                        reportFileName += ".txt";
                    }
                    String reportFullPath = reportDirectoryPath + reportFileName;
                    // Verificar e criar o diretório se necessário
                    File reportDirectory = new File(reportDirectoryPath);
                    if (!reportDirectory.exists()) {
                        if (!reportDirectory.mkdirs()) {
                            System.out.println("Failed to create directory: " + reportDirectoryPath);
                            break;
                        }
                    }
                    try {
                        taskManager.generateReport(reportFullPath);
                        System.out.println("Report generated successfully at: " + reportFullPath);
                    } catch (Exception e) {
                        System.out.println("Failed to generate report: " + e.getMessage());
                    }
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
import java.io.*;

public void saveState(String filePath) {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
        oos.writeObject(highPriorityTasks);
        oos.writeObject(lowPriorityTasks);
        oos.writeObject(completedTasks);
    } catch (IOException e) {
        e.printStackTrace();
    }
}

public void loadState(String filePath) {
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
        highPriorityTasks = (Queue<Task>) ois.readObject();
        lowPriorityTasks = (Stack<Task>) ois.readObject();
        completedTasks = (List<Task>) ois.readObject();
    } catch (IOException | ClassNotFoundException e) {
        e.printStackTrace();
    }
}
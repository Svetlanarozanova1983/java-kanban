import java.io.File;

public class Managers {

    private Managers() {
    }

    public static TaskManager getDefault() {
        return FileBackedTaskManager.loadFromFile(new File("file_data.db"));
    }

    public static HistoryManager getDefaultHistory() {
        return new InMemoryHistoryManager();
    }

}

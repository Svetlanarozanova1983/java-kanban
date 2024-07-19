import java.io.File;

public class Managers {

    private Managers() {

    }
    private final static String PATH_TO_FILE = "/Users/svetlana/work/IdeaProjects/java-kanban/file_data.db";

    public static TaskManager getDefault() {
        return FileBackedTaskManager.loadFromFile(new File(PATH_TO_FILE));
    }

    public static HistoryManager getDefaultHistory() {
        return new InMemoryHistoryManager();
    }

}

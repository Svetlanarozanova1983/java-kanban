import java.util.*;

public class InMemoryHistoryManager implements HistoryManager {

    private static final int tenViewedTasks = 10;
    private final List<Task> historyList = new ArrayList<>();

    @Override
    public void add(Task task) {
        if (historyList.size() == tenViewedTasks) {
            historyList.removeFirst();
        }
        Task ht = new Task(task.getId(), task.getName(), task.getDescription(), task.getStatus());
        historyList.add(ht);
    }

    @Override
    public List<Task> getHistory() {
        return historyList;
    }
}

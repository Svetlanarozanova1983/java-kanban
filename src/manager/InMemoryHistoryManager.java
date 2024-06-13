import java.util.*;

public class InMemoryHistoryManager implements HistoryManager {

    private static final int QNT_VIEWED_TASKS = 10;
    private final LinkedList<Task> historyList = new LinkedList<>();

    @Override
    public void add(Task task) {
        if(task == null) {
            return;
        }
        if (historyList.size() == QNT_VIEWED_TASKS) {
            historyList.removeFirst();
        }
        //копируем объект для истории
        Task ht = new Task(task.getId(), task.getName(), task.getDescription(), task.getStatus());
        historyList.add(ht);
    }

    @Override
    public List<Task> getHistory() {
        List<Task> result = new ArrayList<Task>(historyList);
        return result;
    }
}

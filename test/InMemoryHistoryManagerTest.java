import org.junit.Test;
import java.util.List;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InMemoryHistoryManagerTest {


    @Test
    //задачи, добавляемые в HistoryManager, сохраняют предыдущую версию задачи и её данных
    public void tasksAddedToTheHistoryManagerRetainOfTheTask() {
        Task task = new Task("Сходить в магазин.", "Купить продукты.");
        TaskManager tm = new InMemoryTaskManager();
        Task taskCreated = tm.creationTask(task);
        List<Task> taskHistoryList = tm.getHistory();
        assertEquals(task, taskHistoryList.getFirst());
    }


    @Test
    //задачи, добавляемые в HistoryManager, сохраняют предыдущую версию задачи и её данных
    public void tasksAddedToTheHistoryManagerRetainThePreviousVersionOfTheTask() {
        Task task = new Task("Сходить в магазин.", "Купить продукты.");
        TaskManager tm = new InMemoryTaskManager();
        Task taskCreated = tm.creationTask(task);

        taskCreated.setName("Сходить в магазин 2.");
        tm.updateTask(taskCreated);
        List<Task> taskHistoryList = tm.getHistory();
        assertEquals(taskHistoryList.getLast(), taskHistoryList.getFirst());
    }


    @Test
    //Удаляемые подзадачи не должны хранить внутри себя старые id
    public void tasksRemoveToTheHistoryManagerRetainOfTheTask() {
        Task task = new Task("Сходить в магазин.", "Купить продукты.");
        TaskManager tm = new InMemoryTaskManager();
        Task taskCreated = tm.creationTask(task);
        tm.removeTaskById(taskCreated.getId());
        List<Task> taskHistoryList = tm.getHistory();
        assertTrue(taskHistoryList.isEmpty());
    }
}
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InMemoryHistoryManagerTest {

    @Test
    //задачи, добавляемые в HistoryManager, сохраняют предыдущую версию задачи и её данных
    public void TasksAddedToTheHistoryManagerRetainOfTheTask() {
        Task task = new Task("Сходить в магазин.", "Купить продукты.");
        TaskManager tm = new InMemoryTaskManager();
        Task taskCreated = tm.creationTask(task);
        List<Task> taskHistoryList = tm.getHistory();
        assertEquals(task, taskHistoryList.getFirst());
    }

    @Test
    //задачи, добавляемые в HistoryManager, сохраняют предыдущую версию задачи и её данных
    public void TasksAddedToTheHistoryManagerRetainThePreviousVersionOfTheTask() {
        Task task = new Task("Сходить в магазин.", "Купить продукты.");
        TaskManager tm = new InMemoryTaskManager();
        Task taskCreated = tm.creationTask(task);

        taskCreated.setName("Сходить в магазин 2.");
        tm.updateTask(taskCreated);
        List<Task> taskHistoryList = tm.getHistory();

        assertNotEquals(taskHistoryList.getLast(), taskHistoryList.getFirst());
    }
}
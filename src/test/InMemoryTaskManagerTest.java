import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InMemoryTaskManagerTest {


    @Test
    //InMemoryTaskManager действительно добавляет задачи разного типа и может найти их по id
    public void InMemoryTaskManagerAddTasksAndCanFindThemById() {
        Task task = new Task("Сходить в магазин.", "Купить продукты.");
        TaskManager tm = new InMemoryTaskManager();
        Task taskCreated = tm.creationTask(task);
        Task taskGetted = tm.getTaskById(taskCreated.getId());
        assertEquals(taskCreated, taskGetted);
    }


    @Test
    //InMemoryTaskManager действительно добавляет задачи разного типа и может найти их по id
    public void InMemoryTaskManagerAddSubtasksAndCanFindThemById() {
        Epic epic = new Epic("Отдохнуть на море.", "Каспийское море.");
        TaskManager tm = new InMemoryTaskManager();
        Epic epicCreated = tm.creationEpic(epic);
        Subtask subtask = new Subtask(epicCreated.getId(),"Оформить страховку", "РЕСО-Гарантия");
        Subtask subtaskCreated = tm.creationSubtask(subtask);
        Subtask subtaskGetted = tm.getSubtaskById(subtaskCreated.getId());
        assertEquals(subtaskCreated, subtaskGetted);
    }


    @Test
    //InMemoryTaskManager действительно добавляет задачи разного типа и может найти их по id
    public void InMemoryTaskManagerAddEpicsAndCanFindThemById() {
        Epic epic = new Epic("Отдохнуть на море.", "Каспийское море.");
        TaskManager tm = new InMemoryTaskManager();
        Epic epicCreated = tm.creationEpic(epic);
        Epic epicGetted = tm.getEpicById(epicCreated.getId());
        assertEquals(epicCreated, epicGetted);
    }


    @Test
    //проверяется неизменность задачи (по всем полям) при добавлении задачи в менеджер
    public void ImmutabilityOfTheTaskInAllFieldsWhenAddingTheTaskToTheManager() {
        Task task = new Task("Сходить в магазин.", "Купить продукты.");
        TaskManager tm = new InMemoryTaskManager();
        Task taskCreated = tm.creationTask(task);
        Task taskGetted = tm.getTaskById(taskCreated.getId());

        assertEquals(task.getName(), taskGetted.getName());
        assertEquals(task.getDescription(), taskGetted.getDescription());
        assertEquals(task.getStatus(), taskGetted.getStatus());
    }


    @Test
    //проверяется неизменность епика (по всем полям) при добавлении епика в менеджер
    public void ImmutabilityOfTheEpicInAllFieldsWhenAddingTheEpicToTheManager() {
        Epic epic = new Epic("Отдохнуть на море.", "Каспийское море.");
        TaskManager tm = new InMemoryTaskManager();
        Epic epicCreated = tm.creationEpic(epic);
        Epic epicGetted = tm.getEpicById(epicCreated.getId());

        assertEquals(epic.getName(), epicGetted.getName());
        assertEquals(epic.getDescription(), epicGetted.getDescription());
        assertEquals(epic.getStatus(), epicGetted.getStatus());
    }

    @Test
    //Внутри эпиков не должно оставаться неактуальных id подзадач
    public void ThereShouldBeNoIrrelevantSubtaskIDsInsideTheEpics() {
        Epic epic = new Epic("Отдохнуть на море.", "Каспийское море.");
        TaskManager tm = new InMemoryTaskManager();
        Epic epicCreated = tm.creationEpic(epic);
        Subtask subtask = new Subtask(epicCreated.getId(),"Оформить страховку", "РЕСО-Гарантия");
        Subtask subtaskCreated = tm.creationSubtask(subtask);
        tm.removeSubtaskById(subtaskCreated.getId());
        assertTrue(epicCreated.getSubtaskIds().isEmpty());
    }
}
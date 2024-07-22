import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class TaskManagerTest< T extends TaskManager > {

    public T tm;


    @Test
    //InMemoryTaskManager действительно добавляет задачи разного типа и может найти их по id
    public void inMemoryTaskManagerAddTasksAndCanFindThemById() {
        Task task = new Task("Сходить в магазин.", "Купить продукты.");

        Task taskCreated = tm.creationTask(task);
        Task taskGetted = tm.getTaskById(taskCreated.getId());
        assertEquals(taskCreated, taskGetted);
    }


    @Test
    //InMemoryTaskManager действительно добавляет задачи разного типа и может найти их по id
    public void inMemoryTaskManagerAddSubtasksAndCanFindThemById() {
        Epic epic = new Epic("Отдохнуть на море.", "Каспийское море.");

        Epic epicCreated = tm.creationEpic(epic);
        Subtask subtask = new Subtask(epicCreated.getId(),"Оформить страховку", "РЕСО-Гарантия");
        Subtask subtaskCreated = tm.creationSubtask(subtask);
        Subtask subtaskGetted = tm.getSubtaskById(subtaskCreated.getId());
        assertEquals(subtaskCreated, subtaskGetted);
    }


    @Test
    //InMemoryTaskManager действительно добавляет задачи разного типа и может найти их по id
    public void inMemoryTaskManagerAddEpicsAndCanFindThemById() {
        Epic epic = new Epic("Отдохнуть на море.", "Каспийское море.");

        Epic epicCreated = tm.creationEpic(epic);
        Epic epicGetted = tm.getEpicById(epicCreated.getId());
        assertEquals(epicCreated, epicGetted);
    }


    @Test
    //проверяется неизменность задачи (по всем полям) при добавлении задачи в менеджер
    public void immutabilityOfTheTaskInAllFieldsWhenAddingTheTaskToTheManager() {
        Task task = new Task("Сходить в магазин.", "Купить продукты.");

        Task taskCreated = tm.creationTask(task);
        Task taskGetted = tm.getTaskById(taskCreated.getId());

        assertEquals(task.getName(), taskGetted.getName());
        assertEquals(task.getDescription(), taskGetted.getDescription());
        assertEquals(task.getStatus(), taskGetted.getStatus());
    }


    @Test
    //проверяется неизменность епика (по всем полям) при добавлении епика в менеджер
    public void immutabilityOfTheEpicInAllFieldsWhenAddingTheEpicToTheManager() {
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
    public void thereShouldBeNoIrrelevantSubtaskIDsInsideTheEpics() {
        Epic epic = new Epic("Отдохнуть на море.", "Каспийское море.");
        TaskManager tm = new InMemoryTaskManager();
        Epic epicCreated = tm.creationEpic(epic);
        Subtask subtask = new Subtask(epicCreated.getId(),"Оформить страховку", "РЕСО-Гарантия");
        Subtask subtaskCreated = tm.creationSubtask(subtask);
        tm.removeSubtaskById(subtaskCreated.getId());
        assertTrue(epicCreated.getSubtaskIds().isEmpty());
    }
}

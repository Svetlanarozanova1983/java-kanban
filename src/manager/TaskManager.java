import java.util.Collection;
import java.util.List;

public interface TaskManager {

    int getNextId();


    Task creationTask(Task input);
    Subtask creationSubtask(Subtask input);
    Epic creationEpic(Epic input);


    Task getTaskById(Integer id);
    Subtask getSubtaskById(Integer id);
    Epic getEpicById(Integer id);


    Task updateTask(Task input);
    Subtask updateSubtask(Subtask input);
    Epic updateEpic(Epic input);


    Collection<Task> getTasks();
    Collection<Subtask> getSubtasks();
    Collection<Epic> getEpics();
    Collection<Subtask> getSubtasksByEpicId(Integer id);


    boolean removeTaskById(Integer id);
    boolean removeSubtaskById(Integer id);
    boolean removeEpicById(Integer id);


    void clearTasks();
    void clearSubtasks();
    void clearEpics();


    List<Task> getHistory();

    void joinSubtaskToEpic(Integer subtaskId, Epic epic);
}

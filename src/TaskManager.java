import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class TaskManager {



    private final HashMap<Integer, Task> tasks = new HashMap<>();
    private final HashMap<Integer, Subtask> subtasks = new HashMap<>();
    private final HashMap<Integer, Epic> epics = new HashMap<>();



    //Получение списка всех задач
    public Collection<Task> getTasks() {
        Collection<Task> result =  tasks.values();
        return result;
    }
    public Collection<Subtask> getSubtasks() {
        Collection<Subtask> result =  subtasks.values();
        return result;
    }
    public Collection<Epic> getEpics() {
        Collection<Epic> result =  epics.values();
        return result;
    }



    //Удаление всех задач
    public void clearTasks() {
        tasks.clear();
    }
    public void clearSubtasks() {
        subtasks.clear();
    }
    public void clearEpics() {
        epics.clear();
    }



    //Получение по идентификатору
    public Task getTaskById(Integer id) {
        Task result = tasks.get(id);
        return result;
    }
    public Subtask getTaskSubtaskId(Integer id) {
        Subtask result = subtasks.get(id);
        return result;
    }
    public Epic getTaskEpicId(Integer id) {
        Epic result = epics.get(id);
        return result;
    }



    //Создание. Сам объект должен передаваться в качестве параметра
    public void creationTask(Task input) {
        tasks.put(input.id, input);
    }
    public void creationSubtask(Subtask input) {
        subtasks.put(input.id, input);
    }
    public void creationEpic(Epic input) {
        epics.put(input.id, input);
    }



    //Обновление. Новая версия объекта с верным идентификатором передаётся в виде параметра
    public void updateTask(Task input) {
        tasks.put(input.id, input);
    }

    public void updateSubtask(Subtask input) {
        subtasks.put(input.id, input);
    }
    public void updateEpic(Epic input) {
        epics.put(input.id, input);
    }



    //Удаление по идентификатору
    public void removeTaskById(Integer id) {
        tasks.remove(id);
    }
    public void removeSubtaskById(Integer id) {
        subtasks.remove(id);
    }
    public void removeEpicById(Integer id) {
        epics.remove(id);
    }



    //Получение списка всех подзадач определённого эпика.
    public Collection<Subtask> getSubtasksByEpicId(Integer id) {
        Collection<Subtask> subtaskCollection =  subtasks.values();

        ArrayList<Subtask> result = new ArrayList<>();
        for(Subtask subtask : subtaskCollection){
            if(id == subtask.epicId) {
                result.add(subtask);
            }
        }

        return result;
    }
}



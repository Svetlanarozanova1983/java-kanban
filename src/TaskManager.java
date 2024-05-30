import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class TaskManager {

private int nextId=1;
private int getNextId() {
    return nextId++;
}

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
    public Subtask getSubtaskById(Integer id) {
        Subtask result = subtasks.get(id);
        return result;
    }
    public Epic getEpicById(Integer id) {
        Epic result = epics.get(id);
        return result;
    }


    //Создание. Сам объект должен передаваться в качестве параметра
    public Task creationTask(Task input) {
        Integer newId = getNextId();
        input.setId(newId);
        tasks.put(input.getId(), input);
        return input;
    }
    public Subtask creationSubtask(Subtask input) {
        Integer newId = getNextId();
        input.setId(newId);
        subtasks.put(input.getId(), input);
        return input;
    }
    public Epic creationEpic(Epic input) {
        Integer newId = getNextId();
        input.setId(newId);
        epics.put(input.getId(), input);
        return input;
    }



    //Обновление. Новая версия объекта с верным идентификатором передаётся в виде параметра
    public Task updateTask(Task input) {
        tasks.put(input.getId(), input);
        return input;
    }
    public Subtask updateSubtask(Subtask input) {
        subtasks.put(input.getId(), input);
        return input;
    }
    public Epic updateEpic(Epic input) {
        epics.put(input.getId(), input);
        return input;
    }



    //Удаление по идентификатору
    public boolean removeTaskById(Integer id) {
        return tasks.remove(id) != null;
    }
    public boolean removeSubtaskById(Integer id) {
        return subtasks.remove(id) != null;
    }
    public boolean removeEpicById(Integer id) {
        Collection<Subtask> subtaskCollection = getSubtasksByEpicId(id);

        for(Subtask subtask : subtaskCollection) {
            removeSubtaskById(subtask.getId());
        }
        return epics.remove(id) != null;
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

    public void changeSubtaskStatus(Integer id, Status status) {
        Subtask subtask = getSubtaskById(id);
        Epic epic = getEpicById(subtask.getEpicID());
        subtask.setStatus(status);
        updateSubtask(subtask);
        if(status == Status.IN_PROGRESS && epic.getStatus() != status) {
            epic.setStatus(status);
            updateEpic(epic);
        }
        if(status == Status.DONE && allSubtasksByEpicIdIsDone(subtask.getEpicID())) {
            epic.setStatus(status);
            updateEpic(epic);
        }
    }

    private boolean allSubtasksByEpicIdIsDone(int epicID) {
        Collection<Subtask> subtaskCollection = getSubtasksByEpicId(epicID);
        for(Subtask subtask : subtaskCollection) {
            if(subtask.getStatus() != Status.DONE) {
                return false;
            }
        }
         return true;
    }

    public void printTasks() {
        Collection<Task> allTasks = tasks.values();
        if(allTasks.isEmpty()) {
            System.out.println("Список задач пуст.");
            return;
        }
        for(Task task : allTasks) {
            System.out.println(task);
        }
    }

    public void printSubtasks() {
        Collection<Subtask> allSubtasks = subtasks.values();
        if(allSubtasks.isEmpty()) {
            System.out.println("Список подзадач пуст.");
            return;
        }
        for(Subtask subtask : allSubtasks) {
            System.out.println(subtask);
        }
    }

    public void printEpics() {
        Collection<Epic> allEpics = epics.values();
        if(allEpics.isEmpty()) {
            System.out.println("Список эпиков пуст.");
            return;
        }
        for(Epic epic : allEpics) {
            System.out.println(epic);
        }
    }



    public Status getStatusEpic(Integer id) {
        Collection<Subtask> subtaskCollection = getSubtasksByEpicId(id);
        Status result = Status.NEW;
        for(Subtask subtask : subtaskCollection) {
            if(subtask.getStatus() == Status.IN_PROGRESS) {
                result = Status.IN_PROGRESS;
                break;
            }
        }
        if(result == Status.NEW && allSubtasksByEpicIdIsDone(id)){
            result = Status.DONE;
        }

            return result;
    }
}



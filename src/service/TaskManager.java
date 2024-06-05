import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

    public class TaskManager {

    private int nextId=1;

    private final HashMap<Integer, Task> tasks = new HashMap<>();
    private final HashMap<Integer, Subtask> subtasks = new HashMap<>();
    private final HashMap<Integer, Epic> epics = new HashMap<>();



    //Получение списка всех задач
    public Collection<Task> getTasks() {
        Collection<Task> result =  tasks.values();
        return result;
    }
    //Получение списка всех подзадач
    public Collection<Subtask> getSubtasks() {
        Collection<Subtask> result =  subtasks.values();
        return result;
    }
    //Получение списка всех эпиков
    public Collection<Epic> getEpics() {
        Collection<Epic> result =  epics.values();
        return result;
    }
    //Получение списка всех подзадач определённого эпика.
    public Collection<Subtask> getSubtasksByEpicId(Integer id) {
        Epic currentEpic = getEpicById(id);
        ArrayList<Subtask> result = new ArrayList<>();
        for(Integer subtaskId : currentEpic.getSubtaskIds()){
            result.add(subtasks.get(subtaskId));
        }
        return result;
    }



    //Удаление всех задач
    public void clearTasks() {
        tasks.clear();
    }
    //Удаление всех подзадач
    public void clearSubtasks() {
        for(Epic epic : epics.values()) {
            epic.clearSubtaskIds();
            epic.setStatus(Status.NEW);
            //Integer epicId = subtask.getEpicID();
            //Epic epic = getEpicById(epicId);
            //epic.removeSubtaskId(subtask.getId());
            //reCalcAndSaveEpicStatus(epicId);
        }
            subtasks.clear();
    }
    //Удаление всех эпиков
    public void clearEpics() {
        subtasks.clear();
        epics.clear();
    }



    //Получение задач по идентификатору
    public Task getTaskById(Integer id) {
        Task result = tasks.get(id);
        return result;
    }
    //Получение подзадач по идентификатору
    public Subtask getSubtaskById(Integer id) {
        Subtask result = subtasks.get(id);
        return result;
    }
    //Получение эпиков по идентификатору
    public Epic getEpicById(Integer id) {
        Epic result = epics.get(id);
        return result;
    }



    //Создание задачи
    public Task creationTask(Task input) {
        Integer newId = getNextId();
        input.setId(newId);
        tasks.put(input.getId(), input);
        return input;
    }
    //Создание подзадачи
    //Перед созданием подзадачи, необходимо проверить, существует ли её эпик
    public Subtask creationSubtask(Subtask input) {
        Integer epicId = input.getEpicID();
        Epic epic = getEpicById(epicId);
        if(epic == null) {
            return null;
        }
        Integer newId = getNextId();
        input.setId(newId);
        subtasks.put(input.getId(), input);
        epic.addSubtaskId(input.getId());
        reCalcAndSaveEpicStatus(epicId);
        return input;
    }
    //Создание эпика
    public Epic creationEpic(Epic input) {
        Integer newId = getNextId();
        input.setId(newId);
        epics.put(input.getId(), input);
        return input;
    }



    //Обновление задачи. Новая версия объекта с верным идентификатором передаётся в виде параметра
    public Task updateTask(Task input) {
        Task existedTask = getTaskById(input.getId());
        if(existedTask == null) {
            return null;
        }
        tasks.put(input.getId(), input);
        return input;
    }
    //Обновление подзадачи. Новая версия объекта с верным идентификатором передаётся в виде параметра
    public Subtask updateSubtask(Subtask input) {
        Subtask existedSubtask = getSubtaskById(input.getId());
        if(existedSubtask == null) {
            return null;
        }
        Integer epicId = input.getEpicID();
        //reCalcAndSaveEpicStatus(epicId);
        subtasks.put(input.getId(), input);
        reCalcAndSaveEpicStatus(epicId);
        return input;
    }
    //Обновление эпика. Новая версия объекта с верным идентификатором передаётся в виде параметра
    public Epic updateEpic(Epic input) {
        Epic existedEpic = epics.get(input.getId());
        if(existedEpic == null) {
            return null;
        }
        existedEpic.setName(input.getName());
        existedEpic.setDescription(input.getDescription());
        return existedEpic;
    }



    //Удаление задачи по идентификатору
    public boolean removeTaskById(Integer id) {
        return tasks.remove(id) != null;
    }
    //Удаление подзадачи по идентификатору
    public boolean removeSubtaskById(Integer id) {
        // получить подзадачу по ид
        Subtask subtask = getSubtaskById(id);
        // из нее получаем ид эпика
        Integer epicId = subtask.getEpicID();
        Epic epic = getEpicById(epicId);
        epic.removeSubtaskId(id);
        boolean result = subtasks.remove(id) != null;
        reCalcAndSaveEpicStatus(epicId);
        return result;
    }
    //Удаление эпика по идентификатору
    public boolean removeEpicById(Integer id) {
        Collection<Subtask> subtaskCollection = getSubtasksByEpicId(id);
        for(Subtask subtask : subtaskCollection) {
            subtasks.remove(subtask.getId());
        }
        return epics.remove(id) != null;
    }



    //Получение статуса эпика
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



    //Генератор Id
    private int getNextId() {
        return nextId++;
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



    private void reCalcAndSaveEpicStatus(Integer epicId) {
        Epic epic = getEpicById(epicId);
        Status statusEpic = getStatusEpic(epicId);
        epic.setStatus(statusEpic);
    }

}



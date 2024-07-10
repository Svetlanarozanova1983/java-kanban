import java.util.*;

public class InMemoryTaskManager implements TaskManager {


    private final Map<Integer, Task> tasks = new HashMap<>();
    private final Map<Integer, Subtask> subtasks = new HashMap<>();
    private final Map<Integer, Epic> epics = new HashMap<>();
    private final HistoryManager historyManager = Managers.getDefaultHistory();

    private int nextId=1;

    //Генератор Id
    public int getNextId() {
        return nextId++;
    }


    //Получение списка всех задач
    @Override
    public Collection<Task> getTasks() {
        Collection<Task> result =  tasks.values();
        return result;
    }
    //Получение списка всех подзадач
    @Override
    public Collection<Subtask> getSubtasks() {
        Collection<Subtask> result =  subtasks.values();
        return result;
    }
    //Получение списка всех эпиков
    @Override
    public Collection<Epic> getEpics() {
        Collection<Epic> result =  epics.values();
        return result;
    }
    //Получение списка всех подзадач определённого эпика
    @Override
    public Collection<Subtask> getSubtasksByEpicId(Integer id) {
        Epic currentEpic = getEpicById(id);
        ArrayList<Subtask> result = new ArrayList<>();
        for(Integer subtaskId : currentEpic.getSubtaskIds()) {
            result.add(subtasks.get(subtaskId));
        }
        return result;
    }


    //Удаление всех задач
    @Override
    public void clearTasks() {
        tasks.clear();
    }
    //Удаление всех подзадач
    @Override
    public void clearSubtasks() {
        for(Epic epic : epics.values()) {
            epic.clearSubtaskIds();
            epic.setStatus(Status.NEW);
        }
            subtasks.clear();
    }
    //Удаление всех эпиков
    @Override
    public void clearEpics() {
        subtasks.clear();
        epics.clear();
    }


    //Получение задач по идентификатору
    @Override
    public Task getTaskById(Integer id) {
        Task result = tasks.get(id);
        historyManager.add(result);
        return result;
    }
    //Получение подзадач по идентификатору
    @Override
    public Subtask getSubtaskById(Integer id) {
        Subtask result = subtasks.get(id);
        historyManager.add(result);
        return result;
    }
    //Получение эпиков по идентификатору
    @Override
    public Epic getEpicById(Integer id) {
        Epic result = epics.get(id);
        historyManager.add(result);
        return result;
    }


    //Создание задачи
    @Override
    public Task creationTask(Task input) {
        Integer newId = getNextId();
        input.setId(newId);
        tasks.put(input.getId(), input);
        historyManager.add(input);
        return input;
    }
    //Создание подзадачи
    //Перед созданием подзадачи, необходимо проверить, существует ли её эпик
    @Override
    public Subtask creationSubtask(Subtask input) {
        Integer epicId = input.getEpicID();
        Epic epic = getEpicById(epicId);
        if(epic == null) {
            return null;
        }
        Integer newId = getNextId();
        input.setId(newId);
        subtasks.put(input.getId(), input);
        historyManager.add(input);
        epic.addSubtaskId(input.getId());
        reCalcAndSaveEpicStatus(epicId);
        return input;
    }
    //Создание эпика
    @Override
    public Epic creationEpic(Epic input) {
        Integer newId = getNextId();
        input.setId(newId);
        epics.put(input.getId(), input);
        historyManager.add(input);
        return input;
    }


    //Обновление задачи. Новая версия объекта с верным идентификатором передаётся в виде параметра
    @Override
    public Task updateTask(Task input) {
        Task existedTask = getTaskById(input.getId());
        if(existedTask == null) {
            return null;
        }
        tasks.put(input.getId(), input);
        //historyManager.add(input);
        return input;
    }
    //Обновление подзадачи. Новая версия объекта с верным идентификатором передаётся в виде параметра
    @Override
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
    @Override
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
    @Override
    public boolean removeTaskById(Integer id) {
        historyManager.remove(id);
        return tasks.remove(id) != null;
    }
    //Удаление подзадачи по идентификатору
    @Override
    public boolean removeSubtaskById(Integer id) {
        // получить подзадачу по ид
        Subtask subtask = getSubtaskById(id);
        // из нее получаем ид эпика
        Integer epicId = subtask.getEpicID();
        Epic epic = getEpicById(epicId);
        epic.removeSubtaskId(id);
        boolean result = subtasks.remove(id) != null;
        reCalcAndSaveEpicStatus(epicId);
        historyManager.remove(id);
        return result;
    }
    //Удаление эпика по идентификатору
    @Override
    public boolean removeEpicById(Integer id) {
        Collection<Subtask> subtaskCollection = getSubtasksByEpicId(id);
        for(Subtask subtask : subtaskCollection) {
            subtasks.remove(subtask.getId());
        }
        historyManager.remove(id);
        return epics.remove(id) != null;
    }


    @Override
    public List<Task> getHistory() {
        return historyManager.getHistory();
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
        if(result == Status.NEW && allSubtasksByEpicIdIsDone(id)) {
            result = Status.DONE;
        }
        return result;
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



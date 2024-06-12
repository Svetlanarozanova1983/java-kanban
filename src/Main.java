import java.util.Collection;

public class Main {


    private static final InMemoryTaskManager inMemoryTaskManager = Managers.getDefault();

    public static void main(String[] args) {
        InMemoryTaskManager taskManager = new InMemoryTaskManager();
        addTasks();
        printAllTasks(taskManager);
    }

    private static void addTasks() {
        Task readTheBook = new Task("Прочитать книгу.", "Книга по Java.");
        inMemoryTaskManager.creationTask(readTheBook);
        Task readTheBookToUpdated = new Task(readTheBook.getId(), "Книга прочитана на 30%.",
                "Дальше нужна практика.", Status.IN_PROGRESS);
        inMemoryTaskManager.updateTask(readTheBookToUpdated);


        Task goToTheShop = new Task("Сходить в магазин.", "Купить продукты.");
        inMemoryTaskManager.creationTask(goToTheShop);
        Task goToTheShopToUpdated = new Task(goToTheShop.getId(), "Поход в магазин состоялся.",
                "Продукты куплены.", Status.DONE);
        inMemoryTaskManager.updateTask(goToTheShopToUpdated);


        Epic relaxByTheSea = new Epic("Отдохнуть на море.", "Каспийское море.");
        inMemoryTaskManager.creationEpic(relaxByTheSea);
        Subtask saveUpMoney = new Subtask(relaxByTheSea.getId(), "Накопить денег.", "Сто тысяч рублей.");
        Subtask buyATicket = new Subtask(relaxByTheSea.getId(),"Купить путевку.", "Баку, Азербайджан.");
        Subtask takeOutInsurance = new Subtask(relaxByTheSea.getId(),"Оформить страховку", "РЕСО-Гарантия");
        inMemoryTaskManager.creationSubtask(saveUpMoney);
        inMemoryTaskManager.creationSubtask(buyATicket);
        inMemoryTaskManager.creationSubtask(takeOutInsurance);
        saveUpMoney.setStatus(Status.DONE);
        inMemoryTaskManager.updateSubtask(saveUpMoney);
        buyATicket.setStatus(Status.IN_PROGRESS);
        inMemoryTaskManager.updateSubtask(buyATicket);
        takeOutInsurance.setStatus(Status.IN_PROGRESS);
        inMemoryTaskManager.updateSubtask(takeOutInsurance);
    }

    private static void printAllTasks(TaskManager manager) {
        System.out.println("Задачи:");
        for (Task task : manager.getTasks()) {
            System.out.println(task);
        }
        System.out.println("Эпики:");
        for (Task epic : manager.getEpics()) {
            System.out.println(epic);

            for (Task task : manager.getSubtasksByEpicId(epic.getId())) {
                System.out.println("--> " + task);
            }
        }
        System.out.println("Подзадачи:");
        for (Task subtask : manager.getSubtasks()) {
            System.out.println(subtask);
        }

        System.out.println("История:");
        for (Task task : manager.getHistory()) {
            System.out.println(task);
        }
    }
}

/*
        //Получение списка задач.
        printTasks(taskManager.getTasks());
        //Создание задачи.
        System.out.println("Создание задачи 1.");
        Task readTheBook = new Task("Прочитать книгу.","Книга по Java.");
        Task readTheBookCreation = taskManager.creationTask(readTheBook);
        System.out.println("Задаче 1 присвоен уникальный ID - " + ((readTheBookCreation.getId() != null) ? readTheBookCreation.getId() : "" ));
        System.out.println("Задача 1 создана - " + taskManager.getTaskById(readTheBookCreation.getId()));
        //Обновление задачи.
        System.out.println("Обновление задачи.");
        Task readTheBookToUpdate = new Task(readTheBookCreation.getId(), "Книга прочитана на 30%.", "Дальше нужна практика.", Status.IN_PROGRESS);
        Task readTheBookToUpdated = taskManager.updateTask(readTheBookToUpdate);
        System.out.println("Задача 1 обновлена - " + readTheBookToUpdated);
        //Удаление задачи.
        System.out.println("Удаление задачи.");
        boolean readTheBookToDelete = taskManager.removeTaskById(readTheBookToUpdate.getId());
        System.out.println("Задача 1 удалена.");
        System.out.println("\n");


        //Получение списка эпиков.
        printEpics(taskManager.getEpics());
        //Создание эпика.
        System.out.println("Создание эпика.");
        Epic relaxByTheSea = new Epic("Отдохнуть на море.", "Каспийское море.");
        Epic relaxByTheSeaCreation = taskManager.creationEpic(relaxByTheSea);
        System.out.println("Эпику 1 присвоен уникальный ID - " + ((relaxByTheSeaCreation.getId() != null) ? relaxByTheSeaCreation.getId() : "" ));
        System.out.println("Эпик 1 создан - " + taskManager.getEpicById(relaxByTheSeaCreation.getId()));
        //Получение списка всех подзадач определённого эпика.
        Collection<Subtask> subtasksByEpic = taskManager.getSubtasksByEpicId(relaxByTheSeaCreation.getId());
        System.out.println("Список подзадач пуст.");
        //Создание подзадачи 1.
        System.out.println("Создание подзадачи 1.");
        Subtask saveUpMoney = new Subtask(relaxByTheSea.getId(), "Накопить денег.","Сто тысяч рублей.");
        Subtask saveUpMoneyCreation = taskManager.creationSubtask(saveUpMoney);
        System.out.println("Подзадаче 1 присвоен уникальный ID - " + ((saveUpMoneyCreation.getId() != null) ? saveUpMoneyCreation.getId() : "" ));
        System.out.println("Подзадача 1 создана - " + taskManager.getSubtaskById(saveUpMoneyCreation.getId()));
        //Создание подзадачи 2.
        System.out.println("Создание подзадачи 2.");
        Subtask buyATicket = new Subtask(relaxByTheSea.getId(), "Купить путевку.","Баку, Азербайджан.");
        Subtask buyATicketCreation = taskManager.creationSubtask(buyATicket);
        System.out.println("Подзадаче 2 присвоен уникальный ID - " + ((buyATicketCreation.getId() != null) ? buyATicketCreation.getId() : "" ));
        System.out.println("Подзадача 2 создана - " + taskManager.getSubtaskById(buyATicketCreation.getId()));
        //Обновление подзадачи 1.
        System.out.println("Обновление подзадачи 1.");
        Subtask saveUpMoneyToUpdate = new Subtask(saveUpMoneyCreation.getId(), saveUpMoneyCreation.getEpicID(), "Деньги накоплены на 30%.", "Тридцать тысяч рублей.", Status.DONE);
        Subtask saveUpMoneyToUpdated = taskManager.updateSubtask(saveUpMoneyToUpdate);
        System.out.println("Подзадача 1 обновлена - " + saveUpMoneyToUpdated);
        //Обновление подзадачи 2.
        System.out.println("Обновление подзадачи 2.");
        Subtask buyATicketToUpdate = new Subtask(buyATicketCreation.getId(), buyATicketCreation.getEpicID(), "Путевка куплена.", "Дербент, Дагестан.", Status. IN_PROGRESS);
        Subtask buyATicketToUpdated = taskManager.updateSubtask(buyATicketToUpdate);
        System.out.println("Подзадача 2 обновлена - " + buyATicketToUpdated);
        //Обновление эпика.
        Epic relaxByTheSeaToUpdate = new Epic(relaxByTheSeaCreation.getId(), "Отдых на море.", "Дагестан.", taskManager.getStatusEpic(relaxByTheSeaCreation.getId()));
        Epic relaxByTheSeaToUpdated = taskManager.updateEpic(relaxByTheSeaToUpdate);
        System.out.println("Эпик 1 обновлен - " + relaxByTheSeaToUpdated);
        //Удаление подзадачи 1.
        System.out.println("Удаление подзадачи 1.");
        boolean saveUpMoneyToDelete = taskManager.removeSubtaskById(saveUpMoneyToUpdate.getId());
        System.out.println("Подзадача 1 удалена.");
        //Удаление подзадачи 2.
        System.out.println("Удаление подзадачи 2.");
        boolean buyATicketToDelete = taskManager.removeSubtaskById(buyATicketToUpdate.getId());
        System.out.println("Подзадача 2 удалена.");
        //Удаление эпика.
        boolean relaxByTheSeaToDelete = taskManager.removeEpicById(relaxByTheSeaToUpdated.getId());
        System.out.println("Эпик 1 удален.");
    }


    public static void printTasks(Collection<Task> allTasks) {
        if(allTasks.isEmpty()) {
            System.out.println("Список задач пуст.");
            return;
        }
        for(Task task : allTasks) {
            System.out.println(task);
        }
    }


    public static void printSubtasks(Collection<Subtask> allSubtasks) {
        if(allSubtasks.isEmpty()) {
            System.out.println("Список подзадач пуст.");
            return;
        }
        for(Subtask subtask : allSubtasks) {
            System.out.println(subtask);
        }
    }


    public static void printEpics(Collection<Epic> allEpics) {
        if(allEpics.isEmpty()) {
            System.out.println("Список эпиков пуст.");
            return;
        }
        for(Epic epic : allEpics) {
            System.out.println(epic);
        }
    }
}
*/
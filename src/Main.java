public class Main {

    public static void main(String[] args) {


        TaskManager taskManager = new TaskManager();

        //Получение списка задач.
        taskManager.printTasks();
        //Создание задачи.
        System.out.println("Создание задачи.");
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
        taskManager.printEpics();
        //Создание эпика.
        System.out.println("Создание эпика.");
        Epic relaxByTheSea = new Epic("Отдохнуть на море.", "Каспийское море.");
        Epic relaxByTheSeaCreation = taskManager.creationEpic(relaxByTheSea);
        System.out.println("Эпику 1 присвоен уникальный ID - " + ((relaxByTheSeaCreation.getId() != null) ? relaxByTheSeaCreation.getId() : "" ));
        System.out.println("Эпик 1 создан - " + taskManager.getEpicById(relaxByTheSeaCreation.getId()));
        //Получение списка всех подзадач определённого эпика.
        //taskManager.getSubtasksByEpicId();
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
        boolean saveUpMoneyToDelete = taskManager.removeSubtaskById(saveUpMoneyToUpdate.getId());
        System.out.println("Подзадача 1 удалена.");
        //Удаление эпика.
        boolean relaxByTheSeaToDelete = taskManager.removeEpicById(relaxByTheSeaToUpdate.getId());
        System.out.println("Эпик 1 удален.");
    }
}

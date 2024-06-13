public class Main {


    private static final TaskManager taskManager = Managers.getDefault();

    public static void main(String[] args) {
        addTasks();
        printAllTasks(taskManager);
    }

    private static void addTasks() {
        Task readTheBook = new Task("Прочитать книгу.", "Книга по Java.");
        taskManager.creationTask(readTheBook);
        Task readTheBookToUpdated = new Task(readTheBook.getId(), "Книга прочитана на 30%.",
                "Дальше нужна практика.", Status.IN_PROGRESS);
        taskManager.updateTask(readTheBookToUpdated);


        Task goToTheShop = new Task("Сходить в магазин.", "Купить продукты.");
        taskManager.creationTask(goToTheShop);
        Task goToTheShopToUpdated = new Task(goToTheShop.getId(), "Поход в магазин состоялся.",
                "Продукты куплены.", Status.DONE);
        taskManager.updateTask(goToTheShopToUpdated);


        Epic relaxByTheSea = new Epic("Отдохнуть на море.", "Каспийское море.");
        taskManager.creationEpic(relaxByTheSea);
        Subtask saveUpMoney = new Subtask(relaxByTheSea.getId(), "Накопить денег.", "Сто тысяч рублей.");
        Subtask buyATicket = new Subtask(relaxByTheSea.getId(),"Купить путевку.", "Баку, Азербайджан.");
        Subtask takeOutInsurance = new Subtask(relaxByTheSea.getId(),"Оформить страховку", "РЕСО-Гарантия");
        taskManager.creationSubtask(saveUpMoney);
        taskManager.creationSubtask(buyATicket);
        taskManager.creationSubtask(takeOutInsurance);
        saveUpMoney.setStatus(Status.DONE);
        taskManager.updateSubtask(saveUpMoney);
        buyATicket.setStatus(Status.IN_PROGRESS);
        taskManager.updateSubtask(buyATicket);
        takeOutInsurance.setStatus(Status.IN_PROGRESS);
        taskManager.updateSubtask(takeOutInsurance);
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

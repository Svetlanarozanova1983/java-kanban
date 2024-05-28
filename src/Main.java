public class Main {

    public static void main(String[] args) {


        TaskManager taskManager = new TaskManager();

//        Создайте две задачи, а также эпик с двумя подзадачами и эпик с одной подзадачей.
        taskManager.creationTask(new Task("Прочитать книгу.","Книга по Java"));
        taskManager.creationTask(new Task("Сходить в музей.","Музей Дарвина"));
        Epic RelaxByTheSea = new Epic("Отдохнуть на море.", "Каспийское море");
        taskManager.creationEpic(RelaxByTheSea);
        taskManager.creationSubtask(new Subtask(RelaxByTheSea.id, "Накопить денег.","Сто тысяч рублей"));
        taskManager.creationSubtask(new Subtask(RelaxByTheSea.id, "Купить путевку.","Баку, Азербайджан"));

//        Распечатайте списки эпиков, задач и подзадач через System.out.println(..).
//        Измените статусы созданных объектов, распечатайте их. Проверьте, что статус задачи и подзадачи сохранился, а статус эпика рассчитался по статусам подзадач.
//        И, наконец, попробуйте удалить одну из задач и один из эпиков.
//        Воспользуйтесь дебаггером среды разработки, чтобы понять логику работы программы и отладить её.
//                Не оставляйте в коде мусор — превращённые в комментарии или ненужные куски кода. Это сквозной проект, на его основе вы будете делать несколько следующих домашних заданий.
//                Давайте коммитам осмысленные комментарии: порядок в репозитории и коде — ключ к успеху написания хороших программ.

















/*

        Task GoToTheMuseum;
        Epic RelaxByTheSea;
        Subtask SaveUpMoney;
        Subtask BuyATicket;
        Task ReadTheBook;
        Epic BuildAHouse;
        Subtask BuyBuildingMaterials;

        System.out.println("Прочитать книгу.");
        System.out.println("Сходить в музей.");
        System.out.println("Отдохнуть на море.");
        System.out.println("Накопить денег.");
        System.out.println("Купить путевку.");
        System.out.println("Построить дом.");
        System.out.println("Купить стройматериалы.");
*/
    }
}

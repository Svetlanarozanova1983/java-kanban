import org.junit.Test;
import java.io.File;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileBackedTaskManagerTest extends TaskManagerTest<FileBackedTaskManager> {

    private final File saveTmpFile;

    public FileBackedTaskManagerTest() throws IOException {
        saveTmpFile = File.createTempFile("data", null);
        super.tm = FileBackedTaskManager.loadFromFile(saveTmpFile);
    }

    @Test
    public void saving_and_uploading_an_empty_file() throws IOException {
        File saveTmpFile = File.createTempFile("data", null);
        var sut = FileBackedTaskManager.loadFromFile(saveTmpFile);
        sut.save();
        saveTmpFile.deleteOnExit();
    }

    @Test
    public void saving_multiple_tasks() throws IOException {
        File saveTmpFile = File.createTempFile("data", null);
        var sut = FileBackedTaskManager.loadFromFile(saveTmpFile);
        Task task = new Task("Задача 1", "Описание 1");
        Task taskCreated = sut.creationTask(task);
        Epic epic = new Epic("Епик 1.", "Описание 1.");
        Epic epicCreated = sut.creationEpic(epic);
        Subtask subtask = new Subtask (epicCreated.getId(), "Подзадача 1", "Описание 1");
        Subtask subtaskCreated = sut.creationSubtask(subtask);
        saveTmpFile.deleteOnExit();
    }

    @Test
    public void loading_multiple_tasks() throws IOException {
        File saveTmpFile = File.createTempFile("data", null);
        var sut = new FileBackedTaskManager(saveTmpFile.getPath());
        Task task = new Task("Задача 1", "Описание 1");
        Task taskCreated = sut.creationTask(task);
        var sut2 = FileBackedTaskManager.loadFromFile(saveTmpFile);
        Task taskCreated2 = sut2.getTaskById(taskCreated.getId());
        saveTmpFile.deleteOnExit();
        assertEquals(taskCreated, taskCreated2);
    }
}

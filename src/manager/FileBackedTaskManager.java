import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileBackedTaskManager extends InMemoryTaskManager {

    public Path getPath() {
        return path;
    }

    private final Path path;

    public FileBackedTaskManager(Path path) throws IOException {
        if (!Files.exists(path)) {
            Files.createFile(path);
        }

        this.path = path;
    }

    public FileBackedTaskManager(String path) throws IOException {
        this(Path.of(path));
    }

    public static FileBackedTaskManager loadFromFile(File file) {

        try {

            FileBackedTaskManager fileBackedTaskManager = new FileBackedTaskManager(file.getPath());
            fileBackedTaskManager.load();
            return fileBackedTaskManager;

        } catch (IOException e) {
            throw ManagerSaveException.loadException(e);
        }
    }

    private void load() throws IOException {
        List<String> lines = Files.readAllLines(this.getPath(), Charset.defaultCharset());
        for (int i = 1; i < lines.size(); i++) {
            var fields = lines.get(i).split(",");

            Integer id = Integer.parseInt(fields[0]);
            Status st = Status.valueOf(fields[3]);
            String name = fields[2];
            String descr = fields[4];
            String type = fields[1];

            switch (type) {
                case "task":
                    this.creationTask(new Task(id, name, descr, st));
                    break;
                case "subtask":
                    int epicId = Integer.parseInt(fields[5]);
                    this.creationSubtask(new Subtask(id, epicId, name, descr, st));
                    break;
                case "epic":
                    this.creationEpic(new Epic(id, name, descr, st));
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + type);
            }
        }
    }

    // метод сохраняет текущее состояние менеджера в указанный файл
    public void save() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.getPath().toString()))) {
            bw.write(CSVFormat.getHeader());
            bw.newLine();
            for (Task t : getTasks()) {
                bw.write(t.toString());
                bw.newLine();
            }
            for (Epic e : getEpics()) {
                bw.write(e.toString());
                bw.newLine();
            }
            for (Subtask s : getSubtasks()) {
                bw.write(s.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            throw ManagerSaveException.saveException(e);
        }

    }

    @Override
    public Task creationTask(Task input) {
        var result = super.creationTask(input);
        save();
        return result;

    }

    @Override
    public Subtask creationSubtask(Subtask input) {
        var result = super.creationSubtask(input);
        save();
        return result;
    }

    @Override
    public Epic creationEpic(Epic input) {
        var result = super.creationEpic(input);
        save();
        return result;
    }

    @Override
    public Task updateTask(Task input) {
        var result = super.updateTask(input);
        save();
        return result;
    }

    @Override
    public Subtask updateSubtask(Subtask input) {
        var result = super.updateSubtask(input);
        save();
        return result;
    }

    @Override
    public Epic updateEpic(Epic input) {
        var result = super.updateEpic(input);
        save();
        return result;
    }

    @Override
    public boolean removeTaskById(Integer id) {
        var result = super.removeTaskById(id);
        save();
        return result;
    }

    @Override
    public boolean removeSubtaskById(Integer id) {
        var result = super.removeSubtaskById(id);
        save();
        return result;
    }

    @Override
    public boolean removeEpicById(Integer id) {
        var result = super.removeEpicById(id);
        save();
        return result;
    }

    @Override
    public void clearTasks() {
        super.clearTasks();
        save();
    }

    @Override
    public void clearSubtasks() {
        super.clearSubtasks();
        save();
    }

    @Override
    public void clearEpics() {
        super.clearEpics();
        save();
    }
}

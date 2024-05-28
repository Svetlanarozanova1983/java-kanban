public class Subtask extends Task {
    public Integer epicId;

    public Subtask(Integer epicId, String name, String description) {
        super(name, description);
        this.epicId = epicId;
    }
}

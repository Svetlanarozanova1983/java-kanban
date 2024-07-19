public class Subtask extends Task {

    private Integer epicId;

    public Subtask(Integer epicId, String name, String description) {
        super(name, description);
        this.epicId = epicId;
    }

    public Subtask(Integer id, Integer epicId, String name, String description,  Status  status) {
        super(id, name, description, status);
        this.epicId = epicId;
    }

    public Subtask(Integer id, String name, String description,  Status  status) {
        super(id, name, description, status);
    }

    public int getEpicID() {
        return epicId;
    }

    @Override
    public String toString() {
        return getId() + ",subtask," + getName() + "," + getStatus() + "," + getDescription() + ","+epicId;
    }
}

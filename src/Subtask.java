import java.util.Objects;

public class Subtask extends Task {
    public Integer epicId;

    public Subtask(Integer epicId, String name, String description) {
        super(name, description);
        this.epicId = epicId;
    }

    public Subtask(Integer id, Integer epicId, String name, String description,  Status  status) {
        super(id, name, description, status);
        this.epicId = epicId;
    }

    public int getEpicID() {
        return epicId;
    }

    public Subtask(Integer id, String name, String description,  Status  status) {
        super(id, name, description, status);
    }

    @Override
    public String toString() {
        return "Subtask{" +
                "name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", id=" + getId() +
                ", epicID=" + epicId +
                ", status=" + getStatus() +
                '}';
    }


}

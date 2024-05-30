import java.util.Objects;

public class Epic extends Task {

    public Epic(String name, String description) {
        super(name, description);
    }

    public Epic(Integer id, String name, String description,  Status  status) {
        super(id, name, description, status);
    }


    @Override
    public String toString() {
        return "Epic{" +
                "name= " + getName() + '\'' +
                ", description = " + getDescription() + '\'' +
                ", id=" + getId() +
                ", status = " + getStatus() +
                '}';
    }
}

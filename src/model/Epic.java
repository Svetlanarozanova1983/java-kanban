import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Epic extends Task {

    private final ArrayList<Integer> subtaskIds = new ArrayList<>();

    private final ArrayList<Subtask> subtasks = new ArrayList<>();

    public Epic(String name, String description) {
        super(name, description);
    }

    public Epic(Integer id, String name, String description,  Status  status) {
        super(id, name, description, status);
    }

    public void addSubtask(Subtask input) {
        subtasks.add(input);
        subtaskIds.add(input.getId());
    }

    public void removeSubtask(Subtask input) {
        subtasks.remove(input);
        subtaskIds.remove(input.getId());
    }

    public Collection<Integer> getSubtaskIds() {
        return subtaskIds;
    }

    public void clearSubtaskIds() {
        subtasks.clear();
        subtaskIds.clear();
    }

    // Время начала — дата старта самой ранней подзадачи
    @Override
    public LocalDateTime getStartTime() {
        final ZoneOffset zone = ZoneOffset.of("Z");
        var startTime = subtasks
                        .stream()
                        .filter(f -> f.getStartTime() != null)
                        .mapToLong(s ->  s.getStartTime().toEpochSecond(zone) )
                        .reduce(Long::min);
        if(!startTime.isEmpty()) {
            LocalDateTime result = LocalDateTime.ofEpochSecond(startTime.getAsLong(), 0, zone);
            return result;
        }
        return null;
    }

    //сумма длительности всех задач
    @Override
    public Duration getDuration() {
        long result = subtasks
                .stream()
                .filter(f -> f.getDuration() != null)
                .mapToLong(s -> s.getDuration().toMinutes())
                .sum();
        return Duration.ofMinutes(result);
    }

    // Время завершения — время окончания самой поздней из задач
    @Override
    public LocalDateTime getEndTime() {
        final ZoneOffset zone = ZoneOffset.of("Z");

        LocalDateTime result = LocalDateTime.ofEpochSecond(subtasks
                .stream()
                .mapToLong(s -> {
                    return s.getEndTime().toEpochSecond(zone);                })
                .reduce(Long::max).getAsLong(), 0, zone);
        return result;
    }

    @Override
    public String toString() {
        var dur = getDuration();
        var durStr = dur!= null ?  String.valueOf(dur.toMinutes()) : "";
        var start = getStartTime();
        var startStr = start!= null ?  String.valueOf(start) : "";
        return getId() + ",epic," + getName() + "," + getStatus() + "," + getDescription() + "," + startStr + "," + durStr + ",";
    }
}

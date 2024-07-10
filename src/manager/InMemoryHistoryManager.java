import java.util.*;

public class InMemoryHistoryManager implements HistoryManager {

    private Node<Task> first;
    private Node<Task> last;
    private Map<Integer, Node<Task>> nodeMap = new HashMap<>();


    @Override
    public void add(Task task) {
        if (task == null) {
            return;
        }
        //поиск элементов в хещмап
        var taskId = task.getId();
        Node<Task> result = nodeMap.get(taskId);
        //если нашли то удалить
        if (result != null) {
            removeNode(result);
        }
        Task ht = new Task(task.getId(), task.getName(), task.getDescription(), task.getStatus());

        var ne = linkLast(ht);
        //обновить хэшмап
        nodeMap.put(taskId, ne);
    }

    @Override
    public void remove(int id) {
        Node<Task> node = nodeMap.remove(id);
        if (node == null) {
            return;
        }
        removeNode(node);
    }

    @Override
    public List<Task> getHistory() {
        List<Task> result = getTasks();
        return result;
    }

    private Node<Task> linkLast(Task task) {

        var newElement = new Node<Task>(last, task, null);
        if (last != null) {
            last.next = newElement;
        } else {
            first = newElement;
        }
        if (first == null) {
            first = newElement;
        }
        last = newElement;
        return newElement;
    }

    private ArrayList<Task> getTasks() {
        ArrayList<Task> result = new  ArrayList<>();
        if (first == null) {
            return result;
        }
        result.add(first.value);
        Node<Task> currentElement = first;
        while (currentElement.next != null) {
            currentElement = currentElement.next;
            result.add(currentElement.value);
        }
        result.add(currentElement.value);

        return result;
    }

    private void removeNode(Node<Task> input) {
        if (input.prev == null) {
            first = input.next;
            if (input.next != null) {
                input.next.prev = null;
            }
        }

        if (input.next == null) {
            last = input.prev;
            if (input.prev != null) {
                input.prev.next = null;
            }
        }
        if (input.prev != null && input.next != null) {
            input.prev.next = input.next;
            input.next.prev = input.prev;
        }
    }

    private class Node<T> {
        Node<T> prev;
        Node<T> next;
        T value;

        public Node(Node<T> prev, T value, Node<T> next) {
            this.value = value;
            this.next = next;
            this.prev = prev;
        }
    }
}

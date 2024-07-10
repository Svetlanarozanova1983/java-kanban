import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    @Test
    //экземпляры класса Task равны друг другу, если равен их id
    public void taskInstancesAreEqualToEachOtherIfTheirIdIsEqual() {

        Task task1 = new Task("Прочитать книгу.", "Книга по Java.");
        Task task2 = new Task("Прочитать книгу.", "Книга по Java.");

        assertEquals(task1, task2);
    }
}
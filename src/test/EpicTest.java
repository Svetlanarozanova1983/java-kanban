import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EpicTest {

    @Test
    //экземпляры класса Epic равны друг другу, если равен их id
    public void epicInstancesAreEqualToEachOtherIfTheirIdIsEqual() {

        Epic epic1 = new Epic("Отдохнуть на море.", "Каспийское море.");
        Epic epic2 = new Epic("Отдохнуть на море.", "Каспийское море.");

        assertEquals(epic1, epic2);
    }
}
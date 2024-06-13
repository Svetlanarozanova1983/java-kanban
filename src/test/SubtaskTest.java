import org.junit.Test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SubtaskTest {

    @Test
    //экземпляры класса Subtask равны друг другу, если равен их id
    public void SubtaskInstancesAreEqualToEachOtherIfTheirIdIsEqual() {

        Subtask subtask1 = new Subtask(1,"Накопить денег.", "Сто тысяч рублей.");
        Subtask subtask2 = new Subtask(1,"Накопить денег.", "Сто тысяч рублей.");

        assertEquals(subtask1, subtask2);
    }

    @Test
    //наследники класса Task равны друг другу, если равен их id
    public void TaskHeirsAreEqualToEachOtherIfTheirIdIsEqual() {

        Subtask subtask1 = new Subtask(1, "Оформить страховку", "РЕСО-Гарантия");
        Subtask subtask2 = new Subtask(1, "Оформить страховку", "РЕСО-Гарантия");

        assertEquals(subtask1, subtask2);
    }
}
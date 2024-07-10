import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class ManagersTest {

    @Test
    //утилитарный класс всегда возвращает проинициализированные и готовые к работе экземпляры менеджеров
    public void utilityClassAlwaysReturnsInitializedInstancesOfManagers() {
        TaskManager tm = Managers.getDefault();
        assertInstanceOf(TaskManager.class, tm);
    }
}
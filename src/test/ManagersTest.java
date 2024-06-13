import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class ManagersTest {

    @Test
    //утилитарный класс всегда возвращает проинициализированные и готовые к работе экземпляры менеджеров
    public void UtilityClassAlwaysReturnsInitializedInstancesOfManagers() {
        TaskManager tm = Managers.getDefault();
        assertInstanceOf(TaskManager.class, tm);
    }
}
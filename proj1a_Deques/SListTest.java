import org.junit.Test;
import static org.junit.Assert.*;

public class SListTest {
    @Test
    public void test() {
        SList A = new SList();
        SList B = new SList();
        A.insertFront(1);
        assertEquals(-1,B.getFront());

    }
}

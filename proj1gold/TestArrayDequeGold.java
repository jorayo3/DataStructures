import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    @Test
    public void ArrayDequeTest() {
        /**@source StudentArrayDequeLauncher*/
        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> happy1 = new ArrayDequeSolution<>();
        String message = "";
        for (int i = 0; i < 500; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform();

            if (numberBetweenZeroAndOne < 0.25 && happy1.size() > 0) {
                assertEquals(message + "\nremoveLast()", happy1.removeLast(), sad1.removeLast());
                message = message + "\nremoveLast()";

            } else if (numberBetweenZeroAndOne < 0.50 && happy1.size() > 0) {
                assertEquals(message + "\nremoveFirst()", happy1.removeFirst(), sad1.removeFirst());
                message = message + "\nremoveFirst()";
            } else if (numberBetweenZeroAndOne < 0.75) {
                sad1.addFirst(i);
                happy1.addFirst(i);
                message = message + "\naddFirst(" + i + ")";
            } else {
                sad1.addLast(i);
                happy1.addLast(i);
                message = message + "\naddLast(" + i + ")";
            }

        }

        for (int i = 0; i < happy1.size(); i++) {
            double numberBetweenZeroAndOne = StdRandom.uniform();
            if (numberBetweenZeroAndOne < 0.5) {
                assertEquals(message + "\nremoveLast()", happy1.removeLast(), sad1.removeLast());
                message = message + "\nremoveLast()";
            } else {
                assertEquals(message + "\nremoveFirst()", happy1.removeFirst(), sad1.removeFirst());
                message = message + "\nremoveFirst()";
            }
        }
    }
}

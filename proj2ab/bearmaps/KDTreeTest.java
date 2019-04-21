package bearmaps;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class KDTreeTest {
    @Test
    public void test1() {
        Point p1 = new Point(2, 3); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(1, 5);
        Point p3 = new Point(4, 2);
        Point p4 = new Point(4, 5);
        Point p5 = new Point(3, 3);
        Point p6 = new Point(4, 4);

        NaivePointSet nn = new NaivePointSet(List.of(p1, p2, p3, p4, p5, p6));
        Point ret = nn.nearest(3.0, 4.0); // returns p2
        assertEquals(3.0, ret.getX(), 0.000001);
        assertEquals(3.0, ret.getY(), 0.000001);
    }

    @Test
    public void testDup() {
        Point p1 = new Point(3, 3);
        Point p2 = new Point(3, 3);
        KDTree tree = new KDTree(List.of(p1, p2));
    }

    @Test
    public void randomTest() {
        List<Point> list = new ArrayList();
        for (int i = 0; i < 1000; i++) {
            Point p = new Point(Math.random() * 1000, Math.random() * 1000);
            list.add(p);
        }
        KDTree tree = new KDTree(list);
        NaivePointSet nps = new NaivePointSet(list);
        double delta = 0.000001;
        for (int j = 0; j < 1000; j++) {
            double x = Math.random() * 1000;
            double y = Math.random() * 1000;
            Point q = nps.nearest(x, y);
            Point p = tree.nearest(x, y);
            assertEquals(q.getX(), p.getX(), delta);
            assertEquals(q.getY(), p.getY(), delta);
        }
    }

    @Test
    public void speedTest() {
        List<Point> list = new ArrayList();
        for (int i = 0; i < 100000; i++) {
            Point p = new Point(Math.random() * 1000, Math.random() * 1000);
            list.add(p);
        }
        KDTree tree = new KDTree(list);
        NaivePointSet nps = new NaivePointSet(list);

        List<Point> goals = new ArrayList();
        for (int j = 0; j < 10000; j++) {
            goals.add(new Point(Math.random() * 1000, Math.random() * 1000));
        }

        long npsStart = System.currentTimeMillis();
        for (Point i : goals) {
            nps.nearest(i.getX(), i.getY());
        }
        long npsEnd = System.currentTimeMillis();
        for (Point i : goals) {
            tree.nearest(i.getX(), i.getY());
        }
        long treeEnd = System.currentTimeMillis();

        System.out.println("Time elapsed for nps: " + (npsEnd - npsStart) / 1000.0 +  " seconds.");
        System.out.println("Time elapsed for tree: " + (treeEnd - npsEnd) / 1000.0 +  " seconds.");
    }
    public static void main(String... args) {
        jh61b.junit.TestRunner.runTests("all", KDTreeTest.class);
    }
}

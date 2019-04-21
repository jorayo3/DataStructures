package bearmaps;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayHeapMinPQTest {
    @Test
    public void test1() {
        ArrayHeapMinPQ<Integer> pq = new ArrayHeapMinPQ<>();
        assertFalse(pq.contains(3));
        assertEquals(0, pq.size());
        for (int i = 1; i < 16; i++) {
            pq.add(i, i/2);
            assertTrue(pq.contains(i));
            //assertEquals(i, pq.getMap(i));
        }
        PrintHeapDemo.printSimpleHeapDrawing(pq.getArrayList());
//        PrintHeapDemo.printFancyHeapDrawing(pq.getArrayList());
        assertEquals(1, (int) pq.getSmallest());
        assertEquals(15, pq.size());
        assertEquals(1, (int) pq.removeSmallest());
        PrintHeapDemo.printSimpleHeapDrawing(pq.getArrayList());
        //assertEquals(1, pq.getMap(2));
        pq.changePriority(2, 4);
        //PrintHeapDemo.printSimpleHeapDrawing(pq.getArrayList());g
        pq.changePriority(7, 1);
        //PrintHeapDemo.printSimpleHeapDrawing(pq.getArrayList());
    }

    @Test
    public void test2() {
        ArrayHeapMinPQ<Integer> pq = new ArrayHeapMinPQ<>();
        pq.removeSmallest();
        pq.add(4, 9);
        //PrintHeapDemo.printSimpleHeapDrawing(pq.getArrayList());
        pq.removeSmallest();
        //PrintHeapDemo.printSimpleHeapDrawing(pq.getArrayList());
    }
}

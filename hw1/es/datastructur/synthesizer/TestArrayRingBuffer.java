package es.datastructur.synthesizer;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        int capacity = 10;
        ArrayRingBuffer arb = new ArrayRingBuffer(capacity);
        for (int i = 0; i < capacity; i++) {
            arb.enqueue(i);
        }
        int[] array = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            array[i] = (int) arb.dequeue();
        }
        int[] answer = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        assertArrayEquals(array, answer);
    }

    @Test
    public void iteratorTest() {
        int capacity = 6;
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer(capacity);
        ArrayRingBuffer<Integer> brb = new ArrayRingBuffer(capacity);
        for (int i = 0; i < capacity; i++) {
            arb.enqueue(i);
        }
        for (int i : arb) {
            for (int j : arb) {
                System.out.print(i);
                System.out.println(j);
            }

        }
    }
}

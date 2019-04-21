package hw2;

import org.junit.Test;
import static org.junit.Assert.*;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class TestPercolation {
    @Test
    public void testPerc() {
        Percolation perc = new Percolation(5);
        assertFalse(perc.isOpen(3, 1));
        perc.open(3, 1);
        assertTrue(perc.isOpen(3, 1));
        perc.open(2, 1);
        perc.open(1, 1);
        perc.open(0, 1);
        assertTrue(perc.isFull(0, 1));
        perc.open(3, 2);
        assertTrue(perc.isFull(3, 2));
        assertFalse(perc.percolates());
        perc.open(4, 2);
        assertTrue(perc.percolates());
    }

    @Test
    public void testUnion() {
        WeightedQuickUnionUF union = new WeightedQuickUnionUF(5);
        union.union(0, 3);
        union.union(4, 3);
        assertTrue(union.connected(4, 0));
    }

    @Test
    public void testPercolationStats() {
        System.out.println(0 % 2);
        PercolationFactory pw = new PercolationFactory();
        PercolationStats perc = new PercolationStats(5, 100, pw);

    }
}

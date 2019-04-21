
import org.junit.Test;

import static org.junit.Assert.*;

public class TestUnionFind {
    @Test
    public void someTest() {
        UnionFind UF = new UnionFind(10);
        for (int i = 0; i < 10; i++) {
            assertEquals(1, UF.sizeOf(i));
            assertEquals(-1, UF.parent(i));
        }
    }

    @Test
    public void someTest2() {
        UnionFind UF = new UnionFind(10);
        assertEquals(0, UF.find(0));
        UF.union(0, 1);
        assertEquals(1, UF.parent(0));
        assertEquals(-2, UF.parent(1));
        assertEquals(1, UF.find(0));
        UF.union(2, 1);
    }

    @Test
    public void someTest3() {
        UnionFind UF = new UnionFind(10);
        assertFalse(UF.connected(2,0));
        UF.union(0,1);
        UF.union(0,2);
        UF.union(0,2);
        UF.union(2,1);

        for (int i = 0; i < 10; i++) {
            int a = (int)Math.floor(Math.random()*9);
            int b = (int)Math.floor(Math.random()*9);
            UF.union(a, b);
        }

    }
}

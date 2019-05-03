import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

public class UnionFind {

    int[] pArray;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        pArray = new int[n];
        Arrays.fill(pArray, -1);
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        if (vertex >= pArray.length || vertex < 0) {
            throw new IllegalArgumentException("Invalid index.");
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        int parent = parent(v1);
        while (parent >= 0) {
            parent = parent(parent);
        }
        return -parent;
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        return pArray[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        HashSet<Integer> S = new HashSet<>();
        if (v1 == v2) {
            return true;
        }
        int p1 = parent(v1);
        int p2 = parent(v2);
        while (p1 >= 0 || p2 >= 0) {
            if (p1 >= 0) {
                if (S.contains(p1)) {
                    return true;
                } else {
                    S.add(p1);
                    p1 = parent(p1);
                }
            }
            if (p2 >= 0) {
                if (S.contains(p2)) {
                    return true;
                } else {
                    S.add(p2);
                    p2 = parent(p2);
                }
            }
        }
        return false;
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        int r1 = find(v1);
        int r2 = find(v2);
        if (connected(r1,r2)) {
            return;
        }
        int s1 = sizeOf(r1);
        int s2 = sizeOf(r2);
        if (s2 >= s1) {
            pArray[r2] -= s1;
            pArray[r1] = r2;
        } else {
            pArray[r1] -= s2;
            pArray[r2] = r1;
        }

    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        validate(vertex);
        if (parent(vertex) < 0) {
            return vertex;
        } else {
            Set<Integer> S = new HashSet<>();
            while (parent(vertex) >= 0) {
                S.add(vertex);
                vertex = parent(vertex);
            }
            for (int i : S) {
                if (i != vertex) {
                    pArray[i] = vertex;
                }
            }
            return vertex;
        }
    }

}

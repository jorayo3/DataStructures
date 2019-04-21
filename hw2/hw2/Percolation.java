package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
//TODO fix backwashing

public class Percolation {
    private boolean [][] grid;
    private int top;
    private int bottom;
    private int openSites;
    private int length;
    private WeightedQuickUnionUF union;

    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("Please enter a positive number.");
        }
        openSites = 0;
        length = N;
        union = new WeightedQuickUnionUF(N * N + 2);
        top = N * N;
        bottom = (N * N) + 1;
        grid = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = false;
            }
        }
    }

    private void verifyCoord(int row, int col) {
        if (row < 0 || row >= length) {
            throw new IndexOutOfBoundsException("Row is out of bounds.");
        } else if (col < 0 || col >= length) {
            throw new IndexOutOfBoundsException("Column is out of bounds.");
        }
    }

    public void open(int row, int col) {
        verifyCoord(row, col);
        if (isOpen(row, col)) {
            return;
        }
        grid[row][col] = true;
        int index = xyTo1D(row, col);
        openSites++;
        if (row - 1 >= 0 && isOpen(row - 1, col)) {
            int index2 = xyTo1D(row - 1, col);
            union.union(index, index2);
        }
        if (col - 1 >= 0 && isOpen(row, col - 1)) {
            int index2 = xyTo1D(row, col - 1);
            union.union(index, index2);
        }
        if (row + 1 < length && isOpen(row + 1, col)) {
            int index2 = xyTo1D(row + 1, col);
            union.union(index, index2);
        }
        if (col + 1 < length && isOpen(row, col + 1)) {
            int index2 = xyTo1D(row, col + 1);
            union.union(index, index2);
        }
        if (row == 0) {
            union.union(index, top);
        }
        if (row == length - 1) {
            union.union(index, bottom);
        }
    }

    public boolean isOpen(int row, int col) {
        verifyCoord(row, col);
        return grid[row][col];
    }

    public boolean isFull(int row, int col) {
        verifyCoord(row, col);
        return union.connected(xyTo1D(row, col), top);
    }

    public int numberOfOpenSites() {
        return openSites;
    }

    public boolean percolates() {
        return union.connected(top, bottom);
    }

    public static void main(String[] args) {
    }

    private int xyTo1D(int r, int c) {
        int foo = c * length + r;
        return foo;
    }
}

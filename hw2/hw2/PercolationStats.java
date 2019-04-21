package hw2;
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private double[] results;
    private int T;
    private int N;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0) {
            throw new IllegalArgumentException("N is illegal.");
        } else if (T <= 0) {
            throw new IllegalArgumentException("T is illegal.");
        }

        this.N = N;
        this.T = T;
        results = new double[T];
        for (int i = 0; i < T; i++) {
            int counter = 0;
            Percolation currentPerc = pf.make(N);
            while (!currentPerc.percolates()) {
                int[] coords = index2xy(StdRandom.uniform(N * N));

                if (!currentPerc.isOpen(coords[0], coords[1])) {
                    currentPerc.open(coords[0], coords[1]);
                    counter++;
                }
            }
            results[i] = (double) counter / (double) (N * N);
        }

    }

    public double mean() {
        return StdStats.mean(results);
    }
    // sample mean of percolation threshold
    public double stddev() {
        return StdStats.stddev(results);
    }
    // sample standard deviation of percolation threshold
    public double confidenceLow() {
        return mean() - (1.96 * stddev()) / Math.sqrt(T);
    }

    public double confidenceHigh() {
        return mean() + (1.96 * stddev()) / Math.sqrt(T);
    }

    private int[] index2xy(int index) {
        int[] coords = new int[2];
        coords[0] = index % N;
        coords[1] = index / N;
        return coords;
    }

}

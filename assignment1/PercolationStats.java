/*
 * PercolationStats
 *  Percolation simulator for algs4e class on coursera.org
 *  - Usage: java PercolationStats N T
 *    - N = an N * N percolation grid
 *    - T = Number of experients.
 *
 * Author: Mark Pauley
 * Created: Sept 1, 2013
 *
 */

public class PercolationStats {
    private double sum;     // used for the mean
    private double sumSqr;  // used for the stddev
    private int T;
    private int N;
    
    public PercolationStats(int N, int T) {   
        // perform T independent computational experiments on an N-by-N grid
        sum = 0.0;
        sumSqr = 0.0;   
        this.N = N;
        this.T = T;
        for (int experimentNum = 0; experimentNum < T; experimentNum++) {
            Percolation perc = new Percolation(N);
            int numOpen = 0;
            while (!perc.percolates()) {
                int x = StdRandom.uniform(0, N);
                int y = StdRandom.uniform(0, N);
                if (!perc.isOpen(x + 1, y + 1)) {
                    perc.open(x + 1, y + 1);
                    numOpen++;
                }
            }
            double vacancyProbability = 
                ((double) numOpen) / ((double) this.N * this.N);
            sum += vacancyProbability;
            sumSqr += (vacancyProbability * vacancyProbability);
        }
    }
    
    public double mean() {
        // sample mean of percolation threshold
        return sum / T;
    }

    public double stddev() {
        // sample standard deviation of percolation threshold
        if (T == 1) return Double.NaN;
        return Math.sqrt((sumSqr / T) - (mean() * mean()));
    }
    
    public double confidenceLo() {
        // returns lower bound of the 95% confidence interval
        return mean() - ((1.96*stddev()) / T);
    }

    public double confidenceHi() {
        // returns upper bound of the 95% confidence interval
        return mean() + ((1.96*stddev()) / T);
    }

    public static void main(String[] args) {
        // test client, described below
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("Illegal arguments: " 
                                               + N + " "
                                               + T);
        }

        PercolationStats stats = new PercolationStats(N, T);
        System.out.println(String.format("%-22s = ", "mean") 
                           + stats.mean());
        System.out.println(String.format("%-22s = ", "stddev") 
                           + stats.stddev());
        System.out.println(String.format("%-22s = ", "95% confidenceInterval") 
                           + stats.confidenceLo() + ", " 
                           + stats.confidenceHi());
    }
}

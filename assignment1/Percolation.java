public class Percolation {
    private static boolean testsPassed;

    private boolean[][] grid;
    private int N;
    private WeightedQuickUnionUF uf;

    public Percolation(int N) {
        // create N-by-N grid, with all sites blocked
        grid = new boolean[N][N];
        this.N = N;
       uf = new WeightedQuickUnionUF(N * N + 2);
    }

    // Assertion helpers
    private void assertRowBounds(int i) {
        if (i <= 0 || i > N)
            throw new IndexOutOfBoundsException("row index " + i 
                                                +" out of bounds");
    }

    private void assertColBounds(int j) {
        if (j <= 0 || j > N) 
            throw new IndexOutOfBoundsException("col index " + j 
                                                +" out of bounds");
    }
    
    // open site (row i, column j) if it is not already
    public void open(int i, int j) {
        assertRowBounds(i);
        assertColBounds(j);
        int row = i - 1;
        int col = j - 1;
        if (!isOpen(i, j)) {
            grid[row][col] = true;
            for (int neighbor = -1; neighbor < 2; neighbor += 2) {
                // Propagate fullness to each of the open neighbors
                if (isOpenInternal((row + neighbor), col)) {
                    // vertical neighbor
                    uf.union((row * N) + col, ((row + neighbor) * N) + col);
                }
                if (isOpenInternal(row, (col + neighbor))) {
                    // horizontal neighbor
                    uf.union((row * N) + col, (row * N) + (col + neighbor));
                }
            }
            // connect to the input and output units if necessary
            if (row == 0) uf.union((row * N) + col, (N * N));
            if (col == N-1) uf.union((row * N) + col, (N * N) + 1);
        }
    }
    
    // is site (row i, column j) open?
    public boolean isOpen(int i, int j) {
        assertRowBounds(i);
        assertColBounds(j);
        int row = i - 1;
        int col = j - 1;
        return grid[row][col];
    }

    private boolean isOpenInternal(int row, int col) {
        //private convenience method with bounds checking
        // which simplifies the open() logic.
        
        // Off the grid counts as not open
        if (row < 0 || row >= N || col < 0 || col >= N) return false;
        return grid[row][col];
    }
    
    // is site (row i, column j) full?
    public boolean isFull(int i, int j) {   
        assertRowBounds(i);
        assertColBounds(j);
        int row = i - 1;
        int col = j - 1;
        return uf.connected(N * N, (row * N) + col);
    }

    // does the system percolate?
    public boolean percolates() {
        // node N^2 will be input, N^2 + 1 will be output
        return uf.connected(N * N, (N *N) + 1);
    }
    
    // Helpful visualization tool
     private String displayString() {
        StringBuilder sb = new StringBuilder();
        if (this.percolates()) sb.append("Percolates:\n");
        else sb.append("Doesn't Percolate:\n");
        for (int i = 0; i < N + 2; i++) {
            sb.append("-");
        }
        sb.append("\n");
        for (int i = 1; i <= N; i++) {
            sb.append("|");
            for (int j = 1; j <= N; j++) {
                if (isFull(i, j)) 
                    sb.append("~");
                else if (!isOpen(i, j))
                    sb.append("X");
                else 
                    sb.append(" ");
            }
            sb.append("|\n");
        }
        for (int i = 0; i < N + 2; i++) {
            sb.append("-");
        }
        sb.append("\n");
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println("Running percolate tests");

        testsPassed = true;
        System.out.println("");
        System.out.print("1x1");
        Percolation oneByOne = new Percolation(1);
        // TEST: 1x1, full
        assertPercolates(oneByOne, false);
        // TEST: 1x1, open
        oneByOne.open(1, 1);
        assertPercolates(oneByOne, true);
        
        // Simple open exception tests
        try {
            oneByOne.open(-1, 0);
            testsPassed = false;
            System.out.println("Should have thrown an exception!");
        }
        catch (IndexOutOfBoundsException e) {
            System.out.print(".");
        }

        try {
            oneByOne.open(1, -1);
            testsPassed = false;
            System.out.println("Should have thrown an exception!");
        }
        catch (IndexOutOfBoundsException e) {
            System.out.print(".");
        }
        
        try {
            oneByOne.open(2, 1);
            testsPassed = false;
            System.out.println("Should have thrown an exception");
        }
        catch (IndexOutOfBoundsException e) {
            System.out.print(".");
        }

        try {
            oneByOne.open(1, 2);
            testsPassed = false;
            System.out.println("Should have thrown an exception");
        }
        catch (IndexOutOfBoundsException e) {
            System.out.print(".");
        }

        // Simple isOpen exception tests
        try {
            oneByOne.isOpen(-1, 1);
            testsPassed = false;
            System.out.println("Should have thrown an exception!");
        }
        catch (IndexOutOfBoundsException e) {
            System.out.print(".");
        }

        try {
            oneByOne.isOpen(1, 0);
            testsPassed = false;
            System.out.println("Should have thrown an exception!");
        }
        catch (IndexOutOfBoundsException e) {
            System.out.print(".");
        }
        
        try {
            oneByOne.isOpen(2, 1);
            testsPassed = false;
            System.out.println("Should have thrown an exception");
        }
        catch (IndexOutOfBoundsException e) {
            System.out.print(".");
        }

        try {
            oneByOne.isOpen(1, 2);
            testsPassed = false;
            System.out.println("Should have thrown an exception");
        }
        catch (IndexOutOfBoundsException e) {
            System.out.print(".");
        }

        // TEST: two by two percolations
        System.out.println("");
        System.out.print("2x2");
        Percolation twoByTwo = new Percolation(2);
        assertPercolates(twoByTwo, false);
        twoByTwo.open(2, 1);
        assertPercolates(twoByTwo, false);
        twoByTwo.open(1, 2);
        assertPercolates(twoByTwo, false);
        twoByTwo.open(2, 2);
        assertPercolates(twoByTwo, true);
        twoByTwo.open(1, 1);
        assertPercolates(twoByTwo, true);
        
        for (int i = 0; i < 4; i++) {
            twoByTwo = new Percolation(2);
            twoByTwo.open((i / 2) + 1, (i % 2) + 1);
            assertPercolates(twoByTwo, false);
        }
        System.out.println("");

        //Test: three by three percolations
        System.out.print("3x3");
        Percolation threeByThree = new Percolation(3);
        assertPercolates(threeByThree, false);
        for (int i = 0; i < 9; i++) {
            threeByThree = new Percolation(3);
            threeByThree.open((i / 3) + 1, (i % 3) + 1);
            assertPercolates(threeByThree, false);
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                threeByThree = new Percolation(3);
                threeByThree.open((i / 3) + 1, (i % 3) + 1);
                threeByThree.open((j / 3) + 1, (j % 3) + 1);
                assertPercolates(threeByThree, false);
            }
        }
        
        System.out.println("");
        if (testsPassed) {
            System.out.println("All tests passed!");
        }
    }

    private static void assertPercolates(Percolation perc, 
                                         boolean percolates) {
        if (perc.percolates() != percolates) {
            testsPassed = false;
            System.out.println("X\n" + perc.displayString());
        }
        else {
            System.out.print(".");
        }
    }
}

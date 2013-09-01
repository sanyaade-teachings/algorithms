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
        if (i < 0 || i >= N) 
            throw new IndexOutOfBoundsException("row index i out of bounds");
    }

    private void assertColBounds(int j) {
        if (j < 0 || j >= N) 
            throw new IndexOutOfBoundsException("col index j out of bounds");
    }
    
    // open site (row i, column j) if it is not already
    public void open(int i, int j) {
        assertRowBounds(i);
        assertColBounds(j);
        if (!isOpen(i, j)) {
            grid[i][j] = true;
            for (int neighbor = -1; neighbor < 2; neighbor += 2) {
                if (isOpenInternal((i + neighbor), j)) {
                    uf.union((i * N) + j, ((i + neighbor) * N) + j);
                }
                if (isOpenInternal(i, (j + neighbor))) {
                    uf.union((i * N) + j, (i * N) + (j + neighbor));
                }
            }
            // connect to the input and output units if necessary
            if (i == 0) uf.union((i * N) + j, (N * N));
            if (i == N-1) uf.union((i * N) + j, (N * N) + 1);
        }
    }
    
    // is site (row i, column j) open?
    public boolean isOpen(int i, int j) {
        assertRowBounds(i);
        assertColBounds(j);
        return grid[i][j];
    }

    private boolean isOpenInternal(int i, int j) {
        //private convenience method with bounds checking
        // which simplifies the open() logic.
        
        // Off the grid counts as not open
        if (i < 0 || i >= N || j < 0 || j >= N) return false;
        return grid[i][j];
    }
    
    // is site (row i, column j) full?
    public boolean isFull(int i, int j) {   
        assertRowBounds(i);
        assertColBounds(j);
        return uf.connected(N * N, (i * N) + j);
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
        for (int i = 0; i < N; i++) {
            sb.append("|");
            for (int j = 0; j < N; j++) {
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
        oneByOne.open(0, 0);
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
            oneByOne.open(0, -1);
            testsPassed = false;
            System.out.println("Should have thrown an exception!");
        }
        catch (IndexOutOfBoundsException e) {
            System.out.print(".");
        }
        
        try {
            oneByOne.open(1, 0);
            testsPassed = false;
            System.out.println("Should have thrown an exception");
        }
        catch (IndexOutOfBoundsException e) {
            System.out.print(".");
        }

        try {
            oneByOne.open(0, 1);
            testsPassed = false;
            System.out.println("Should have thrown an exception");
        }
        catch (IndexOutOfBoundsException e) {
            System.out.print(".");
        }

        // Simple isOpen exception tests
        try {
            oneByOne.isOpen(-1, 0);
            testsPassed = false;
            System.out.println("Should have thrown an exception!");
        }
        catch (IndexOutOfBoundsException e) {
            System.out.print(".");
        }

        try {
            oneByOne.isOpen(0, -1);
            testsPassed = false;
            System.out.println("Should have thrown an exception!");
        }
        catch (IndexOutOfBoundsException e) {
            System.out.print(".");
        }
        
        try {
            oneByOne.isOpen(1, 0);
            testsPassed = false;
            System.out.println("Should have thrown an exception");
        }
        catch (IndexOutOfBoundsException e) {
            System.out.print(".");
        }

        try {
            oneByOne.isOpen(0, 1);
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
        twoByTwo.open(1, 0);
        assertPercolates(twoByTwo, false);
        twoByTwo.open(0, 1);
        assertPercolates(twoByTwo, false);
        twoByTwo.open(1, 1);
        assertPercolates(twoByTwo, true);
        twoByTwo.open(0, 0);
        assertPercolates(twoByTwo, true);
        
        for (int i = 0; i < 4; i++) {
            twoByTwo = new Percolation(2);
            twoByTwo.open(i / 2, i % 2);
            assertPercolates(twoByTwo, false);
        }
        System.out.println("");

        //Test: three by three percolations
        System.out.print("3x3");
        Percolation threeByThree = new Percolation(3);
        assertPercolates(threeByThree, false);
        for (int i = 0; i < 9; i++) {
            threeByThree = new Percolation(3);
            threeByThree.open(i / 3, i % 3);
            assertPercolates(threeByThree, false);
        }
        for (int i = 0; i < 9; i++) {
            for (int j = i + 1; j < 9; j++) {
                threeByThree = new Percolation(3);
                threeByThree.open(i / 3, i % 3);
                threeByThree.open(j / 3, j % 3);
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

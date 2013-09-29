/*************************************************************************
 * Name: Mark Pauley
 * Email: pauley@unsaturated.net
 *
 * Compilation:  javac Boards.java
 * Execution:
 * Dependencies: 
 *
 * Description: An 8-puzzle board model
 *
 *************************************************************************/

public class Board {

    private int N;
    private int[] board;

    public Board(int[][] blocks) {
        // construct a board from an N-by-N array of blocks
        // (where blocks[i][j] = block in row i, column j)
        N = blocks.length;
        board = new int[N * N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i * N + j] = blocks[i][j];
            }
        }
    }

    public int dimension() {
        // board dimension N
        return N;
    }

    public int hamming() {
        // number of blocks out of place
        int numOutOfPlace = 0;
        for (int i = 0; i < (N * N); i++) {
            if (board[i] != i) numOutOfPlace++;
        }
        return numOutOfPlace;
    }
    
    public int manhattan() {
        // sum of Manhattan distances between blocks and goal
        int sum = 0;
        for (int i = 0; i < (N * N); i++) 
            sum += manhattanDistance(i, board[i]);
        
        return sum;
    }

    private int manhattanDistance(int position, int value) {
        int row = position % N;
        int col = position / N;
        int goalRow = value % N;
        int goalCol = value / N;
        return abs(goalRow - row) + abs(goalCol - col);
    }

    private int abs(int i) {
        if (i < 0) return -1 * i;
        return i;
    }
    
    public boolean isGoal() {
        // is this board the goal board?
        return false;
    }
     
    public Board twin() {
        // a board obtained by exchanging two adjacent blocks in the same row
        return null;
    }

    public boolean equals(Object y) {
        // does this board equal y?
        return false;
    }

    public Iterable<Board> neighbors() {
        // all neighboring boards
        return null;
    }

    public String toString() { 
        // string representation of the board 
        //  (in the output format specified below)
        return "";
    }
}
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
        return 0;
    }
    
    public int manhattan() {
        // sum of Manhattan distances between blocks and goal
        return 0;
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
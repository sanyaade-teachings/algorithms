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

    /*
     * Convenience constructor (private)
     */
    private Board(int[] board, int N) {
        this.N = N;
        this.board = new int[N * N];
        for (int i = 0; i < board.length; i++) {
            this.board[i] = board[i];
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
        return hamming() == 0;
    }
     
    public Board twin() {
        // a board obtained by exchanging two adjacent blocks in the same row
        int firstExchangeSquare = findTwinPoint();
        int[] twinBoard = java.util.Arrays.copyOf(board, board.length);
        int temp = twinBoard[firstExchangeSquare];
        twinBoard[firstExchangeSquare] = twinBoard[firstExchangeSquare + 1];
        twinBoard[firstExchangeSquare + 1] = temp;
        return new Board(twinBoard, N);
    }

    private int findTwinPoint() {
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N - 1; j++)
                if ((board[i * N + j] != 0) && (board[i * N + j + 1] != 0))
                return i;

        return -1;
    }
    

    public boolean equals(Object other) {
        if (this.getClass() != other.getClass()) return false;
        Board otherBoard = (Board) other;
        // does this board equal the other board?
        if (dimension() != otherBoard.dimension()) return false;
        for (int i = 0; i < board.length; i++) 
            if (board[i] != otherBoard.board[i]) return false;
        
        return true;
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
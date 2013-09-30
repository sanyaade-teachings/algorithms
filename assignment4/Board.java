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

import java.util.Iterator;

public class Board {

    private int N;
    private char[] board;
    private int zeroIndex;
    private int manhattanDistance;

    private class Neighbors implements Iterable<Board> {
        private Board boardObj;
        private int emptySpotIndex;
        private int[] swapIndices;


        private class NeighborIterator implements Iterator<Board> {
            private Neighbors neighbors;
            private int curIdx; // index into swapIndices

            private NeighborIterator(Neighbors neighbors) {
                this.neighbors = neighbors;
                curIdx = 0;
            }
            
            public boolean hasNext() {
                return curIdx < neighbors.swapIndices.length;
            }

            public Board next() {
                return new Board(neighbors.boardObj, 
                                 neighbors.swapIndices[curIdx++]);
            }
            
            public void remove() {
                // FIXME: throw exception?
            }
            
        }
        
        public Neighbors(Board board) {
            boardObj = board;
            emptySpotIndex = board.zeroIndex();
            findSwapIndices();
        }

        private void findSwapIndices() {
            int numSwapIndices = 0;
            int n = boardObj.dimension();
            int emptySpotRow = emptySpotIndex / n;
            int emptySpotCol = emptySpotIndex % n;
            int[] tempSwaps = new int[4];
            for (int i = -1; i < 2; i += 2) {
                if (i + emptySpotRow >= 0 && i + emptySpotRow < N) {
                    int swapIndex = ((i + emptySpotRow) * n) + emptySpotCol;
                    tempSwaps[numSwapIndices++] = swapIndex;
                }
                if (i + emptySpotCol >= 0 && i + emptySpotCol < N) {
                    int swapIndex = (emptySpotRow * n + (i + emptySpotCol));
                    tempSwaps[numSwapIndices++] = swapIndex;
                }
            }
            if (numSwapIndices < 4) {
                swapIndices = java.util.Arrays.copyOf(tempSwaps, 
                                                      numSwapIndices);
            }
            else {
                swapIndices = tempSwaps;
            }
        }

        public Iterator<Board> iterator() {
            return new NeighborIterator(this);
        }

    }

    public Board(int[][] blocks) {
        // construct a board from an N-by-N array of blocks
        // (where blocks[i][j] = block in row i, column j)
        N = blocks.length;
        zeroIndex = -1;
        manhattanDistance = -1;
        board = new char[N * N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                char val = (char) blocks[i][j];
                board[(i * N) + j] = val;
                if (val == 0) zeroIndex = (i * N) + j;
            }
        }
    }

    private Board(Board otherBoard, int swapTo) {
        N = otherBoard.N;
        board = java.util.Arrays.copyOf(otherBoard.board, N*N);
        board[otherBoard.zeroIndex] = board[swapTo];
        board[swapTo] = 0;
        zeroIndex = swapTo;
        manhattanDistance = -1;
    }

    /*
     * Convenience constructor (private)
     */
    private Board(char[] board, int N) {
        this.N = N;
        this.board = new char[N * N];
        zeroIndex = -1;
        manhattanDistance = -1;
        for (int i = 0; i < board.length; i++) {
            char val = board[i];
            this.board[i] = val;
            if (val == 0) zeroIndex = i;
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
            if (board[i] != i + 1) numOutOfPlace++;
        }
        if (board[N * N - 1] == 0) numOutOfPlace--;
        return numOutOfPlace;
    }
    
    public int manhattan() {
        if (manhattanDistance == -1) {
            // sum of Manhattan distances between blocks and goal
            int sum = 0;
            for (int i = 0; i < (N * N); i++) 
                sum += manhattanDistance(i, board[i]);
            
            manhattanDistance = sum;
        }
        return manhattanDistance;
    }

    private int manhattanDistance(int position, int value) {
        int row = position / N;
        int col = position % N;
        int goalRow = (value - 1) / N;
        int goalCol = (value - 1) % N;
        if (value == 0) {
            goalRow = N - 1;
            goalCol = N - 1;
        }

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
        char[] twinBoard = java.util.Arrays.copyOf(board, board.length);
        char temp = twinBoard[firstExchangeSquare];
        twinBoard[firstExchangeSquare] = twinBoard[firstExchangeSquare + 1];
        twinBoard[firstExchangeSquare + 1] = temp;
        return new Board(twinBoard, N);
    }

    private int findTwinPoint() {
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N - 1; j++)
                if ((board[i * N + j] != 0) && (board[i * N + j + 1] != 0))
                return i * N + j;

        return -1;
    }
    

    public boolean equals(Object other) {
        if (other == null) return false;
        if (this.getClass() != other.getClass()) return false;
        Board otherBoard = (Board) other;
        // does this board equal the other board?
        if (dimension() != otherBoard.dimension()) return false;
        for (int i = 0; i < board.length; i++) 
            if (board[i] != otherBoard.board[i]) return false;
        
        return true;
    }

    public Iterable<Board> neighbors() {
        return new Neighbors(this);
    }

    private int zeroIndex() {
        return zeroIndex;
    }

    public String toString() { 
        // string representation of the board 
        StringBuilder sb = new StringBuilder(5 + 3 * N);
        sb.append(N);
        sb.append('\n');
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(String.format("%3d", (int) board[i * N + j]));
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
/*************************************************************************
 * Name: Mark Pauley
 * Email: pauley@unsaturated.net
 *
 * Compilation:  javac Solver.java
 * Execution: java Solver boardFile
 * Dependencies: Board.java
 *
 * Description: An 8-puzzle board solver
 *
 *************************************************************************/

import java.util.Comparator;
import java.util.Iterator;

public class Solver {    

    private static class GameTreeNode implements Iterable<Board> {
        private GameTreeNode parent;
        private Board board;
        private int numMoves;

        public GameTreeNode(GameTreeNode parent, Board board) {
            this.board = board;
            this.parent = parent;
            if (parent != null) {
                numMoves = parent.numMoves + 1;
            }
            else {
                numMoves = 0;
            }
        }

        public static class NodeManhattanComparator 
            implements Comparator<GameTreeNode> {
            public int compare(GameTreeNode a, GameTreeNode b) {
                return (a.board.manhattan() + a.numMoves)
                    - (b.board.manhattan() + b.numMoves);
            }
            public boolean equals(GameTreeNode a, GameTreeNode b) {
                return compare(a, b) == 0;
            }
        }

        public static class NodeHammingComparator
            implements Comparator<GameTreeNode> {
            public int compare(GameTreeNode a, GameTreeNode b) {
                return (a.board.hamming() + a.numMoves)
                    - (b.board.hamming() + b.numMoves);
            }
            public boolean equals(GameTreeNode a, GameTreeNode b) {
                return compare(a, b) == 0;
            }
        }

        private static class GameNodeIterator implements Iterator<Board> {
            private GameTreeNode curNode;
            
            GameNodeIterator(GameTreeNode startNode) {
                curNode = startNode;
            }
            
            public boolean hasNext() {
                return curNode != null;
            }

            public Board next() {
                Board board = curNode.board;
                curNode = curNode.parent;
                return board;
            }

            public void remove() {
                // FIXME: throw exception?
            }
        }
        
        public Iterator<Board> iterator() {
            Stack<Board> moveStack = new Stack<Board>();
            Iterator<Board> moves = new GameNodeIterator(this);
            while (moves.hasNext()) {
                moveStack.push(moves.next());
            }
            return moveStack.iterator();
        }
    }

    private static Comparator<GameTreeNode> COMPARATOR 
        = new GameTreeNode.NodeManhattanComparator();
    
    private boolean isSolved;
    private boolean isSolvable;
    private MinPQ<GameTreeNode> gameNodeQueue;
    private GameTreeNode solution;

    private MinPQ<GameTreeNode> twinNodeQueue;
    
    public Solver(Board initial) {
        // find a solution to the initial board (using the A* algorithm)
        isSolved = false;
        isSolvable = true;
        solution = null;
        gameNodeQueue = new MinPQ(COMPARATOR);
        twinNodeQueue = new MinPQ(COMPARATOR);
        
        GameTreeNode gameTree = new GameTreeNode(null, initial);
        GameTreeNode twinTree = new GameTreeNode(null, initial.twin());

        gameNodeQueue.insert(gameTree);
        twinNodeQueue.insert(twinTree);

        while (!isSolved && isSolvable) {
            // Try to get closer to the solution
            GameTreeNode curNode = gameNodeQueue.delMin();
            if (curNode.board.isGoal()) {
                solution = curNode;
                isSolved = true;
                continue;
            }
            else {
                for (Board newBoard : curNode.board.neighbors()) {
                    GameTreeNode newNode = new GameTreeNode(curNode, newBoard);
                    if (curNode.parent == null
                       || !newNode.board.equals(curNode.parent.board)) {
                        gameNodeQueue.insert(newNode);
                    }
                }
            }
            // Then try to disprove the solvability
            GameTreeNode curTwin = twinNodeQueue.delMin();
            if (curTwin.board.isGoal()) {
                isSolvable = false;
                continue;
            }
            else {
                for (Board newBoard : curTwin.board.neighbors()) {
                    GameTreeNode newNode = new GameTreeNode(curTwin, newBoard);
                    if (curTwin.parent == null
                       || !newNode.board.equals(curTwin.parent.board)) {
                        twinNodeQueue.insert(newNode);
                    }
                }
            }
        }
    }
    
    public boolean isSolvable() {
        // is the initial board solvable?
        return isSolvable;
    }
     
    public int moves() {
        // min number of moves to solve initial board; -1 if no solution
        if (!isSolvable) {
            return -1;
        }
        else {
            return solution.numMoves;
        }
    }
    
    public Iterable<Board> solution() {
        // sequence of boards in a shortest solution; null if no solution
        return solution;
    }
    

    // solve a slider puzzle (given below)
    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);
        
        // solve the puzzle
        Solver solver = new Solver(initial);
        
        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
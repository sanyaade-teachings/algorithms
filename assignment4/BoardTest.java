/****     
 *
 * BoardTest.java
 * a junit test for the Board class                                          
 *
 ****/

import org.junit.*;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class BoardTest {

    private int[][] solvedBoard(int N) {
        int[][] blocks = new int[N][N];
        int blockNum = 0;
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = blockNum++;

        return blocks;
    }

    private int[][] reversedBoard(int N) {
        int[][] blocks = new int[N][N];
        int blockNum = (N * N) - 1;
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = blockNum--;

        return blocks;
    }
    
    @Test public void testMakeBoard() {
        int[][] blocks = solvedBoard(3);
        Board testBoard = new Board(blocks);
        assertNotNull(testBoard);
    }

    @Test public void testDimensions() {
        Board testBoard = new Board(solvedBoard(4));
        assertEquals(testBoard.dimension(), 4);
    }

    @Test public void testHamming() {
        Board solvedBoard = new Board(solvedBoard(4));
        assertEquals(solvedBoard.hamming(), 0);
        Board reversedBoard = new Board(reversedBoard(4));
        assertEquals(reversedBoard.hamming(), 16);
    }

    @Test public void testManhattan() {
        Board solvedBoard = new Board(solvedBoard(4));
        assertEquals(solvedBoard.manhattan(), 0);
        Board reversedBoard = new Board(reversedBoard(2));
        assertEquals(reversedBoard.manhattan(), 8);
        reversedBoard = new Board(reversedBoard(3));
        assertEquals(reversedBoard.manhattan(), 24);
       reversedBoard = new Board(reversedBoard(4));
        assertEquals(reversedBoard.manhattan(), 64);
    }

    public static void main(String args[]) {
        org.junit.runner.JUnitCore.main("BoardTest");
    }
}
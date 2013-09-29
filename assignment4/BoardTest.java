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
    
    @Test public void testMakeBoard() {
        int[][] blocks = solvedBoard(3);
        Board testBoard = new Board(blocks);
        assertNotNull(testBoard);
    }

    public static void main(String args[]) {
        org.junit.runner.JUnitCore.main("BoardTest");
    }
}
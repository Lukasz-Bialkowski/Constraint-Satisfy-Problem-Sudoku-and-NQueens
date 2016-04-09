/*
package algorithms.algorithm;

import algorithms.dto.Point;

public class QueensFC {

    int[][] board;
    boolean[][] domain;
    int queensPlacedCounter;

    public QueensFC(int size) {
      board = new int[size][size];
      domain = new int[size][size];
      queensPlacedCounter = 0;
    }

    public boolean isDomainEmpty() {
        for (int i = 0; i < boxSize * boxSize; i++) {
            for (int j = 0; j < boxSize * boxSize; j++) {
                if (sudoku[i][j] == 0) {
                    if(isDomainEmptyFor(new Point(i, j))){
                        return true;
                    };
                }
            }
        }
        return false;
    }

    public void algorithm() {

    }

    public Point findEmpty() {
        for (int i = 0; i < boxSize * boxSize; i++) {
            for (int j = 0; j < boxSize * boxSize; j++) {
                if (domain[i][j] == 0) {
                    return new Point(i, j);
                }
            }
        }
        return null;
    }

    public void setValue(Point point, Boolean value) {
        int row = point.getRow();
        int column = point.getColumn();
        domain[row][column] = value;
    }

    public void setValue(Point point, Integer value) {
        int row = point.getRow();
        int column = point.getColumn();
        board[row][column] = value;
    }

    public int[][] board() {
        return board;
    }

}
*/

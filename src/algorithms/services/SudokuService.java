package algorithms.services;

import java.util.HashSet;

public class SudokuService {

    public static int[][] createSudokuBoard(int boardSize, int emptyVariables) {

        int[][] sudoku;
        switch (boardSize) {
            case 4:
                int[][] temp1 = {{3, 1, 2, 4}, {4, 2, 3, 1}, {1, 3, 4, 2}, {2, 4, 1, 3}};
                sudoku = temp1;
                break;
            default:
                int[][] temp2 =
                    {
                        {3, 1, 6, 5, 7, 8, 4, 9, 2},
                        {5, 2, 9, 1, 3, 4, 7, 6, 8},
                        {4, 8, 7, 6, 2, 9, 5, 3, 1},
                        {2, 6, 3, 4, 1, 5, 9, 8, 7},
                        {9, 7, 4, 8, 6, 3, 1, 2, 5},
                        {8, 5, 1, 7, 9, 2, 6, 4, 3},
                        {1, 3, 8, 9, 4, 7, 2, 5, 6},
                        {6, 9, 2, 3, 5, 1, 8, 7, 4},
                        {7, 4, 5, 2, 8, 6, 3, 1, 9}
                    };
                sudoku = temp2;
                break;
        }

        HashSet<Integer> set = new HashSet<>();
        while(set.size() != emptyVariables)
            set.add(randomizer(boardSize*boardSize));
        for (int i : set) {
            sudoku[i/boardSize][i%boardSize]=0;
        }
        return sudoku;
    }

    public static int randomizer(int max) {
        return (int) (Math.random() * max);
    }

    public static void printBoard(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}

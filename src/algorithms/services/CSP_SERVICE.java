package algorithms.services;

import algorithms.algorithm.SudokuFC;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;

public class CSP_SERVICE {

    public static int[][] createSudokuBoard(int boardSize, int emptyVariables) {

        int[][] sudoku;
        switch (boardSize) {
            case 4:
                int[][] temp1 = {{3, 1, 2, 4}, {4, 2, 3, 1}, {1, 3, 4, 2}, {2, 4, 1, 3}};
                sudoku = temp1;
                break;
            case 16:
                int[][] temp3 = {
                        {4, 10, 9, 15, 1, 7, 13, 8, 6, 14, 2, 12, 16, 5, 3, 11},
                        {2, 5, 3, 1, 15, 4, 11, 16, 13, 9, 8, 7, 6, 10, 12, 14},
                        {14, 6, 13, 12, 3, 10, 5, 2, 16, 11, 1, 4, 8, 15, 9, 7},
                        {11, 7, 16, 8, 6, 14, 9, 12, 5, 3, 10, 15, 1, 2, 13, 4},
                        {8, 16, 11, 4, 13, 15, 14, 9, 2, 5, 7, 3, 12, 1, 10, 6},
                        {1, 14, 6, 13, 12, 8, 4, 5, 10, 16, 9, 11, 2, 3, 7, 15},
                        {10, 15, 5, 3, 2, 1, 6, 7, 4, 12, 14, 8, 9, 11, 16, 13},
                        {12, 2, 7, 9, 11, 3, 16, 10, 15, 13, 6, 1, 4, 8, 14, 5},
                        {9, 4, 1, 10, 14, 2, 3, 13, 11, 15, 12, 6, 7, 16, 5, 8},
                        {5, 8, 14, 16, 7, 9, 1, 6, 3, 4, 13, 10, 11, 12, 15, 2},
                        {7, 3, 15, 6, 16, 11, 12, 4, 8, 2, 5, 9, 14, 13, 1, 10},
                        {13, 12, 2, 11, 10, 5, 8, 15, 7, 1, 16, 14, 3, 6, 4, 9},
                        {15, 9, 8, 2, 4, 12, 7, 3, 1, 10, 11, 13, 5, 14, 6, 16},
                        {6, 13, 12, 5, 9, 16, 15, 1, 14, 8, 4, 2, 10, 7, 11, 3},
                        {16, 11, 4, 7, 8, 13, 10, 14, 12, 6, 3, 5, 15, 9, 2, 1},
                        {3, 1, 10, 14, 5, 6, 2, 11, 9, 7, 15, 16, 13, 4, 8, 12}};
                sudoku = temp3;
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
        for (int i = 1; i <= board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if(j%(int)Math.sqrt(board.length)==0)
                    System.out.print("  ");
                System.out.print(board[i-1][j] + " ");
            }
            if(i%(int)Math.sqrt(board.length)==0)
                System.out.print("\n");
            System.out.println();
        }
    }

    public static void printqueensBoard(int[][] board) {
        for (int i = 1; i <= board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                System.out.print(board[i-1][j] + " ");
            }
            System.out.println();
        }
    }

    public static String generateStringSudoku(int[][] board) {
        String str = "";
        for (int i = 1; i <= board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (j % (int) Math.sqrt(board.length) == 0)
                    str += "     ";
                str += String.format("%4s", board[i - 1][j] + "");
            }
            if (i % (int) Math.sqrt(board.length) == 0)
                str += "\n";
            str += "\n";
        }
        return str;
    }

    public static String generateStringQueens(int[][] board) {
        String str = "";
        for (int i = 1; i <= board.length; i++) {
            str += "| ";
            for (int j = 0; j < board.length; j++) {
                str += String.format("%6s", board[i - 1][j] + "");
            }
            str += "  |\n";
        }
        return str;
    }

    public static String htmlArray(int[][] data) {
        StringBuilder sb = new StringBuilder();
        sb.append("<html>\n");
        sb.append("<table>\n");
        for (int row = 0; row < data.length; row++) {
            sb.append("\t<tr>\n");
            for (int col = 0; col < data[0].length; col++) {
                sb.append("\t\t<td> " + data[row][col] + " </td>\n");
            }
            sb.append("\t</tr>\n");
        }
        sb.append("</table>\n");
        sb.append("</html>");
        return sb.toString();
    }

}

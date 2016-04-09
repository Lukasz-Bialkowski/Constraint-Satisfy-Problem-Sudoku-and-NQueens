package algorithms.algorithm;

import algorithms.devpackage.BoardVariable;
import algorithms.services.SudokuService;

public class SudokuBT {

    int[][] sudokuBoard;

    public SudokuBT(int boardSize, int emptyVar) {
        sudokuBoard = SudokuService.createSudokuBoard(boardSize, emptyVar);
    }

    public int[][] createAndInitializeBoard(int boardSize) {

        int[][] temp = new int[boardSize][];
        for (int i = 0; i < boardSize; i++) {
            int[] row = new int[boardSize];
            for (int j = 0; j < boardSize; j++) {
                row[j] = 0;
            }
            temp[i] = row;
        }
        return temp;
    }

    public BoardVariable findEmptySpace() {
        for (int i = 0; i < sudokuBoard.length; i++) {
            for (int j = 0; j < sudokuBoard.length; j++) {
                if (sudokuBoard[i][j] == 0) {
                    return new BoardVariable(i, j);
                }
            }
        }
        return null;
    }

    public boolean isCubeAvailable(int selectedValue, BoardVariable variable) {

        int rowsCube = rowsPart(variable.getRow());
//        System.out.println("Duza kostka Y" + rowsCube);
        int columnsCube = columnPart(variable.getColumn());
//        System.out.println("Duza kostka X" + columnsCube);
        int firstRowInCube = rowsCube * (int) Math.sqrt(sudokuBoard.length);
//        System.out.println("Pierwszy indeks rowa: "+firstRowInCube);
        int firstColumnInCube = columnsCube * (int) Math.sqrt(sudokuBoard.length);
//        System.out.println("Pierwszy indeks columny: "+firstColumnInCube);

        for (int i = firstRowInCube; i < (firstRowInCube + Math.sqrt(sudokuBoard.length)); i++) {
            for (int j = firstColumnInCube; j < (firstColumnInCube + Math.sqrt(sudokuBoard.length)); j++) {
                if (sudokuBoard[i][j] == selectedValue) {
                    return false;
                }
            }
        }
        return true;
    }

    private int columnPart(int column) {
        return (int) (column/Math.sqrt(sudokuBoard.length));
    }

    private int rowsPart(int row) {
        return (int) (row/Math.sqrt(sudokuBoard.length));
    }

    public boolean isRowAvailable(int row, int selectedValue) {
        for (int i = 0; i < sudokuBoard.length; i++) {
            if (sudokuBoard[row][i] == selectedValue) {
                System.out.println("Row fail");
                return false;
            }
        }
        return true;
    }

    public boolean isColumnAvailable(int column, int selectedValue) {

        for (int i = 0; i < sudokuBoard.length; i++) {
            if (sudokuBoard[i][column] == selectedValue) {
                System.out.println("Column fail");
                return false;
            }
        }
        return true;
    }

    public boolean algorithm() {

        BoardVariable variable = findEmptySpace();
        if (variable == null) {
            return true;
        }
        for (int i = 1; i <= sudokuBoard.length; i++) {
            if (constraintsSatisfyied(i, variable)) {
                sudokuBoard[variable.getRow()][variable.getColumn()] = i;
                if (algorithm()) {return true;}
                sudokuBoard[variable.getRow()][variable.getColumn()] = 0;
            }
        }
        return false;
    }

    public boolean constraintsSatisfyied(int selectedValue, BoardVariable variable){
        System.out.println("Sprawdzam poprawnosc ["+variable.getRow()+", "+variable.getColumn()+"]");
        if (!isRowAvailable(variable.getRow(), selectedValue) || !isColumnAvailable(variable.getColumn(), selectedValue) || !isCubeAvailable(selectedValue, variable)) {
            return false;
        } else {
            return true;
        }
    }

    public int[][] board(){
        return sudokuBoard;
    }
}

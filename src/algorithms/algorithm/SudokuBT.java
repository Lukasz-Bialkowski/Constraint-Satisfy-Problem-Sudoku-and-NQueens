package algorithms.algorithm;

import algorithms.dto.BoardVariable;
import algorithms.services.SudokuService;

public class SudokuBT {

    int[][] sudokuBoard;

    public SudokuBT(int boardSize, int emptyVar) {
        sudokuBoard = SudokuService.createSudokuBoard(boardSize, emptyVar);
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
        int columnsCube = columnPart(variable.getColumn());
        int firstRowInCube = rowsCube * (int) Math.sqrt(sudokuBoard.length);
        int firstColumnInCube = columnsCube * (int) Math.sqrt(sudokuBoard.length);

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
                return false;
            }
        }
        return true;
    }

    public boolean isColumnAvailable(int column, int selectedValue) {

        for (int i = 0; i < sudokuBoard.length; i++) {
            if (sudokuBoard[i][column] == selectedValue) {
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

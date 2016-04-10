package algorithms.algorithm;

import algorithms.dto.BoardVariable;
import algorithms.dto.Point;
import algorithms.services.CSP_SERVICE;

import java.util.*;

public class SudokuBT {

    int[][] sudokuBoard;
    int boxSize;

    public SudokuBT(int boardSize, int emptyVar) {
        boxSize = (int) Math.sqrt(boardSize);
        sudokuBoard = CSP_SERVICE.createSudokuBoard(boardSize, emptyVar);
    }

    public boolean algorithm() {

        BoardVariable variable = findEmptySpace();
        if (variable == null) {
            return true;
        }
        for (int i = 1; i <= sudokuBoard.length; i++) {
            if (constraintsSatisfyied(i, variable)) {
                sudokuBoard[variable.getRow()][variable.getColumn()] = i;
                if (algorithm()) {
                    return true;
                }
                sudokuBoard[variable.getRow()][variable.getColumn()] = 0;
            }
        }
        return false;
    }

    public boolean algorithmMRV() {

        List<BoardVariable> variables = findEmptySpaces();
        if (variables.isEmpty()) {
            return true;
        }

        BoardVariable bestPointSoFar = variables.get(0);
        int minimalDomainSize = availableValuesNumber(bestPointSoFar);

        for (BoardVariable point : variables) {
            int domainSize = availableValuesNumber(point);
            if (domainSize < minimalDomainSize) {
                minimalDomainSize = domainSize;
                bestPointSoFar = point;
            }
        }

        for (int i = 1; i <= sudokuBoard.length; i++) {
            if (constraintsSatisfyied(i, bestPointSoFar)) {
                sudokuBoard[bestPointSoFar.getRow()][bestPointSoFar.getColumn()] = i;
                if (algorithmMRV()) {
                    return true;
                }
                sudokuBoard[bestPointSoFar.getRow()][bestPointSoFar.getColumn()] = 0;
            }
        }
        return false;
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

    public List<BoardVariable> findEmptySpaces() {
        List<BoardVariable> emptySpaces = new ArrayList<>();
        for (int i = 0; i < sudokuBoard.length; i++) {
            for (int j = 0; j < sudokuBoard.length; j++) {
                if (sudokuBoard[i][j] == 0) {
                    emptySpaces.add(new BoardVariable(i, j));
                }
            }
        }
        return emptySpaces;
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

    public Integer availableValuesNumber(BoardVariable point) {
        HashSet<Integer> neighboursColumn = new HashSet<Integer>();
        HashSet<Integer> neighboursRow = new HashSet<Integer>();
        HashSet<Integer> neighboursBox = new HashSet<Integer>();

        int column = point.getColumn();
        int row = point.getRow();

        for (int i = 0; i < boxSize * boxSize; i++) {
            neighboursColumn.add(sudokuBoard[i][column]);
            neighboursRow.add(sudokuBoard[row][i]);
        }

        int cell_x = row / boxSize;
        int cell_y = column / boxSize;
        for (int i = cell_x * boxSize; i <= cell_x * boxSize + boxSize - 1; i++) {
            for (int j = cell_y * boxSize; j <= cell_y * boxSize + boxSize - 1; j++) {
                neighboursBox.add(sudokuBoard[i][j]);
            }
        }

        HashSet<Integer> result = new HashSet<>();
        result.addAll(neighboursBox);
        result.addAll(neighboursRow);
        result.addAll(neighboursColumn);

        HashSet<Integer> domain = new HashSet<>();
        for (int i = 1; i <= boxSize * boxSize; i++) {
            domain.add(i);
        }
        domain.removeAll(result);

        return domain.size();

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

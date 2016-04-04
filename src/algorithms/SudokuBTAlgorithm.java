package algorithms;

import java.util.HashSet;

public class SudokuBTAlgorithm {

    int[][] sudokuBoard;

    public SudokuBTAlgorithm(int boardSize, int emptyVar) {
        sudokuBoard = createSudokuBoard(boardSize, emptyVar);
    }

    public void printBoard() {
        for (int i = 0; i < sudokuBoard.length; i++) {
            for (int j = 0; j < sudokuBoard.length; j++) {
                System.out.print(sudokuBoard[i][j]+" ");
            }
            System.out.println();
        }
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
                if (algorithm()) {
                    return true;
                }
                sudokuBoard[variable.getRow()][variable.getColumn()] = 0;
            }
        }

        return false;
    }

    public int[][] createSudokuBoard(int boardSize, int emptyVariables) {

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

    public int randomizer(int max) {
        return (int) (Math.random() * max);
    }

    public boolean constraintsSatisfyied(int selectedValue, BoardVariable variable){
        System.out.println("Sprawdzam poprawnosc ["+variable.getRow()+", "+variable.getColumn()+"]");
        if (!isRowAvailable(variable.getRow(), selectedValue) || !isColumnAvailable(variable.getColumn(), selectedValue) || !isCubeAvailable(selectedValue, variable)) {
            return false;
        } else {
            return true;
        }
    }
}

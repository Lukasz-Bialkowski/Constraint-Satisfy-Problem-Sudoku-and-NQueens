package algorithms;

public class NQueensBTAlgorithm {

    private int queens;
    private int chessBoard[][];

    public NQueensBTAlgorithm(int problemSize) {
        queens = problemSize;
        chessBoard = createAndInitializeBoard(queens);
//        chessBoard = testInitializing(queens);
    }

    private int[][] createAndInitializeBoard(int boardSize) {

        int[][] temp = new int[boardSize][];
        for (int i = 0; i < queens; i++) {
            int[] row = new int[queens];
            for (int j = 0; j < queens; j++) {
                row[j] = 0;
            }
            temp[i] = row;
        }
        return temp;
    }

    private int[][] testInitializing(int boardSize) {
        int[][] temp = {{0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 1, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}};
        return temp;
    }

    public boolean algorithm(int i) {
        if(i==queens){
            return true;
        }
        for (int j = 0; j < queens; j++) {
            if (isPlacementCorrect(i, j)){
                chessBoard[i][j] = 1;
                if(algorithm(i+1)){return true;}
            }

        }
        return false;
    }

    boolean isPlacementCorrect(int row, int column) {
        // sprawdzam zmienna opatrzona dwoma parametrami row i column.
        System.out.println("Sprawdzam poprawnosc ["+row+", "+column+"]");
        if (!isRowAvailable(row) || !isColumnAvailable(column) || !isCrossingAvailable(row, column)) {
            return false;
        } else {
            return true;
        }
    }

    public void printBoard() {
        for (int i = 0; i < queens; i++) {
            for (int j = 0; j < queens; j++) {
                System.out.print(chessBoard[i][j]+" ");
            }
            System.out.println();
        }
    }

    boolean isCrossingAvailable(int row, int column) {
        int reset = Math.min(row, column);
        int i = row - reset;
        int j = column - reset;
        while (i < queens && j < queens) {
            if(chessBoard[i][j] == 1){
                System.out.println("Left Cross fail");
                return false;
            }
            i++;
            j++;
        }
        i = row - reset;
        j = column + reset;
        if (j >= queens) {
            j = queens -1;
        }
        while (i < queens && j >= 0) {
            if (chessBoard[i][j] == 1) {
                System.out.println("Right Cross fail");
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    boolean isRowAvailable(int row) {
        for (int i = 0; i < queens; i++) {
            if (chessBoard[row][i] == 1) {
                System.out.println("Row fail");
                return false;
            }
        }
        return true;
    }

    boolean isColumnAvailable(int column) {

        for (int i = 0; i < queens; i++) {
            if (chessBoard[i][column] == 1) {
                System.out.println("Column fail");
                return false;
            }
        }
        return true;
    }
}

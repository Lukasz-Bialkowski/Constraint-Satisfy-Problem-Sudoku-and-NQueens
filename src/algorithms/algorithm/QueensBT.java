package algorithms.algorithm;

public class QueensBT {

    private int size;
    private int chessBoard[][];

    public QueensBT(int problemSize) {
        size = problemSize;
        chessBoard = createAndInitializeBoard(size);
    }

    public boolean algorithm(int i) {
        if(i== size){
            return true;
        }
        for (int j = 0; j < size; j++) {
            if (isPlacementCorrect(i, j)){
                chessBoard[i][j] = 1;
                if(algorithm(i+1)){return true;}
            }
            chessBoard[i][j] = 0;
        }
        return false;
    }

    private int[][] createAndInitializeBoard(int boardSize) {

        int[][] temp = new int[boardSize][];
        for (int i = 0; i < size; i++) {
            int[] row = new int[size];
            for (int j = 0; j < size; j++) {
                row[j] = 0;
            }
            temp[i] = row;
        }
        return temp;
    }

    boolean isPlacementCorrect(int row, int column) {
        if (!isRowAvailable(row) || !isColumnAvailable(column) || !isCrossingAvailable(row, column)) {
            return false;
        } else {
            return true;
        }
    }

    boolean isCrossingAvailable(int row, int column) {
        int i = row;
        int j = column;
        while (i < size && i >= 0 && j < size && j >= 0) {
            if(chessBoard[i][j] == 1){
                return false;
            }
            i--;
            j++;
        }
        i = row;
        j = column;
        while (i < size && i >= 0 && j < size && j >= 0) {
            if(chessBoard[i][j] == 1){
                return false;
            }
            i++;
            j--;
        }
        i = row;
        j = column;
        while (i < size && i >= 0 && j < size && j >= 0) {
            if(chessBoard[i][j] == 1){
                return false;
            }
            i--;
            j--;
        }
        i = row;
        j = column;
        while (i < size && i >= 0 && j < size && j >= 0) {
            if(chessBoard[i][j] == 1){
                return false;
            }
            i++;
            j++;
        }
        i = row;
        j = column;

        return true;
    }

    boolean isRowAvailable(int row) {
        for (int i = 0; i < size; i++) {
            if (chessBoard[row][i] == 1) {
                return false;
            }
        }
        return true;
    }

    boolean isColumnAvailable(int column) {

        for (int i = 0; i < size; i++) {
            if (chessBoard[i][column] == 1) {
                return false;
            }
        }
        return true;
    }

    public int[][] board(){
        return chessBoard;
    }
}

import algorithms.BoardVariable;
import algorithms.NQueensBTAlgorithm;
import algorithms.SudokuBTAlgorithm;
import algorithms.SudokuFCAlgorithm;

public class Main {

    public static void main(String args[]) {
//        NQueensBTAlgorithm nQueensBTAlgorithm = new NQueensBTAlgorithm(5);
//        nQueensBTAlgorithm.printBoard();
//        nQueensBTAlgorithm.algorithm(0);
//        nQueensBTAlgorithm.printBoard();

//        SudokuBTAlgorithm sudokuBTAlgorithm = new SudokuBTAlgorithm(9, 5);
//        sudokuBTAlgorithm.printBoard();
//        sudokuBTAlgorithm = new SudokuBTAlgorithm(4, 8);
//        sudokuBTAlgorithm.printBoard();
//        sudokuBTAlgorithm.algorithm();
//        sudokuBTAlgorithm.printBoard();
        //

        SudokuFCAlgorithm sudokuFCAlgorithm = new SudokuFCAlgorithm(9,30);
        sudokuFCAlgorithm.printBoard();
        System.out.println();
        sudokuFCAlgorithm.solve();
        sudokuFCAlgorithm.printBoard();
    }
}

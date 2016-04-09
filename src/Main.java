import algorithms.algorithm.QueensBT;
import algorithms.algorithm.QueensFC;
import algorithms.algorithm.SudokuBT;
import algorithms.algorithm.SudokuFC;
import algorithms.services.CSP_SERVICE;

public class Main {

    public static void main(String args[]) {
        queensbtTest(10);
        sudokubtTest(9,30);
        queensfcTest(7);
        sudokufcTest(9,36);
    }

    public static void queensbtTest(int problemsize) {
        QueensBT queensbt;
        queensbt = new QueensBT(problemsize);
        System.out.println("Queens BT");
        CSP_SERVICE.printqueensBoard(queensbt.board());
        queensbt.algorithm(0);
        System.out.println();
        CSP_SERVICE.printqueensBoard(queensbt.board());
        System.out.println("*************************************");
    }

    public static void sudokubtTest(int size, int empty) {
        SudokuBT sudokubt;
        sudokubt = new SudokuBT(size, empty);
        System.out.println("Sudoku BT");
        CSP_SERVICE.printBoard(sudokubt.board());
        sudokubt.algorithm();
        System.out.println();
        CSP_SERVICE.printBoard(sudokubt.board());
        System.out.println("*************************************");
    }

    public static void queensfcTest(int problemsize) {
        QueensFC queensfc;
        queensfc = new QueensFC(problemsize);
        System.out.println("Queens FC");
        CSP_SERVICE.printqueensBoard(queensfc.board());
        queensfc.algorithm();
        System.out.println();
        CSP_SERVICE.printqueensBoard(queensfc.board());
        System.out.println("*************************************");
    }

    public static void sudokufcTest(int size, int empty) {
        SudokuFC sudokufc;
        sudokufc = new SudokuFC(size, empty);
        System.out.println("Sudoku FC");
        CSP_SERVICE.printBoard(sudokufc.board());
        sudokufc.algorithm();
        System.out.println();
        CSP_SERVICE.printBoard(sudokufc.board());
        System.out.println("*************************************");
    }

}

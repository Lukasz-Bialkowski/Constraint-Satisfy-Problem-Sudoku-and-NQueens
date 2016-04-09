import algorithms.algorithm.QueensBT;
import algorithms.algorithm.QueensFC;
import algorithms.algorithm.SudokuBT;
import algorithms.algorithm.SudokuFC;
import algorithms.services.SudokuService;

public class Main {

    public static void main(String args[]) {
        sudokubtTest(9, 30);
        queensbtTest(9);
        queensfcTest(9);
        sudokufcTest(9,30);
    }

    public static void queensbtTest(int problemsize) {
        QueensBT queensbt;
        queensbt = new QueensBT(problemsize);
        System.out.println("Queens BT");
        SudokuService.printBoard(queensbt.board());
        queensbt.algorithm(0);
        System.out.println();
        SudokuService.printBoard(queensbt.board());
        System.out.println("*************************************");
    }

    public static void sudokubtTest(int size, int empty) {
        SudokuBT sudokubt;
        sudokubt = new SudokuBT(size, empty);
        System.out.println("Sudoku BT");
        SudokuService.printBoard(sudokubt.board());
        sudokubt.algorithm();
        System.out.println();
        SudokuService.printBoard(sudokubt.board());
        System.out.println("*************************************");
    }

    public static void queensfcTest(int problemsize) {
        QueensFC queensfc;
        queensfc = new QueensFC(problemsize);
        System.out.println("Queens FC");
        SudokuService.printBoard(queensfc.board());
        queensfc.algorithm();
        System.out.println();
        SudokuService.printBoard(queensfc.board());
        System.out.println("*************************************");
    }

    public static void sudokufcTest(int size, int empty) {
        SudokuFC sudokufc;
        sudokufc = new SudokuFC(size, empty);
        System.out.println("Sudoku FC");
        SudokuService.printBoard(sudokufc.board());
        sudokufc.algorithm();
        System.out.println();
        SudokuService.printBoard(sudokufc.board());
        System.out.println("*************************************");
    }

}

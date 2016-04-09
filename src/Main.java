import algorithms.algorithm.QueensBT;
import algorithms.algorithm.SudokuBT;
import algorithms.algorithm.SudokuFC;
import algorithms.devpackage.TestSudoku;
import algorithms.dto.Point;
import algorithms.services.SudokuService;

public class Main {

    public static void main(String args[]) {
//        sudokubtTest(9, 5);
//        queensbtTest(6);

        SudokuFC sudokuFC = new SudokuFC(4,10);
        SudokuService.printBoard(sudokuFC.board());
        sudokuFC.algorithm();
        System.out.println();
        SudokuService.printBoard(sudokuFC.board());

    }

    public static void queensbtTest(int problemsize) {
        QueensBT queensbt;
        queensbt = new QueensBT(problemsize);
        SudokuService.printBoard(queensbt.board());
        System.out.println(queensbt.algorithm(0));
        SudokuService.printBoard(queensbt.board());
    }

    public static void sudokubtTest(int size, int empty) {
        SudokuBT sudokubt;
        sudokubt = new SudokuBT(size, empty);
        SudokuService.printBoard(sudokubt.board());
        sudokubt.algorithm();
        SudokuService.printBoard(sudokubt.board());
    }

//    public static void queensfcTest() {

//        QueensFC queensfc;
//        queensfc = new QueensFC();
//        SudokuService.printBoard(queensfc.board());
//        queensfc.algorithm();
//        SudokuService.printBoard(queensfc.board());
//    }

//    public static void sudokufcTest() {
//        SudokuFC sudokufc;
//        sudokufc = new SudokuBT(9, 5);
//        SudokuService.printBoard(sudokufc.board());
//        sudokufc.algorithm();
//        SudokuService.printBoard(sudokufc.board());
//    }

}

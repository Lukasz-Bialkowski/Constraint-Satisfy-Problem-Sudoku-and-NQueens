import algorithms.Queens;
import algorithms.algorithm.QueensBT;
import algorithms.algorithm.QueensFC;
import algorithms.algorithm.SudokuBT;
import algorithms.algorithm.SudokuFC;
import algorithms.services.CSP_SERVICE;

public class Main {

    public static void main(String args[]) {
//        queensbtTest(17);
//        sudokubtTest(16,136);
//        queensfcTest(17);
//        sudokufcTest(16,136);
    }

    public static void queensbtTest(int problemsize) {
        QueensBT queensbt;
        queensbt = new QueensBT(problemsize);
        System.out.println("Queens BT");
        CSP_SERVICE.printqueensBoard(queensbt.board());
        long start = System.currentTimeMillis();
        queensbt.algorithm(0);
        long time = System.currentTimeMillis() - start;
        System.out.println();
        System.out.println("Execution time: " + time);
        CSP_SERVICE.printqueensBoard(queensbt.board());
        System.out.println("*************************************");
    }

    public static void sudokubtTest(int size, int empty) {
        SudokuBT sudokubt;
        sudokubt = new SudokuBT(size, empty);
        System.out.println("Sudoku BT");
        CSP_SERVICE.printBoard(sudokubt.board());
        long start = System.currentTimeMillis();
        sudokubt.algorithm();
        long time = System.currentTimeMillis() - start;
        System.out.println();
        System.out.println("Execution time: " + time);
        CSP_SERVICE.printBoard(sudokubt.board());
        System.out.println("*************************************");
    }

    public static void queensfcTest(int problemsize) {
        QueensFC queensfc;
        queensfc = new QueensFC(problemsize);
        System.out.println("Queens FC");
        CSP_SERVICE.printqueensBoard(queensfc.board());
        long start = System.currentTimeMillis();
        queensfc.algorithm();
        long time = System.currentTimeMillis() - start;
        System.out.println();
        System.out.println("Execution time: " + time);
        CSP_SERVICE.printqueensBoard(queensfc.board());
        System.out.println("*************************************");
    }

    public static void sudokufcTest(int size, int empty) {
        SudokuFC sudokufc;
        sudokufc = new SudokuFC(size, empty);
        System.out.println("Sudoku FC");
        CSP_SERVICE.printBoard(sudokufc.board());
        long start = System.currentTimeMillis();
        sudokufc.algorithm();
        System.out.println();
        long time = System.currentTimeMillis() - start;
        System.out.println("Execution time: " + time);
        CSP_SERVICE.printBoard(sudokufc.board());
        System.out.println("*************************************");
    }

    public static void sudokufcTestMEDIUM() {
        System.out.println("MRVBV");
        SudokuFC sudokuFC = new SudokuFC(16, 130);
        CSP_SERVICE.printBoard(sudokuFC.board());
        long start = System.currentTimeMillis();
        sudokuFC.algorithmMRVBV();
        long time = System.currentTimeMillis() - start;
        System.out.println("Execution time: " + time);
        System.out.println();
        CSP_SERVICE.printBoard(sudokuFC.board());
//
        System.out.println("MRV");
        sudokuFC = new SudokuFC(16, 130);
        CSP_SERVICE.printBoard(sudokuFC.board());
        start = System.currentTimeMillis();
        sudokuFC.algorithmMRV();
        time = System.currentTimeMillis() - start;
        System.out.println("Execution time: " + time);
        System.out.println();
        CSP_SERVICE.printBoard(sudokuFC.board());
//
        System.out.println("Normal");
        sudokuFC = new SudokuFC(4, 12);
        CSP_SERVICE.printBoard(sudokuFC.board());
        start = System.currentTimeMillis();
        sudokuFC.algorithm();
        time = System.currentTimeMillis() - start;
        System.out.println("Execution time: " + time);
        System.out.println();
        CSP_SERVICE.printBoard(sudokuFC.board());
    }

    public static void sudokubtTestMEDIUM() {
        SudokuBT sudokubt;
        System.out.println("MRV");
        sudokubt = new SudokuBT(9, 75);
        CSP_SERVICE.printBoard(sudokubt.board());
        long start = System.currentTimeMillis();
        sudokubt.algorithmMRV();
        long time = System.currentTimeMillis() - start;
        System.out.println("Execution time: " + time);
        System.out.println();
        CSP_SERVICE.printBoard(sudokubt.board());
//
        sudokubt = new SudokuBT(16, 140);
        System.out.println("Normal");
        CSP_SERVICE.printBoard(sudokubt.board());
        start = System.currentTimeMillis();
        sudokubt.algorithm();
        time = System.currentTimeMillis() - start;
        System.out.println("Execution time: " + time);
        System.out.println();
        CSP_SERVICE.printBoard(sudokubt.board());
    }

    public static void queensFCTestMEDIUM() {
        QueensFC queensFC;
        System.out.println("MRV");
        queensFC = new QueensFC(14);
        CSP_SERVICE.printBoard(queensFC.board());
        long start = System.currentTimeMillis();
        queensFC.algorithmMRV();
        long time = System.currentTimeMillis() - start;
        System.out.println("Execution time: " + time);
        System.out.println();
        CSP_SERVICE.printBoard(queensFC.board());

        queensFC = new QueensFC(12);
        System.out.println("Normal");
        CSP_SERVICE.printBoard(queensFC.board());
        start = System.currentTimeMillis();
        queensFC.algorithm();
        time = System.currentTimeMillis() - start;
        System.out.println("Execution time: " + time);
        System.out.println();
        CSP_SERVICE.printBoard(queensFC.board());
    }

    public static void queensBTTestMEDIUM() {

        QueensBT queensBT;
        System.out.println("MRV");
        queensBT = new QueensBT(7);
        CSP_SERVICE.printBoard(queensBT.board());
        long start = System.currentTimeMillis();
        boolean b = queensBT.algorithmMRV();
        long time = System.currentTimeMillis() - start;
        System.out.println("Execution time: " + time);
        System.out.println(b);
        CSP_SERVICE.printBoard(queensBT.board());

        queensBT = new QueensBT(26);
        System.out.println("Normal");
        CSP_SERVICE.printBoard(queensBT.board());
        start = System.currentTimeMillis();
        queensBT.algorithm(0);
        time = System.currentTimeMillis() - start;
        System.out.println("Execution time: " + time);
        System.out.println();
        CSP_SERVICE.printBoard(queensBT.board());
    }

}

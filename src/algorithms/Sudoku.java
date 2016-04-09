package algorithms;

import algorithms.algorithm.SudokuBT;
import algorithms.algorithm.SudokuFC;
import algorithms.services.CSP_SERVICE;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by luke_bialkowski on 4/9/2016.
 */
public class Sudoku {
    private JPanel MainPanel;
    private JComboBox comboBox1;
    private JButton startButton;
    private JRadioButton BacktrackingAlgorithm;
    private JRadioButton ForwardCheckingAlgorithm;
    private JSpinner spinner1;

    public Sudoku() {

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SudokuFC sudokuFC;
                SudokuBT sudokuBT;
                String boardInitialyAsAString;
                String boardAfterAlgorithm;
                int emptyPoints = (Integer) spinner1.getValue();
                String problemSize = (String) comboBox1.getSelectedItem();
                int iProblemSize = Integer.parseInt(problemSize);

                if (BacktrackingAlgorithm.isSelected()) {

                    sudokuBT = new SudokuBT(iProblemSize, emptyPoints);

                    boardInitialyAsAString = CSP_SERVICE.generateStringSudoku(sudokuBT.board());
                    JOptionPane.showMessageDialog(null, "Plansza poczatkowa:\n\n" + boardInitialyAsAString);

                    sudokuBT.algorithm();

                    boardAfterAlgorithm = CSP_SERVICE.generateStringSudoku(sudokuBT.board());
                    JOptionPane.showMessageDialog(null, "Rozwiazane zadanie:\n\n" + boardAfterAlgorithm);

                } else {

                    sudokuFC = new SudokuFC(iProblemSize, emptyPoints);

                    boardInitialyAsAString = CSP_SERVICE.generateStringSudoku(sudokuFC.board());
                    JOptionPane.showMessageDialog(null, "Plansza poczatkowa:\n\n" + boardInitialyAsAString);

                    sudokuFC.algorithm();

                    boardAfterAlgorithm = CSP_SERVICE.generateStringSudoku(sudokuFC.board());
                    JOptionPane.showMessageDialog(null, "Rozwiazane zadanie:\n\n" + boardAfterAlgorithm);
                }

            }
        });
    }

    public static void main(String[] args) {
        JFrame window = new JFrame("SUDOKU CSP PROBLEM SOLVER");
        window.setContentPane(new Sudoku().MainPanel);
        window.setVisible(true);
        window.pack();
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}

package algorithms;

import algorithms.algorithm.QueensBT;
import algorithms.algorithm.QueensFC;
import algorithms.services.CSP_SERVICE;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by luke_bialkowski on 4/9/2016.
 */
public class Queens {
    private JPanel panel1;
    private JRadioButton BacktrackingAlgorithm;
    private JRadioButton ForwardCheckingAlgorithm;
    private JSpinner spinner1;
    private JButton button1;
    private JCheckBox MRVCheckBox;

    public Queens() {
        panel1.setSize(400, 600);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                QueensBT queensBT;
                QueensFC queensFC;
                String boardInitialyAsAString;
                String boardAfterAlgorithm;
                int problemSize = (Integer) spinner1.getValue();
                long start;
                long time;

                if (BacktrackingAlgorithm.isSelected()) {

                    if (MRVCheckBox.isSelected()) {
                        queensBT = new QueensBT(problemSize);
                        boardInitialyAsAString = CSP_SERVICE.htmlArray(queensBT.board());
                        System.out.println("QUEENS BACKTRACKING MRV");
                        start = System.currentTimeMillis();
                        // SOLVING PROBLEM
                        queensBT.algorithm(0);
                        time = System.currentTimeMillis() - start;
                        boardAfterAlgorithm = CSP_SERVICE.htmlArray(queensBT.board());
                    } else {
                        queensBT = new QueensBT(problemSize);
                        boardInitialyAsAString = CSP_SERVICE.htmlArray(queensBT.board());
                        System.out.println("QUEENS BACKTRACKING CLEAN");
                        start = System.currentTimeMillis();
                        // SOLVING PROBLEM
                        queensBT.algorithm(0);
                        time = System.currentTimeMillis() - start;
                        boardAfterAlgorithm = CSP_SERVICE.htmlArray(queensBT.board());
                    }

                } else {

                    if (MRVCheckBox.isSelected()) {
                        queensFC = new QueensFC(problemSize);
                        boardInitialyAsAString = CSP_SERVICE.htmlArray(queensFC.board());
                        System.out.println("QUEENS FORWARD CHECKING MRV");
                        start = System.currentTimeMillis();
                        // SOLVING PROBLEM
                        queensFC.algorithmMRV();
                        time = System.currentTimeMillis() - start;
                        boardAfterAlgorithm = CSP_SERVICE.htmlArray(queensFC.board());
                    } else {
                        queensFC = new QueensFC(problemSize);
                        boardInitialyAsAString = CSP_SERVICE.htmlArray(queensFC.board());
                        System.out.println("QUEENS FORWARD CHECKING CLEAN");
                        start = System.currentTimeMillis();
                        // SOLVING PROBLEM
                        queensFC.algorithm();
                        time = System.currentTimeMillis() - start;
                        boardAfterAlgorithm = CSP_SERVICE.htmlArray(queensFC.board());
                    }

                }

                Output output = new Output();
                JFrame outputWindow = new JFrame("CSP PROBLEM SOLVED");
                output.setOut(boardAfterAlgorithm);
                output.setIn(boardInitialyAsAString);
                output.setExecTime(time + "ms");
                outputWindow.setContentPane(output.getPanel1());
                outputWindow.pack();
                outputWindow.setVisible(true);
                outputWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            }
        });
    }

    public static void main(String[] args) {

        JFrame window = new JFrame("NQUEENS CSP PROBLEM SOLVER");
        window.setContentPane(new Queens().panel1);
        window.setVisible(true);
        window.pack();
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }
}

package algorithms;

import javax.swing.*;

/**
 * Created by luke_bialkowski on 4/9/2016.
 */
public class Output {
    private JPanel panel1;
    private JLabel execTime;
    private JFormattedTextField initBoard;
    private JFormattedTextField outputTable;
    public JLabel out;
    public JLabel in;

    public Output() {
        panel1.setSize(600, 500);
    }

    public JLabel getOut() {
        return out;
    }

    public void setOut(JLabel out) {
        this.out = out;
    }

    public JLabel getIn() {
        return in;
    }

    public void setIn(JLabel in) {
        this.in = in;
    }

    public JPanel getPanel1() {
        return panel1;
    }

    public void setPanel1(JPanel panel1) {
        this.panel1 = panel1;
    }

    public void setExecTime(JLabel execTime) {
        this.execTime = execTime;
    }

    public JFormattedTextField getInitBoard() {
        return initBoard;
    }

    public void setInitBoard(String initBoard) {
        this.initBoard.setText(initBoard);
    }

    public JFormattedTextField getOutputTable() {
        return outputTable;
    }

    public void setOutputTable(String outputTable) {
        this.outputTable.setText(outputTable);
    }

    public JLabel getExecTime() {
        return execTime;
    }

    public void setExecTime(String execTime) {
        this.execTime.setText(execTime);
    }

    public void setOut(String boardAfterAlgorithm) {

        this.out.setText(boardAfterAlgorithm);
    }

    public void setIn(String boardInitialyAsAString) {

        this.in.setText(boardInitialyAsAString);
    }
}

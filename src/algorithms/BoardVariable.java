package algorithms;

public class BoardVariable {

    int row;
    int column;

    @Override
    public String toString() {
        return "BoardVariable{" +
                "row=" + row +
                ", column=" + column +
                '}';
    }

    public BoardVariable(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
}

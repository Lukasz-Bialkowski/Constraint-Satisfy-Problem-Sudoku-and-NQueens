package algorithms;
import java.util.ArrayList;
import java.util.HashSet;

public class SudokuFCAlgorithm {
    int[][] sudokuBoard;
    int size;
    ArrayList<ArrayList<Integer>> D; // = new ArrayList<ArrayList<Integer>>();

    public SudokuFCAlgorithm(int boardSize, int emptyVariables) {
        size = (int) Math.sqrt(boardSize);
        sudokuBoard = createSudokuBoard(boardSize, emptyVariables);

//        sudokuBoard = new int[size * size][size * size];
        D = new ArrayList<ArrayList<Integer>>(size * size * size * size);
        initializeDomain();
    }

    public int[][] createSudokuBoard(int boardSize, int emptyVariables) {

        int[][] sudoku;

        switch (boardSize) {
            case 4:
                int[][] temp1 = {{3, 1, 2, 4}, {4, 2, 3, 1}, {1, 3, 4, 2}, {2, 4, 1, 3}};
                sudoku = temp1;
                break;
            default:
                int[][] temp2 =
                        {
                                {3, 1, 6, 5, 7, 8, 4, 9, 2},
                                {5, 2, 9, 1, 3, 4, 7, 6, 8},
                                {4, 8, 7, 6, 2, 9, 5, 3, 1},
                                {2, 6, 3, 4, 1, 5, 9, 8, 7},
                                {9, 7, 4, 8, 6, 3, 1, 2, 5},
                                {8, 5, 1, 7, 9, 2, 6, 4, 3},
                                {1, 3, 8, 9, 4, 7, 2, 5, 6},
                                {6, 9, 2, 3, 5, 1, 8, 7, 4},
                                {7, 4, 5, 2, 8, 6, 3, 1, 9}
                        };
                sudoku = temp2;
                break;
        }

        HashSet<Integer> set = new HashSet<>();
        while(set.size() != emptyVariables)
            set.add(randomizer(boardSize*boardSize));
        for (int i : set) {
            sudoku[i/boardSize][i%boardSize]=0;
        }
        return sudoku;
    }

    public int randomizer(int max) {
        return (int) (Math.random() * max);
    }

    public void initializeDomain() {
        ArrayList<Integer> allvalues= new ArrayList<Integer>();
        for (int i= 1;i<=size*size;i++){
            allvalues.add(i);
        }
        for (int i = 0; i < size * size * size * size; i++) {
            D.add(new ArrayList<Integer>(allvalues));
        }
    }

    public boolean solve() {
        ArrayList<Integer> sudokuAsList = GetAssignment(sudokuBoard);
        if (INITIAL_FC(sudokuAsList)) {
            sudokuAsList = FC(sudokuAsList);
            if (sudokuAsList != null) {
                sudokuBoard = GetPuzzle(sudokuAsList);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public ArrayList<Integer> FC(ArrayList<Integer> asn) {
        boolean complete = true;
        int X;
        for (X = 0; X < asn.size(); X++) {
            if (asn.get(X) == 0) {
                complete = false;
                break;
            }
        }
        if (complete) {
            return asn;
        } else {
            ArrayList<ArrayList<Integer>> Dold = new ArrayList<ArrayList<Integer>>(	size * size * size * size);
            for (int i = 0; i < D.size(); i++) {
                ArrayList<Integer> DoldElement = new ArrayList<Integer>(D.get(i));
                Dold.add(DoldElement);
            }
            ArrayList <Integer> temp = new ArrayList<Integer>(D.get(X));
            for (Integer V : temp) {
                if (AC_FC(X, V)) {
                    asn.set(X, V);
                    ArrayList<Integer> R = FC(asn);
                    if (R != null) {
                        return R;
                    } else {
                        asn.set(X, 0);
                        for (int i = 0; i < D.size(); i++) {
                            D.set(i, new ArrayList<Integer>( Dold.get(i)));
                        }
                    }
                } else {
                    for (int i = 0; i < D.size(); i++) {
                        D.set(i,  new ArrayList<Integer>( Dold.get(i)));
                    }
                }
            }
        }
        return null;// failure
    }

    public boolean AC_FC(Integer X, Integer V) {
        D.get(X).clear();
        D.get(X).add(V);
        ArrayList<Integer> Q = new ArrayList<Integer>(); // list of all relevant

        int col = GetColumn(X);
        int row = GetRow(X);
        int cell_x = row / size;
        int cell_y = col / size;

        // all variables in the same column
        for (int i = 0; i < size * size; i++) {
            if (GetVariable(i, col) > X) {
                Q.add(GetVariable(i, col));
            }
        }
        // all variables in the same row
        for (int j = 0; j < size * size; j++) {
            if (GetVariable(row, j) > X) {
                Q.add(GetVariable(row, j));
            }
        }
        // all variables in the same size*size box
        for (int i = cell_x * size; i <= cell_x * size + 2; i++) {
            for (int j = cell_y * size; j <= cell_y * size + 2; j++) {
                if (GetVariable(i, j) > X) {
                    Q.add(GetVariable(i, j));
                }
            }
        }

        // REVISE(Y,X)
        boolean consistent = true;
        while (!Q.isEmpty() && consistent) {
            Integer Y = (Integer) Q.remove(0);
            if (REVISE(Y, X)) {
                consistent = !D.get(Y).isEmpty();
            }
        }
        return consistent;
    }

    public boolean REVISE(int neighbourIndex, int selectedVariable) {

        boolean DELETED = false;
        ArrayList<Integer> neighbourDomain = D.get(neighbourIndex);
        ArrayList<Integer> selectedDomain = D.get(selectedVariable);

        for (int i = 0; i < neighbourDomain.size(); i++) {
            int vi = neighbourDomain.get(i);
            ArrayList<Integer> xiEqVal = new ArrayList<Integer>(sudokuBoard.length * sudokuBoard.length);
            for (int var = 0; var < sudokuBoard.length * sudokuBoard.length; var++) {
                xiEqVal.add(var, 0);
            }

            xiEqVal.set(neighbourIndex, vi);
            boolean hasSupport = false;

            for (int j = 0; j < selectedDomain.size(); j++) {
                Integer vj = (Integer) selectedDomain.get(j);
                if (CONSISTENT(xiEqVal, selectedVariable, vj)) {
                    hasSupport = true;
                    break;
                }
            }

            if (!hasSupport) {
                neighbourDomain.remove((Integer) vi);
                DELETED = true;
            }

        }

        return DELETED;
    }

    public boolean CONSISTENT(ArrayList<Integer> asn, Integer variable,
                              Integer val) {
        Integer v1, v2;

        // variable to be assigned must be clear
        assert (asn.get(variable) == 0);
        asn.set(variable, val);

        // alldiff(col[i])
        for (int i = 0; i < size * size; i++) {
            for (int j = 0; j < size * size; j++) {
                for (int k = 0; k < size * size; k++) {
                    if (k != j) {
                        v1 = (Integer) asn.get(GetVariable(i, j));
                        v2 = (Integer) asn.get(GetVariable(i, k));
                        if (v1 != 0 && v2 != 0 && v1.compareTo(v2) == 0) {
                            asn.set(variable, 0);
                            return false;
                        }
                    }
                }
            }
        }

        // alldiff(row[j])
        for (int j = 0; j < size * size; j++) {
            for (int i = 0; i < size * size; i++) {
                for (int k = 0; k < size * size; k++) {
                    if (k != i) {
                        v1 = (Integer) asn.get(GetVariable(i, j));
                        v2 = (Integer) asn.get(GetVariable(k, j));
                        if (v1 != 0 && v2 != 0 && v1.compareTo(v2) == 0) {
                            asn.set(variable, 0);
                            return false;
                        }
                    }
                }
            }
        }

        // alldiff(block[size*i,size*j])
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                for (int i1 = 0; i1 < size; i1++) {
                    for (int j1 = 0; j1 < size; j1++) {
                        int var1 = GetVariable(size * i + i1, size * j + j1);
                        for (int i2 = 0; i2 < size; i2++) {
                            for (int j2 = 0; j2 < size; j2++) {
                                int var2 = GetVariable(size * i + i2, size * j
                                        + j2);
                                if (var1 != var2) {
                                    v1 = (Integer) asn.get(var1);
                                    v2 = (Integer) asn.get(var2);
                                    if (v1 != 0 && v2 != 0
                                            && v1.compareTo(v2) == 0) {
                                        asn.set(variable, 0);
                                        return false;
                                    }
                                }
                            }
                        }

                    }
                }
            }
        }

        asn.set(variable, 0);
        return true;
    }

    public boolean INITIAL_FC(ArrayList<Integer> sudokuVariables) {

        for (int i = 0; i < sudokuVariables.size(); i++) {
            int sudokuVariable = sudokuVariables.get(i);

            if (sudokuVariable != 0) {

                ArrayList<Integer> neighbours = constrainsNeighboursIndexes(i);

                boolean domainIsEmpty = true;

                for (int neighbourIndex : neighbours) {
                    // usun z dziedziny ta wartosc
                    // sprawdz czy nie jest po usunieciu pusta
                }

                while (!neighbours.isEmpty() && consistent) {
                    int Y = neighbours.remove(0);
                    if (REVISE(Y, i)) {
                        consistent = !D.get(Y).isEmpty();
                    }
                }
            }
        }

        return true;
    }

    public ArrayList<Integer> constrainsNeighboursIndexes(int selectedVariableIndex) {
        ArrayList<Integer> neighboursIndexes = new ArrayList<Integer>();

        int col = columnInBoard(selectedVariableIndex);
        int row = rowInBoard(selectedVariableIndex);
        // VARIABLES FROM COLUMN

        for (int i = 0; i < sudokuBoard.length; i++) {
            if (variableIndex(i, col) != selectedVariableIndex) {
                neighboursIndexes.add(variableIndex(i, col));
            }
        }
        // VARIABLES FROM ROW
        for (int j = 0; j < sudokuBoard.length; j++) {
            if (variableIndex(row, j) != selectedVariableIndex) {
                neighboursIndexes.add(variableIndex(row, j));
            }
        }

        // VARIABLES FROM CUBE
        int cell_x = row / size;
        int cell_y = col / size;

        for (int i = cell_x * size; i <= cell_x * size + 2; i++) {
            for (int j = cell_y * size; j <= cell_y * size + 2; j++) {
                if (variableIndex(i, j) != selectedVariableIndex) {
                    neighboursIndexes.add(variableIndex(i, j));
                }
            }
        }

        return neighboursIndexes;
    }

    public ArrayList<Integer> generateSudokuVariables() {

        ArrayList<Integer> sudokuVariables = new ArrayList<Integer>();
        for (int i = 0; i < sudokuBoard.length; i++) {
            for (int j = 0; j < sudokuBoard.length; j++) {

                int variableIndex = variableIndex(i, j);
                sudokuVariables.add(variableIndex, sudokuBoard[i][j]);

                if (sudokuBoard[i][j] != 0) {
                    D.get(variableIndex).clear();
                    D.get(variableIndex).add(sudokuBoard[i][j]);
                }
            }
        }
        return sudokuVariables;
    }

    public int variableIndex(int row, int column) {
        return (row * sudokuBoard.length + column);
    }

    public int rowInBoard(int variableIndex) {
        return (variableIndex / (sudokuBoard.length));
    }

    public int columnInBoard(int variableIndex) {
        return variableIndex - ((variableIndex / (sudokuBoard.length)) * sudokuBoard.length);
    }

    public void printBoard() {
        for (int i = 0; i < sudokuBoard.length; i++) {
            for (int j = 0; j < sudokuBoard.length; j++) {
                System.out.print(sudokuBoard[i][j]+" ");
            }
            System.out.println();
        }
    }

}

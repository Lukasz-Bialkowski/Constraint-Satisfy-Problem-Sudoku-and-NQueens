package algorithms.algorithm;

import algorithms.dto.Point;
import algorithms.services.CSP_SERVICE;

import java.util.*;

public class QueensBT {

    private int size;
    private int chessBoard[][];
    private int boxSize;
    int queensPlaced = 0;

    public QueensBT(int problemSize) {
        size = problemSize;
        boxSize = (int) Math.sqrt(problemSize);
        chessBoard = createAndInitializeBoard(size);
    }

    public boolean algorithm(int i) {
        if(i== size){
            return true;
        }
        for (int j = 0; j < size; j++) {
            if (isPlacementCorrect(i, j)){
                chessBoard[i][j] = 1;
                if(algorithm(i+1)){return true;}
            }
            chessBoard[i][j] = 0;
        }
        return false;
    }

    public boolean algorithmMRV() {
        int[][] domain;
        domain = calculateDomain();
        List<Point> points = findEmptyPoints();
        List<Point> pointsD = findEmptyPoints(domain);
        if (pointsD.size() <= 0) {
            return true;
        }

        HashMap<Point, Integer> domainValueDomainSumms = new HashMap<>();
        for (Point point : points) {
            domainValueDomainSumms.put(point, calculateNumberOfConstraintVariables(point, domain));
        }

        List<Point> pointsSorted = QueensBT.sortByValueAndExtractList(domainValueDomainSumms);

        for (Point point : points) {
            int i = point.getRow();
            int j = point.getColumn();
            if (isPlacementCorrect(i, j)) {
                chessBoard[i][j] = 1;
                queensPlaced++;
                if (algorithmMRV()) {
                    return true;
                }
            }
            chessBoard[i][j] = 0;
            queensPlaced--;
        }
        return false;
    }

    public int[][] calculateDomain() {

        int[][] domain = new int[size][size];

        for (int i = 0; i < boxSize * boxSize; i++) {
            for (int j = 0; j < boxSize * boxSize; j++) {
                if (chessBoard[i][j] != 0) {
                    calculateDomainFor(new Point(i, j), domain);
                }
            }
        }

        return domain;
    }

    public void calculateDomainFor(Point point, int[][] domain) {

        int row = point.getRow();
        int column = point.getColumn();

        for (int i = 0; i < this.size; i++) {
            domain[i][column] += 1;
            domain[row][i] += 1;
        }

        int i = row;
        int j = column;
        while (i < size && i >= 0 && j < size && j >= 0) {
            domain[i][j] += 1;
            i--;
            j++;
        }
        i = row;
        j = column;
        while (i < size && i >= 0 && j < size && j >= 0) {
            domain[i][j] += 1;
            i++;
            j--;
        }
        i = row;
        j = column;
        while (i < size && i >= 0 && j < size && j >= 0) {
            domain[i][j] += 1;
            i--;
            j--;
        }
        i = row;
        j = column;
        while (i < size && i >= 0 && j < size && j >= 0) {
            domain[i][j] += 1;
            i++;
            j++;
        }
        i = row;
        j = column;
    }

    private Integer calculateNumberOfConstraintVariables(Point point, int[][] domain) {
        int sum = 0;

        int column = point.getColumn();
        int row = point.getRow();

        for (int i = 0; i < this.size; i++) {
            if (domain[i][column] == 0)
                sum += 1;
            if (domain[row][i] == 0)
                sum += 1;
        }

        int i = row;
        int j = column;
        while (i < size && i >= 0 && j < size && j >= 0) {
            if (domain[i][j] == 0) sum += 1;
            i--;
            j++;
        }
        i = row;
        j = column;
        while (i < size && i >= 0 && j < size && j >= 0) {
            if (domain[i][j] == 0) sum += 1;
            i++;
            j--;
        }
        i = row;
        j = column;
        while (i < size && i >= 0 && j < size && j >= 0) {
            if (domain[i][j] == 0) sum += 1;
            i--;
            j--;
        }
        i = row;
        j = column;
        while (i < size && i >= 0 && j < size && j >= 0) {
            if (domain[i][j] == 0) sum += 1;
            i++;
            j++;
        }
        i = row;
        j = column;


        return sum;
    }

    public static <K, V extends Comparable<? super V>> List<K> sortByValueAndExtractList(Map<K, V> map) {
        List<Map.Entry<K, V>> list =
                new LinkedList<Map.Entry<K, V>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        List<K> result = new ArrayList<>();
        for (Map.Entry<K, V> entry : list) {
            result.add(entry.getKey());
        }
        return result;
    }

    private int[][] createAndInitializeBoard(int boardSize) {

        int[][] temp = new int[boardSize][];
        for (int i = 0; i < size; i++) {
            int[] row = new int[size];
            for (int j = 0; j < size; j++) {
                row[j] = 0;
            }
            temp[i] = row;
        }
        return temp;
    }

    boolean isPlacementCorrect(int row, int column) {
        if (!isRowAvailable(row) || !isColumnAvailable(column) || !isCrossingAvailable(row, column)) {
            return false;
        } else {
            return true;
        }
    }

    boolean isCrossingAvailable(int row, int column) {
        int i = row;
        int j = column;
        while (i < size && i >= 0 && j < size && j >= 0) {
            if(chessBoard[i][j] == 1){
                return false;
            }
            i--;
            j++;
        }
        i = row;
        j = column;
        while (i < size && i >= 0 && j < size && j >= 0) {
            if(chessBoard[i][j] == 1){
                return false;
            }
            i++;
            j--;
        }
        i = row;
        j = column;
        while (i < size && i >= 0 && j < size && j >= 0) {
            if(chessBoard[i][j] == 1){
                return false;
            }
            i--;
            j--;
        }
        i = row;
        j = column;
        while (i < size && i >= 0 && j < size && j >= 0) {
            if(chessBoard[i][j] == 1){
                return false;
            }
            i++;
            j++;
        }
        i = row;
        j = column;

        return true;
    }

    boolean isRowAvailable(int row) {
        for (int i = 0; i < size; i++) {
            if (chessBoard[row][i] == 1) {
                return false;
            }
        }
        return true;
    }

    boolean isColumnAvailable(int column) {

        for (int i = 0; i < size; i++) {
            if (chessBoard[i][column] == 1) {
                return false;
            }
        }
        return true;
    }

    public List<Point> findEmptyPoints() {
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (chessBoard[i][j] == 0) {
                    points.add(new Point(i, j));
                }
            }
        }
        return points;
    }

    public List<Point> findEmptyPoints(int[][] domain) {

        List<Point> points = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (domain[i][j] == 0) {
                    points.add(new Point(i, j));
                }
            }
        }
        return points;
    }

    public int[][] board(){
        return chessBoard;
    }
}

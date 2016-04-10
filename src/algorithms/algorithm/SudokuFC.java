package algorithms.algorithm;

import algorithms.dto.Point;
import algorithms.services.CSP_SERVICE;

import java.util.*;
import java.util.stream.Stream;

public class SudokuFC {

    int boxSize;
    int[][] sudoku;
    List<HashSet<Integer>> columnVorbiddenDomains;
    List<HashSet<Integer>> boxVorbiddenDomains;
    List<HashSet<Integer>> rowVorbiddenDomains;

    public SudokuFC(int boardSize, int empty) {
        this.boxSize = (int) Math.sqrt(boardSize);
        this.sudoku = CSP_SERVICE.createSudokuBoard(boardSize, empty);
        columnVorbiddenDomains = new ArrayList<>(boardSize);
        boxVorbiddenDomains = new ArrayList<>(boardSize);
        rowVorbiddenDomains = new ArrayList<>(boardSize);
        initDomains();
    }

    public boolean algorithm() {
        Point point = findEmpty();
        if (point == null) {return true;}
        List<Integer> availableValuesFor = availableValueFor(point);
        for (Integer value : availableValuesFor) {
            setValue(point, value);
            refreshDomains(point);
            if (isDomainEmpty()) {
            } else {
                if (algorithm()) {
                    return true;
                }
            }
            setValue(point, 0);
            refreshDomains(point);

        }
        return false;
    }

    public boolean algorithmMRV() {

        List<Point> points = findEmptyPoints();
        if (points.isEmpty()) {
            return true;
        }

        Point bestPointSoFar;
        int minimalDomainSize;
        bestPointSoFar = points.get(0);
        minimalDomainSize = availableValueFor(bestPointSoFar).size();

        for (Point point : points) {
            int domainSize = availableValueFor(point).size();
            if (domainSize < minimalDomainSize) {
                minimalDomainSize = domainSize;
                bestPointSoFar = point;
            }
        }

        List<Integer> availableValuesFor = availableValueFor(bestPointSoFar);
        for (Integer value : availableValuesFor) {
            setValue(bestPointSoFar, value);
            refreshDomains(bestPointSoFar);
            if (isDomainEmpty()) {
            } else {
                if (algorithmMRV()) {
                    return true;
                }
            }
            setValue(bestPointSoFar, 0);
            refreshDomains(bestPointSoFar);

        }
        return false;
    }

    public boolean algorithmMRVBV() {

        List<Point> points = findEmptyPoints();
        if (points.isEmpty()) {
            return true;
        }

        Point bestPointSoFar;
        int minimalDomainSize;
        bestPointSoFar = points.get(0);
        minimalDomainSize = availableValueFor(bestPointSoFar).size();

        for (Point point : points) {
            int domainSize = availableValueFor(point).size();
            if (domainSize < minimalDomainSize) {
                minimalDomainSize = domainSize;
                bestPointSoFar = point;
            }
        }

        HashMap<Integer, Integer> domainValueDomainSumms = new HashMap<>();
        List<Integer> pointDomain = availableValueFor(bestPointSoFar);

        for (Integer pointDomainValue : pointDomain) {
            setValue(bestPointSoFar, pointDomainValue);
            refreshDomains(bestPointSoFar);

            domainValueDomainSumms.put(pointDomainValue, calculateDomainSizesOfNeighbours());

            setValue(bestPointSoFar, 0);
            refreshDomains(bestPointSoFar);
        }

        List<Integer> domainsSorted = SudokuFC.sortByValueAndExtractList(domainValueDomainSumms);

        for (Integer value : domainsSorted) {
            setValue(bestPointSoFar, value);
            refreshDomains(bestPointSoFar);
            if (isDomainEmpty()) {
            } else {
                if (algorithmMRVBV()) {
                    return true;
                }
            }
            setValue(bestPointSoFar, 0);
            refreshDomains(bestPointSoFar);

        }
        return false;
    }

    private Integer calculateDomainSizesOfNeighbours() {
        int summ = 0;
        List<Point> emptyPoints = findEmptyPoints();
        for (Point point : emptyPoints) {
            summ += availableValueFor(point).size();
        }
        return summ;
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

    public void initDomains(){
        for (int row = 0; row < boxSize*boxSize; row++) {
            HashSet<Integer> neighboursRow= new HashSet<Integer>();
            for (int column = 0; column < boxSize * boxSize; column++) {
                neighboursRow.add(sudoku[row][column]);
            }
            this.rowVorbiddenDomains.add(neighboursRow);
        }
        for (int column= 0; column < boxSize*boxSize; column++) {
            HashSet<Integer> neighboursColumn= new HashSet<Integer>();
            for (int row = 0; row < boxSize * boxSize; row++) {
                neighboursColumn.add(sudoku[row][column]);
            }
            this.columnVorbiddenDomains.add(neighboursColumn);
        }
        for (int row = 0; row < boxSize * boxSize; row+=boxSize) {
            for (int column= 0; column < boxSize*boxSize; column+=boxSize) {

                HashSet<Integer> neighboursBox = new HashSet<Integer>();
                int cell_x = row / boxSize;
                int cell_y = column / boxSize;
                for (int i = cell_x * boxSize; i <= cell_x * boxSize + boxSize-1; i++) {
                    for (int j = cell_y * boxSize; j <= cell_y * boxSize + boxSize-1; j++) {
                        neighboursBox.add(sudoku[i][j]);
                    }
                }

                this.boxVorbiddenDomains.add(neighboursBox);
            }
        }


    }

    public HashSet<Integer> refreshDomains(Point point) {

        HashSet<Integer> neighboursColumn = new HashSet<Integer>();
        HashSet<Integer> neighboursRow= new HashSet<Integer>();
        HashSet<Integer> neighboursBox = new HashSet<Integer>();

        int column = point.getColumn();
        int row = point.getRow();

        for (int i = 0; i < boxSize * boxSize; i++) {
            neighboursColumn.add(sudoku[i][column]);
            neighboursRow.add(sudoku[row][i]);
        }

        int cell_x = row / boxSize;
        int cell_y = column / boxSize;
        for (int i = cell_x * boxSize; i <= cell_x * boxSize + boxSize-1; i++) {
            for (int j = cell_y * boxSize; j <= cell_y * boxSize + boxSize-1; j++) {
                neighboursBox.add(sudoku[i][j]);
            }
        }

        // Podmieniam wartosci dziedziny
        this.columnVorbiddenDomains.set(column, neighboursColumn);
        this.rowVorbiddenDomains.set(row, neighboursRow);
        this.boxVorbiddenDomains.set(boxIndex(row, column), neighboursBox);

        HashSet<Integer> result = new HashSet<>();
        result.addAll(neighboursBox);
        result.addAll(neighboursRow);
        result.addAll(neighboursColumn);

        return result;
    }

    public HashSet<Integer> availableValuesFromVorbidden(Set<Integer> vorbiddenValues){
        HashSet<Integer> domain = new HashSet<>();
        for (int i = 1; i<= boxSize * boxSize; i++) { domain.add(i); }
        domain.removeAll(vorbiddenValues);
        return domain;
    }

    public List<Integer> availableValueFor(Point point){
        int row = point.getRow();
        int column = point.getColumn();
        HashSet<Integer> neighboursColumn = this.columnVorbiddenDomains.get(column);
        HashSet<Integer> neighboursRow = this.rowVorbiddenDomains.get(row);
        HashSet<Integer> neighboursBox = this.boxVorbiddenDomains.get(boxIndex(row, column));

        HashSet<Integer> result = new HashSet<>();
        result.addAll(neighboursColumn);
        result.addAll(neighboursRow);
        result.addAll(neighboursBox);

//        System.out.println("Box domains: " + neighboursBox);
//        System.out.println("Column domains: " + neighboursColumn);
//        System.out.println("Row domains: " + neighboursRow);
//        System.out.println("Vorbidden values: " + result);

        HashSet<Integer> availableValues = availableValuesFromVorbidden(result);
        List<Integer> avList = new ArrayList<>(availableValues);
        return avList;
    }

    public int boxIndex(int row, int column) {
        int cell_x = row / boxSize;
        int cell_y = column / boxSize;
        return (cell_x * boxSize + cell_y);
    }

    public Point findEmpty() {
        for (int i = 0; i < boxSize * boxSize; i++) {
            for (int j = 0; j < boxSize * boxSize; j++) {
                if (sudoku[i][j] == 0) {
                    return new Point(i, j);
                }
            }
        }
        return null;
    }

    public List<Point> findEmptyPoints() {
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < boxSize * boxSize; i++) {
            for (int j = 0; j < boxSize * boxSize; j++) {
                if (sudoku[i][j] == 0) {
                    points.add(new Point(i, j));
                }
            }
        }
        return points;
    }

    public void setValue(Point point, Integer value) {
        int row = point.getRow();
        int column = point.getColumn();
        sudoku[row][column] = value;
    }

    public boolean isDomainEmpty() {
        for (int i = 0; i < boxSize * boxSize; i++) {
            for (int j = 0; j < boxSize * boxSize; j++) {
                if (sudoku[i][j] == 0) {
                    if(isDomainEmptyFor(new Point(i, j))){
                        return true;
                    };
                }
            }
        }
        return false;
    }

    public boolean isDomainEmptyFor(Point point) {
        int row = point.getRow();
        int column = point.getColumn();
        HashSet<Integer> columnDomains = this.columnVorbiddenDomains.get(column);
        HashSet<Integer> boxDomains = this.rowVorbiddenDomains.get(row);
        HashSet<Integer> rowDomains = this.boxVorbiddenDomains.get(boxIndex(row, column));

        HashSet<Integer> result = new HashSet<>();
        result.addAll(columnDomains);
        result.addAll(boxDomains);
        result.addAll(rowDomains);
        HashSet<Integer> availableValues = availableValuesFromVorbidden(result);
        if (availableValues.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public int[][] board() {
        return sudoku;
    }
}

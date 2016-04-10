package algorithms.algorithm;

import algorithms.dto.Point;

import java.util.*;

public class QueensFC {

    int[][] board;
    int[][] domain;
    int queensPlacedCounter;
    int size;

    public QueensFC(int size) {
      this.board = new int[size][size];
      this.domain = new int[size][size];
      this.queensPlacedCounter = 0;
      this.size = size;
      initDomains();
    }

    public boolean algorithm() {
        List<Point> points = emptyPoints();
        if (points.isEmpty()) {return true;}
        for (Point point : points) {
            setValue(point, 1);
            this.queensPlacedCounter++;
            refreshDomainsIn(point);
            if (isDomainEmpty()) {

            } else {
                if (algorithm()) {
                    return true;
                }
            }
            setValue(point, 0);
            refreshDomainsOut(point);
            this.queensPlacedCounter--;

        }

        return false;
    }

    public boolean algorithmMRV() {

        List<Point> points = emptyPoints();
        if (points.isEmpty()) {
            return true;
        }

        HashMap<Point, Integer> domainValueDomainSumms = new HashMap<>();
        for (Point point : points) {
            domainValueDomainSumms.put(point, calculateNumberOfConstraintVariables(point));
        }

        List<Point> pointsSorted = QueensFC.sortByValueAndExtractList(domainValueDomainSumms);

        for (Point point : pointsSorted) {
            setValue(point, 1);
            this.queensPlacedCounter++;
            refreshDomainsIn(point);
            if (isDomainEmpty()) {

            } else {
                if (algorithmMRV()) {
                    return true;
                }
            }
            setValue(point, 0);
            refreshDomainsOut(point);
            this.queensPlacedCounter--;
        }

        return false;
    }

    private Integer calculateNumberOfConstraintVariables(Point point) {
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

    public void refreshDomainsIn(Point point) {

        int column = point.getColumn();
        int row = point.getRow();

        for (int i = 0; i < this.size; i++) {
            domain[i][column]+=1;
            domain[row][i]+=1;
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

    public void refreshDomainsOut(Point point) {

        int column = point.getColumn();
        int row = point.getRow();

        for (int i = 0; i < this.size; i++) {
            domain[i][column]-=1;
            domain[row][i]-=1;
        }

        int i = row;
        int j = column;
        while (i < size && i >= 0 && j < size && j >= 0) {
            domain[i][j] -= 1;
            i--;
            j++;
        }
        i = row;
        j = column;
        while (i < size && i >= 0 && j < size && j >= 0) {
            domain[i][j] -= 1;
            i++;
            j--;
        }
        i = row;
        j = column;
        while (i < size && i >= 0 && j < size && j >= 0) {
            domain[i][j] -= 1;
            i--;
            j--;
        }
        i = row;
        j = column;
        while (i < size && i >= 0 && j < size && j >= 0) {
            domain[i][j] -= 1;
            i++;
            j++;
        }
        i = row;
        j = column;
    }

    public void initDomains() {
      int[] row;
      int[] domainRow;
      for(int i = 0; i < size; i++) {
        row = new int[size];
        domainRow = new int[size];
        for(int j = 0; j < size; j++) {
          row[j] = 0;
        }
        board[i] = row;
        domain[i] = domainRow;
      }
    }

    public boolean isDomainEmpty() {
        int emptyPoints = emptyPoints().size();
        int queensNotPlacedCounter = (size - queensPlacedCounter);
        return  emptyPoints < queensNotPlacedCounter;
    }

    public List<Point> emptyPoints() {
        List<Point> emptyPoints = new ArrayList<Point>();
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if (domain[i][j] == 0) {
                    emptyPoints.add(new Point(i, j));
                }
            }
        }
        return emptyPoints;
    }

    public Point findEmpty() {
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if (domain[i][j] == 0) {
                    return new Point(i, j);
                }
            }
        }
        return null;
    }

    public void setValue(Point point, Integer value) {
        int row = point.getRow();
        int column = point.getColumn();
        board[row][column] = value;
    }

    public int[][] board() {
        return board;
    }

}

package algorithms.algorithm;

import algorithms.dto.Point;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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

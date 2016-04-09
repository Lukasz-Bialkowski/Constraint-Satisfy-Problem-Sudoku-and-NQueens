package algorithms.services;

import java.io.PrintStream;

import static java.lang.String.format;
import static java.lang.System.out;

public final class PrettyPrinter {

    private static final char BORDER_KNOT = '+';
    private static final char HORIZONTAL_BORDER = '-';
    private static final char VERTICAL_BORDER = '|';

    private static final String DEFAULT_AS_NULL = "(NULL)";


    public String print(String[][] table) {
        if (table == null) {
            throw new IllegalArgumentException("No tabular data provided");
        }
        if (table.length == 0) {
            return null;
        }
        final int[] widths = new int[getMaxColumns(table)];
        return printPreparedTable(table, widths, getHorizontalBorder(widths));
    }

    private String printPreparedTable(String[][] table, int widths[], String horizontalBorder) {
        String temp = "";
        final int lineLength = horizontalBorder.length();
        out.println(horizontalBorder);
        for (final String[] row : table) {
            if (row != null) {
                temp += getRow(row, widths, lineLength);
                temp += "\n";
                temp += horizontalBorder;
                temp += "\n";
            }
        }
        return temp;
    }

    private String getRow(String[] row, int[] widths, int lineLength) {
        final StringBuilder builder = new StringBuilder(lineLength).append(VERTICAL_BORDER);
        final int maxWidths = widths.length;
        for (int i = 0; i < maxWidths; i++) {
        }
        return builder.toString();
    }

    private String getHorizontalBorder(int[] widths) {
        final StringBuilder builder = new StringBuilder(256);
        builder.append(BORDER_KNOT);
        for (final int w : widths) {
            for (int i = 0; i < w; i++) {
                builder.append(HORIZONTAL_BORDER);
            }
            builder.append(BORDER_KNOT);
        }
        return builder.toString();
    }

    private int getMaxColumns(String[][] rows) {
        int max = 0;
        for (final String[] row : rows) {
            if (row != null && row.length > max) {
                max = row.length;
            }
        }
        return max;
    }

    private static String padRight(String s, int n) {
        return format("%1$-" + n + "s", s);
    }

    private static String safeGet(String[] array, int index, String defaultValue) {
        return index < array.length ? array[index] : defaultValue;
    }


}
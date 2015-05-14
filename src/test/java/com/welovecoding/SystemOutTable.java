package com.welovecoding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SystemOutTable {

  private final List<Row> rows = new ArrayList<>();

  public void parseBy(String tableToParse, String rowLimiter, String cellLimiter) {
    List<String> parsedRows = new ArrayList<>();

    parsedRows = Arrays.asList(tableToParse.split(rowLimiter));

    for (String parsedRow : parsedRows) {
      List<String> parsedCells = new ArrayList<>();
      this.addRow();

      parsedCells = Arrays.asList(parsedRow.split(cellLimiter));
      for (String parsedCell : parsedCells) {
        parsedCell = parsedCell.trim();
        if (!"".equals(parsedCell)) {
          this.putInLastRow(parsedCell);
        }
      }
    }
  }

  public void putInLastRow(String value) {
    getLastRow().addNextCell(value);
  }

  public void addRow() {
    rows.add(new Row());
  }

  private Row getLastRow() {
    int lastRowIndex = rows.size() - 1;
    if (lastRowIndex < 0) {
      // list is empty
      rows.add(new Row());
      lastRowIndex = rows.size() - 1;
    }

    return rows.get(lastRowIndex);
  }

  private int getLongestStringSizeForIndex(int index) {
    int longestStringLength = 0;
    int currentStringLength;
    for (Row row : rows) {
      currentStringLength = row.getStringLengthFor(index);
      if (currentStringLength > longestStringLength) {
        longestStringLength = currentStringLength;
      }
    }
    return longestStringLength;
  }

  private int getLongestRow() {
    int longestRowLength = 0;
    int currentRowLength;
    for (Row row : rows) {
      currentRowLength = row.size();
      if (currentRowLength > longestRowLength) {
        longestRowLength = currentRowLength;
      }
    }
    return longestRowLength;
  }

  @Override
  public String toString() {
    for (int i = 0; i < getLongestRow(); i++) {
      int longestStringSize = getLongestStringSizeForIndex(i);
      for (Row row : rows) {
        row.fillUpTo(getLongestRow());
        Cell cell = row.getCell(i);
        if (cell != null) {
          cell.fillUpTo(longestStringSize);
        }
      }
    }
    StringBuilder table = new StringBuilder();
    for (Row row : rows) {
      for (Cell cell : row.getCells()) {
        table.append(cell.toString());
      }
      table.append("\n");
    }
    return table.toString();
  }

  private class Row {

    private final List<Cell> cells = new ArrayList<>();

    private int getStringLengthFor(int index) {
      Cell cell = this.getCell(index);
      if (cell == null) {
        return 0;
      }
      return cell.length();
    }

    public Cell getCell(int index) {
      try {
        // let it fail
        return cells.get(index);
      } catch (IndexOutOfBoundsException e) {
        return null;
      }
    }

    public int size() {
      return cells.size();
    }

    public List<Cell> getCells() {
      return cells;
    }

    public void addNextCell(String value) {
      cells.add(new Cell(value));
    }

    private void fillUpTo(int size) {
      int fillAmount = size - cells.size();
      for (int i = 0; i < fillAmount; i++) {
        addNextCell("");
      }
    }

  }

  private class Cell {

    private static final char FILL_CHAR = ' ';

    private final String value;
    private String fill;

    public Cell() {
      this.value = "";
    }

    public Cell(String value) {
      if (value == null) {
        this.value = "";
      } else {
        value = value.trim();
        value = value.replaceAll("\n", "");
        value = value.replaceAll("\t", "    ");
        this.value = value;
      }
    }

    public int length() {
      return value.length();
    }

    private void fillUpTo(int size) {
      size = size + 1;
      int numFillers = size - value.length();
      StringBuilder filler = new StringBuilder();
      for (int i = 0; i < numFillers; i++) {
        filler.append(FILL_CHAR);
      }
      fill = filler.toString();
    }

    @Override
    public String toString() {
      return value + fill;
    }

  }
}

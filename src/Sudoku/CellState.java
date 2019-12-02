package Sudoku;

import java.util.HashSet;
import java.util.Set;

public class CellState implements Cloneable {
    public CellState() {
        this.cellValue = CellState.BLANK_CELL;
        this.possibleValues = new HashSet<Integer>();
    }

    public CellState(int cellValue) {
        this.cellValue = cellValue;
        this.possibleValues = new HashSet<Integer>();
    }

    public CellState(int cellValue, Set<Integer> possibleValues) {
        this.cellValue = cellValue;
        this.possibleValues = possibleValues;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null || obj.getClass() != this.getClass())
            return false;

        CellState cellState = (CellState) obj;
        return this.cellValue == cellState.cellValue && this.possibleValues.equals(cellState.possibleValues);
    }

    @Override
    public int hashCode() {
        return this.cellValue*100 + this.possibleValues.hashCode();
    }

    public CellState clone() {
        return new CellState(this.cellValue, new HashSet<Integer>(this.possibleValues));
    }

    public boolean isBlankCell() {
        return this.cellValue == CellState.BLANK_CELL;
    }

    public int getValue() {
        return this.cellValue;
    }

    public void setValue(int value) {
        this.cellValue = value;
    }

    public boolean hasPossibleValues() {
        return !this.possibleValues.isEmpty();
    }

    public Set<Integer> getPossibleValues() {
        return this.possibleValues;
    }

    public void addPossibleValue(int value) {
        this.possibleValues.add(value);
    }

    public void removePossibleValue(int value) {
        this.possibleValues.remove(value);
    }

    public static final int BLANK_CELL = 0;

    private int cellValue;
    private Set<Integer> possibleValues;
}

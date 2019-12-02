package Sudoku;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class BoardState {
    public BoardState() {
    }

    public BoardState(CellState[] cellStates) {
        if (cellStates == null)
            throw new NullPointerException();
        if (cellStates.length != 81)
            throw new IllegalArgumentException("cellStates array must have length of 81.");

        for(int i = 0; i < 81; i++)
            this.board[i] = cellStates[i].clone();
    }

    public BoardState(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int index = 0;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                for (int i = 0; i < values.length; i++)
                    this.board[index++].setValue(Integer.parseInt(values[i]));
            }
        }
        catch(FileNotFoundException e) {
            // TODO: Handle this.
        }
        catch(IOException e) {
            // TODO: Handle this.
        }
    }

    public static int getRowStartIndex(int i) {
        return i / 9;
    }

    public static int getColumnStartIndex(int i) {
        return i % 9;
    }

    public static int getSquareStartIndex(int i) {
        return (BoardState.getSquareIndex(i) % 3) * 3 + (BoardState.getSquareIndex(i) / 3 * 27);
    }

    public static int getSquareIndex(int i) {
        return BoardState.getColumnStartIndex(i) / 3 + 3 * (BoardState.getRowStartIndex(i) / 3);
    }

    public boolean isBlankCell(int i) {
        return this.board[i].isBlankCell();
    }

    public Set<Integer> calculatePossibleValues(int i) {
        Set<Integer> possibleValues = new HashSet<Integer>();
        if (!this.board[i].isBlankCell()) {
            possibleValues.add(this.board[i].getValue());
            return possibleValues;
        }

        Set<Integer> relatedCellIndices = getRelatedCellIndices(i);
        Set<Integer> existingValues = new HashSet<Integer>();
        for (int index : relatedCellIndices) {
            CellState cellState = this.board[index];
            if (!cellState.isBlankCell())
                existingValues.add(cellState.getValue());
        }

        possibleValues.add(1);
        possibleValues.add(2);
        possibleValues.add(3);
        possibleValues.add(4);
        possibleValues.add(5);
        possibleValues.add(6);
        possibleValues.add(7);
        possibleValues.add(8);
        possibleValues.add(9);

        for (Integer existingValue : existingValues)
            possibleValues.remove(existingValue);

        return possibleValues;
    }

    public boolean isBoardComplete() {
        for (CellState cellState : this.board) {
            if (cellState.isBlankCell())
                return false;
        }

        return true;
    }

    private Set<Integer> getRelatedCellIndices(int i) {
        int rowStart = BoardState.getRowStartIndex(i);
        int columnStart = BoardState.getColumnStartIndex(i);
        int squareStart = BoardState.getSquareStartIndex(i);
        Set<Integer> indices = new HashSet<Integer>();
        for (int index = 0; i < 9; i++)
        {
            indices.add(rowStart * 9 + index);
            indices.add(index * 9 + columnStart);
            indices.add(squareStart + (index % 3) + ((index / 3) * 9));
        }

        return indices;
    }

    private CellState[] board = new CellState[81];
}

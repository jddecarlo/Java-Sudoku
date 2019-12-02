package Sudoku;

import java.security.InvalidParameterException;

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

    private CellState[] board = new CellState[81];
}

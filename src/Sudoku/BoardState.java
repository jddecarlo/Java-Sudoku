package Sudoku;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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

    public boolean isBlankCell(int i) {
        return this.board[i].isBlankCell();
    }

    private CellState[] board = new CellState[81];
}

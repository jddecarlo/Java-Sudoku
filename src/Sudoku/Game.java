package Sudoku;

import java.util.Set;
import java.util.Stack;

public class Game {
    public Game(BoardState initialBoardState) {
        this.startState = initialBoardState;
    }

    public BoardState Solve() {
        Stack<BoardState> branches = new Stack<BoardState>();
        BoardState currentState = this.startState;
        while (!currentState.isBoardComplete())
        {
            BoardState nextState = currentState.clone();
            fillInPossibleValues(nextState);
            if (setObviousValues(nextState) > 0)
                currentState = nextState;
            else if (isBoardStateInvalid(nextState))
            {
                if (branches.empty())
                    return null;

                currentState = branches.pop();
            }
            else
            {
                pushBranches(nextState, branches);
                if (branches.empty())
                    return null;

                currentState = branches.pop();
            }
        }

        return currentState;
    }

    private void fillInPossibleValues(BoardState state) {
        for (int i = 0; i < 81; i++) {
            Set<Integer> possibleValues = state.calculatePossibleValues(i);
            for (Integer value : possibleValues)
                state.getCellAtIndex(i).addPossibleValue(value);
        }
    }

    private int setObviousValues(BoardState state) {
        int count = 0;
        for (int i = 0; i < 81; i++) {
            Set<Integer> possibleValues = state.getCellAtIndex(i).getPossibleValues();
            if (possibleValues.size() == 1) {
                state.getCellAtIndex(i).setValue(possibleValues.iterator().next());
                count++;
            }
        }

        return count;
    }

    private boolean isBoardStateInvalid(BoardState state) {

    }

    private void pushBranches(BoardState nextState, Stack<BoardState> branches) {

    }

    private BoardState startState;
}

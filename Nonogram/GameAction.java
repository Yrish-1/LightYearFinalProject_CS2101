package Nonogram;
/* GameAction.java
 * PURPOSE: Defines actions that can be performed in the game
 * OOP CONCEPTS: Abstraction (Interface), Polymorphism
 * 
 * WHY INTERFACE:
 * - All actions follow same contract: execute()
 * - Easy to add new actions without changing existing code
 * - Demonstrates STRATEGY PATTERN
 * 
 * TODO:
 * - Implement ShowSolutionAction
 * - Implement CheckSolutionAction
 */

interface GameAction {
    void execute(UserGrid grid, InputHandler input, MenuDisplay display);
}

// Concrete action - marking with X, O, or Fill
class MarkAction implements GameAction {
    private Mark markType;

    public MarkAction(Mark markType) {
        this.markType = markType;
    }

    @Override
    public void execute(UserGrid grid, InputHandler input, MenuDisplay display) {
        Coordinate coord = input.getCoordinate();

        if (coord == null) {
            display.showMessage("Please enter valid numbers!");
            return;
        }

        if (grid.placeMark(coord, markType)) {
            display.showMessage("Marked at " + coord);
        } else {
            display.showMessage("Invalid coordinates!");
        }
    }
}

// Concrete action - clearing a mark
class ClearAction implements GameAction {
    @Override
    public void execute(UserGrid grid, InputHandler input, MenuDisplay display) {
        Coordinate coord = input.getCoordinate();

        if (coord == null) {
            display.showMessage("Please enter valid numbers!");
            return;
        }

        if (coord.isValidFor(grid.getRows(), grid.getCols())) {
            grid.clearMark(coord);
            display.showMessage("Cleared at " + coord);
        } else {
            display.showMessage("Invalid coordinates!");
        }
    }
}

// TODO: Implement this action
// class ShowSolutionAction implements GameAction {
// private PuzzleFlow puzzleFlow;
// private boolean isShowing = false;
//
// public ShowSolutionAction(PuzzleFlow puzzleFlow) {
// this.puzzleFlow = puzzleFlow;
// }
//
// @Override
// public void execute(UserGrid grid, InputHandler input, MenuDisplay display) {
// // TODO: Toggle showing solution
// // TODO: Display solution grid side-by-side with user grid
// }
// }

// TODO: Implement this action
// class CheckSolutionAction implements GameAction {
// private PuzzleFlow puzzleFlow;
//
// @Override
// public void execute(UserGrid grid, InputHandler input, MenuDisplay display) {
// // TODO: Compare user grid with puzzle solution
// // TODO: Show message if correct or not
// }
// }
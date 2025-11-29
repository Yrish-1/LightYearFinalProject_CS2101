package conogram;
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
        Coordinate coord = input.getCoordinate(grid.getRows(), grid.getCols());
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
        Coordinate coord = input.getCoordinate(grid.getRows(), grid.getCols()); // ✅ FIXED

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

class ShowSolutionAction implements GameAction {
    private PuzzleFlow puzzleFlow;
    private boolean isShowing;

    public ShowSolutionAction(PuzzleFlow puzzleFlow) {
        this.puzzleFlow = puzzleFlow;
        this.isShowing = false;
    }

    @Override
    public void execute(UserGrid grid, InputHandler input, MenuDisplay display) {
        // Toggle the visibility
        isShowing = !isShowing;

        if (isShowing) {
            display.showMessage("\n=== SOLUTION (Cheat Mode ON) ===");
            displaySolution();
            display.showMessage("================================\n");
        } else {
            display.showMessage("Solution hidden.");
        }
    }

    // Helper method to display the solution
    private void displaySolution() {
        int[][] solution = puzzleFlow.getPuzzle();

        System.out.println("Solution Grid:");
        for (int i = 0; i < solution.length; i++) {
            for (int j = 0; j < solution[i].length; j++) {
                if (solution[i][j] == 1) {
                    System.out.print("■ "); // Filled square
                } else {
                    System.out.print(". "); // Empty square
                }
            }
            System.out.println();
        }
    }
}

class CheckSolutionAction implements GameAction {
    private PuzzleFlow puzzleFlow;

    public CheckSolutionAction(PuzzleFlow puzzleFlow) {
        this.puzzleFlow = puzzleFlow;
    }

    @Override
    public void execute(UserGrid grid, InputHandler input, MenuDisplay display) {
        display.showMessage("\n🔍 Checking your solution...\n");

        int[][] solution = puzzleFlow.getPuzzle();
        boolean isCorrect = grid.checkSolution(solution);

        if (isCorrect) {
            // User solved it correctly!
            display.showMessage("╔════════════════════════════════╗");
            display.showMessage("║  🎉 CONGRATULATIONS! 🎉       ║");
            display.showMessage("║  You solved the puzzle!        ║");
            display.showMessage("╚════════════════════════════════╝");

            // Show some stats
            int filled = grid.countFilledCells();
            int total = grid.getRows() * grid.getCols();
            display.showMessage("Cells filled: " + filled + "/" + total);

        } else {
            // Not quite right yet
            display.showMessage("❌ Not quite right yet!");
            display.showMessage("💡 Hint: Check your marks carefully.");
            display.showMessage("   Remember: Fill (■) or X for 1s, Empty (.) or O for 0s\n");

            // Show progress
            int filled = grid.countFilledCells();
            int total = grid.getRows() * grid.getCols();
            display.showMessage("Progress: " + filled + "/" + total + " cells marked");
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
package conogram;
/* PuzzleGame.java
 * PURPOSE: Main game controller - orchestrates all components
 * OOP CONCEPTS: Composition, Encapsulation
 * 
 * LOTS of TODOs for your groupmates!
 */

import java.util.HashMap;
import java.util.Map;

public class PuzzleGame {
    private PuzzleFlow puzzleFlow;
    private UserGrid userGrid;
    private InputHandler inputHandler;
    private MenuDisplay menuDisplay;
    private Map<String, GameAction> actions;

    // TODO: Add these fields (groupmate can add)
    // private int score;
    // private long startTime;
    // private boolean solutionVisible;

    public PuzzleGame(PuzzleFlow puzzleFlow) {
        this.puzzleFlow = puzzleFlow;

        int[][] puzzle = puzzleFlow.getPuzzle();
        this.userGrid = new UserGrid(puzzle.length, puzzle[0].length);
        this.inputHandler = new InputHandler();
        this.menuDisplay = new MenuDisplay();
        this.actions = new HashMap<>();

        initializeActions();

        // TODO: Initialize score and timer
        // this.score = 0;
        // this.startTime = System.currentTimeMillis();
    }

    private void initializeActions() {
        actions.put("X", new MarkAction(new MarkX()));
        actions.put("O", new MarkAction(new MarkO()));
        actions.put("/", new MarkAction(new MarkFilled()));
        actions.put("C", new ClearAction());
        actions.put("S", new ShowSolutionAction(puzzleFlow));
        actions.put("K", new CheckSolutionAction(puzzleFlow));

        // TODO: Add these actions (your groupmate implements)
        // actions.put("S", new ShowSolutionAction(puzzleFlow));
        // actions.put("H", new HintAction(puzzleFlow));
    }

    private void showWelcome() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║     WELCOME TO NONOGRAM PUZZLE!       ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("\n📋 HOW TO PLAY:");
        System.out.println("   • Fill squares that should be marked (1 in solution)");
        System.out.println("   • Use X or ■ for filled squares");
        System.out.println("   • Use O or leave empty for blank squares");
        System.out.println("   • Goal: Match the hidden pattern!\n");

        System.out.println("Grid size: " + userGrid.getRows() + "x" + userGrid.getCols());
        System.out.println("Total cells: " + (userGrid.getRows() * userGrid.getCols()));
        System.out.println("\nGood luck! 🍀\n");
    }

    private void showProgress() {
        int filled = userGrid.countFilledCells();
        int total = userGrid.getRows() * userGrid.getCols();
        int percentage = (int) ((filled / (double) total) * 100);

        System.out.println("\n📊 Progress: " + filled + "/" + total + " cells (" + percentage + "%)");
    }

    // ===== NEW: Auto-check if puzzle is solved =====
    private boolean checkAutoWin() {
        int[][] solution = puzzleFlow.getPuzzle();
        return userGrid.checkSolution(solution);
    }

    public void start() {
        showWelcome(); // Show welcome message

        boolean running = true;

        while (running) {
            userGrid.display(); // Show current grid state
            showProgress(); // Show progress

            menuDisplay.showMainMenu();
            String choice = inputHandler.getMenuChoice();

            if (choice.equals("R")) {
                // User wants to quit
                if (inputHandler.getConfirmation("Are you sure you want to quit?")) {
                    menuDisplay.showMessage("\n👋 Thanks for playing! Goodbye!");
                    running = false;
                }
            } else if (actions.containsKey(choice)) {
                // Execute the chosen action
                actions.get(choice).execute(userGrid, inputHandler, menuDisplay);

                // ===== AUTO WIN-CHECK (after marking actions only) =====
                if (choice.equals("X") || choice.equals("/")) {
                    if (checkAutoWin()) {
                        // Player won!
                        System.out.println("\n");
                        userGrid.display();
                        System.out.println("\n╔════════════════════════════════════════╗");
                        System.out.println("║        🎉 CONGRATULATIONS! 🎉         ║");
                        System.out.println("║     YOU SOLVED THE PUZZLE! 🏆         ║");
                        System.out.println("╚════════════════════════════════════════╝\n");

                        showProgress();

                        if (inputHandler.getConfirmation("Play again?")) {
                            // Reset the grid for a new game
                            userGrid.reset();
                            menuDisplay.showMessage("\n🔄 Starting new game...\n");
                        } else {
                            running = false;
                            menuDisplay.showMessage("\n👋 Thanks for playing!");
                        }
                    }
                }

            } else {
                menuDisplay.showMessage("❌ Invalid option. Please try again.");
            }
        }
    }

    // Clean up resources
    public void cleanup() {
        inputHandler.close();
    }
}
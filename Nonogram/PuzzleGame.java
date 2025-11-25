package Nonogram;
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

        // TODO: Add these actions (your groupmate implements)
        // actions.put("S", new ShowSolutionAction(puzzleFlow));
        // actions.put("H", new HintAction(puzzleFlow));
    }

    public void start() {
        menuDisplay.showMessage("Welcome to Nonogram Puzzle!");
        // TODO: Show instructions

        boolean running = true;

        while (running) {
            menuDisplay.showMainMenu();
            String choice = inputHandler.getMenuChoice();

            if (choice.equals("R")) {
                menuDisplay.showMessage("Returning...");
                running = false;
            } else if (actions.containsKey(choice)) {
                actions.get(choice).execute(userGrid, inputHandler, menuDisplay);
                userGrid.display();

                // TODO: Check if puzzle is solved after each action
                // if (userGrid.checkSolution(puzzleFlow.getPuzzle())) {
                // menuDisplay.showMessage("Congratulations! You solved it!");
                // // TODO: Show score and time
                // running = false;
                // }

            } else {
                menuDisplay.showMessage("Invalid option. Please try again.");
            }
        }
    }

    // TODO: Implement these methods

    // TODO: Calculate score based on time and mistakes
    // private int calculateScore() { ... }

    // TODO: Show hint (reveal one correct cell)
    // public void showHint() { ... }

    // TODO: Save game state
    // public void saveGame() { ... }

    public void cleanup() {
        inputHandler.close();
    }
}
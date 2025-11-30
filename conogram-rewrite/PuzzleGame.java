// this file contains the user-facing game portion!
// for internal game logic pwease check PuzzleLogic.java
// for the main menu, please check Main
public class PuzzleGame {
    // mode selection will be on main
    private int mode = 2;

    // constructor
    public PuzzleGame(int mode) {
        this.mode = mode;
    }

    public void gameInstance() {
        InputHandler input = new InputHandler();
        int row, col;
        switch (mode) {
            // MODE 1: Regular Game
            case 1:
                PuzzleLogic newPuzzle = new PuzzleLogic();
                newPuzzle.createPuzzle();
                newPuzzle.newInstance();
                boolean quit = false;   // quit variable
                do {
                    System.out.print("\n========[CONOGRAMS: Regular Mode]========");
                    newPuzzle.displayInstance();
                    System.out.print("Choose a cell (row col): ");
                    row = input.getValidateInput();
                    if (row == -1) {
                        continue;
                    }
                    col = input.getValidateInput();
                    if (col == -1) {
                        continue;
                    }

                    if (row != -1 && col != -1) {
                        newPuzzle.displayInstanceHighlighted(row, col);
                        System.out.print("\nWhat would you like to do with this cell?\n[1] Fill\t\t[3] Cross Out\n[2] Mark as Possible\t[4] Clear Cell\nEnter your choice: ");
                        int mark = input.getValidateInput();

                        while (InputHandler.intValidation(mark, 1, 4) == false || mark == -1) {
                            if (mark != -1) {
                                System.out.print("Invalid input, please try again: ");
                            }
                            mark = input.getValidateInput();
                        }
                        newPuzzle.setCell(row, col, mark);
                    }

                } while (newPuzzle.compareAnswer() == false && quit == false);
                break;
            // MODE 2: Daily Challenge
             case 2:
                PuzzleLogic dailyPuzzle = new DailyPuzzle();
                dailyPuzzle.createPuzzle();
                dailyPuzzle.newInstance();
                quit = false;   // quit variable
                do {
                    System.out.print("\n========[CONOGRAMS: Daily Puzzle]========");
                    dailyPuzzle.displayInstance();
                    System.out.print("Choose a cell (row col): ");
                    row = input.getValidateInput();
                    if (row == -1) {
                        continue;
                    }
                    col = input.getValidateInput();
                    if (col == -1) {
                        continue;
                    }

                    if (row != -1 && col != -1) {
                        dailyPuzzle.displayInstanceHighlighted(row, col);
                        System.out.print("\nWhat would you like to do with this cell?\n[1] Fill\t\t[3] Cross Out\n[2] Mark as Possible\t[4] Clear Cell\nEnter your choice: ");
                        int mark = input.getValidateInput();

                        while (InputHandler.intValidation(mark, 1, 4) == false || mark == -1) {
                            if (mark != -1) {
                                System.out.print("Invalid input, please try again: ");
                            }
                            mark = input.getValidateInput();
                        }
                        dailyPuzzle.setCell(row, col, mark);
                    }
                } while (dailyPuzzle.compareAnswer() == false && quit == false);
                break; 
        }
    }
}

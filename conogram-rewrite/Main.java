public class Main {
    public static void main(String[] args) {
        InputHandler menuInput = new InputHandler();
        int mode;
        int warn = 0;

        // menu WIP!
        System.out.print("CONOGRAMS\nSelect mode:\n\n\t\t[1] Regular Mode\n\t\t[2] Daily Puzzle\n\n\nEnter your choice: ");

        do {
            if (warn == 1) {
                System.out.print("Invalid input, please try again: ");
                warn = 0;
            }
            mode = menuInput.getValidateInput();
            if (InputHandler.intValidation(mode, 0, 2) == true) {
                break;
            }
            if (mode != -1) {
                warn = 1;
            }
        } while (true);

        PuzzleGame newGame = new PuzzleGame(mode);
        newGame.gameInstance();
    }
}

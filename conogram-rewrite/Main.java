public class Main {
    public static void main(String[] args) {
        InputHandler menuInput = new InputHandler();
        int mode;
        int warn = 0;

        System.out.print("[CONOGRAMS]======================\nWelcome, Player!\nPlease select a mode:\n\n\t[1] Regular Mode\n\t[2] Daily Puzzle\n\nEnter your choice: ");
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

        System.out.println("Thank you for playing our game! <3");
    }
}

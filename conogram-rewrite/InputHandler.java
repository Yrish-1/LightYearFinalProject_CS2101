import java.util.InputMismatchException;
import java.util.Scanner;

public class InputHandler {
    private Scanner scanner;

    public InputHandler() {
        this.scanner = new Scanner(System.in);
    }

    public int getValidateInput() {
        // kinda breaks if the final input in a series of misinputs is still valid (e.g. user inputs "e 1")
        // its a minor inconvenience but ueee
        try {
            int val = scanner.nextInt();
            return val;
        } catch (InputMismatchException e) {
            System.out.print("Invalid input, please try again: ");
            scanner.next(); // discards invalid token
            return -1;
        }
    }

    public static boolean intValidation(int input, int minBound, int maxBound) {
        return !(input < minBound || input > maxBound);
    }
}

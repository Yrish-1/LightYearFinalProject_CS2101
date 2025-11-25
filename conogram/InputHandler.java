package conogram;
/* InputHandler.java
 * PURPOSE: Handles all user input
 * OOP CONCEPTS: Encapsulation, Single Responsibility
 * 
 * TODO for groupmate:
 * - Add input validation (negative numbers, out of range)
 * - Add confirmation prompts
 * - Better error handling
 */

import java.util.Scanner;

class InputHandler {
    private Scanner scanner;

    public InputHandler() {
        this.scanner = new Scanner(System.in);
    }

    // Gets menu choice from user
    public String getMenuChoice() {
        System.out.print("Enter choice: ");
        return scanner.nextLine().trim().toUpperCase();
    }

    // Gets coordinate from user
    // TODO: Add validation - prevent negative numbers
    // TODO: Add error handling for non-numeric input
    public Coordinate getCoordinate() {
        System.out.print("Select a square (row col): ");
        String input = scanner.nextLine().trim();
        String[] coords = input.split("\\s+");

        if (coords.length >= 2) {
            try {
                int row = Integer.parseInt(coords[0]);
                int col = Integer.parseInt(coords[1]);

                // TODO: Validate row and col are not negative
                // TODO: Add confirmation "You selected (row, col). Confirm? (Y/N)"

                return new Coordinate(row, col);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }

    // TODO: Add this method
    // public boolean getConfirmation(String prompt) {
    // System.out.print(prompt + " (Y/N): ");
    // String input = scanner.nextLine().trim().toUpperCase();
    // return input.equals("Y");
    // }

    public void close() {
        scanner.close();
    }
}
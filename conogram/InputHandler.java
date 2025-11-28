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
    public Coordinate getCoordinate(int maxRows, int maxCols) {
        while (true) {
            System.out.print("Select a square (row col) or 'cancel': ");
            String input = scanner.nextLine().trim();

            // Allow user to cancel
            if (input.equalsIgnoreCase("cancel") || input.equalsIgnoreCase("c")) {
                return null;
            }

            String[] coords = input.split("\\s+");

            // Check if two numbers were entered
            if (coords.length < 2) {
                System.out.println("Error!Please enter both row and column (e.g., '2 3')");
                continue;
            }

            try {
                int row = Integer.parseInt(coords[0]);
                int col = Integer.parseInt(coords[1]);

                // Validate no negative numbers
                if (row < 0 || col < 0) {
                    System.out.println("Error!Row and column cannot be negative!");
                    continue;
                }

                // Validate within bounds
                if (row >= maxRows || col >= maxCols) {
                    System.out.println("Error!Out of bounds! Grid is " + maxRows + "x" + maxCols);
                    System.out.println("    Valid range: row [0-" + (maxRows - 1) + "], col [0-" + (maxCols - 1) + "]");
                    continue;
                }

                // Valid input!
                return new Coordinate(row, col);

            } catch (NumberFormatException e) {
                System.out.println("Error!Invalid input! Please enter numbers only (e.g., '2 3')");
            }
        }
    }

    public void close() {
        scanner.close();
    }
}

// TODO: Add this method
// public boolean getConfirmation(String prompt) {
// System.out.print(prompt + " (Y/N): ");
// String input = scanner.nextLine().trim().toUpperCase();
// return input.equals("Y");
// }
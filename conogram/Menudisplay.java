package conogram;
/* MenuDisplay.java
 * PURPOSE: Handles all console output/display logic
 * OOP CONCEPTS: Single Responsibility Principle, Encapsulation
 * 
 * WHY SEPARATE CLASS:
 * - All display logic in ONE place
 * - Easy to change menu format without touching other code
 * - Could easily swap to GUI later
 */

class MenuDisplay {

    // Displays the main game menu
    public void showMainMenu() {
        System.out.println("\n=== What do you want to do? ===");
        System.out.println("[X] Mark X");
        System.out.println("[O] Mark O");
        System.out.println("[/] Fill Square");
        System.out.println("[C] Clear");
        System.out.println("[S] Show Solution"); // TODO: Implement this action
        System.out.println("[R] Return");
    }

    // Generic message display
    public void showMessage(String message) {
        System.out.println(message);
    }

    // TODO: Add these methods
    // public void showWelcome() { ... }
    // public void showGameOver() { ... }
    // public void showScore(int score) { ... }
}
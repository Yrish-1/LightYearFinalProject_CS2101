package Nonogram;
/* Coordinate.java
 * PURPOSE: Encapsulates a position on the grid (row, column)
 * OOP CONCEPTS: Encapsulation, Data Hiding
 * 
 * WHY THIS CLASS EXISTS:
 * Instead of passing around separate row/col integers, we bundle them
 * into one object. This makes code cleaner and safer.
 */

class Coordinate {
    // Private fields - demonstrates ENCAPSULATION
    private int row;
    private int col;

    // Constructor
    public Coordinate(int row, int col) {
        this.row = row;
        this.col = col;
    }

    // Getters - controlled access to private data
    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    // Business logic - validates if coordinate is within bounds
    // This is better than checking validity everywhere in code
    public boolean isValidFor(int maxRows, int maxCols) {
        return row >= 0 && row < maxRows &&
                col >= 0 && col < maxCols;
    }

    // Override toString for easy printing
    @Override
    public String toString() {
        return "(" + row + ", " + col + ")";
    }
}
package conogram;
/* UserGrid.java
 * PURPOSE: Manages the user's marking grid
 * OOP CONCEPTS: Encapsulation, Data Hiding
 * 
 * TODO:
 * - Add checkSolution() method
 * - Add countFilledCells() method
 * - Add reset() method
 */

class UserGrid {
    private Mark[][] grid; // 2D array of marks
    private int rows;
    private int cols;

    public UserGrid(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new Mark[rows][cols];
        initializeGrid();
    }

    // Initialize all cells as empty
    private void initializeGrid() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = new MarkEmpty();
            }
        }
    }

    // Place a mark at given coordinate
    public boolean placeMark(Coordinate coord, Mark mark) {
        if (coord.isValidFor(rows, cols)) {
            grid[coord.getRow()][coord.getCol()] = mark;
            return true;
        }
        return false;
    }

    // Clear a mark at given coordinate
    public void clearMark(Coordinate coord) {
        if (coord.isValidFor(rows, cols)) {
            grid[coord.getRow()][coord.getCol()] = new MarkEmpty();
        }
    }

    // Display the grid
    public void display() {
        System.out.println("\nYour Grid:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(grid[i][j].display());
            }
            System.out.println();
        }
    }

    // Getters
    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public boolean checkSolution(int[][] solution) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int cellValue = grid[i][j].getValue();
                int solutionValue = solution[i][j];

                if (solutionValue == 1) {
                    // Should be filled (MarkFilled=3 or MarkX=1)
                    if (cellValue != 3 && cellValue != 1) {
                        return false;
                    }
                } else {
                    // Should NOT be filled (MarkEmpty=0 or MarkO=2)
                    if (cellValue == 3 || cellValue == 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // Count how many cells are filled (not empty)
    public int countFilledCells() {
        int count = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j].getValue() != 0) {
                    count++;
                }
            }
        }
        return count;
    }

    // Reset the entire grid to empty
    public void reset() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = new MarkEmpty();
            }
        }
        System.out.println("Grid has been reset!");
    }

}

// TODO for groupmate: Implement these methods

// TODO: Check if user's grid matches the solution
// public boolean checkSolution(int[][] solution) {
// // Compare grid with solution
// // Return true if they match
// }

// TODO: Count how many cells are filled (not empty)
// public int countFilledCells() {
// // Loop through grid and count non-empty cells
// }

// TODO: Reset the entire grid to empty
// public void reset() {
// // Set all cells back to MarkEmpty
// }
// }
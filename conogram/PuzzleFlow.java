package conogram;
/* TO-DO
* 1. set DrawPuzzle to private; add getters and setters
* 2. separate method for showing puzzle solutions (for debugging)
* 3. figure out how 1 works
*/

public class PuzzleFlow {
    private int[][] puzzleInstance; // Store puzzle as instance variable

    // Private method to create the puzzle
    private void drawPuzzle(int sizeR, int sizeC) {
        this.puzzleInstance = new int[sizeR][sizeC];
        double n;

        // loop to create the puzzle
        for (int i = 0; i < sizeR; i++) {
            for (int j = 0; j < sizeC; j++) {
                n = Math.random();
                if (n >= 0.5) {
                    puzzleInstance[i][j] = 1;
                } else {
                    puzzleInstance[i][j] = 0;
                }
            }
        }

        // for debug only - immediately show solution
        for (int i = 0; i < puzzleInstance.length; i++) {
            for (int j = 0; j < puzzleInstance[i].length; j++) {
                System.out.print(puzzleInstance[i][j]);
            }
            System.out.print("\n");
        }
    }

    // Getter - returns the puzzle
    public int[][] getPuzzle() {
        return puzzleInstance;
    }

    // Setter - generates a new puzzle with given dimensions
    public void setPuzzle(int sizeR, int sizeC) {
        drawPuzzle(sizeR, sizeC);
    }

    // changes puzzle state to being solved
    public int SolveToggle() {
        return 1;
    }
}
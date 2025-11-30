// this file contains game logic!
// specifically: puzzle creation, daily puzzle creation, solution checking
// all these imported packages are for the daily functionality
import java.util.Random;
import java.time.*;
import java.time.format.DateTimeFormatter;

// lists are for the hint generation system
import java.util.List;
import java.util.LinkedList;

public class PuzzleLogic {
    // Randomization and Solution Initialization
    protected int rows = 10;
    protected int cols = 10;

    // WIN CONDITION: Have these two arrays match!
    protected int Solution[][] = new int[rows][cols];
    protected int puzzleInstance[][] = new int[rows][cols];

    // Puzzle generation
    public void createPuzzle() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                double det = Math.random();
                // det can be adjusted for balancing purposes
                if (det >= 0.39625) {
                    this.Solution[i][j] = 1;
                } else {
                    this.Solution[i][j] = 0;
                }
            }
        }
    }

    // Number hint generation, per-row / col
    // i (rei) wrote rows first but technically columns will be drawn first
    protected List <Integer> hintNumRow = new LinkedList<Integer>();
    protected List <Integer> hintNumCol = new LinkedList<Integer>();
    protected char hintRows[] = new char[9];
    protected char hintCols[] = new char[5];

    public void genHintRows(int currentRow) {
        // set all hints as spaces
        int counter = 0;
        for (int i = 0; i < 9; i++) {
            hintRows[i] = ' ';
        }

        // prepare hints
        for (int i = 0; i < 10; i++) {
            if (this.Solution[currentRow][i] == 1) {
                counter++;
            } else if (this.Solution[currentRow][i] == 0 && counter == 0) {
                continue;
            } else {
                hintNumRow.addLast(counter);
                counter = 0;
            }
        }

        if (counter != 0) {
            hintNumRow.addLast(counter);
        }

        // finalise hint character array
        if (hintNumRow.isEmpty()) {
            // when no hints are necessary
            for (int i = 9; i > 0; i--) {
                if (i == 9) {
                    hintRows[i - 1] = '0';
                } else {
                    hintRows[i - 1] = ' ';
                }
            }
        } else {
            for (int i = 9; i > 0; i -= 2) {
                if (hintNumRow.isEmpty()) {
                    hintRows[i - 1] = ' ';
                } else {
                    int hintTemp = hintNumRow.getLast();
                    hintRows[i - 1] = (char) (hintTemp + 48);
                    hintNumRow.removeLast();
                }
            }
        }
        
        // if a '10' was created (which would make a colon instead)
        if (hintRows[8] == ':') {
            hintRows[8] = 'F';
        }
    }

    public void genHintCols(int currentCol) {
        // set all hints as spaces
        int counter = 0;
        for (int i = 0; i < 5; i++) {
            hintCols[i] = ' ';
        }

        // prepare hints
        for (int i = 0; i < 10; i++) {
            if (this.Solution[i][currentCol] == 1) {
                counter++;
            } else if (this.Solution[i][currentCol] == 0 && counter == 0) {
                continue;
            } else {
                hintNumCol.addLast(counter);
                counter = 0;
            }
        }

        if (counter != 0) {
            hintNumCol.addLast(counter);
        }

        // finalise hint character array
        if (hintNumCol.isEmpty()) {
            // when no hints are necessary
            for (int i = 5; i > 0; i--) {
                if (i == 5) {
                    hintCols[i - 1] = '0';
                } else {
                    hintCols[i - 1] = ' ';
                }
            }
        } else {
            for (int i = 5; i > 0; i--) {
                if (hintNumCol.isEmpty()) {
                    hintCols[i - 1] = ' ';
                } else {
                    int hintTemp = hintNumCol.getLast();
                    hintCols[i - 1] = (char) (hintTemp + 48);
                    hintNumCol.removeLast();
                }
            }
        }
        
        // if a '10' was created (which would make a colon instead)
        if (hintCols[4] == ':') {
            hintCols[4] = 'F';
        }
    }

    // User instance generation
    public void newInstance() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // Set everything as 0 (unfilled)
                this.puzzleInstance[i][j] = 0;
            }
        }
    }

    // User instance setter
    public void setCell(int selectRow, int selectCol, int mark) {
        if (mark == 4) {
            this.puzzleInstance[selectRow - 1][selectCol - 1] = 0;
        } else {
            this.puzzleInstance[selectRow - 1][selectCol - 1] = mark;
        }
    }

    // User instance getter
    public void displayInstance() {
        // print column hints first
        // this is so cursed
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 11; j++) {
                if (j == 0) {
                    System.out.print("          ");
                } else {
                    // set hints for the previous j column
                    genHintCols(j - 1);
                    // print out the current hint at the current column
                    System.out.print(hintCols[i] + "  ");

                    // yes this regenerates all the hints every single time it has to draw one of them
                    // genHintCols runs 75+ times more than if it was more sanely implemented
                    // help
                }
            }
            System.out.print("\n");
        }

        for (int i = 0; i < rows; i++) {
            // row printing can be done in a more sane way at least
            genHintRows(i);
            for (int k = 0; k < 9; k++) {
                System.out.print(hintRows[k]);
            }
            for (int j = 0; j < cols; j++) {
                System.out.print("[");
                switch (this.puzzleInstance[i][j]) {
                    case 0: // unfilled
                        System.out.print(" ");
                        break;
                    case 1: // filled
                        System.out.print("■");
                        break;
                    case 2: // marked as possible
                        System.out.print("O");
                        break;
                    case 3: // marked as X
                        System.out.print("X");
                        break;
                }
                System.out.print("]");
            }
            // Row numbering
            System.out.print(" " + (i + 1) + "\n");
        }

        // Column numbering
        for (int i = 0; i < 11; i++) {
            if (i == 0) {
                System.out.print("          ");
            } else {
                System.out.print((i) + "  ");
            }
            if (i == 10) {
                System.out.print("\n");
            }
        }
    }

    // For when the user has selected a square
    public void displayInstanceHighlighted(int row, int col) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 11; j++) {
                if (j == 0) {
                    System.out.print("          ");
                } else {
                    genHintCols(j - 1);
                    System.out.print(hintCols[i] + "  ");
                }
            }
            System.out.print("\n");
        }

        for (int i = 0; i < rows; i++) {
            genHintRows(i);
            for (int k = 0; k < 9; k++) {
                System.out.print(hintRows[k]);
            }
            for (int j = 0; j < cols; j++) {
                System.out.print("[");
                // special case for selected row
                if (i == row - 1 && j == col - 1) {
                    if (col == 10) {
                        System.out.print('_');
                    } else {
                        System.out.print("_]");
                    }
                    continue;
                }
                switch (this.puzzleInstance[i][j]) {
                    case 0: // unfilled
                        System.out.print(" ");
                        break;
                    case 1: // filled
                        System.out.print("■");
                        break;
                    case 2: // marked as possible
                        System.out.print("O");
                        break;
                    case 3: // marked as X
                        System.out.print("X");
                        break;
                }
                System.out.print("]");
            }
            System.out.print(" " + (i + 1) + "\n");
        }
    }

    // Comparison between userspace and solution
    public boolean compareAnswer() {
        boolean isSolved = true;

        // make copy of current puzzleInstance for safety
        // this is a janky solution that might be resource intensive due to how often it runs
        // i (rei) can't think of anything better ;w;
        int compPuzzle[][] = new int [rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (puzzleInstance[i][j] > 1) {
                    compPuzzle[i][j] = 0;   // non-solution markings should be ignored for the purposes of this method
                } else {
                    compPuzzle[i][j] = puzzleInstance[i][j];
                }
            }
        }

        // actual comparison algorithm
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (compPuzzle[i][j] != this.Solution[i][j]) {
                    isSolved = false;
                }
            }
        }
        return isSolved;
    }
}

// functionality for dailies
class DailyPuzzle extends PuzzleLogic {
    /* Flow:
    1. get today's date;
    2. set date formatter to day-of-year;
    3. declare a date string following the format specified;
    4. convert the date string into an int, add in current year, then typecast as a long */
    protected LocalDate date = LocalDate.now();
    protected DateTimeFormatter formatter = DateTimeFormatter.ofPattern("DDD");
    protected String dateString = date.format(formatter);
    protected long dateLong = Integer.parseInt(dateString) + Year.now().getValue();

    // RNG setup
    protected Random rand = new Random(dateLong);
    protected double det = rand.nextDouble() * 10;

    @Override
    public void createPuzzle() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (det >= 3.9625) {
                    this.Solution[i][j] = 1;
                } else {
                    this.Solution[i][j] = 0;
                }

                // some silly math to re-roll det WHILE ensuring that it's consistent if the player resets the daily
                int temp = (int) det;
                double temp2 = temp;
                det -= temp2;
                det *= 10;

                // det eventually becomes 0 on large enough puzzle sizes
                // this is a safeguard against that
                if (det == 0) {
                    rand.setSeed(dateLong * 256);
                    det = rand.nextDouble() * 10;
                }
            }
        }
        // silly side effect of this hacked together algorithm:
        // before det becomes 0, the values are consistently 8.75, then 7.5, then 5, then 0
        // this will not be noticed by the user but it's a funny issue nonetheless
    }
}

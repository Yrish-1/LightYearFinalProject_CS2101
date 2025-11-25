package conogram;

public class Main {
    public static void main(String[] args) {
        PuzzleFlow test = new PuzzleFlow();
        int sizeR = 5, sizeC = 5;

        test.setPuzzle(sizeR, sizeC); // Use setter instead of DrawPuzzle

        // Just add these 3 lines:
        PuzzleGame game = new PuzzleGame(test);
        game.start();
        game.cleanup();
    }
}
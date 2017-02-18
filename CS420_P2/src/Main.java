/**
 * Created by Gabriel on 2017/02/17.
 */
public class Main {
   public static final int BOARD_SIZE = 20;

   public static void main(String[] args) {
      testRandomBoard();
   }

   private static void testRandomBoard() {
      Result hillClimb, genetic;

      System.out.println("Running hill climb...");
      hillClimb = (new HillClimbSolver(generateBoard())).solve();

      System.out.printf("Hill Climbing Solution:\n%s\n" +
                        "Genetic Solution:\n%s\n",
                        hillClimb,
                        "Not implemented yet");
   }

   public static int[] generateBoard() {
      int[] board = new int[BOARD_SIZE]; // all are false by default

      for (int i = 0; i < board.length; i++) {
         board[i] = (int)(Math.random() * BOARD_SIZE);
      }

      return board;
   }
}

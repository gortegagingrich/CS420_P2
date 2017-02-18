/**
 * Created by Gabriel on 2017/02/17.
 */
public class Main {
   public static final int BOARD_SIZE = 20;

   public static void main(String[] args) {
      testRandomBoard();
   }

   private static void testRandomBoard() {
      BoardState board = new BoardState(generateBoard());
      Result hillClimb, genetic;

      hillClimb = (new HillClimbSolver(board)).solve();

      System.out.printf("Board:\n%sNumber of Conflicts:%d\n\n" +
                        "Hill Climbing Solution:\n%s\n" +
                        "Genetic Solution:\n%s\n",
                        board.toString(),
                        BoardState.countConflicts(board.getBoard()),
                        hillClimb,
                        "Genetic not implemented");
   }

   private static boolean[][] generateBoard() {
      boolean[][] board = new boolean[BOARD_SIZE][BOARD_SIZE]; // all are false by default

      for (boolean[] r: board) {
         // for each row, set a random index to true to place a queen
         r[(int)(Math.random() * BOARD_SIZE)] = true;
      }

      return board;
   }
}

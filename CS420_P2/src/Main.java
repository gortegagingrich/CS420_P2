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
      hillClimb = (new HillClimbSolver(BoardState.generateBoard())).solve();


      System.out.printf("Hill Climbing Solution:\n%s\n" +
                        "Genetic Solution:\n%s\n",
                        hillClimb,
                        "Not implemented yet");
   }
}

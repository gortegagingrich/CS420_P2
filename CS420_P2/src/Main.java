/**
 * Created by Gabriel on 2017/02/17.
 */
public class Main {
   public static final int BOARD_SIZE = 20;

   public static void main(String[] args) {
      getData(200);
   }

   private static void getData(int num) {
      Result hillClimb, genetic;

      System.out.println("Algorithm,Conflict Count,Time (seconds),Cost");

      for (int i = 0; i < num; i++) {
         hillClimb = (new HillClimbSolver(BoardState.generateBoard())).solve();
         System.out.printf("HillClimb,%s",hillClimb.toCSV());
      }

      for (int i = 0; i < num; i++) {
         genetic = (new GeneticSolver()).solve();
         System.out.printf("Genetic,%s",genetic.toCSV());
      }
   }

   private static void testRandomBoard() {
      Result hillClimb, genetic;

      System.out.println("Running hill climb...");
      hillClimb = (new HillClimbSolver(BoardState.generateBoard())).solve();

      System.out.println("Running genetic...");
      genetic = (new GeneticSolver()).solve();


      System.out.printf("Hill Climbing Algorithm:\n%s\n" +
                        "Genetic Algorithm:\n%s\n",
                        hillClimb,
                        genetic);
   }
}

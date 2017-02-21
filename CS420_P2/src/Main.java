import java.util.Scanner;

/**
 * n-queens solver using simple hill climbing and a genetic algorithm
 * <p>
 * Created by Gabriel on 2017/02/17.
 */
class Main {
   public static final int BOARD_SIZE = 20;

   /**
    * Main method
    *
    * @param args takes no command line arguments
    */
   public static void main(String[] args) {
      Scanner scan = new Scanner(System.in);
      String c;
      main:
      while (true) {
         testRandomBoard();

         System.out.print("Solve another (Y/n): ");

         switch (((c = scan.nextLine()).length() > 0) ? c.charAt(0) : 'Y') {
            case 'n':
               System.out.println("Exiting...");
               break main;
         }
      }
   }

   /**
    * Runs both algorithms n times and formats output to be easily parsable.
    * Used to generate data to determine runtime and cost.
    *
    * @param n number of times to run each algorithm
    */
   private static void getData(int n) {
      Result hillClimb, genetic;

      System.out.println("Algorithm,Conflict Count,Time (seconds),Cost");

      for (int i = 0; i < n; i++) {
         hillClimb = (new HillClimbSolver(BoardState.generateBoard())).solve();
         System.out.printf("HillClimb,%s", hillClimb.toCSV());
      }

      for (int i = 0; i < n; i++) {
         genetic = (new GeneticSolver()).solve();
         System.out.printf("Genetic,%s", genetic.toCSV());
      }
   }

   /**
    * Runs both algorithms on randomly generated boards.
    */
   private static void testRandomBoard() {
      Result hillClimb, genetic;

      System.out.println("Running hill climb...");
      hillClimb = (new HillClimbSolver(BoardState.generateBoard())).solve();

      System.out.println("Running genetic...");
      genetic = (new GeneticSolver()).solve();


      System.out.printf("\nHill Climbing Algorithm:\n%s\n" +
                        "Genetic Algorithm:\n%s\n",
                        hillClimb,
                        genetic);
   }
}

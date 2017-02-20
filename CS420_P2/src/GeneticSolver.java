import java.util.Arrays;

/**
 * Object that solves an N-queens problem using a genetic algorithm.
 * <p>
 * Created by Gabriel on 2017/02/17.
 */
class GeneticSolver {
   private BoardState[] population;
   // mutation rate
   public static double MUTATE = 0.2;
   // crossover rate
   public static double CROSSOVER = 0.9;

   /**
    * Generate initial population of 200 random boards
    */
   public GeneticSolver() {
      // The population size took some tweaking, but 200 makes it finish relatively quickly
      population = new BoardState[200];

      // cost is going to be the number of board states generated
      BoardState.COUNT = 0;

      for (int i = 0; i < population.length; i++) {
         population[i] = new BoardState(BoardState.generateBoard());
         // increment cost
         BoardState.COUNT++;
      }
   }

   /**
    * Implementation of genetic algorithm to solve n-queens.
    *
    * @return Result instance with runtime set
    */
   public Result solve() {
      long start, end;

      start = System.nanoTime();
      while (true) {
         // Sort population in non-increasing order by fitness.
         // Second half will be ignored
         BoardState[] nextPopulation = select(population);

         if (nextPopulation[0].getFitness() == Main.BOARD_SIZE) {
            end = System.nanoTime();

            Result out = new Result(nextPopulation[0]);
            out.setTime(end - start);
            return (out);
         }

         // crossover
         for (int i = (int)(nextPopulation.length * (1 - CROSSOVER)); i < nextPopulation.length; i++) {
            int[] a, b;

            a = nextPopulation[(int)(nextPopulation.length * (1 - CROSSOVER))].getBoard();
            b = nextPopulation[(int)(nextPopulation.length * (1 - CROSSOVER))].getBoard();

            nextPopulation[i] = reproduce(a, b);

            // mutation
            if (Math.random() < MUTATE) {
               mutate(nextPopulation[i]);
            }

            // increment cost
            BoardState.COUNT++;
         }

         population = nextPopulation;
      }
   }

   /**
    * Copies and sorts the given population in non-increasing order.
    *
    * @param in unsorted population
    *
    * @return sorted population
    */
   private BoardState[] select(BoardState[] in) {
      // sort in by fitness
      BoardState[] out = new BoardState[in.length];
      System.arraycopy(in, 0, out, 0, in.length);
      Arrays.sort(out, (a, b) -> (
              compareStates(a, b)
      ));
      return out;
   }

   /**
    * Compares two given board states by fitness.
    *
    * @param a First board state.
    * @param b Second board state.
    *
    * @return (fitness of b) - (fitness of a)
    */
   private int compareStates(BoardState a, BoardState b) {
      int out = b.getFitness() - a.getFitness();

      a.setNotModified();
      b.setNotModified();

      return out;
   }

   /**
    * Generates child of two given board descriptions.
    * Concatenates second half of b to first half of a.
    *
    * @param a first board description
    * @param b second board description
    *
    * @return child board state of two given parents
    */
   private BoardState reproduce(int[] a, int[] b) {
      int[] nextBoard = new int[a.length];

      // in my testing, splitting them in half tends to improve performance slightly
      int index = a.length / 2;

      for (int i = 0; i < a.length; i++) {
         nextBoard[i] = (i < index) ? a[i] : b[i];
      }

      return (new BoardState(nextBoard));
   }

   /**
    * Randomizes position of the queen in a randomly selected row of the given board state.
    *
    * @param board Child board to be mutated
    */
   private void mutate(BoardState board) {
      // mutates by randomizing queen position in a random row
      board.getBoardInsecure()[(int) (Main.BOARD_SIZE * Math.random())] = (int) (Main.BOARD_SIZE * Math.random());
   }
}

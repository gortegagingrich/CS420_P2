import java.util.Arrays;

/**
 * Created by Gabriel on 2017/02/17.
 */
public class GeneticSolver {
   private BoardState[] population;

   public GeneticSolver() {
      // The population size took some tweaking, but 200 makes it finish relatively quickly
      population = new BoardState[200];

      for (int i = 0; i < population.length; i++) {
         population[i] = new BoardState(BoardState.generateBoard());
      }
   }

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
         for (int i = nextPopulation.length / 2; i < nextPopulation.length; i++) {
            int[] a, b;

            a = nextPopulation[(int) (nextPopulation.length / 2)].getBoard();
            b = nextPopulation[(int) (nextPopulation.length / 2)].getBoard();

            nextPopulation[i] = reproduce(a, b);

            // mutation
            if (Math.random() < 0.1) {
               mutate(nextPopulation[i]);
            }
         }

         population = nextPopulation;
      }
   }

   private BoardState[] select(BoardState[] in) {
      // sort in by fitness
      BoardState[] out = new BoardState[in.length];
      System.arraycopy(in, 0, out, 0, in.length);
      Arrays.sort(out, (a, b) -> (
              compareStates(a, b)
      ));
      return out;
   }

   private int compareStates(BoardState a, BoardState b) {
      int out = b.getFitness() - a.getFitness();

      a.setModified(false);
      b.setModified(false);

      return out;
   }

   private BoardState reproduce(int[] a, int[] b) {
      int[] nextBoard = new int[a.length];

      // in my testing, splitting them in half tends to improve performance slightly
      int index = a.length / 2;

      for (int i = 0; i < a.length; i++) {
         nextBoard[i] = (i < index) ? a[i] : b[i];
      }

      return (new BoardState(nextBoard));
   }

   private void mutate(BoardState board) {
      // mutates by randomizing queen position in a random row
      board.getBoardInsecure()[(int) (Main.BOARD_SIZE * Math.random())] = (int) (Main.BOARD_SIZE * Math.random());
   }
}

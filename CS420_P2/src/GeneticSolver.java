import java.util.Arrays;

/**
 * Created by Gabriel on 2017/02/17.
 */
public class GeneticSolver implements Solver {
   private BoardState[] population;

   public GeneticSolver(int[][] b) {
   }

   @Override
   public Result solve() {
      long start, end;
      Result r;
      BoardState b;

      start = System.nanoTime();
      b = solveGenetic();
      end = System.nanoTime();

      r = new Result(b);
      r.setTime(end-start);

      return r;
   }

   private BoardState solveGenetic() {
      // selection
      // crossover
      // mutation

      return null;
   }

   private BoardState[] select(BoardState[] in) {
      // select most fit n/2 elements from in
      return null;
   }

   private BoardState reproduce(boolean[][] a, boolean[][] b) {
      return null;
   }

   private void mutate(BoardState board) {
      // mutates by randomizing queen position in a random row
   }
}

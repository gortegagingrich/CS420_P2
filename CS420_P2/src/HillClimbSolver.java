/**
 * Created by Gabriel on 2017/02/17.
 */
public class HillClimbSolver implements Solver {
   private BoardState board;
   private int numConflicts;

   public HillClimbSolver(BoardState board) {
      this.board = board;
      numConflicts = BoardState.countConflicts(board.getBoard());
   }

   @Override
   public Result solve() {
      Result r;
      Long start, end;

      start = System.nanoTime();
      r = solveRecursion();
      end = System.nanoTime();
      r.setTime(end - start);

      return r;
   }

   public Result solveRecursion() {
      int[][] conflictsGrid = new int[Main.BOARD_SIZE][Main.BOARD_SIZE];
      boolean[][] tempBoard, successorBoard;
      int[] min = {Integer.MAX_VALUE, -1, -1};
      int i,j;

      // determine which change if any would result in the fewest conflicts
      for (i = 0; i < Main.BOARD_SIZE; i++) {
         tempBoard = board.getBoard();

         // set entire row to false
         for (j = 0; j < Main.BOARD_SIZE; j++) {
            tempBoard[i][j] = false;
         }

         // record number of conflicts for every possible state of the row
         for (j = 0; j < Main.BOARD_SIZE; j++) {
            tempBoard[i][j] = true;
            conflictsGrid[i][j] = BoardState.countConflicts(tempBoard);
            tempBoard[i][j] = false;

            // update location of minimum conflicts if necessary
            if (conflictsGrid[i][j] < min[0]) {
               min = new int[] {conflictsGrid[i][j], i, j};
            }
         }
      }

      // if new minimum has fewer conflicts that current state
      if (min[0] < numConflicts) {
         numConflicts = min[0];
         successorBoard = board.getBoard();

         for (i = 0; i < Main.BOARD_SIZE; i++) {
            successorBoard[min[1]][i] = (min[2]==i);
         }

         board = new BoardState(successorBoard);
         return solve();
      }

      return new Result(board);
   }
}

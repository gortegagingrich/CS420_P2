/**
 * Created by Gabriel on 2017/02/17.
 */
public class HillClimbSolver implements Solver {
   private int[] board;
   private int numConflicts;

   public HillClimbSolver(int[] board) {
      this.board = board;
      numConflicts = BoardState.countConflicts(board);
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
      // {number of conflicts, index to change, value of index}
      int[] min = {Integer.MAX_VALUE,0,0};
      int[] nextBoard = new int[Main.BOARD_SIZE];
      int conflictCount;
      System.arraycopy(board,0,nextBoard,0,Main.BOARD_SIZE);

      // find successor with minimum number of conflicts
      for (int i = 0; i < nextBoard.length; i++) {
         for (int j = 0; j < nextBoard.length; j++) {
            nextBoard[i] = j;
            conflictCount = BoardState.countConflicts(nextBoard);

            if (conflictCount < min[0]) {
               min[0] = conflictCount;
               min[1] = i;
               min[2] = j;
            }
         }

         nextBoard[i] = board[i];
      }

      // if successor has fewer than current, recursively call on successor
      if (min[0] < numConflicts) {
         numConflicts = min[0];
         board[min[1]] = min[2];

         return solveRecursion();
      }

      return (new Result(new BoardState(board)));
   }
}

/**
 * Created by Gabriel on 2017/02/17.
 */
public class Result {
   private BoardState board;
   private long time;

   public Result(BoardState board) {
      this.board = board;
      time = 0;
   }

   public void setTime(long time) {
      this.time = time;
   }

   public String toString() {
      return String.format("Final board:\n%s" +
                           "Number of conflicts:%d\n" +
                           "Time elapsed: %f seconds\n",
                           board,
                           BoardState.countConflicts(board.getBoard()),
                           time * 0.000000001);
   }

   public String toCSV() {
      // conflict count, time (seconds), Board Count
      String out = String.format("%d,%f,%d\n",BoardState.countConflicts(board.getBoard()),time*0.000000001,BoardState.COUNT);
      return out;
   }
}

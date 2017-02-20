/**
 * Class that describes an object that formats the results from solving an n-queens problem
 * <p>
 * Created by Gabriel on 2017/02/17.
 */
public class Result {
   private final BoardState board;
   private       long       time;

   /**
    * Sets board
    * Initializes time to 0
    *
    * @param board Final board state from solving n-queens
    */
   public Result(BoardState board) {
      this.board = board;
      time = 0;
   }

   /**
    * Sets elapsed time.
    *
    * @param time Time in nanoseconds
    */
   public void setTime(long time) {
      this.time = time;
   }

   /**
    * Generates formatted string giving readable results.
    *
    * @return Formatted string containing final board, conflict count, and time elapsed
    */
   public String toString() {
      return String.format("Final board:\n%s" +
                           "Number of conflicts:%d\n" +
                           "Time elapsed: %f seconds\n",
                           board,
                           BoardState.countConflicts(board.getBoard()),
                           time * 0.000000001);
   }

   /**
    * Generates formatted string giving easily parsable results.
    *
    * @return Formatted string containing number of conflicts, time, and number of board states separated by commas
    */
   public String toCSV() {
      // conflict count, time (seconds), Board Count
      return String
              .format("%d,%f,%d\n", BoardState.countConflicts(board.getBoard()), time * 0.000000001, BoardState.COUNT);
   }
}

/**
 * Class describing a representation of individual board states
 * <p>
 * Created by Gabriel on 2017/02/17.
 */
public class BoardState {
   private int[]   board;
   private boolean modified;
   private int     fitness;

   /**
    * Creates board state with given board
    *
    * @param board Representation of the board. {row 1 queen index, row 2 queen index, ...}
    */
   public BoardState(int[] board) {
      assert (board.length == Main.BOARD_SIZE);

      this.board = new int[board.length];
      modified = true;
      fitness = getFitness();

      System.arraycopy(board, 0, this.board, 0, board.length);
   }

   /**
    * Counts totale number of conflicts in a given board
    *
    * @param board Representation of board. {row 1 queen index, row 2 queen index, ...}
    *
    * @return total number of conflicts in the board
    */
   public static int countConflicts(int[] board) {
      int count = 0;
      int j;

      for (int i = 0; i < board.length; i++) {
         for (j = i + 1; j < board.length; j++) {
            //check horizontal
            if (board[j] == board[i]) {
               count++;
            }

            // check diagonals
            if (Math.abs(board[j] - board[i]) == Math.abs(j - i)) {
               count++;
            }
         }
      }

      return count;
   }

   /**
    * Randomly generates a board of size Main.BOARD_SIZE
    *
    * @return Randomly generated representation of board. {row 1 queen index, row 2 queen index, ...}
    */
   public static int[] generateBoard() {
      int[] board = new int[Main.BOARD_SIZE]; // all are false by default

      for (int i = 0; i < board.length; i++) {
         board[i] = (int) (Math.random() * Main.BOARD_SIZE);
      }

      return board;
   }

   /**
    * More secure getter for the board's int array.
    *
    * @return reference to copy of board of board
    */
   public int[] getBoard() {
      int[] board = new int[this.board.length];

      System.arraycopy(this.board, 0, board, 0, board.length);

      return board;
   }

   /**
    * Insecure getter for the board's int array.
    * Sets modified to true so it recalculates fitness every time getFitness() is called.
    *
    * @return Reference to board
    */
   public int[] getBoardInsecure() {
      modified = true; // means you probably have to recalculate fitness
      return board;
   }

   /**
    * Generates string representation of the board with 'q' representing queens and '-' representing empty tiles
    *
    * @return String representation of board with trailing new line
    */
   public String toString() {
      String out = "";

      for (int i : board) {
         for (int j = 0; j < board.length; j++) {
            out += String.format("%s ", (j == i) ? "q" : "-");
         }

         out += "\n";
      }

      return out;
   }

   /**
    * Fitness function for use in the Genetic algorithm
    *
    * @return number of queens with no conflicts
    */
   public int getFitness() {
      if (modified) {
         fitness = 0;
         int i, j;
         boolean hasConflicts;

         for (i = 0; i < board.length; i++) {
            hasConflicts = false;

            for (j = i + 1; j < board.length; j++) {
               // check horizontal
               if (board[j] == board[i] ||
                   (Math.abs(board[j] - board[i]) == Math.abs(j - i))) {
                  hasConflicts = true;
                  break;
               }
            }

            fitness += (hasConflicts) ? (0) : (1);
         }
      }

      return fitness;
   }
}

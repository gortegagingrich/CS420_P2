import java.util.Arrays;

/**
 * Created by Gabriel on 2017/02/17.
 */
public class BoardState implements Comparable<BoardState> {
   private int[] board;

   public BoardState(int[] board) {
      assert(board.length == Main.BOARD_SIZE);

      this.board = new int[board.length];

      System.arraycopy(board,0,this.board,0,board.length);
   }

   public int[] getBoard() {
      int[] board = new  int[this.board.length];

      System.arraycopy(this.board,0,board,0,board.length);

      return board;
   }

   public int[] getBoardInsecure() {
      return board;
   }

   public String toString() {
      String out = "";

      for (int i: board) {
         for (int j = 0; j < Main.BOARD_SIZE; j++) {
            out += String.format("%s ",(j == i) ? "q" : "-");
         }

         out += "\n";
      }

      return out;
   }

   public int getFitness() {
      return 0;
   }

   public static int countConflicts(int[] board) {
      int count = 0;
      int j;

      for (int i = 0; i < board.length; i++) {
         for (j = i+1; j < board.length; j++) {
            //check horizontal
            if (board[j] == board[i]) {
               count++;
            }

            // check diagonals
            if (Math.abs(board[j] - board[i]) == Math.abs(i - j)) {
               count++;
            }
         }
      }

      return count;
   }

   @Override
   public int compareTo(BoardState boardState) {
      return boardState.getFitness() - getFitness();
   }
}

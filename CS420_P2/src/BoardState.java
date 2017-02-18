/**
 * Created by Gabriel on 2017/02/17.
 */
public class BoardState {
   private boolean[][] board;

   public BoardState(boolean[][] board) {
      assert(board.length == Main.BOARD_SIZE);

      this.board = new boolean[Main.BOARD_SIZE][Main.BOARD_SIZE];

      for (int i = 0; i < this.board.length; i++) {
         for (int j = 0; j < this.board.length ;j++) {
            this.board[i][j] = board[i][j];
         }
      }
   }

   public boolean[][] getBoard() {
      boolean[][] board = new  boolean[this.board.length][this.board.length];

      for (int i = 0; i < board.length; i++) {
         System.arraycopy(this.board[i], 0, board[i], 0, board.length);
      }

      return board;
   }

   public String toString() {
      String out = "";

      for (boolean[] row: board) {
         for (boolean b: row) {
            // displays queens as "q" and blank squares as "-"
            out = String.format("%s%s ", out, b ? "q" : "-");
         }
         out += "\n";
      }

      return out;
   }

   public static int countConflicts(boolean[][] board) {
      int count = 0;
      int x,y,xx,yy;

      for (x = 0; x < Main.BOARD_SIZE; x++) {
         for (y = 0; y < Main.BOARD_SIZE; y++) {
            // if square contains queen
            if (board[x][y]) {
               // check horizontal
               for (xx = x + 1; xx < Main.BOARD_SIZE; xx++) {
                  if (board[xx][y]) {
                     count++;
                  }
               }

               // check diagonal down
               xx = x;
               yy = y;

               while (++xx < Main.BOARD_SIZE && ++yy < Main.BOARD_SIZE) {
                  if (board[xx][yy]) {
                     count++;
                  }
               }

               // check diagonal up
               xx = x;
               yy = y;

               while (++xx < Main.BOARD_SIZE && --yy > -1) {
                  if (board[xx][yy]) {
                     count++;
                  }
               }
            }
         }
      }

      return count;
   }
}

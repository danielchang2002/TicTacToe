import java.util.ArrayList;
import java.util.Arrays;

public class Board {
    private final String[] board;

    public Board() {
        board = new String[9];
        for (int i = 0; i < 9; i++) {
            board[i] = "-";
        }
    }

    public Board(String[] board) {
        this.board = board.clone();
    }

    public boolean isEmpty(int index) {
        return board[index].equals("-");
    }

    public boolean isFull() {
        for (String s : board) {
            if (s.equals("-")) {
                return false;
            }
        }
        return true;
    }

    public void draw() {
        for (int i = 0; i < 6; i++) {
            if (board[i].equals("-")) {
                System.out.print("___");
            } else if (board[i].equals("X")) {
                System.out.print("_X_");
            } else {
                System.out.print("_O_");
            }
            if (i % 3 == 2) {
                System.out.println();
            } else {
                System.out.print("|");
            }
        }
        for (int i = 6; i < 9; i++) {
            if (board[i].equals("-")) {
                System.out.print("   ");
            } else if (board[i].equals("X")) {
                System.out.print(" X ");
            } else {
                System.out.print(" O ");
            }
            if (i % 3 != 2) {
                System.out.print("|");
            } else {
                System.out.println();
            }
        }
    }

    public ArrayList<Board> neighbors(String player) {
        ArrayList<Board> a = new ArrayList<Board>();
        for (int i = 0; i < 9; i++) {
            if (board[i].equals("-")) {
                String[] newBoard = this.board.clone();
                newBoard[i] = player;
                a.add(new Board(newBoard));
            }
        }
        return a;
    }

    public boolean hasWin(String player) {
        // check vertical
        for (int i = 0; i < 3; i++) {
            if (board[i].equals(player) && board[i + 3].equals(player) && board[i + 6].equals(player)) {
                return true;
            }
        }

        // check horizontal
        for (int i = 0; i < 7; i += 3) {
            if (board[i].equals(player) && board[i + 1].equals(player) && board[i + 2].equals(player)) {
                return true;
            }
        }

        // check diagonal
        return (board[0].equals(player) && board[4].equals(player) && board[8].equals(player)) ||
                (board[2].equals(player) && board[4].equals(player) && board[6].equals(player));
    }

    public String[] getBoard() {
        return this.board.clone();
    }

    public boolean isCompTurn() {
        int numX = 0;
        int numO = 0;
        for (String s : board) {
            if (s.equals("X")) {
                numX++;
            } else if (s.equals("O")) {
                numO++;
            }
        }
        if (Game.COMP.equals("X")) {
            return numX == numO;
        }
        return numX != numO;
    }

    public int evaluate() {
        if (hasWin(Game.COMP)) {
            return 10;
        }
        if (hasWin(Game.PLAYER)) {
            return -10;
        }
        return 0;
    }

    public Board getPlayerMoveBoard(int move) {
        String[] newBoard = Arrays.copyOf(board, board.length);
        newBoard[move] = Game.PLAYER;
        return new Board(newBoard);
    }
}

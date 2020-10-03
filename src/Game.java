import java.util.Scanner;

public class Game {
    private Board board;
    private final Solver solver;
    public static String PLAYER;
    public static String COMP;
    // X always goes first
    // computer is maximizer
    private Scanner user;

    // X is player, goes first, is minimizer
    // O is computer, goes second, is maximizer

    public Game() {
        this.solver = new Solver();
        user = new Scanner(System.in);
    }

    private void queryUser() {
        System.out.println("Xs or Os?");
        String player = "";
        while (true) {
            player = user.next();
            if (player.equals("X") || player.equals("O")) {
                PLAYER = player;
                break;
            }
        }
        if (PLAYER.equals("X")) {
            COMP = "O";
        } else {
            COMP = "X";
        }
    }

    public void run() {
        String again = "";
        while (true) {
            game();
            System.out.println("Play again? Y/N");
            again = user.next();
            if (again.equals("N")) {
                break;
            }
        }
    }

    public void game() {
        queryUser();
        board = new Board();
        board.draw();
        if (PLAYER.equals("X")) {
            playerMove();
            board.draw();
        }
        while (true) {
            compMove();
            board.draw();
            if (hasEnd()) {
                break;
            }
            playerMove();
            board.draw();
            if (hasEnd()) {
                break;
            }
        }
    }

    private void compMove() {
        solver.updateBoard(board);
        board = solver.bestMove();
    }

    private void playerMove() {
        int move = -1;
        while (move < 0 || move > 8 || !board.isEmpty(move)) {
            move = user.nextInt();
        }
        this.board = board.getPlayerMoveBoard(move);
    }

    private boolean hasEnd() {
        int eval = board.evaluate();
        if (eval > 0) {
            System.out.println(Game.COMP + " WINS!!!");
            return true;
        }
        if (eval < 0) {
            System.out.println(Game.PLAYER + " WINS!!!");
            return true;
        }
        if (board.isFull()) {
            System.out.println("TIE!!!");
            return true;
        }
        return false;
    }


}


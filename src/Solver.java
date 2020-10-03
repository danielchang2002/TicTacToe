public class Solver {
    private Board board;

    public Solver() {
    }

    public void updateBoard(Board b) {
        board = b;
    }

    public Board bestMove() {
        if (!board.isCompTurn()) {
            throw new IllegalCallerException("Called solver on a board that is player's turn");
        }
        Board bestMove = null;
        int bestScore = Integer.MIN_VALUE;
        for (Board b : board.neighbors(Game.COMP)) { // Computer generates all of his possible moves
            int score = minimax(b, 0, false);
//            b.draw();
//            System.out.println(score);
            if (score > bestScore) { // Computer picks the best move
                bestScore = score;
                bestMove = b;
            }
        }
        return bestMove;
    }

    private int minimax(Board b, int depth, boolean isMaximizer) {
//        System.out.println("Depth: " + depth);
//        b.draw();
        int eval = b.evaluate();
        if (eval > 0) {
            return eval - depth;
        }
        if (eval < 0) {
            return eval + depth;
        }
        if (b.isFull()) {
            return 0;
        }
        if (isMaximizer) {
            // Player's move is passed into minimax
            // The score of that move is the maximum score of that move's children
            int bestScore = Integer.MIN_VALUE;
            for (Board neighbor : b.neighbors(Game.COMP)) {
                int score = minimax(neighbor, depth + 1, false);
                bestScore = Math.max(bestScore, score);
            }
            return bestScore;
        } else {
            // Computer's possible move is passed into minimax
            // the score of its possible move is the minimum score of all of the move's children
            int bestScore = Integer.MAX_VALUE;
            for (Board neighbor : b.neighbors(Game.PLAYER)) { // gets all possible moves from player, O
                int score = minimax(neighbor, depth + 1, true);
                bestScore = Math.min(bestScore, score);
            }
            return bestScore;
        }
    }
}

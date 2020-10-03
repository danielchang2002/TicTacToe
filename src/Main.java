public class Main {
    public static void main(String[] args) {
//        String[] testBoard = {"X", "O", "X", "O", "O", "X", "-", "-", "-"};
//        String[] testBoard = {"X", "-", "O", "-", "-", "-", "-", "-", "-"};
//
//        Board b = new Board(testBoard);
//        b.draw();
//        System.out.println(b.isXTurn());
//        Solver s = new Solver();
//        s.updateBoard(b);
//        s.bestMove().draw();
        Game g = new Game();
        g.run();

    }
}

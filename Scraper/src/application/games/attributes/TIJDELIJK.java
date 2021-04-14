package application.games.attributes;

public class TIJDELIJK {

    public static void main(String[] args) throws CloneNotSupportedException {
        Reversi reversi = new Reversi();//hier  moet even naar gekeken worden
        BKE bke = new BKE();
        Board board = new Board(3);
        MiniMax miniMax = new MiniMax();
        board.createBoard();
        board.printBoard();
        /*board.setStartPieceOnBoard(27,'x');
        board.setStartPieceOnBoard(36,'x');    
        board.setStartPieceOnBoard(35,'o');
        board.setStartPieceOnBoard(28,'o');
        */
        

        reversi.setPieceOnBoard(board, 6, 'o');
        reversi.setPieceOnBoard(board, 4, 'o');
        reversi.setPieceOnBoard(board, 2, 'o');
        //reversi.setPieceOnBoard(board, 14, 'x');
        //reversi.setPieceOnBoard(board, 12, 'o');
        System.out.println(bke.isWonBKE(board));
        board.printBoard();
        //reversi.setPieceOnBoard(board, 34,'x');
        //board.printBoard();
        //reversi.setPieceOnBoard(board,42,'o');
        //board.printBoard();
        //reversi.setPieceOnBoard(board, 43,'x');
        //board.printBoard();
        //reversi.setPieceOnBoard(board, 26,'o');
        //board.printBoard();
        
        
        //board.setStartPieceOnBoard(board.getNeighbor(0, 0, 1),'o');
        //System.out.println(reversi.getFreeSpaces(board,'o')+ "hello");
        //System.out.println(reversi.bestSpot(reversi.getFreeSpaces(board,'o')));
        //System.out.println(miniMax.miniMaxi(board,10,0,0,'o').getLastSet());
        //System.out.println(miniMax.miniMaxi(board,1,'o'));
        //miniMax.copyBoard(board);
        
    }
    
}

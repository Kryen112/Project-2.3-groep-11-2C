package application.games.attributes;

public class TIJDELIJK {

    public static void main(String[] args) throws CloneNotSupportedException {
        Reversi reversi = new Reversi();//hier  moet even naar gekeken worden
        Board board = new Board();
        miniMax miniMax = new miniMax();
        board.createBoard();
        board.pirntBoard();
        /*board.setStartPieceOnBoard(27,'x');
        board.setStartPieceOnBoard(36,'x');    
        board.setStartPieceOnBoard(35,'o');
        board.setStartPieceOnBoard(28,'o');
        */
        
        reversi.setStartPieces(board, 'o');
        reversi.setPieceOnBoard(board, 1, 'x');
        reversi.setPieceOnBoard(board, 2, 'o');
        reversi.setPieceOnBoard(board, 13, 'o');
        reversi.setPieceOnBoard(board, 14, 'x');
        reversi.setPieceOnBoard(board, 12, 'o');
        board.pirntBoard();
        //reversi.setPieceOnBoard(board, 34,'x');
        //board.pirntBoard();
        //reversi.setPieceOnBoard(board,42,'o');
        //board.pirntBoard();
        //reversi.setPieceOnBoard(board, 43,'x');
        //board.pirntBoard();
        //reversi.setPieceOnBoard(board, 26,'o');
        //board.pirntBoard();
        
        //System.out.println(board.getNeighbor(4, 9, 3));
        System.out.println(reversi.getFreeSpaces(board,'o')+ "hello");
        System.out.println(reversi.bestSpot(reversi.getFreeSpaces(board,'o')));
        System.out.println(miniMax.miniMaxi(board,10,0,0,'o').getLastSet());
        //System.out.println(miniMax.miniMaxi(board,1,'o'));
        //miniMax.copyBoard(board);
        
    }
    
}

package application.games.attributes;
import java.lang.*;

public class miniMax {
    Reversi reversi = new Reversi();//hoer  moet even naar gekeken worden
    public static void main(String[] args) {
        Board board = new Board();
        Reversi reversi = new Reversi();
        board.createBoard();
        board.pirntBoard();
        board.setStartPieceOnBoard(27,'x');
        board.setStartPieceOnBoard(36,'x');    
        board.setStartPieceOnBoard(35,'o');
        board.setStartPieceOnBoard(28,'o');
        board.pirntBoard();
        reversi.setPieceOnBoard(board, 34,'x');
        board.pirntBoard();
        reversi.setPieceOnBoard(board,42,'o');
        board.pirntBoard();
        reversi.setPieceOnBoard(board, 43,'x');
        board.pirntBoard();
        reversi.setPieceOnBoard(board, 26,'o');
        board.pirntBoard();
        //System.out.println(board.getNeighbor(4, 9, 3));
        System.out.println(reversi.getFreeSpaces(board)+ "hello");
        System.out.println(reversi.bestSpot(reversi.getFreeSpaces(board)));
        
    }

    public Board miniMaxi(Board board, int depth, char maximizingPlayer) throws CloneNotSupportedException{
        if(depth == 0 ){//or game over
            return board;
        }
        if(maximizingPlayer == 'o'){
            int eval= 0;
            for(int element : reversi.getFreeSpaces(board)){
                Board boardCopy = (Board) board.clone();
                reversi.setPieceOnBoard(boardCopy, element, 'x');
                eval = miniMaxi(board, depth-1, maximizingPlayer).getoPoints();
                board.setoPoints(Math.max(eval, board.getoPoints()));
            }
            return board;

        }
        else {
            int eval= 0;
            for(int element : reversi.getFreeSpaces(board)){
                Board boardCopy = (Board) board.clone();
                reversi.setPieceOnBoard(boardCopy, element, 'o');
                eval = miniMaxi(board, depth-1, maximizingPlayer).getoPoints();
                board.setoPoints(Math.min(eval, board.getoPoints()));
            }
            return board;
        }

        
    }
    
}

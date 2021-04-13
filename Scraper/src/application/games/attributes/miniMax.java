package application.games.attributes;
import java.lang.*;

import javax.swing.border.EmptyBorder;

public class miniMax {
 Reversi reversi = new Reversi();//hoer  moet even naar gekeken worden
    

    public Board copyBoard(Board board){
        Board newBoard = new Board();
        newBoard.setGameBoard(board.getGameBoard());
        //System.out.println(board.getoPoints()+ "gekopieerde board");
        newBoard.setxPoints(board.getxPoints());
        newBoard.setoPoints(board.getoPoints());
        //System.out.println("dit is het bord");
        //board.pirntBoard();
        return newBoard;
    }


    public Board miniMaxi(Board board, int depth, int alpha, int beta, char maximizingPlayer) throws CloneNotSupportedException{
        if(depth == 0 ){//or game over
            return board;
        }
        if(maximizingPlayer == 'o'){
            int eval= 0;
            int maxEval= 0;
            boolean firstTime = true;
            Board boardCopy = new Board();
            Board boardToReturn = new Board();
            if( reversi.getFreeSpaces(board,'o') != null){
                for(int element : reversi.getFreeSpaces(board,'o')){
                    boardCopy = copyBoard(board);
                    reversi.setPieceOnBoard(boardCopy, element, 'o');
                    //System.out.println("O" + boardCopy.oPoints + " " + boardCopy.xPoints + boardCopy);
                    //boardCopy.pirntBoard();
                    eval = miniMaxi(boardCopy, depth-1, alpha, beta, 'x').getXOPoints();
                    //board.setoPoints(Math.max(eval, board.getoPoints()));
                    if(firstTime){
                        maxEval = eval;
                        boardToReturn = boardCopy;
                        alpha = eval;
                        firstTime = false;
                    }
                    else{
                        if(eval >= maxEval){
                            maxEval = eval;
                            boardToReturn = boardCopy;
                        }
                        beta = Math.max(beta, eval);
                        if(beta <= alpha){
                            break;
                        }
                    }
                    
                }
                return boardToReturn;
            }
            else{
                return boardCopy;
            }

        }
        else {
            int eval= 0;
            int minEval= 0;
            boolean firstTime = true;
            Board boardCopy = new Board();
            Board boardToReturn = new Board();
            if(reversi.getFreeSpaces(board,'x') != null){
                for(int element : reversi.getFreeSpaces(board,'x')){
                    boardCopy = copyBoard(board);
                    reversi.setPieceOnBoard(boardCopy, element, 'x');
                    //System.out.println("X" + boardCopy.oPoints + " " + boardCopy.xPoints + reversi.getFreeSpaces(board, 'x') + boardCopy);
                    //System.out.println("X");
                    //boardCopy.pirntBoard();
                    eval = miniMaxi(boardCopy, depth-1,alpha,beta, 'o').getXOPoints();
                    if(firstTime){
                        minEval = eval;
                        beta = eval;
                        boardToReturn = boardCopy;
                        firstTime = false;
                    }
                    else{
                        if(eval <= minEval){
                            minEval = eval;
                            boardToReturn = boardCopy;
                        }
                        beta = Math.min(beta, eval);
                        if(alpha <= beta){
                            break;
                        }
                    }
                }
                return boardToReturn;
            }
            else{
                return boardCopy;
            }
        }

        
    }
    
}

package application.games.attributes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Reversi {
    public void setStartPieces(Board board, char pieceToPlace) {
        if(pieceToPlace=='o'){
            board.setStartPieceOnBoard(28,'o');
            board.setStartPieceOnBoard(35,'o');
            board.setStartPieceOnBoard(27,'x');
            board.setStartPieceOnBoard(36,'x');
        }
        else{
            board.setStartPieceOnBoard(28,'x');
            board.setStartPieceOnBoard(35,'x');
            board.setStartPieceOnBoard(27,'o');
            board.setStartPieceOnBoard(36,'o');
        }
        
    }

    public void setPieceOnBoard(Board board, int space, char pieceToPlace) {
        //getNeighbor(int direction, int space, int steps);
        board.getBoard()[board.getRow(space)][board.getCollum(space)] = pieceToPlace;
        char pieceToBeat;
        if(pieceToPlace == 'o'){
            pieceToBeat ='x';
        }
        else{
            pieceToBeat ='o';
        }

        boolean won = false;
        for(int y = 0; y < 8; y++) {
            int steps = 1;
            if(board.getSpaceValue(board.getNeighbor(y, space, steps)) == pieceToBeat){
                if(won){
                    board.setStartPieceOnBoard(board.getNeighbor(y, space, steps),pieceToPlace);
                }
                steps++;
                while(board.getSpaceValue(board.getNeighbor(y, space, steps)) == pieceToBeat){
                    if(won){
                        board.setStartPieceOnBoard(board.getNeighbor(y, space, steps),pieceToPlace);
                    }
                    steps++;
                }
                if(board.getSpaceValue(board.getNeighbor(y, space, steps)) == pieceToPlace){
                    if(won){
                        won = false;
                    }
                    else{
                        y--;
                        won = true;
                    }
                }
            }
        }
        

    }

    public List<Integer> getFreeSpaces(Board board) {
        List<Integer> list=new ArrayList<Integer>();
        for(int i = 0; i< board.getBoard().length; i++){
            for(int j = 0; j< board.getBoard()[i].length; j++){
                if(isMoveAllowed(board, board.getSpace(i, j))){
                    list.add(board.getSpace(i, j));
                }
            }
        }


        if (list.isEmpty()){
            return null;
        }
        return list;
    }

    public List<Integer> bestSpot(List<Integer> spaces){
        //Random rand = new Random();
        List<Integer> Region1 = new ArrayList<>();
        Collections.addAll(Region1, 18,19,20,21,26,27,28,29,34,35,36,37,42,43,44,45);
        List<Integer> Region2 = new ArrayList<>();
        Collections.addAll(Region2, 10,11,12,13,17,22,25,30,33,38,41,46,50,51,52,53);
        List<Integer> Region3 = new ArrayList<>();
        Collections.addAll(Region3, 2,3,4,5,16,23,24,31,32,39,40,47,58,59,60,61);
        List<Integer> Region4 = new ArrayList<>();
        Collections.addAll(Region4, 1,6,8,9,14,15,48,49,54,55,57,62);
        List<Integer> Region5 = new ArrayList<>();
        Collections.addAll(Region5, 0,7,56,63);

        List<List<Integer>> lists = new ArrayList<>();
        Collections.addAll(lists, Region5,Region3,Region1,Region2,Region4);
        List<Integer> toChoseFrom = new ArrayList<>();

        for (List<Integer> list : lists){
            if(!toChoseFrom.isEmpty()){
                return toChoseFrom;//.get(rand.nextInt(toChoseFrom.size()));
            }  
            toChoseFrom.addAll(spaces);      
            toChoseFrom.retainAll(list);
        }
              
        return toChoseFrom;
    }

    public Boolean isMoveAllowed(Board board, int space) {
        Boolean move = false;
        if(board.isValidSpace(space)){
            for(int y = 0; y < 8; y++) {
                int steps = 1;
                if(board.getSpaceValue(board.getNeighbor(y, space, steps)) == 'x'){

                    steps++;
                    while(board.getSpaceValue(board.getNeighbor(y, space, steps)) == 'x'){
                        steps++;
                    }
                    if(board.getSpaceValue(board.getNeighbor(y, space, steps)) == 'o'){
                        move = true;
                    }
                }
            }
        }
        return move;
    }

    
}

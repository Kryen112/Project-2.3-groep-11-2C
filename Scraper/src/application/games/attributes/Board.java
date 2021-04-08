package application.games.attributes;

import application.App;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board implements Boards {
    public int rows;
    public int cols;
    public int totalSpaces;
    public int offset;
    public List<String> spaces;
    public char[][] board;
    public int height = 8;

    @Override
    public Board execute() {
        createBoard();
        return null;
    }

    public void createBoard() {
        board = new char[height][height];
        for (int x = 0; x < height; x++)
        {
            for (int y = 0; y < height; y++)
            {
                board[x][y] = '.';
            }
        }
    }

    public void setStartPieces(char pieceToPlace) {
        if(pieceToPlace=='o'){
            setStartPieceOnBoard(28,'o');
            setStartPieceOnBoard(35,'o');
            setStartPieceOnBoard(27,'x');
            setStartPieceOnBoard(36,'x');
        }
        else{
            setStartPieceOnBoard(28,'x');
            setStartPieceOnBoard(35,'x');
            setStartPieceOnBoard(27,'o');
            setStartPieceOnBoard(36,'o');
        }

    }

    @Override
    public String title() {
        return null;
    }

    /*    public void markPlaceOnBoard(int space, String marker) {

        }
    */
    public void setStartPieceOnBoard(int space, char pieceToPlace) {
        board[getRow(space)][getCollum(space)] = pieceToPlace;
        pirntBoard();
    }

    public void setPieceOnBoard(int space, char pieceToPlace) {
        //getNeighbor(int direction, int space, int steps);
        board[getRow(space)][getCollum(space)] = pieceToPlace;
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
            if(getSpaceValue(getNeighbor(y, space, steps)) == pieceToBeat){
                if(won){
                    setStartPieceOnBoard(getNeighbor(y, space, steps),pieceToPlace);
                }
                steps++;
                while(getSpaceValue(getNeighbor(y, space, steps)) == pieceToBeat){
                    if(won){
                        setStartPieceOnBoard(getNeighbor(y, space, steps),pieceToPlace);
                    }
                    steps++;
                }
                if(getSpaceValue(getNeighbor(y, space, steps)) == pieceToPlace){
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
    /*  public ArrayList<ArrayList<String>> getColumns() {
          return null;
      }

      public ArrayList<ArrayList<String>> getRows() {
          return null;
      }
  */
    public ArrayList<ArrayList<String>> getLeftDiagonal() {
        return null;
    }

    public ArrayList<ArrayList<String>> getRightDiagonal() {
        return null;
    }

    public void clearBoard() {
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < height; j++) {
                board[i][j] = '.';
            }
        }
    }

    public void pirntBoard(){
        for(int i = 0; i< height; i++){
            System.out.println(board[i]);
        }
        System.out.println("&&&&&&&&");
    }

    public char getSpaceValue(int space) {
        if (space == height*height){
            return 'z';
        }
        return board[getRow(space)][getCollum(space)];
    }

    public int getRow(int space) {
        return space/height;
    }

    public int getCollum(int space) {
        return space%height;
    }

    public int getRandomSet() {
        Random random = new Random();
        List<Integer> freeSpaces = getFreeSpaces();
        int size = freeSpaces.size();
        int location = freeSpaces.get(random.nextInt(size));
        setPieceOnBoard(location, 'o');
        return location;
    }

    public List<Integer> getFreeSpaces() {
        List<Integer> list= new ArrayList<Integer>();
        for(int i = 0; i< board.length; i++){
            for(int j = 0; j< board[i].length; j++){
                if(isMoveAllowed(getSpace(i, j))){
                    list.add(getSpace(i, j));
                }
            }
        }

        if (list.isEmpty()){
            return null;
        }
        return list;
    }

    public Boolean isValidSpace(int space) {
        if(space <= (height*height-1) && space >= 0){
            return isSpaceAvailabe(space);
        }
        return false;
    }

    public Boolean isSpaceAvailabe(int space) {
        if(board[getRow(space)][getCollum(space)] == '.'){
            return true;
        }
        else{
            return false;
        }

    }

    public int getSpace(int row, int collum){
        int space = height*height;
        if(row >= 0 && collum >= 0 && row < height && collum < height){
            space = row*height + collum;
            //System.out.println("d");
        }
        return space;
    }

    public List<Integer> getFreeSpacesX() {
        List<Integer> list= new ArrayList<Integer>();
        for(int i = 0; i< board.length; i++){
            for(int j = 0; j< board[i].length; j++){
                if(isMoveAllowedX(getSpace(i, j))){
                    list.add(getSpace(i, j));
                }
            }
        }

        if (list.isEmpty()){
            return null;
        }
        return list;
    }

    public Boolean isMoveAllowedX(int space) {
        boolean move = false;
        if(isValidSpace(space)){
            for(int y = 0; y < 8; y++) {
                int steps = 1;
                if(getSpaceValue(getNeighbor(y, space, steps)) == 'o'){

                    steps++;
                    while(getSpaceValue(getNeighbor(y, space, steps)) == 'o'){
                        steps++;
                    }
                    if(getSpaceValue(getNeighbor(y, space, steps)) == 'x'){
                        move = true;
                    }
                }
            }
        }

        return move;
    }

    public Boolean isMoveAllowed(int space) {
        boolean move = false;
        if(isValidSpace(space)){
            for(int y = 0; y < 8; y++) {
                int steps = 1;
                if(getSpaceValue(getNeighbor(y, space, steps)) == 'x'){

                    steps++;
                    while(getSpaceValue(getNeighbor(y, space, steps)) == 'x'){
                        steps++;
                    }
                    if(getSpaceValue(getNeighbor(y, space, steps)) == 'o'){
                        move = true;
                    }
                }
            }
        }

        return move;
    }

    public int getNeighbor(int direction, int space, int steps){
        int value = height*height;
        switch(direction) {
            case 0:
                value = getSpace(getRow(space)-steps,getCollum(space)-steps);
                break;
            case 1:
                value = getSpace(getRow(space)-steps,getCollum(space));
                break;
            case 2:
                value = getSpace(getRow(space)-steps,getCollum(space)+steps);
                break;
            case 3:
                value = getSpace(getRow(space),getCollum(space)+steps);
                break;
            case 4:
                value = getSpace(getRow(space)+steps,getCollum(space)+steps);
                break;
            case 5:
                value = getSpace(getRow(space)+steps,getCollum(space));
                break;
            case 6:
                value = getSpace(getRow(space)+steps,(getCollum(space)-steps));
                break;
            case 7:
                value = getSpace(getRow(space),getCollum(space)-steps);
                break;
        }
        return value;
    }
}

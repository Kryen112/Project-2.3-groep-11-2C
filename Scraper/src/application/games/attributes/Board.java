package application.games.attributes;

import application.App;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.sound.midi.SysexMessage;




public class Board implements Cloneable {
    int lastSet;
    int xPoints;
    int oPoints;
    char[][] gameBoard;
    final int height = 8;// dit kan veranderd worden bij boterkaas en eieren 
    Random rand = new Random();


    public Board execute() {
        createBoard();
        return null;
    }

    public char[][] getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(char[][] gameBoard) {
        this.gameBoard = new char[height][height];
        for(int i = 0; i < height; i++) {
            System.arraycopy(gameBoard[i], 0, this.gameBoard[i], 0, height);
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

    public void setLastSet(int lastSet) {
        this.lastSet = lastSet;
    }

    public int getLastSet() {
        return lastSet;
    }

    public int getXOPoints(){
        return oPoints-xPoints;
    }

    public int getxPoints() {
        return xPoints;
    }

    public void setxPoints(int xPoints) {
        this.xPoints = xPoints;

    }

    public void addxPoints(int xPoints) {
        this.xPoints = this.xPoints + xPoints;
    }

    public void removexPoints(int xPoints) {
        this.xPoints = this.xPoints - xPoints;
    }

    public int getoPoints() {
        return oPoints;
    }

    public void setoPoints(int oPoints) {
        this.oPoints = oPoints;
    }

    public void addoPoints(int oPoints) {
        this.oPoints = this.oPoints + oPoints;
    }

    public void removeoPoints(int oPoints) {
        this.oPoints = this.oPoints - oPoints;
    }

    public void createBoard() {
        gameBoard = new char[height][height];
        for (int x = 0; x < height; x++) 
        {
            for (int y = 0; y < height; y++) 
            {
                gameBoard[x][y] = '.';
            }
        }
    }



/*    public void markPlaceOnBoard(int space, String marker) {

    }
*/
    public void setStartPieceOnBoard(int space, char pieceToPlace) {
        gameBoard[getRow(space)][getCollum(space)] = pieceToPlace;
    }


    
  /*  public ArrayList<ArrayList<String>> getColumns() {
        return null;
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

    public void pirntBoard(){
        for(int i = 0; i< height; i++){
            System.out.println(gameBoard[i]);
        }
        System.out.println("&&&&&&&&");
    }

    public char getSpaceValue(int space) {
        if (space == height*height){
            return 'z';
        }
        return gameBoard[getRow(space)][getCollum(space)];
    }

    public int getRow(int space) {
        int row = space/height;
        return row;
    }

    public int getCollum(int space) {
        int cullum = space%height;
        return cullum;
    }

    
    public Boolean isValidSpace(int space) {
        if(space <= (height*height-1) && space >= 0){
            if(isSpaceAvailabe(space)){
                return true;
            }
        }
        return false;
    }

    public Boolean isSpaceAvailabe(int space) {
        if(gameBoard[getRow(space)][getCollum(space)] == '.'){
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

    


    public int getNeighbor(int direction, int space, int steps){
        int value = height*height;
        switch(direction) {
            case 0:
                value = getSpace(getRow(space)-1*steps,getCollum(space)-1*steps);
                break;
            case 1:
                value = getSpace(getRow(space)-1*steps,getCollum(space));
                break;               
            case 2:
                value = getSpace(getRow(space)-1*steps,getCollum(space)+1*steps);
                break;
            case 3:
                value = getSpace(getRow(space),getCollum(space)+1*steps);
                break;
            case 4:                
                value = getSpace(getRow(space)+1*steps,getCollum(space)+1*steps);
                break;                
            case 5:
                value = getSpace(getRow(space)+1*steps,getCollum(space));
                break;
            case 6:
                value = getSpace(getRow(space)+1*steps,getCollum(space)-1*steps);
                break;
            case 7:
                value = getSpace(getRow(space),getCollum(space)-1*steps);                    
                break;
                
                
        }
        return value;
    }

    
}

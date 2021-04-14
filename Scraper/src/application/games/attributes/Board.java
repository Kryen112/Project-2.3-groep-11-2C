package application.games.attributes;

import java.util.ArrayList;
import java.util.Random;

public class Board{
    int lastSet;
    int xPoints;
    int oPoints;
    char[][] gameBoard;
    int height = 0;// dit kan veranderd worden bij boterkaas en eieren 
    Random rand = new Random();
    int stoneAmountX = 0;
    int stoneAmountO = 0;

    public Board(int height){
        setHeight(height);
        createBoard();
    }


    public void clearBoard(){
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < height; j++) {
                gameBoard[i][j] = '.';
            }
        }
        stoneAmountX = 0;
        stoneAmountO = 0;
    }
/*
    public Board execute(int height) {
        setHeight(height);
        createBoard();
        return null;
    }
*/ 
    public char[][] getGameBoard() {
        return gameBoard;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getStoneAmount(char piece) {
        if(piece == 'o'){
            return stoneAmountO;
        }
        else{
            return stoneAmountX;
        }
    }

    public void setStoneAmount(int stoneAmount, char piece) {
        if(piece == 'o'){
            this.stoneAmountO = stoneAmount;
        }
        else{
            this.stoneAmountX = stoneAmount;
        }
    }

    public void addOneStoneAmount(char piece){
        if(piece == 'o'){
            stoneAmountO++;
        }
        else{
        stoneAmountX++;
        }
    }

    public int getHeight() {
        return height;
    }

    public void setGameBoard(char[][] gameBoard) {
        this.gameBoard = new char[height][height];
        for(int i = 0; i < height; i++) {
            System.arraycopy(gameBoard[i], 0, this.gameBoard[i], 0, height);
        }
    }

/*    public void setStartPieces(char pieceToPlace) {
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
*/
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
        gameBoard[getRow(space)][getColumn(space)] = pieceToPlace;
        addOneStoneAmount(pieceToPlace);
    }

    public void printBoard(){
        for(int i = 0; i< height; i++){
            System.out.println(gameBoard[i]);
        }
        System.out.println("&&&&&&&&");
    }

    public char getSpaceValue(int space) {
        if (space == height*height){
            return 'z';
        }
        return gameBoard[getRow(space)][getColumn(space)];
    }

    public int getRow(int space) {
        int row = space/height;
        return row;
    }

    public int getColumn(int space) {
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
        if(gameBoard[getRow(space)][getColumn(space)] == '.'){
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
                value = getSpace(getRow(space)-1*steps,getColumn(space)-1*steps);
                break;
            case 1:
                value = getSpace(getRow(space)-1*steps,getColumn(space));
                break;               
            case 2:
                value = getSpace(getRow(space)-1*steps,getColumn(space)+1*steps);
                break;
            case 3:
                value = getSpace(getRow(space),getColumn(space)+1*steps);
                break;
            case 4:                
                value = getSpace(getRow(space)+1*steps,getColumn(space)+1*steps);
                break;                
            case 5:
                value = getSpace(getRow(space)+1*steps,getColumn(space));
                break;
            case 6:
                value = getSpace(getRow(space)+1*steps,getColumn(space)-1*steps);
                break;
            case 7:
                value = getSpace(getRow(space),getColumn(space)-1*steps);                    
                break;
                
                
        }
        return value;
    }

    
}
package application.games.attributes;

import application.App;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
<<<<<<< Updated upstream

public class Board implements Boards {
    public int rows;
    public int cols;
    public int totalSpaces;
    public int offset;
    public List<String> spaces;
    public char[][] board;
    public int height = 8;
=======

import javax.sound.midi.SysexMessage;




public class Board implements Boards {
    int xPoints;
    int oPoints;
    char gameBoard[][];
    final int height = 8;// dit kan veranderd worden bij boterkaas en eieren 
    Random rand = new Random();
>>>>>>> Stashed changes

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
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

<<<<<<< Updated upstream
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


=======
    public int getxPoints() {
        return xPoints;
    }

    public void setxPoints(int xPoints) {
        this.xPoints = xPoints;
    }

    public int getoPoints() {
        return oPoints;
    }

    public void setoPoints(int oPoints) {
        this.oPoints = oPoints;
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

    public char[][] getBoard() {
        return gameBoard;
    }

    public void setBoard(char[][] gameBoard) {
        this.gameBoard = gameBoard;
    }

    @Override
    public String title() {
        return null;
    }

/*    public void markPlaceOnBoard(int space, String marker) {

    }
*/
    public void setStartPieceOnBoard(int space, char pieceToPlace) {
        gameBoard[getRow(space)][getCollum(space)] = pieceToPlace;
    }


    
  /*  public ArrayList<ArrayList<String>> getColumns() {
        return null;
>>>>>>> Stashed changes
    }
    /*  public ArrayList<ArrayList<String>> getColumns() {
          return null;
      }

<<<<<<< Updated upstream
      public ArrayList<ArrayList<String>> getRows() {
          return null;
      }
  */
=======
    public ArrayList<ArrayList<String>> getRows() {
        return null;
    }
*/
>>>>>>> Stashed changes
    public ArrayList<ArrayList<String>> getLeftDiagonal() {
        return null;
    }

    public ArrayList<ArrayList<String>> getRightDiagonal() {
        return null;
    }

<<<<<<< Updated upstream
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
        List<Integer> freeSpaces = bestSpot(getFreeSpaces());
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
=======
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
>>>>>>> Stashed changes
    }

    public int getCollum(int space) {
        int cullum = space%height;
        return cullum;
    }

    
    public Boolean isValidSpace(int space) {
        if(space <= (height*height-1) && space >= 0){
<<<<<<< Updated upstream
            return isSpaceAvailabe(space);
=======
            if(isSpaceAvailabe(space)){
                return true;
            }
>>>>>>> Stashed changes
        }
        return false;
    }

    public Boolean isSpaceAvailabe(int space) {
<<<<<<< Updated upstream
        if(board[getRow(space)][getCollum(space)] == '.'){
=======
        if(gameBoard[getRow(space)][getCollum(space)] == '.'){
>>>>>>> Stashed changes
            return true;
        }
        else{
            return false;
        }

<<<<<<< Updated upstream
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

=======
    }

    public int getSpace(int row, int collum){
        int space = height*height;
        if(row >= 0 && collum >= 0 && row < height && collum < height){
            space = row*height + collum;
            //System.out.println("d");
        }
        return space;
    }

    


>>>>>>> Stashed changes
    public int getNeighbor(int direction, int space, int steps){
        int value = height*height;
        switch(direction) {
            case 0:
<<<<<<< Updated upstream
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
=======
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
                
                
>>>>>>> Stashed changes
        }
        return value;
    }

    
}

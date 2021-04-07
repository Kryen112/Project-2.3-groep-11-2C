package application.games.attributes;

import application.games.Game;

import java.util.ArrayList;
import java.util.List;

public class Board implements Boards {
    int rows;
    int cols;
    int totalSpaces;
    int offset;
    List<String> spaces;

    @Override
    public Board execute() {
        return null;
    }

    @Override
    public String title() {
        return null;
    }

    public void markPlaceOnBoard(int space, String marker) {

    }

    public void setPieceOnBoard(int space, Piece pieceToPlace) {

    }

    public ArrayList<ArrayList<String>> getColumns() {
        return null;
    }

    public ArrayList<ArrayList<String>> getRows() {
        return null;
    }

    public ArrayList<ArrayList<String>> getLeftDiagonal() {
        return null;
    }

    public ArrayList<ArrayList<String>> getRightDiagonal() {
        return null;
    }

    public List<String> getSpaces() {
        return null;
    }

    public List<Integer> getFreeSpaces() {
        return null;
    }

    public Boolean isValidSpace(int space) {
        return null;
    }

    public Boolean isSpaceAvailabe(int space) {
        return null;
    }

    public Boolean isMoveAllowed(int space) {
        return null;
    }
}

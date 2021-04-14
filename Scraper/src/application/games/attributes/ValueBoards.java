package application.games.attributes;

public class ValueBoards {

    final int height = 8;
    Board[] valueBoards;
    static final private int[][] scoreBoard = {
        { 70, -20,  70,  70,  70,  70, -20, 70},
        {-20, -30,   -10,   -10,   -10,   -10, -30, -20},
        { 70,   -10,   15,   15,   15,   15,   -10,  70},
        { 70,   -10,   15,   10,   10,   15,   -10,  70},
        { 70,   -10,   15,   10,   10,   15,   -10,  70},
        { 70,   -10,   15,   15,   15,   15,   -10,  70},
        {-20, -30,   -10,   -10,   -10,   -10, -30, -20},
        { 70, -20,  70,  70,  70,  70, -20, 70}};




    public int getSpaceValue(int space) {
        if (space == height*height){
            return 'z';
        }
        return scoreBoard[getRow(space)][getCollum(space)];
    }

    public int getRow(int space) {
        int row = space/height;
        return row;
    }

    public int getCollum(int space) {
        int cullum = space%height;
        return cullum;
    }

    public void setOnBoard(int space, int pieceToPlace) {
        scoreBoard[getRow(space)][getCollum(space)] = pieceToPlace;
    }

}

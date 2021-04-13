package application.games.attributes;

public class ValueBoards {

    final int height = 8;
    Board[] valueBoards;
    static final private int[][] scoreBoard = {
        { -1000, -100,  150,  100,  100,  150, -100, 1000},
        {-100, -200,   20,   20,   20,   20, -200, -100},
        { 150,   20,   15,   15,   15,   15,   20,  150},
        { 100,   20,   15,   10,   10,   15,   20,  100},
        { 100,   20,   15,   10,   10,   15,   20,  100},
        { 150,   20,   15,   15,   15,   15,   20,  150},
        {-100, -200,   20,   20,   20,   20, -200, -100},
        {1000, -100,  150,  100,  100,  150, -100, 1000}};


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

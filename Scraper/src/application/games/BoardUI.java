package application.games;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.util.HashMap;


public class BoardUI {
    Pane[][] gameBoardUI;
    int height;
    HashMap<String, Integer> states;

    private int[][] winningPositionsBKE = {
            { 0, 0, 0},
            { 0, 0, 0},
            { 0, 0, 0}
    };

    public BoardUI(int h, HashMap<String, Integer> states){
        setHeight(h);
        gameBoardUI = createBoardUI();
        this.states = states;
    }

    public void resetWinningPositions() {
        this.winningPositionsBKE = new int[][] {
                { 0, 0, 0},
                { 0, 0, 0},
                { 0, 0, 0}
        };
    }

    public void setHeight(int height){
        this.height = height;
    }
    public int getHeight(){
        return height;
    }

    public Pane[][] createBoardUI() {
        gameBoardUI = new Pane[height][height];

        int id = 0;
        for (int x = 0; x < height; x++)
        {
            for (int y = 0; y < height; y++)
            {
                gameBoardUI[x][y] = new Pane();
                Pane thisPane = gameBoardUI[x][y];
                thisPane.setId("" + id);
                id++;
            }
        }
        return gameBoardUI;
    }

    public Image getEmptyTile(String gameType, String id) {
        if (gameType.equals(Game.BKE)) {
            return new Image("application/images/emptyTileTTT.png");
        } else if (gameType.equals(Game.REV)) {
            if (id.equals("35") || id.equals("28")) {
                // player 1
                return new Image("application/images/zwart.png");
            } else if (id.equals("27") || id.equals("36")) {
                // player 2
                return new Image("application/images/wit.png");
            } else {
                return new Image("application/images/emptyTileREV.png");
            }
        }
        return null;
    }

    public Pane getGameBoardPane(int x, int y) {
        return gameBoardUI[x][y];
    }

    public Pane[][] getGameBoardUI() {
        return gameBoardUI;
    }

    public int getStateOfPane(Pane p, String id) {
        return states.get(id);
    }

    public Boolean isWonBKE() {
        int xHorizontal = 0;
        int oHorizontal = 0;

        // horizontal row check
        for(int i = 0; i<3; i++) {
            for(int j = 0; j<3; j++) {
                if(getStateOfPane(getGameBoardPane(i, j), getGameBoardPane(i, j).getId()) == 1) {
                    winningPositionsBKE[i][j] = 1;
                    xHorizontal++;
                }
                if(getStateOfPane(getGameBoardPane(i, j), getGameBoardPane(i, j).getId()) == 2) {
                    winningPositionsBKE[i][j] = 2;
                    oHorizontal++;
                }
            }

            if(xHorizontal == 3 || oHorizontal == 3) {
                return true;
            }

            xHorizontal = 0;
            oHorizontal = 0;
            resetWinningPositions();
        }

        int xVertical = 0;
        int oVertical = 0;

        for(int i = 0; i<3; i++) {
            for(int j = 0; j<3; j++) {
                if(getStateOfPane(getGameBoardPane(i, j), getGameBoardPane(i, j).getId()) == 1) {
                    winningPositionsBKE[i][j] = 1;
                    xVertical++;
                }
                if(getStateOfPane(getGameBoardPane(i, j), getGameBoardPane(i, j).getId()) == 2) {
                    winningPositionsBKE[i][j] = 2;
                    oVertical++;
                }
            }

            if(xVertical == 3 || oVertical == 3) {
                return true;
            }
            xVertical = 0;
            oVertical = 0;
            resetWinningPositions();
        }

        int count = 0;
        int xDiagonal = 0;
        int oDiagonal = 0;

        // diagonal top left to bottom right
        for(int i = 0; i < 3; i++) {
            if(getStateOfPane(getGameBoardPane(i, count), getGameBoardPane(i, count).getId()) == 1) {
                winningPositionsBKE[i][count] = 1;
                xDiagonal++;
            }
            if(getStateOfPane(getGameBoardPane(i, count), getGameBoardPane(i, count).getId()) == 2) {
                winningPositionsBKE[i][count] = 2;
                oDiagonal++;
            }
            if(xDiagonal == 3 || oDiagonal == 3) {
                return true;
            }
            count++;
        }

        resetWinningPositions();
        xDiagonal = 0;
        oDiagonal = 0;
        count = 0;

        // diagonal bottom left to top right
        for(int i = 2; i >= 0; i--) {
            if(getStateOfPane(getGameBoardPane(i, count), getGameBoardPane(i, count).getId()) == 1) {
                winningPositionsBKE[i][count] = 1;
                xDiagonal++;
            }
            if(getStateOfPane(getGameBoardPane(i, count), getGameBoardPane(i, count).getId()) == 2) {
                winningPositionsBKE[i][count] = 2;
                oDiagonal++;
            }
            if(xDiagonal == 3 || oDiagonal == 3) {
                return true;
            }
            count++;
        }
        resetWinningPositions(  );
        return false;
    }
}

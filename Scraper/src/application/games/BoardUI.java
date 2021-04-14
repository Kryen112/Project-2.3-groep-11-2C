package application.games;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.util.HashMap;


public class BoardUI {
    Pane[][] gameBoardUI;
    int height;
    HashMap<String, Integer> states;

    public BoardUI(int h, HashMap<String, Integer> states){
        setHeight(h);
        gameBoardUI = createBoardUI();
        this.states = states;
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

    
}

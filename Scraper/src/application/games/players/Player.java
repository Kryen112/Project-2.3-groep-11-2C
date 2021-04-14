package application.games.players;

import javafx.scene.image.Image;
import static application.games.Game.*;

/**
 * Template voor alle Player Klasses
 */
public abstract class Player implements PlayerInterface {
    String name;
    int score;
    int wins;
    int losses;
    int typeOfPlayer;
    Boolean isX = false;
    Boolean isO = false;

    public int getTypeOfPlayer() {
        if(typeOfPlayer == 0 || typeOfPlayer == 1) {
            return this.typeOfPlayer;
        } else {
            System.out.println("Foute typeOfPlayer! De type moet 0 (computer) of 1(mens) zijn, en de waarde is: " + typeOfPlayer);
            return -1;
        }
    }

    public Image getIcon(String gameType, String player) {
        if (gameType.equals(BKE)) {
            if (player.equals(X)) {
                isX = true;
                return new Image("application/images/x.png");
            } else if (player.equals(O)) {
                isO = true;
                return new Image("application/images/o.png");
            }
        } else if (gameType.equals(REV)) {
            if (player.equals(X)) {
                isX = true;
                return new Image("application/images/zwart.png");
            } else if (player.equals(O)) {
                isO = true;
                return new Image("application/images/wit.png");
            }
        }
        return null;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return this.name;
    }

    public int getScore() {
        return this.score;
    }

    public int getWins() {
        return this.wins;
    }

    public int getLosses() {
        return this.losses;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void increaseLosses() {
        losses++;
    }
    public void increaseWins() {
        wins++;
    }

}

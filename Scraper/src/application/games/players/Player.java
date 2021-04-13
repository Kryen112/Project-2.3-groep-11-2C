package application.games.players;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Player implements PlayerInterface {
    String name;
    int score;
    int wins;
    int losses;
    int typeOfPlayer;
    Image icon;


    public int getTypeOfPlayer() {
        if(typeOfPlayer == 0 || typeOfPlayer == 1) {
            return this.typeOfPlayer;
        } else {
            System.out.println("Foute typeOfPlayer! De type moet 0 (computer) of 1(mens) zijn, en de waarde is: " + typeOfPlayer);
            return -1;
        }
    }

    public void setIcon(String iconUrl) {
        icon = new Image(iconUrl);
    }

    public Image getIcon(){
        return icon;
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
}

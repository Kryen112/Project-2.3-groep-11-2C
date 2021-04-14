package application.games.players;

import javafx.scene.image.ImageView;

public class HumanPlayer extends Player {

    public HumanPlayer(String userName) {
        name = userName;
        score = 0;
        wins = 0;
        losses = 0;
        setTypeOfPlayer();
    }

    @Override
    public void doMove() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setTypeOfPlayer() {
        typeOfPlayer = 1;
    }
}

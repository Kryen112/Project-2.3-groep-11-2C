package application.games.players;

public class ComputerPlayer extends Player {

    public ComputerPlayer() {
        name = "Computer";
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
        typeOfPlayer = 0;
    }
}

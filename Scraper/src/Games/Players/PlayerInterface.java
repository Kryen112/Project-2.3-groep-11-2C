package Games.Players;

public interface PlayerInterface {
    int getTypeOfPlayer();
    String getName();
    int getScore();
    int getWins();
    int getLosses();

    void setTypeOfPlayer();
    void doMove();

}

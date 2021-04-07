package games.players;

public interface PlayerInterface {
    int getTypeOfPlayer();
    String getName();
    int getScore();
    int getWins();
    int getLosses();

    void setTypeOfPlayer();
    void doMove();

}

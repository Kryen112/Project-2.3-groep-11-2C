package application.games.players;

public abstract class Player implements PlayerInterface {
    String name;
    int score;
    int wins;
    int losses;
    int typeOfPlayer;

    public int getTypeOfPlayer() {
        if(typeOfPlayer == 0 || typeOfPlayer == 1) {
            return this.typeOfPlayer;
        } else {
            System.out.println("Foute typeOfPlayer! De type moet 0 (computer) of 1(mens) zijn, en de waarde is: " + typeOfPlayer);
            return -1;
        }
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

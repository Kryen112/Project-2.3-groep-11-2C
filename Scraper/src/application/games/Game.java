package application.games;

import application.games.players.Player;


import java.util.ArrayList;

public class Game extends Board {
    String gameTitle;
    Board board;

    Player player1;
    Player player2;

    Player currentPlayer;
    Player winner;

    GameTypes typeOfGame;

    public void takeTurn() {

    }

    public void changeCurrentPlayer() {

    }

    public Boolean isGameOver() {
        return null;
    }

    public Boolean isWinner(Player p) {
        return null;
    }

    public Boolean isLoser(Player p) {
        return null;
    }

    public Boolean isGameTie() {
        return null;
    }

    public Player getWinner() {
        return null;
    }

    public ArrayList<ArrayList<String>> getWinningCombinations() {
        return null;
    }
}

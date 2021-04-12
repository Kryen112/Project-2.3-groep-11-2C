package application.games;

import application.games.attributes.Board;
import application.games.players.Player;
import java.util.ArrayList;
import java.util.Random;

public class Game extends Board {
    Random r = new Random();
    String gameTitle;
    Board board;

    Player player1;
    Player player2;

    Player currentPlayer;
    Player winner;

    private char turn = 'x';


    public Game(){ }

    public Game(String title, Board b, Player p1, Player p2){
        gameTitle = title;
        board = b;
        player1 = p1;
        player2 = p2;

        int i = r.nextInt(2);
        if (i == 0) {
            setTurn('x');
            currentPlayer = p1;
        } else {
            setTurn('o');
            currentPlayer = p2;
        }
        winner = null;
    }

    public String getGameTitle() { return gameTitle; }
    public Board getGameBoard() { return board; }
    public Player getPlayer1() { return player1; }
    public Player getPlayer2() { return player2; }
    public Player getCurrentPlayer() { return currentPlayer; }

    public void takeTurn() {

    }

    public char getTurn() {
        return turn;
    }

    public void setTurn(char turn) {
        this.turn = turn;
    }


    public void changeTurn() {
        if(getTurn() == 'x') {
            setTurn('o');
        }
        else if(getTurn() == 'o') {
            setTurn('x');
        }
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

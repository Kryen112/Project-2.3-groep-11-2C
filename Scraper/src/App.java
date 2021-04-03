import java.util.List;

import Games.*;
import Games.GameAttributes.*;
import Games.GameMenu.*;
import Games.Players.*;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application implements Runnable {
    private String name;
    private boolean running;

    private List<GameTypes> gameTypes;
    private Game selectedGame;

    private Input input;
    private Output output;

    private List<Player> gamePlayers;
    private currentPlayer player;
    private Player winner;

    public static void main(String[] args) {
        launch(args);
    }

    public void startGame() {

    }

    public Game startNewGame(int gameChoice, Board b) {
        return null;
    }

    public Boolean gameInProgress() {
        return true;
    }

    public List<Board> getBoardTypes() {
        return null;
    }

    public int getSelection(List<Options> options) {
        return 0;
    }

    public void makeMove() {

    }

    public void endOfGame() {

    }

    public void playAgain() {

    }

    public List<Options> playCommand() {
        return null;
    }

    public void playAgainCommand(int select) {

    }

    public void quit() {
        
    }

    @Override
    public void start(Stage arg0) throws Exception {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        
    }
}

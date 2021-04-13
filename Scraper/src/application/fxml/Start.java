package application.fxml;

import application.App;
import application.games.Board;
import application.games.BoardUI;
import application.games.players.ComputerPlayer;
import application.games.players.HumanPlayer;
import application.games.Game;

import application.games.players.Player;
import com.sun.prism.Image;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import javax.swing.text.html.ImageView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Class Starts handles the Logic behind the FXML Start page
 *
 * @author Anouk,
 */
public class Start {
    public static final boolean DEBUG = true; // change to false to hide debug messages
    public static HumanPlayer user;           // the user who uses the application
    public static ComputerPlayer ai = new ComputerPlayer(); // the ai
    public static String gameType;            // the gameType the user chose
    public static final String BKE = "BOTERKAASENEIEREN";
    public static final String REV = "REVERSI";

    // PANE VIEW
    @FXML protected BorderPane mainPane;    // the mainPane of application
    @FXML protected Text title;             // Title
    @FXML protected Text info;              // Subtitle/info
    @FXML protected VBox centerScreen;      // Center of borderpane

    // HOME
    @FXML protected Group homeScreen;
    @FXML protected Button backButtonLocal;

    // LOGIN
    @FXML protected Group loginCenterBox;   // Group that holds all of the login items
    @FXML protected HBox loginBox;
    @FXML protected HBox loginMessageBox;
    @FXML protected Text loginMessage;
    @FXML protected TextField userName;
    @FXML protected Button verder;

    // gameCenter
    @FXML protected Group games;
    @FXML protected VBox centerGameLocal;
    @FXML protected VBox centerGameOnline;
    @FXML protected VBox playerList;
    @FXML protected VBox challengesListView;
    @FXML protected Text playerListList;
    @FXML protected TextField enemyUserName;
    @FXML protected Text challengeMessage;
    @FXML protected ListView<String> listView;

    // GAMECENTER
    @FXML protected Group gameCenterBox; // Group that holds all of the game items
    @FXML protected HBox gameSelection;  // Box to show games that can be played
    @FXML protected VBox gameSettingsBox;
    @FXML protected VBox centerGame;

    // ACTIVE GAME
    @FXML protected Group gameBoard;
    @FXML protected GridPane gameTiles;
    public Rectangle boardTile;

    static final int EMPTY = 0;
    static final int CAPTUREDBYP1 = 1;
    static final int CAPTUREDBYP2 = 2;

    HashMap<String, Integer> stateOfTile;

    // LOGIN SCREEN METHODS
    /**
     * Method to handle the login button being pushed
     */
    @FXML
    protected void handleLoginAction(ActionEvent event) {
        //Make connection with server
        App.makeConnectionWithServer();
        
        games.getChildren().remove(centerGameLocal);
        games.getChildren().remove(centerGameOnline);

        // player name may not be "Gebruiker"
        if (userName.getText().equals("Gebruiker")) {
            showMessage(loginMessage, 1, "U mag deze gebruikersnaam niet gebruiken");
        }
        // username cannot be empty 
        else if (!userName.getText().isEmpty() || !userName.getText().isBlank()) {
            String player = userName.getText().toLowerCase();

            App.server.login(player, result -> {
                switch (result) {
                    case "OK":  // Login succes
                        user = new HumanPlayer(player);
                        showMessage(loginMessage, 0, ("Inloggen gelukt, Welkom " + player + "!") );
                        App.server.setLoggedIn(true);
                        loginBox.setVisible(false);     // hide login
                        verder.setVisible(true);        // enable continue button
                        break;
                    // Login not success
                    case "ERR already logged in":
                        showMessage(loginMessage, 1, "U bent al ingelogd");
                        break;

                    case "ERR duplicate name exists":
                        showMessage(loginMessage, 1, "Deze gebruikersnaam bestaat al");
                        break;

                    default:
                        showMessage(loginMessage, 1, "Er is iets fout gegaan, probeer het opnieuw.");
                        break;
                }}); // end switch / login
        } else {
            showMessage(loginMessage, 1, "Gebruikersnaam kan niet leeg zijn");
        }
    }

    /**
     * Player clicked local play
     * Set username to something useful
     * Proceed to game selection screen
     */
    @FXML
    public void handleLocalPlay() {
        //Local user is a user with name "Gebruiker"
        user = new HumanPlayer("Gebruiker");

        //Set title and infotext
        title.setText(("AI Gaming"));
        info.setText("Kies een spel, speel tegen de computer of een vriend");

        //Handle screen transitions
        games.getChildren().remove(centerGameLocal);
        games.getChildren().remove(centerGameOnline);
        loginCenterBox.getChildren().remove(loginBox);
        loginCenterBox.getChildren().remove(loginMessageBox);
        homeScreen.setVisible(false);
        backButtonLocal.setVisible(true);
        gameCenterBox.setVisible(true); 
    }

    /**
     * Player clicked online play
     * Proceed to connect to server and login actions
     */
    @FXML
    public void handleOnlinePlay() {
        //Set title and infotext
        title.setText(("AI Gaming Login"));
        info.setText("Voer een gebruikersnaam in en login!");

        //Handle screen transitions
        loginCenterBox.setVisible(true);
        homeScreen.setVisible(false);
    }

    /**
     * Method for the back button in the login screen
     * Goes back to the homeScreen
     */
    @FXML
    public void backToHomeScreenFromLogin() {
        loginCenterBox.setVisible(false);

        title.setText(("AI Gaming Home"));
        info.setText("Wil je online of lokaal spelen?");
        homeScreen.setVisible(true);
    }

    /**
     * Method for the back button in the local play screen
     * Goes back to the homeScreen
     */
    @FXML
    public void backToHomeScreenFromLocal() {
        //Set title and infotext
        title.setText(("AI Gaming Home"));
        info.setText("Wil je online of lokaal spelen?");

        //Handle screen transitions
        gameCenterBox.setVisible(false);
        backButtonLocal.setVisible(false);
        loginCenterBox.getChildren().add(loginBox);
        loginCenterBox.getChildren().add(loginMessageBox);
        homeScreen.setVisible(true);
    }

    /**
     * Method to hide loginScreen and show the GameScreen
     * when the continue button is pushed
     */
    @FXML
    protected void handleContinue() {
        loginCenterBox.getChildren().remove(loginBox);
        loginCenterBox.getChildren().remove(loginMessageBox);

        title.setText(( "AI Gaming - " + user.getName()));
        info.setText("Kies een Spel, speel tegen de Computer, een Vriend of speel Online");

        gameCenterBox.getChildren().remove(gameSettingsBox);
        gameCenterBox.setVisible(true);
    }

    @FXML
    public void setUpBoterKaasEieren(MouseEvent actionEvent) {
        gameType = BKE;

        // Local play
        if (user.getName().equals("Gebruiker")) {
            centerScreen.getChildren().remove(gameCenterBox);
            games.getChildren().add(centerGameLocal);
            title.setText("Boter, Kaas en Eieren");
            games.setVisible(true);
        } 
        // Online play
        else {
            centerScreen.getChildren().remove(gameCenterBox);
            games.getChildren().add(centerGameOnline);
            title.setText("Boter, Kaas en Eieren");
            playerList.setVisible(true);
            games.setVisible(true);
        }
    }
    /**
     * Method that handles javaFx gameScreen change from gameCenter to settings
     */
    public void openSettings(){
        gameCenterBox.getChildren().remove(gameSelection);
        gameCenterBox.getChildren().add(gameSettingsBox);
        gameSettingsBox.setVisible(true);
    }

    /**
     * Method that handles javaFx gameScreen change from settings to gameCenter
     */
    @FXML
    public void setUpReversi(MouseEvent actionEvent) {
        gameType = REV;

        // Local play
        if (user.getName().equals("Gebruiker")) {
            centerScreen.getChildren().remove(gameCenterBox);
            games.getChildren().add(centerGameLocal);
            title.setText("Reversi");
            games.setVisible(true);
        }
        // Online play
        else {
            centerScreen.getChildren().remove(gameCenterBox);
            games.getChildren().add(centerGameOnline);
            title.setText("Reversi");
            playerList.setVisible(true);
            challengesListView.setVisible(true);
            games.setVisible(true);
        }
    }

    @FXML
    public void playNewGame(ActionEvent actionEvent) {
        App.server.forfeit();
        if(gameType.equals(BKE)) {
            App.server.subscribe("Tic-tac-toe", result ->  System.out.println("Subscribed to Tic-tac-toe") );
        }
        if(gameType.equals(REV)) {
            App.server.subscribe("Reversi", result ->  System.out.println(result) );
        }
    }

    public void setUpActiveGameScreen(int boardSize, String gameName, Player player1, Player player2, int tile) {
        mainPane.getChildren().remove(centerScreen);
        mainPane.getChildren().add(gameBoard);
        gameBoard.setVisible(true);

        BoardUI board =  new BoardUI(boardSize);
        Game thisGame = new Game(gameName, board, player1, player2);

        Player p1 = thisGame.getPlayer1();
        Player p2 = thisGame.getPlayer2();

        title.setText(thisGame.getGameTitle() + " - " + p1.getName() + " VS " + p2.getName());
        String currentPlayer = thisGame.getCurrentPlayer().getName();

        // turnbox
        info.setText(currentPlayer + " " + thisGame.getTurn() + " is aan zet");

        if (DEBUG) { gameTiles.setGridLinesVisible(true); }

        Pane[][] gameBoardUI = board.getGameBoardUI();
        int x = 0;
        int y = 0;

        for (Pane[] pane : gameBoardUI) {
            for (Pane p : pane) {
                boardTile = new Rectangle(60, 60);
                boardTile.setFill(Color.WHITESMOKE);

                stateOfTile.put(p.getId(), EMPTY);
                p.getChildren().add(boardTile);

                gameTiles.add(p, x, y);
                x++;

                if (x % board.getHeight() == 0) {
                    y++;
                    x = 0;
                }

                p.setOnMouseClicked(e -> {
                    UserClickedTile(e, p, thisGame);
                });
            }
        }

    }

    @FXML
    public void playLocalvsAI(ActionEvent actionEvent) {
        stateOfTile = new HashMap<>();

        if(gameType.equals(BKE)) {
            setUpActiveGameScreen(3, "Boter, Kaas en Eieren", user, ai, 100);
            //TODO spelen tegen AI
        }
        if(gameType.equals(REV)) {
            setUpActiveGameScreen(8, "Reversi", user, ai, 80);
            //TODO spelen tegen AI
        }
    }

    public void UserClickedTile(MouseEvent e, Pane p, Game thisGame ){
        Rectangle r = (Rectangle) e.getTarget();
        int status = stateOfTile.get(p.getId());
        Player current = thisGame.getCurrentPlayer();
        boolean isPlayerOne = false;

        if (current.equals(thisGame.getPlayer1())) {
            isPlayerOne = true;
        }

        switch (status) {
            case EMPTY:
                if (isPlayerOne) {
                    r.setFill(Color.BLUE);
                    status = CAPTUREDBYP1;
                } else { // player 2
                    r.setFill(Color.GREEN);
                    status = CAPTUREDBYP2;
                }
                stateOfTile.put(p.getId(), status);
                break;
            case CAPTUREDBYP1:
            case CAPTUREDBYP2:
                System.out.println("Tile already captured");
        }

        thisGame.changeTurn();
    }

    @FXML
    public void playLocalvs(ActionEvent actionEvent) {
        if(title.getText().equals("Boter, Kaas en Eieren")) {
            //TODO BKE GUI en spelen tegen elkaar
        }
        if(title.getText().equals("Reversi")) {
            //TODO Reversi GUI en spelen tegen elkaar
        }
    }

    @FXML
    public void uitleg(ActionEvent actionEvent) {
        System.out.println("uitleg");
    }

    @FXML
    public void goBackLocal(ActionEvent actionEvent) {
        games.getChildren().remove(centerGameLocal);
        centerScreen.getChildren().add(gameCenterBox);
        title.setText(( "AI Gaming - " + user.getName()));
        info.setText("Kies een spel, speel tegen de computer of een vriend");
    }

    @FXML
    public void goBackOnline(ActionEvent actionEvent) {
        games.getChildren().remove(centerGameOnline);
        centerScreen.getChildren().add(gameCenterBox);
        playerList.setVisible(false);
        title.setText(( "AI Gaming - " + user.getName()));
        info.setText("Kies een Spel, speel tegen de Computer, een Vriend of speel Online");
    }

    @FXML
    public void acceptChallenge(ActionEvent actionEvent) {
        if(title.getText().equals("Reversi")) {
            App.server.acceptChallenge();
        }
    }

    @FXML
    public void challengePlayer(ActionEvent actionEvent) {
        if (!enemyUserName.getText().isEmpty() || !enemyUserName.getText().isBlank()) {
            String enemyPlayer = enemyUserName.getText();
            String gameName = title.getText();
            App.server.challengePlayer(enemyPlayer, gameName, result -> { 
                if (result.contains("SVR GAME CHALLENGE CANCELLED")) {
                    showMessage(challengeMessage, 1, "De huidige uitdaging is geannuleerd");
                } else if (result.contains("SVR GAME CHALLENGE")) { //TODO bericht als je uitgedaagd bent (LISTVIEW)
                    showMessage(challengeMessage, 0, "U bent uitgedaagd!");
                } else {
                switch (result) {
                    case "OK":
                        showMessage(challengeMessage, 0, ("Je hebt deze speler uitgedaagd: " + enemyPlayer));
                        break;
                    case "ERR player not found":
                        showMessage(challengeMessage, 1, ("Speler " + enemyPlayer + " niet gevonden."));
                        break;
                    default:
                        showMessage(challengeMessage, 1, "Er is iets fout gegaan, probeer het opnieuw.");
                        break;
                    }
                }
            }); 
        } else {
            showMessage(challengeMessage, 1, "Spelernaam kan niet leeg zijn.");
        }
    }
    
    @FXML
    public void getPlayerList(ActionEvent actionEvent) {
        App.server.getPlayerList( result -> {
            String[] arr = result.replace("[", "").replace("]", "").replace("\"", "").replace("SVR PLAYERLIST ", "").split(", ");
            if (result.contains("PLAYERLIST")) {
                showMessage(playerListList, 0, Arrays.toString(arr));
            }
        });
    } 

    /**
     * Method to check if javaFx textField is not Empty or Blank
     * @param textField the javaFx textField to check
     * @return true if textField is not empty or blank
     */
    public boolean textFieldNotEmptyOrBlank(TextField textField) {
        return !textField.getText().isEmpty() || !textField.getText().isBlank();
    }

    /**
     * Method to show succes / error messages to javaFx Text and terminal for Debug
     * @param textBox Text javafx field to place the message
     * @param type 0 = succes / 1 = error
     * @param msg message to show
     */
    @FXML
    public void showMessage(Text textBox, int type, String msg) {
        if (type == 1) {
            textBox.setStyle("-fx-fill: RED;");
            if (DEBUG){ System.out.println("DEBUG ERROR " + msg); }
        } else {
            textBox.setStyle("-fx-fill: GREEN;");
            if (DEBUG){ System.out.println("DEBUG SUCCESS " + msg); }
        }
        textBox.setText(msg);
    }
}
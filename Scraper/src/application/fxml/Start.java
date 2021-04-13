package application.fxml;

import application.App;
import application.games.Board;
import application.games.Game;
import application.games.players.ComputerPlayer;
import application.games.players.HumanPlayer;
import application.games.players.Player;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * Class Starts handles the Logic behind the FXML Start page
 *
 * @author Anouk, Stefan
 */
public class Start {
    public static final boolean DEBUG = true; // change to false to hide debug messages
    public static HumanPlayer user;           // the user who uses the application
    public static ComputerPlayer ai = new ComputerPlayer(); // the ai
    public static String gameType;            // the gameType the user chose
    public static final String BKE = "BOTERKAASENEIEREN";
    public static final String REV = "REVERSI";
    public String toAdd = "";

    //List views

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
    @FXML protected TextField ipAddress;
    @FXML protected TextField portNr;
    @FXML protected Button verder;

    // gameCenter
    @FXML protected Group games;
    @FXML protected VBox centerGameLocal;
    @FXML protected VBox centerGameOnline;
    @FXML protected VBox playerList;
    @FXML protected Label playerListList;
    @FXML protected TextField enemyUserName;
    @FXML protected Text challengeMessage;
    @FXML protected Button logOutButton;

    // GAMECENTER
    @FXML protected Group gameCenterBox; // Group that holds all of the game items
    @FXML protected HBox gameSelection;  // Box to show games that can be played
    @FXML protected VBox gameSettingsBox;
    @FXML protected VBox centerGame;

    // ACTIVE GAME
    @FXML protected Group activeGame;

    // LOGIN SCREEN METHODS
    /**
     * Method to handle the login button being pushed
     * Only used for logging in on the school server
     */
    @FXML
    protected void handleLoginActionOnSchoolServer(ActionEvent event) {
        //Make connection with server
        try {
            App.makeConnectionWithServer();
        } catch (Exception e) {
            System.out.println("Fout met serverconnectie.");
        }
        
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
                        loginBox.setVisible(false);       // hide login
                        loginMessageBox.setVisible(true); //enable loginmessagebox
                        verder.setVisible(true);          // enable continue button
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
     * Method to handle the login button being pushed
     * Used for logging in with ip+port
     */
    @FXML
    protected void handleLoginActionOnOtherServer(ActionEvent event) {
        try {
            App.makeConnectionWithServer(ipAddress.getText(), portNr.getText());
        } catch (Exception e) {
            System.out.println("Fout met serverconnectie.");
        }
        
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
                        loginBox.setVisible(false);       // hide login
                        loginMessageBox.setVisible(true); //enable loginmessagebox
                        verder.setVisible(true);          // enable continue button
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
        loginBox.setVisible(true);
        loginCenterBox.setVisible(true);
        loginMessageBox.setVisible(false);
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
     * The logout button
     */
    @FXML
    public void logOut() {
        //Set variables
        App.server.logout();
        App.server.setLoggedIn(false);

        //Set title, infotext and username
        title.setText(("AI Gaming Home"));
        info.setText("Wil je online of lokaal spelen?");
        userName.setText("");

        //Handle screen transitions
        loginCenterBox.getChildren().add(loginBox);
        loginCenterBox.getChildren().add(loginMessageBox);
        homeScreen.setVisible(true);
        loginCenterBox.setVisible(false);
        gameCenterBox.setVisible(false);
        logOutButton.setVisible(false);

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
        logOutButton.setVisible(true);
    }

    @FXML
    public void setUpBoterKaasEieren(MouseEvent actionEvent) {
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
     * Method that handles javaFx gameScreen change from settings to gameCenter
     */
    @FXML
    public void setUpReversi(MouseEvent actionEvent) {
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

    @FXML
    public void playNewGame(ActionEvent actionEvent) {
        App.server.forfeit(); //TODO Niet nodig als je niet in een game bent
        if(title.getText().equals("Boter, Kaas en Eieren")) {
            App.server.subscribe("Tic-tac-toe", result ->  System.out.println("Subscribed to Tic-tac-toe") );
        }
        if(title.getText().equals("Reversi")) {
            App.server.subscribe("Reversi", result ->  System.out.println(result) );
        }
    }

    @FXML
    public void playLocalvsAI(ActionEvent actionEvent) {
        if(title.getText().equals("Boter, Kaas en Eieren")) {
            mainPane.getChildren().remove(centerScreen);

            Board bke =  new Board();

            Game thisGame = new Game("Boter, Kaas en Eieren", bke, user, ai);
            Player p1 = thisGame.getPlayer1();
            Player p2 = thisGame.getPlayer2();

            title.setText(thisGame.getGameTitle() + " - " + p1.getName() + " VS " + p2.getName());

            // turnbox
            info.setText(thisGame.getCurrentPlayer().getName() + " is aan zet");

            // GAMEBOARD
            GridPane bkeBoard = new GridPane();

            for ( int x = 0; x < bke.height; x++ ) {
                for (int y = 0; y < bke.height; y++ ) {
                    bkeBoard.add(
                            new Pane( new Text("hello") )
                            , (y + 2) , (x + 2));
                }
            }
            VBox holder = new VBox(bkeBoard);
            mainPane.setCenter(holder);
            System.out.println(mainPane.getChildren().toString());
//            mainPane.getChildren().get().add(bkeBoard);


            //TODO BKE GUI en spelen tegen AI
        }
        if(title.getText().equals("Reversi")) {
            //TODO Reversi GUI en spelen tegen AI
        }
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
    public void acceptChallenge(ActionEvent actionEvent) { //TODO REVERSIGAME START OR TICTACTOEGAME START
        if(title.getText().equals("Reversi")) {
            App.server.acceptChallenge(event ->
                showMessage(challengeMessage, 1, "Je hebt nog geen open uitdagingen staan")
            );
        } else if (title.getText().equals("Boter, Kaas en Eieren")) {
            //App.server.acceptChallenge();
        } else {
            showMessage(challengeMessage, 1, "Geen spel geselecteerd");
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
                toAdd = "";
                for (String player : arr) {
                    toAdd += (player + "\n");
                }
                Platform.runLater(() -> playerListList.setText(toAdd));
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
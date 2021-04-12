package application.fxml;

import application.App;
import application.games.players.HumanPlayer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.awt.*;
import java.io.IOException;
import java.util.Locale;
import java.util.Random;

/**
 * Class Starts handles the Logic behind the FXML Start page
 *
 * @author Anouk,
 */
public class Start {
    public static final boolean DEBUG = true; // change to false to hide debug messages
    public static HumanPlayer user;           // the user who uses the application
    public static String gameType;            // the gameType the user chose
    public static final String BKE = "BOTERKAASENEIEREN";
    public static final String REV = "REVERSI";

//    @FXML protected static BorderPane mainPane;    // the mainPane of application
//    @FXML public Group start;

    // PANE VIEW
    @FXML protected Text title;             // Title
    @FXML protected Text info;              // Subtitle/info
    @FXML protected VBox centerScreen;      // Center of borderpane

    // LOGIN
    @FXML protected Group loginCenterBox;   // Group that holds all of the login items
    @FXML protected HBox loginBox;
    @FXML protected HBox loginMessageBox;
    @FXML protected Text loginMessage;
    @FXML protected TextField userName;
    @FXML protected Button verder;

    // GAMECENTER
    @FXML protected Group gameCenterBox; // Group that holds all of the game items
    @FXML protected HBox gameSelection;  // Box to show games that can be played
    @FXML protected VBox gameSettingsBox;
    @FXML protected VBox centerGame;

    @FXML protected TextField enemyUserName;
    @FXML protected Text challengeMessage;

    // LOGIN SCREEN METHODS
    /**
     * Method to handle the login button being pushed
     */
    @FXML
    protected void handleLoginAction() {
//        games.getChildren().remove(centerGame);

        // username can not be empty
        if (textFieldNotEmptyOrBlank(userName)) {
            String player = userName.getText().toLowerCase();

            // Login User
            App.server.login(player, (result) -> {
                switch (result) {
                    case "OK":  // Login succes
                        loginUserAndContinue(player);
                        break;
                    // Login not success
                    case "ERR already logged in":
                        showMessage(loginMessage, 1, "U bent al ingelogd");
                        break;

                    case "ERR duplicate name exists":
                        showMessage(loginMessage, 1, "Deze gebruikersnaam bestaat al");
                        break;
                }}); // end switch / login
        } else {
            showMessage(loginMessage, 1, "Gebruikersnaam kan niet leeg zijn");
        }
    }

    /**
     * Method to handle localPlay button being pushed
     * controleert of de gebruiker een naam heeft ingevuld.
     * ja  -> gebruik die naam als gebruikersnaam
     * nee -> gebruik random generated gebruikernaam
     */
    @FXML
    public void handleLocalPlay() {
        if (textFieldNotEmptyOrBlank(userName)) {   // user heeft naam ingevuld
            String player = userName.getText().toLowerCase();
            loginUserAndContinue(player);
        } else {                                    // user heeft geen naam ingevuld
            Random r = new Random();
            String player = "gebruiker" + r.nextInt(100);
            loginUserAndContinue(player);
        }
    }

    /**
     * Method to login user with specified name and create continue screen
     * @param userName the name of the player
     */
    public void loginUserAndContinue(String userName) {
        user = new HumanPlayer(userName); // create player and set name
        // show message succes
        showMessage(loginMessage, 0, ("Inloggen gelukt, Welkom " + userName + "!") );

        loginBox.setVisible(false);     // hide login
        verder.setVisible(true);        // enable continue button
    }

    /**
     * Method to hide loginScreen and show the GameScreen
     * when the continue button is pushed
     */
    @FXML
    protected void handleContinue() {
        loginCenterBox.getChildren().remove(loginBox);
        loginCenterBox.getChildren().remove(loginMessageBox);

        title.setText(( "AI Gaming [ " + user.getName() + " ]"));
        info.setText("Kies een Spel, speel tegen de Computer, een Vriend of speel Online");

        gameCenterBox.getChildren().remove(gameSettingsBox);
        gameCenterBox.setVisible(true);
    }

//    public static void setAndShowNewGameScreen(String resourceName) throws IOException {
//        Parent root = FXMLLoader.load(Start.class.getResource(resourceName));
//        application.App.appPrimaryStage.setScene(new Scene(root, application.App.UIWIDTH, application.App.UIHEIGHT));
//        application.App.appPrimaryStage.show();
//    }

    // GAME CENTER METHODS
    /**
     * Method to open game Settings depending on which game the user wants to play
     * @param actionEvent the event id defines which game should be played
     */
    @FXML
    public void setUpGameSettings(MouseEvent actionEvent) {
        if (actionEvent.getTarget().toString().contains("id=bke")) {
            title.setText("Boter, Kaas en Eieren");
            gameType = BKE;
            openSettings();

        } else if ((actionEvent.getTarget().toString().contains("id=rev"))) {
            title.setText("Reversi");
            gameType = REV;
            openSettings();
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
    public void goBackToGameCenter() {
        gameCenterBox.getChildren().remove(gameSettingsBox);
        gameCenterBox.getChildren().add(gameSelection);
        title.setText(( "AI Gaming [ " + user.getName() + " ]"));
        info.setText("Kies een Spel, speel tegen de Computer, een Vriend of speel Online");
    }

    @FXML
    public void playNewGame(ActionEvent actionEvent) {
        App.server.forfeit();
        if(gameType.equals(BKE)) {
            App.server.subscribe("Tic-tac-toe", (result) -> { System.out.println("Subscribed to Tic-tac-toe"); });
        }
        if(gameType.equals(REV)) {
            App.server.subscribe("Reversi", (result) -> { System.out.println("Subscribed to Reversi"); });
        }
    }

    @FXML
    public void uitleg(ActionEvent actionEvent) {
        System.out.println("uitleg");
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
                switch (result) {
                    case "OK":
                        showMessage(challengeMessage, 0, ("Je hebt deze speler uitgedaagd: " + enemyPlayer));
                        break;
                    case "ERR player not found":
                        showMessage(challengeMessage, 0, ("Speler " + enemyPlayer + " niet gevonden."));
                        break;
                    case "ERRplayernot":
                        showMessage(challengeMessage, 0, ("Speler " + enemyPlayer + " niet gevonden."));
                        break;
                    }
            }); 
        }
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
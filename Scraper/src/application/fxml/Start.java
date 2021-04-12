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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Arrays;

/**
 * Class Starts handles the Logic behind the FXML Start page
 * This class includes buttons that change the mainPane BorderPane
 *
 * @author Anouk,
 */
public class Start {

    @FXML protected static BorderPane mainPane;    // the mainPane of application
    @FXML public Group start;

    @FXML protected Text title;             // title of pane view
    @FXML protected Text info;              // info of pane view
    @FXML protected VBox centerScreen;

    // login
    @FXML protected Group loginCenterBox;
    @FXML protected HBox loginBox;
    @FXML protected TextField userName;
    @FXML protected TextField enemyUserName;
    @FXML protected Text challengeMessage;
    @FXML protected Text playerListList;
    @FXML protected Text loginMessage;
    @FXML protected VBox loginMessageBox;
    @FXML protected Button verder;

    public HumanPlayer user;

    // gameCenter
    @FXML protected Group gameCenterBox;
    @FXML protected Group games;
    @FXML protected VBox centerGameLocal;
    @FXML protected VBox centerGameOnline;
    @FXML protected VBox playerList;

    @FXML
    protected void handleLoginAction(ActionEvent event) {
        games.getChildren().remove(centerGameLocal);
        games.getChildren().remove(centerGameOnline);

        // player name may not be "Gebruiker"
        if (userName.getText().equals("Gebruiker")) {
            showMessage(loginMessage, 1, "U mag deze gebruikersnaam niet gebruiken");
        }
        // username cannot be empty 
        else if (!userName.getText().isEmpty() || !userName.getText().isBlank()) {
            String player = userName.getText();


            App.server.login(player, result -> {
                switch (result) {
                    case "OK":
                        // create player and set name
                        user = new HumanPlayer(player);
                        showMessage(loginMessage, 0, ("Inloggen gelukt, Welkom " + player + "!") );
                        loginBox.setVisible(false); // hide login
                        verder.setVisible(true);    // enable continue button
                        break;

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

    public void handleLocalPlay(ActionEvent actionEvent) {
        user = new HumanPlayer("Gebruiker");
        games.getChildren().remove(centerGameLocal);
        games.getChildren().remove(centerGameOnline);
        loginCenterBox.getChildren().remove(loginBox);
        loginCenterBox.getChildren().remove(loginMessageBox);

        title.setText(( "AI Gaming [ " + user.getName() + " ]"));
        info.setText("Kies een spel, speel tegen de computer of een vriend");

        gameCenterBox.setVisible(true);
    }

    @FXML
    protected void handleContinue(ActionEvent event) {
        loginCenterBox.getChildren().remove(loginBox);
        loginCenterBox.getChildren().remove(loginMessageBox);

        title.setText(( "AI Gaming [ " + user.getName() + " ]"));
        info.setText("Kies een Spel, speel tegen de Computer, een Vriend of speel Online");

        gameCenterBox.setVisible(true);
    }

    /**
     *
     * @param textBox Text javafx field to place the message
     * @param type 0 = succes / 1 = error
     * @param msg message to show
     */
    @FXML
    public void showMessage(Text textBox, int type, String msg) {
        if (type == 1) {
            textBox.setStyle("-fx-fill: RED;");
        } else {
            textBox.setStyle("-fx-fill: GREEN;");
        }
        textBox.setText(msg);
    }

    public static void setAndShowNewGameScreen(String resourceName) throws IOException {
        Parent root = FXMLLoader.load(Start.class.getResource(resourceName));
        application.App.appPrimaryStage.setScene(new Scene(root, application.App.UIWIDTH, application.App.UIHEIGHT));
        application.App.appPrimaryStage.show();
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

    @FXML
    public void playNewGame(ActionEvent actionEvent) {
        App.server.forfeit();
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
        title.setText(( "AI Gaming [ " + user.getName() + " ]"));
        info.setText("Kies een spel, speel tegen de computer of een vriend");
    }

    @FXML
    public void goBackOnline(ActionEvent actionEvent) {
        games.getChildren().remove(centerGameOnline);
        centerScreen.getChildren().add(gameCenterBox);
        playerList.setVisible(false);
        title.setText(( "AI Gaming [ " + user.getName() + " ]"));
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
}
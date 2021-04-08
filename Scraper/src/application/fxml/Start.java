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
    @FXML protected Text loginMessage;
    @FXML protected VBox loginMessageBox;
    @FXML protected Button verder;

    public static HumanPlayer user;

    // gameCenter
    @FXML protected Group gameCenterBox;
    @FXML protected Group games;
    @FXML protected VBox centerGame;

    @FXML
    protected void handleLoginAction(ActionEvent event) {
        games.getChildren().remove(centerGame);

        // username can not be empty
        if (!userName.getText().isEmpty() || !userName.getText().isBlank()) {
            String player = userName.getText();

            App.server.login(player, (result) -> {
                switch (result) {
                    case "OK":
                        // create player and set name
                        user = new HumanPlayer(player);
                        showMessage(loginMessage, 0, ("Inloggen gelukt, Welkom " + player + "!") );
                        loginBox.setVisible(false); // hide login
                        verder.setVisible(true);    // enable continue button
                        break;

                    case "ERR Already logged in":
                        showMessage(loginMessage, 1, "U bent al ingelogd");
                        break;

                    case "ERR Duplicate name exists":
                        showMessage(loginMessage, 1, "Deze gebruikersnaam bestaat al");
                        break;
                }}); // end switch / login
        } else {
            showMessage(loginMessage, 1, "Gebruikersnaam kan niet leeg zijn");
        }
    }

    public void handleLocalPlay(ActionEvent actionEvent) {
        user = new HumanPlayer("Gebruiker");
        loginCenterBox.getChildren().remove(loginBox);
        loginCenterBox.getChildren().remove(loginMessageBox);

        title.setText(( "AI Gaming [ " + user.getName() + " ]"));
        info.setText("Kies een Spel, speel tegen de Computer, een Vriend of speel Online");

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
    public void setUpBoterKaasEieren(MouseEvent actionEvent) throws IOException {
        centerScreen.getChildren().remove(gameCenterBox);
        games.getChildren().add(centerGame);
        title.setText("Boter, Kaas en Eieren");
        games.setVisible(true);
    }

    @FXML
    public void setUpOthello(MouseEvent actionEvent) throws IOException {
        centerScreen.getChildren().remove(gameCenterBox);
        games.getChildren().add(centerGame);
        games.setVisible(true);
        title.setText("Othello");
    }

    @FXML
    public void playNewGame(ActionEvent actionEvent) {
        if(title.getText().equals("Boter, Kaas en Eieren")) {
            App.server.subscribe("Tic-tac-toe", (result) -> { System.out.println("Subscribed to Tic-tac-toe"); });
        }
        if(title.getText().equals("Othello")) {
            App.server.subscribe("Reversi", (result) -> { System.out.println("Subscribed to Reversi"); });
        }
    }

    @FXML
    public void uitleg(ActionEvent actionEvent) {
        System.out.println("uitleg");
    }

    @FXML
    public void goBack(ActionEvent actionEvent) {
        games.getChildren().remove(centerGame);
        centerScreen.getChildren().add(gameCenterBox);
        title.setText(( "AI Gaming [ " + user.getName() + " ]"));
        info.setText("Kies een Spel, speel tegen de Computer, een Vriend of speel Online");
    }
}
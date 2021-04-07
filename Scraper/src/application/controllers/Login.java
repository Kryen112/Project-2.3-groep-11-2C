package application.controllers;

import application.App;
import application.games.players.HumanPlayer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class Login {
    @FXML protected Text info;
    @FXML protected HBox loginBox;
    @FXML protected TextField userName;
    @FXML protected Text message;

    @FXML
    protected void handleLoginAction(ActionEvent event) {
        // username can not be empty
        if (!userName.getText().isEmpty() || !userName.getText().isBlank()) {
            String player = userName.getText();

            App.server.login(player, (result) -> {
                switch (result) {
                    case "OK":
                        HumanPlayer user = new HumanPlayer(player);
                        showMessage(0, ("Inloggen gelukt, Welkom " + player + "!") );
                        info.setText("Kies een spel, speel tegen een computer, een vriend of online");
                        loginBox.setDisable(true);
                        BorderpaneController.getProfile().setText(user.getName());
                        break;
                    case "ERR Already logged in":
                        showMessage(1, "U bent al ingelogd");
                        break;
                    case "ERR Duplicate name exists":
                        showMessage(1, "Deze gebruikersnaam bestaat al");
                        break;
                }
            });

        } else {
            showMessage(1, "Gebruikersnaam kan niet leeg zijn, vul een gebruikersnaam in en klik op de login knop");
        }
    }

    /**
     *
     * @param type 0 = succes / 1 = error
     * @param msg message to show
     */
    public void showMessage(int type, String msg) {
        if (type == 1) {
            message.setStyle("-fx-fill: RED;");
        } else {
            message.setStyle("-fx-fill: GREEN;");
        }
        message.setText(msg);
    }
}

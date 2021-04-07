package application.controllers;

import application.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class Login {
    @FXML protected TextField userName;
    @FXML protected Text message;

    @FXML
    protected void handleLoginAction(ActionEvent event) {
        // print for testing
        // System.out.println("login button clicked");

        // username can not be empty
        if (!userName.getText().isEmpty() || !userName.getText().isBlank()) {
            String player = userName.getText();
            // TODO set player name
            App.server.login(player, (result) -> {
                switch (result) {
                    case "OK":
                        message.setText("Inloggen gelukt");
                        message.setStyle("-fx-fill: GREEN;");
                        break;
                    case "ERR Already logged in":
                        message.setText("U bent al ingelogd");
                        message.setStyle("-fx-fill: RED;");
                        break;
                    case "ERR Duplicate name exists":
                        message.setText("Gebruikersnaam bestaat al!");
                        message.setStyle("-fx-fill: RED;");
                        break;
                }
            });

            // TODO bedenk stappen na inloggen (invoeren naam)

            // printing for testing
            System.out.println(player);
        } else {
            message.setStyle("-fx-fill: RED;");
            message.setText("Gebruikersnaam kan niet leeg zijn, vul een gebruikersnaam in en klik op de login knop");
        }
    }
}

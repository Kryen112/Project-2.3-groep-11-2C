package groep11.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class Login {
    @FXML protected TextField userName;
    @FXML protected Text errorLogin;

    @FXML
    protected void handleLoginAction(ActionEvent event) {
        // print for testing
//        System.out.println("login button clicked");

        // username can not be empty
        if (!userName.getText().isEmpty() || !userName.getText().isBlank()) {
            String player = userName.getText();
            // TODO set player name

            // TODO bedenk stappen na inloggen (invoeren naam)

            // printing for testing
            System.out.println(player);
        } else {
            errorLogin.setText("Gebruikersnaam kan niet leeg zijn, vul een gebruikersnaam in en klik op de login knop");
        }
    }
}

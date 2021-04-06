package groep11.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class  HomeController {

//        // username bevat geen spaties / is leeg
//        if (user.getText().isBlank() || user.getText().isEmpty()) {
//            errorLogin.setText("Gebruikersnaam mag niet leeg zijn");
//        } else {
//            // TODO Player setUsername()
//            String username = user.getText();
//
//            // go to new scene
//            goToNewView("gameScreen");
//        }

    @FXML
    protected void handleBackButton(ActionEvent event) throws IOException, InterruptedException {
        System.out.println("About us button clicked");
    }

    @FXML
    protected void playTicTacToe(ActionEvent event) throws IOException, InterruptedException {
        System.out.println("Tic Tac Toe button clicked");
    }

    @FXML
    protected void playRiversi(ActionEvent event) throws IOException, InterruptedException {
        System.out.println("Riversi button clicked");
    }



}

package groep11.controllers;

import groep11.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;

import java.io.IOException;

public class Boterkaaseieren {
    @FXML private RadioButton localvsai;
    @FXML private RadioButton localvslocal;
    @FXML private RadioButton onlinevsai;
    @FXML private RadioButton onlinevsrandom;

    /** positions - multidemensional char array to keep track of the positions (e = empty) */
    private final char[][] positions = {
            {'e', 'e', 'e'},
            {'e', 'e', 'e'},
            {'e', 'e', 'e'}
    };

    /** winning_positions - multdimensional char array to keep track of the winning sets (e = empty) */
    private char[][] winningPositions = {
            {'e', 'e', 'e'},
            {'e', 'e', 'e'},
            {'e', 'e', 'e'}
    };

    private char currentTurn = 'x';
    private int turnCounter = 0;

    private boolean isGameOver = false;

//   terug button
    @FXML
    public void goBackHome(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/start.fxml"));
        App.appPrimaryStage.setScene(App.homeScene);
        App.appPrimaryStage.show();

    }

    @FXML
    public void playNewGame(ActionEvent actionEvent) {
    }

    @FXML
    public void playGameWithPlayer(ActionEvent actionEvent) {

    }

    @FXML
    public void uitlegTicTacToe(ActionEvent actionEvent) {

    }
}

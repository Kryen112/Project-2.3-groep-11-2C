package application.fxml;

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

    //   terug button
    @FXML
    public void goBackHome(ActionEvent actionEvent) throws IOException {
        Start.setAndShowNewGameScreen("start.fxml");
    }

    @FXML
    public void playNewGame(ActionEvent actionEvent) throws IOException {

    }

    @FXML
    public void playGameWithPlayer(ActionEvent actionEvent) {
        System.out.println("speel");
    }

    @FXML
    public void uitlegTicTacToe(ActionEvent actionEvent) {
    System.out.println("uitleg");
    }
}

package controllers;

import app.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.text.Text;

import java.io.IOException;

public class Boterkaaseieren {
    @FXML private RadioButton localvsai;
    @FXML private RadioButton localvslocal;
    @FXML private RadioButton onlinevsai;
    @FXML private RadioButton onlinevsrandom;

    //   terug button
    @FXML
    public void goBackHome(ActionEvent actionEvent) {
        App.appPrimaryStage.setScene(App.homeScene);
        App.appPrimaryStage.show();
    }

    @FXML
    public void playNewGame(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/activeTicTacToe.fxml"));
        App.appPrimaryStage.setScene(new Scene(root, App.UIWIDTH, App.UIHEIGHT));
        App.appPrimaryStage.show();

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

package application.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.RadioButton;

import java.io.IOException;

public class Boterkaaseieren {
    @FXML private RadioButton localvsai;
    @FXML private RadioButton localvslocal;
    @FXML private RadioButton onlinevsai;
    @FXML private RadioButton onlinevsrandom;

    //   terug button
    @FXML
    public void goBackHome(ActionEvent actionEvent) {
//        application.App.appPrimaryStage.setScene(application.App.homeScene);
//        application.App.appPrimaryStage.show();
    }

    @FXML
    public void playNewGame(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../application.fxml/activeTicTacToe.application.fxml"));
//        application.App.appPrimaryStage.setScene(new Scene(root, application.App.UIWIDTH, application.App.UIHEIGHT));
//        application.App.appPrimaryStage.show();

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

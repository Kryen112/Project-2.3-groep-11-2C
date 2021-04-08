package application.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class Othello {

    public void goBackHome(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../application.fxml/start.application.fxml"));
//        application.App.appPrimaryStage.setScene(application.App.homeScene);
//        application.App.appPrimaryStage.show();

    }
}

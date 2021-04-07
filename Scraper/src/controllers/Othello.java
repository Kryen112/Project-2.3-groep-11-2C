package controllers;

import app.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class Othello {

    public void goBackHome(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/start.fxml"));
        App.appPrimaryStage.setScene(App.homeScene);
        App.appPrimaryStage.show();

    }
}

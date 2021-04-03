package groep11.controllers;

import groep11.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class Boterkaaseieren {

    public void goBackHome(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/start.fxml"));
        App.appPrimaryStage.setScene(App.homeScene);
        App.appPrimaryStage.show();

    }
}

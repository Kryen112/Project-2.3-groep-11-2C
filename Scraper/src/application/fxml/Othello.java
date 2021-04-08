package application.fxml;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class Othello {

    public void goBackHome(ActionEvent actionEvent) throws IOException {
        Start.setAndShowNewGameScreen("start.fxml");

    }
}

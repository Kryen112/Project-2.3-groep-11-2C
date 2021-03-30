import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class TicTacToe extends Application {

    private final GridPane tictactoe = new GridPane();

    @Override
    public void start(Stage primaryStage) {
        BorderPane pane = setBorderPane(primaryStage);
        Scene scene = new Scene(pane, 500, 500);

        primaryStage.setTitle("TicTacToe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public BorderPane setBorderPane(Stage primaryStage) {
        BoterKaasEnEierenUI boterKaasEnEierenUI = new BoterKaasEnEierenUI();
        TicTacToe ticTacToe = new TicTacToe();
        BorderPane pane = new BorderPane();

        pane.setPadding(new Insets(10, 10, 10, 10));

        HBox hbButtons = new HBox();

        Button stopButton = new Button("Stop");
        stopButton.setOnAction(event -> 
            boterKaasEnEierenUI.start(primaryStage)
        );

        Button resetButton = new Button("Reset");
        resetButton.setOnAction(event -> 
            ticTacToe.start(primaryStage)
        );

        hbButtons.getChildren().add(stopButton);
        hbButtons.getChildren().add(resetButton);
        hbButtons.setAlignment(Pos.CENTER_RIGHT);

        hbButtons.setSpacing(50);

        pane.setTop(hbButtons);

        int id = 0;

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                tictactoe.add(createButton(id,50, 50, i, j), i, j);
                id++;
            }
        }

        tictactoe.setAlignment(Pos.CENTER);

        pane.setCenter(tictactoe);

        return pane;
    }

    public Button createButton(int id, int width, int height, int xPos, int yPos) {
        Button button = new Button();
        button.setPrefWidth(width);
        button.setPrefHeight(height);
        button.setStyle(buttonStyle(id));
        button.setOnAction(event -> doMove(button, xPos, yPos));
        return button;
    }

    public String buttonStyle(int id) {
        switch (id) {
            case 0: return "-fx-border-color: black; -fx-border-width: 0 2 2 0; -fx-background-color: transparent";
            case 1: return "-fx-border-color: black; -fx-border-width: 2 2 2 0; -fx-background-color: transparent";
            case 2: return "-fx-border-color: black; -fx-border-width: 2 2 0 0; -fx-background-color: transparent";
            case 3: return "-fx-border-color: black; -fx-border-width: 0 2 2 2; -fx-background-color: transparent";
            case 4: return "-fx-border-color: black; -fx-border-width: 2 2 2 2; -fx-background-color: transparent";
            case 5: return "-fx-border-color: black; -fx-border-width: 2 2 0 2; -fx-background-color: transparent";
            case 6: return "-fx-border-color: black; -fx-border-width: 0 0 2 2; -fx-background-color: transparent";
            case 7: return "-fx-border-color: black; -fx-border-width: 2 0 2 2; -fx-background-color: transparent";
            case 8: return "-fx-border-color: black; -fx-border-width: 2 0 0 2; -fx-background-color: transparent";
            default: return null;
        }
    }

    public void doMove(Button button, int xPos, int yPos) {
        tictactoe.add(getX(), xPos, yPos);
        button.setOnAction(null);
    }

    public ImageView getX() {
        ImageView x = new ImageView("./x.png");
        x.setFitHeight(45);
        x.setFitWidth(45);
        return x;
    }
}

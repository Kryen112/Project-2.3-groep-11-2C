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
        BorderPane pane = setBorderPane();
        Scene scene = new Scene(pane, 500, 500);

        primaryStage.setTitle("TicTacToe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public BorderPane setBorderPane() {
        BorderPane pane = new BorderPane();

        pane.setPadding(new Insets(10, 10, 10, 10));

        HBox hbButtons = new HBox();

        Button quit = new Button("Quit");


        hbButtons.getChildren().add(quit);
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
        return switch (id) {
            case 0 -> "-fx-border-color: black; -fx-border-width: 0 2 2 0; -fx-background-color: transparent";
            case 1 -> "-fx-border-color: black; -fx-border-width: 2 2 2 0; -fx-background-color: transparent";
            case 2 -> "-fx-border-color: black; -fx-border-width: 2 2 0 0; -fx-background-color: transparent";
            case 3 -> "-fx-border-color: black; -fx-border-width: 0 2 2 2; -fx-background-color: transparent";
            case 4 -> "-fx-border-color: black; -fx-border-width: 2 2 2 2; -fx-background-color: transparent";
            case 5 -> "-fx-border-color: black; -fx-border-width: 2 2 0 2; -fx-background-color: transparent";
            case 6 -> "-fx-border-color: black; -fx-border-width: 0 0 2 2; -fx-background-color: transparent";
            case 7 -> "-fx-border-color: black; -fx-border-width: 2 0 2 2; -fx-background-color: transparent";
            case 8 -> "-fx-border-color: black; -fx-border-width: 2 0 0 2; -fx-background-color: transparent";
            default -> null;
        };
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

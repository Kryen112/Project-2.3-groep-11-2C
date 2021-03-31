import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class TicTacToe extends Application {

    private final GridPane tictactoe = new GridPane();
    private VBox turnBox = new VBox();

    // e = empty, starting with empty positions
    private final char[][] positions = {
            {'e', 'e', 'e'},
            {'e', 'e', 'e'},
            {'e', 'e', 'e'}
    };

    private char turn = 'x';
    private boolean gameOver = false;

    @Override
    public void start(Stage primaryStage) {
        BorderPane pane = setBorderPane(primaryStage);
        pane.setId("pane");
        Scene scene = new Scene(pane, 1280, 720);
        scene.getStylesheets().addAll(this.getClass().getResource("Menus.css").toExternalForm());


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
        hbButtons.setAlignment(Pos.TOP_RIGHT);
        hbButtons.setId("topPane");
        hbButtons.setSpacing(50);

        pane.setTop(hbButtons);

        Label turnLabel = new Label("De beurt is aan:");
        turnBox.getChildren().add(turnLabel);
        turnBox.getChildren().add(new Label("" + getTurn()));
        pane.setLeft(turnBox);

        int id = 0;

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                tictactoe.add(createButton(id, 150, 150, i, j), i, j);
                id++;
            }
        }

        tictactoe.setAlignment(Pos.CENTER);

        pane.setCenter(tictactoe);

        return pane;
    }

    public Button createButton(int id, int width, int height, int hPos, int vPos) {
        Button button = new Button();
        button.setPrefWidth(width);
        button.setPrefHeight(height);
        button.setStyle(buttonStyle(id));
        button.setOnAction(event -> doMove(button, hPos, vPos));
        return button;
    }

    public Boolean isWon() {
        int x_horizontal = 0;
        int o_horizontal = 0;

        int x_vertical = 0;
        int o_vertical = 0;

        // horizontal and vertical row check
        for(int i = 0; i<3; i++) {
            for(int j = 0; j<3; j++) {
                if(positions[i][j] == 'x') {
                    x_horizontal++;
                }
                if(positions[i][j] == 'o') {
                    o_horizontal++;
                }
                if(positions[j][i] == 'x') {
                    x_vertical++;
                }
                if(positions[j][i] == 'o') {
                    o_vertical++;
                }
            }

            if(x_horizontal == 3 || o_horizontal == 3 || x_vertical == 3 || o_vertical == 3) {
                return true;
            }

            x_horizontal = 0;
            o_horizontal = 0;
            x_vertical = 0;
            o_vertical = 0;
        }

        int count = 0;
        int x_diagonal = 0;
        int o_diagonal = 0;

        // diagonal top left to bottom right
        for(int i = 0; i < 3; i++) {
            if(positions[i][count] == 'x') {
                x_diagonal++;
            }
            if(positions[i][count] == 'o') {
                o_diagonal++;
            }
            if(x_diagonal == 3 || o_diagonal == 3) {
                return true;
            }
            count++;
        }

        x_diagonal = 0;
        o_diagonal = 0;
        count = 0;

        // diagonal bottom left to top right
        for(int i = 2; i >= 0; i--) {
            if(positions[i][count] == 'x') {
                x_diagonal++;
            }
            if(positions[i][count] == 'o') {
                o_diagonal++;
            }
            if(x_diagonal == 3 || o_diagonal == 3) {
                return true;
            }
            count++;
        }
        return false;
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

    public void doMove(Button button, int hPos, int vPos) {

        if(!gameOver) {
            tictactoe.add(getXO(), hPos, vPos);
            positions[hPos][vPos] = turn;
            button.setOnAction(null);
        }

        if(isWon()) {
            gameOver = true;
            turnBox.getChildren().set(0, new Label(getTurn() + " heeft gewonnen!"));
            turnBox.getChildren().set(1, new Label(""));
        } else {
            changeTurn();
            turnBox.getChildren().set(1, new Label("" + getTurn()));
        }
    }

    public void changeTurn() {
        if(getTurn() == 'x') {
            setTurn('o');
        }
        else if(getTurn() == 'o') {
            setTurn('x');
        }
    }

    public ImageView getXO() {
        ImageView xo = new ImageView();

        if(getTurn() == 'x') {
            xo = new ImageView("./x.png");
        }

        if(getTurn() == 'o') {
            xo = new ImageView("./o.png");
        }

        xo.setFitHeight(150);
        xo.setFitWidth(150);

        return xo;
    }

    public char getTurn() {
        return turn;
    }

    public void setTurn(char turn) {
        this.turn = turn;
    }
}

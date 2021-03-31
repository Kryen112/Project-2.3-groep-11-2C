import java.util.Random;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TicTacToeAI extends Application {

    /** tictactoe - the tictactoe game board */
    private final GridPane tictactoe = new GridPane();
    private final VBox turnBox = new VBox();
    private boolean isSet = false;

    /** positions - multidemensional char array to keep track of the positions (e = empty) */
    private final char[][] positions = {
            {'e', 'e', 'e'},
            {'e', 'e', 'e'},
            {'e', 'e', 'e'}
    };

    /** winning_positions - multdimensional char array to keep track of the winning sets (e = empty) */
    private char[][] winningPositions = {
            {'e', 'e', 'e'},
            {'e', 'e', 'e'},
            {'e', 'e', 'e'}
    };

    /** turn - char that represents current turn of player */
    private char turn;

    /** gameOver - boolean to check if game is over */
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
        randomTurnPicker();
    }

    public BorderPane setBorderPane(Stage primaryStage) {
        BoterKaasEnEierenUI boterKaasEnEierenUI = new BoterKaasEnEierenUI();
        TicTacToeAI ticTacToeAI = new TicTacToeAI();

        BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(10, 10, 10, 10));

        Button stopButton = new Button("Stop");
        stopButton.setOnAction(event -> 
            boterKaasEnEierenUI.start(primaryStage)
        );

        Button resetButton = new Button("Reset");
        resetButton.setOnAction(event -> 
            ticTacToeAI.start(primaryStage)
        );

        HBox hbButtons = new HBox();
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
        button.setOnAction(event -> processTurn(button, hPos, vPos));
        return button;
    }

    public Boolean isWon() {
        int xHorizontal = 0;
        int oHorizontal = 0;

        // horizontal row check
        for(int i = 0; i<3; i++) {
            for(int j = 0; j<3; j++) {
                if(positions[i][j] == 'x') {
                    winningPositions[i][j] = 'x';
                    xHorizontal++;
                }
                if(positions[i][j] == 'o') {
                    winningPositions[i][j] = 'o';
                    oHorizontal++;
                }
            }

            if(xHorizontal == 3 || oHorizontal == 3) {
                return true;
            }

            xHorizontal = 0;
            oHorizontal = 0;
            resetWinningPositions();
        }

        int xVertical = 0;
        int oVertical = 0;

        for(int i = 0; i<3; i++) {
            for(int j = 0; j<3; j++) {
                if(positions[j][i] == 'x') {
                    winningPositions[j][i] = 'x';
                    xVertical++;
                }
                if(positions[j][i] == 'o') {
                    winningPositions[j][i] = 'o';
                    oVertical++;
                }
            }

            if(xVertical == 3 || oVertical == 3) {
                return true;
            }
            xVertical = 0;
            oVertical = 0;
            resetWinningPositions();
        }

        int count = 0;
        int xDiagonal = 0;
        int oDiagonal = 0;

        // diagonal top left to bottom right
        for(int i = 0; i < 3; i++) {
            if(positions[i][count] == 'x') {
                winningPositions[i][count] = 'x';
                xDiagonal++;
            }
            if(positions[i][count] == 'o') {
                winningPositions[i][count] = 'o';
                oDiagonal++;
            }
            if(xDiagonal == 3 || oDiagonal == 3) {
                return true;
            }
            count++;
        }

        resetWinningPositions();
        xDiagonal = 0;
        oDiagonal = 0;
        count = 0;

        // diagonal bottom left to top right
        for(int i = 2; i >= 0; i--) {
            if(positions[i][count] == 'x') {
                winningPositions[i][count] = 'x';
                xDiagonal++;
            }
            if(positions[i][count] == 'o') {
                winningPositions[i][count] = 'o';
                oDiagonal++;
            }
            if(xDiagonal == 3 || oDiagonal == 3) {
                return true;
            }
            count++;
        }
        resetWinningPositions();
        return false;
    }

    public String buttonStyle(int id) {
        switch (id) {
            case 0: return "-fx-border-color: black; -fx-border-width: 0 4 4 0; -fx-background-color: transparent";
            case 1: return "-fx-border-color: black; -fx-border-width: 4 4 4 0; -fx-background-color: transparent";
            case 2: return "-fx-border-color: black; -fx-border-width: 4 4 0 0; -fx-background-color: transparent";
            case 3: return "-fx-border-color: black; -fx-border-width: 0 4 4 4; -fx-background-color: transparent";
            case 4: return "-fx-border-color: black; -fx-border-width: 4 4 4 4; -fx-background-color: transparent";
            case 5: return "-fx-border-color: black; -fx-border-width: 4 4 0 4; -fx-background-color: transparent";
            case 6: return "-fx-border-color: black; -fx-border-width: 0 0 4 4; -fx-background-color: transparent";
            case 7: return "-fx-border-color: black; -fx-border-width: 4 0 4 4; -fx-background-color: transparent";
            case 8: return "-fx-border-color: black; -fx-border-width: 4 0 0 4; -fx-background-color: transparent";
            default: return null;
        }
    }

    public void processTurn(Button button, int hPos, int vPos) {
        doUserTurn(button, hPos, vPos);
        if(!isSet) {
            doAITurn();
        }
        isSet = false;
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                System.out.print(positions[i][j]);
            }
        }
        System.out.println("");
    }

    public void changeTurn() {
        if(getTurn() == 'x') {
            setTurn('o');
        }
        else if(getTurn() == 'o') {
            setTurn('x');
        }
    }

    public void doAITurn() {
        if(!gameOver) {
            boolean validMove = false;

            while (!validMove) {
                Random r = new Random();
                int i = r.nextInt(3);
                int j = r.nextInt(3);
                if (positions[j][i] == 'e') {
                    positions[j][i] = 'o';
                    tictactoe.add(getXO(), i, j);
                    validMove = true;
                }
            }
        }

        if(isWon()) {
            gameOver = true;
            setWinningColors();
            turnBox.getChildren().set(0, new Label(getTurn() + " heeft gewonnen!"));
            turnBox.getChildren().set(1, new Label(""));

        } else {
            changeTurn();
            turnBox.getChildren().set(1, new Label("" + getTurn()));
        }
    }

    public void doUserTurn(Button button, int hPos, int vPos) {
        if(!gameOver) {
            if (positions[vPos][hPos] == 'o') {
                button.setOnAction(null);
                isSet = true;
            } else {
                tictactoe.add(getXO(), hPos, vPos);
                positions[vPos][hPos] = turn;
                button.setOnAction(null);
            }
        }

        if(isWon()) {
            gameOver = true;
            setWinningColors();
            turnBox.getChildren().set(0, new Label(getTurn() + " heeft gewonnen!"));
            turnBox.getChildren().set(1, new Label(""));

        } else {
            if(!isSet) {
                changeTurn();
            }
            turnBox.getChildren().set(1, new Label("" + getTurn()));
        }
    }

    public void resetWinningPositions() {
        this.winningPositions = new char[][] {
                {'e', 'e', 'e'},
                {'e', 'e', 'e'},
                {'e', 'e', 'e'}
        };
    }

    public void setWinningColors() {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(winningPositions[i][j] == 'x') {
                    ImageView x_won = new ImageView("./x_won.png");
                    x_won.setFitHeight(150);
                    x_won.setFitWidth(150);
                    tictactoe.add(x_won, j, i);
                }
                if(winningPositions[i][j] == 'o') {
                    ImageView o_won = new ImageView("./o_won.png");
                    o_won.setFitWidth(150);
                    o_won.setFitHeight(150);
                    tictactoe.add(o_won, j, i);
                }
            }
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

    public void randomTurnPicker() {
        Random r = new Random();
        int i = r.nextInt(2);
        if(i==0) {
            turn = 'x';
        } else {
            turn = 'o';
            doAITurn();
        }
    }

    public char getTurn() {
        return turn;
    }

    public void setTurn(char turn) {
        this.turn = turn;
    }
}
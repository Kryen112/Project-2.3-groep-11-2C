package application.controllers;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class ActiveTicTacToe {
    @FXML
    private Text turnOfPlayer;
    @FXML
    private Pane pane;


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

    private String beurtVan = "Aan zet: ";
    private char turn = 'x';


    private int turnCounter = 0;

    private boolean isGameOver = false;

    public void clicked(MouseEvent mouseEvent) {
        Pane toFind = (Pane) mouseEvent.getSource();
        System.out.println(toFind.getId() + " clicked");

        toFind.getChildren().set(0, turnOfPlayer);
    }
}

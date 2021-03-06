package application.games;

import application.App;
import application.games.attributes.MiniMax;
import application.games.players.Player;
import application.serverconnect.Server;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import application.games.attributes.BKE;
import application.games.attributes.Board;
import application.games.attributes.Reversi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Klasse Game
 *
 * @author Anouk,
 * Project 2.3 2021 - Groep 2C
 */
public class Game {
    Random r = new Random();

    public String gameTitle;
    public BoardUI boardUI;      // The UI of the game board
    public Board board;
    public Reversi reversi;
    public BKE bke;

    private final Player player1;       // Players of the Game
    private final Player player2;
    private int player1GameScore;
    private int player2GameScore;

    private Player currentPlayer; // de speler die aan zet is
    private char turn = 'x';
    private int turns = 0;
    private Player winner = null;
    private Boolean activeGame = true;

    public final static String BKE = "Boter, Kaas en Eieren";
    public final static String REV = "Reversi";
    public final static String X = "X";
    public final static String O = "O";

    MiniMax mm;

    /**
     * Constructor klasse Game
     * Wordt aangeroepen als er een nieuwe Game wordt gestart
     * @param title       Titel van de Game
     * @param p1          Speler 1 (vanuit Input van de Server)
     * @param p2          Speler 2 (vanuit Input van de Server
     */
    public Game(String title, Player p1, Player p2){
        if (title.equals(BKE)) {
            gameTitle = BKE;
            board = new Board(3);
            boardUI = new BoardUI(3, new HashMap<>());
            bke = new BKE();
        } else if (title.equals(REV)) {
            gameTitle = REV;
            board = new Board(8);
            boardUI = new BoardUI(8, new HashMap<>());
            reversi = new Reversi();
        } else {
            gameTitle = "";
        }

        player1 = p1;
        player1.setScore(0);

        player2 = p2;
        player2.setScore(0);

        // Random wie begint
        int i = r.nextInt(2);
        if (i == 0) {   // Player one plays as X / zwart
            currentPlayer = player1;
            turn = X.charAt(0);
        } else {        // Player two plays as O / wit
            currentPlayer = player2;
            turn = O.charAt(0);
        }

        mm = new MiniMax();
    }

    /**
     * Return the Title of Game
     * @return GameType [BKE / REV]
     */
    public String getGameTitle() {
        return this.gameTitle;
    }

    public Reversi getReversi() {
        return this.reversi;
    }

    /**
     * Methode om het bord dat gebruikt wordt te retourneren
     * @return BoardUI het bord dat gebruikt wordt
     */
    public Board getBoard() {
        return board;
    }

    public BoardUI getBoardUI() {
        return this.boardUI;
    }

    public Player getWinner() {
        return this.winner;
    }

    /**
     * Method to return the Player who plays as Player one
     * @return Player one of Game
     */
    public Player getPlayer1() {
        return this.player1;
    }
    /**
     * Method to return the Player who plays as Player two
     * @return Player two of Game
     */
    public Player getPlayer2() {
        return this.player2;
    }
    /**
     * Method to return the Current Player
     * @return the player who has the current turn of Game
     */
    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }
    /**
     * Method to return the Current turn
     * @return current turn X / O
     */
    public char getTurn() {
        return this.turn;
    }

    public void setWinner() {
        if(gameTitle.equals(BKE)){
            char winner = bke.isWonBKE(getBoard());
            if(winner != '.'){

            }
        }

        if(gameTitle.equals(REV)) {
            if(board.getStoneAmount('x') > board.getStoneAmount('o')) {
                // tegenstander wint
                this.winner = player2;
            }
            else if (board.getStoneAmount('x') < board.getStoneAmount('o')) {
                // wij winnen
                this.winner = player1;
            } else {
                // draw
                this.winner = null;
            }
        }
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public boolean isWon(){
        if(gameTitle == BKE){
            char winner = bke.isWonBKE(getBoard());
            if(winner != '.'){
                return true;
            }
            else return false;
        }
        if(gameTitle.equals(REV)){
            char winner = reversi.isWonRev(getBoard());
            if(winner != '.'){
                if(winner == 'o') {
                    this.winner = player1;
                } else {
                    this.winner = player2;
                }
                return true;
            }
            else {
                return false;
            }
        }
        return false;
    }

    /**
     * Methode om de beurt te wisselen
     */
    public void changeTurn() {
        if(currentPlayer.equals(player1)) { // player one is current player
            currentPlayer = player2; // change player
            turn = O.charAt(0);
        } else { // player two is current player
            currentPlayer = player1; // change player
            turn = X.charAt(0);
        }
    }

    /**
     * Methode om icon op Pane te plaatsen
     * De gebruiker zet een pion op het bord
     * @param view de ImageView waarin de afbeelding geplaatst moet worden
     * @return
     */
    public ImageView setPieceOnBoard(ImageView view) {
        String thisTurn = "" + getTurn();
        view = new ImageView(this.currentPlayer.getIcon( this.gameTitle, thisTurn ));
        return view;
    }

    /**
     * Methode om te controleren of de Game nog actief is
     * @return true als Game nog gespeeld wordt
     */
    public Boolean isGameOver() {
        return !this.activeGame;
    }

    /**
     * Methode om de game over te activeren
     */
    public void setGameOver() {
        this.activeGame = false;
    }

    /**
     * Methode die gebruikt wordt als de Speler het spel verloren heeft
     * @param p player that lost the game
     */
    public void playerIsLoser(Player p) {
        p.increaseLosses(); // add one to losses
    }
    /**
     * Methode die gebruikt wordt om te controleren wie het spel gewonnen heeft
     * @param p player that won the game
     */
    public void playerIsWinner(Player p) {
        p.increaseWins(); // add one to wins
    }
    /**
     * Methode om te controleren of het spel een DRAW is
     * @return true als game DRAW is
     */
    public Boolean isGameTie() {
        return (player1GameScore == player2GameScore);
    }

    public int getTurns() {
        return turns;
    }

    public void incrementTurns() {
        this.turns++;
    }

    public Boolean isWonREV() {
        return true;
    }

    public void addPointToPlayer(Player p) {
        if (p.equals(this.getPlayer1())) {
            player1GameScore++;
        } else if (p.equals(this.getPlayer2())) {
            player2GameScore++;
        }
    }

    public void copyList(){

        for(int i = 0; i < board.getHeight(); i++) {
            for(int j = 0; j < board.getHeight(); j++) {
                // controleer voor elk punt of het overeenkomt

//                System.out.println(board.getGameBoardChar(i,j));
                //board.printBoard();
                if (board.getGameBoardChar(i,j) == 'o') {
                    ImageView imageView;

                    if(App.server.getInputProcesser().black.equals(App.server.getInputProcesser().opponent)) {
                        imageView = new ImageView(new Image("application/images/wit.png"));
                    } else {
                        imageView = new ImageView(new Image("application/images/zwart.png"));
                    }

                    boardUI.getGameBoardPane(i, j).getChildren().add(imageView);
                } else if ( board.getGameBoardChar(i,j) == 'x'){
                    ImageView imageView;

                    if(App.server.getInputProcesser().black.equals(App.server.getInputProcesser().opponent)) {
                        imageView = new ImageView(new Image("application/images/zwart.png"));
                    } else {
                        imageView = new ImageView(new Image("application/images/wit.png"));
                    }

                    boardUI.getGameBoardPane(i, j).getChildren().add(imageView);
                } else {

                }
                boardUI.getGameBoardPane(i, j);
            }
        }
    }
    public MiniMax getMm() {
        return mm;
    }
}

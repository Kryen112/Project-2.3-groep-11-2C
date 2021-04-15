package application.fxml;

import application.App;
import application.games.Game;
import application.games.BoardUI;
import application.games.players.ComputerPlayer;
import application.games.players.HumanPlayer;
import application.games.players.Player;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import java.util.LinkedList;
import java.util.HashMap;

/**
 * Class Starts handles the Logic behind the FXML Start page
 *
 * @author Anouk, Stefan, Douwe, Robert, Jason
 * Project 2.3 Hanze Hogeschool 2021
 */
public class Start implements Runnable {
    public final boolean DEBUG = true; // change to false to hide debug messages
    public HumanPlayer user;           // the user who uses the application
    public HumanPlayer player2;           // the user who uses the application
    public ComputerPlayer ai = new ComputerPlayer(); // the ai
    public String gameType;            // the gameType the user chose
    public final String BKE = "Boter, Kaas en Eieren";
    public final String REV = "Reversi";
    public String toAdd = "";
    public BoardUI bordToUse;
    public Pane[][] gameBoardUI;

    @FXML ImageView BOTERKAASEIEREN;
    @FXML ImageView REVERSI;

    Boolean online = false;
    Boolean lokaal = false;
    Boolean loggedIn = false;

    @FXML Button buttonOnline;
    @FXML Button buttonLocal;

    // PANE VIEW
    @FXML Button backButton;
    @FXML protected BorderPane mainPane;    // the mainPane of application
    @FXML protected Text title;             // Title
    @FXML protected Text info;              // Subtitle/info
    @FXML protected VBox centerScreen;      // Center of borderpane

    // HOME
    @FXML protected Group homeScreen;
    @FXML protected Button backButtonLocal;

    // LOGIN
    @FXML protected Group loginCenterBox;   // Group that holds all of the login items
    @FXML protected VBox loginBox;
    @FXML protected HBox loginMessageBox;
    @FXML protected Text loginMessage;
    @FXML protected TextField userName;
    @FXML protected TextField ipAddress;
    @FXML protected TextField portNr;
    @FXML protected Button verder;

    // gameCenter
    @FXML protected Group games;
    @FXML protected VBox centerGameLocal;
    @FXML protected VBox centerGameOnline;
    @FXML protected VBox playerList;
    @FXML protected Label playerListList;
    @FXML protected TextField enemyUserName;
    @FXML protected Text challengeMessage;
    @FXML protected TextField turnTime;
    @FXML protected Button logOutButton;

    // GAMECENTER
    @FXML protected Group gameCenterBox; // Group that holds all of the game items
    @FXML protected HBox gameSelection;  // Box to show games that can be played
    @FXML protected VBox centerGame;

    // ACTIVE GAME
    @FXML protected Group gameBoard;
    @FXML protected GridPane gameTiles;
    @FXML protected Text gameLog;
    public ImageView boardTile;

    static final int EMPTY = 0;
    static final int CAPTUREDBYP1 = 1;
    static final int CAPTUREDBYP2 = 2;

    HashMap<String, Integer> stateOfTile;
    HashMap<String, Pane> listOfPanes = new HashMap<>();
    Boolean inGame = false;
    static Game thisGame;

    /**
     * Method to handle the login button being pushed
     * Only used for logging in on the school server
     */
    @FXML
    protected void handleLoginAction(ActionEvent event) {
        if (DEBUG) {
            System.out.println(event.getTarget().toString());
        }
        Button pushed = (Button) event.getTarget();

        //Make connection with server
        try {
            if (pushed.getText().equals("Login op schoolserver")) {
                App.makeConnectionWithServer();
            } else if (pushed.getText().equals("Login op Netwerk")) {
                App.makeConnectionWithServer(ipAddress.getText(), portNr.getText());
            }
        } catch (Exception e) {
            showMessage(info, 1, "Fout met Server connectie");
            System.out.println("Fout met serverconnectie.");
        }
        showMessage(info, 0, "");
        if (DEBUG) {
            System.out.println(centerScreen.getChildren().toString());
        }
        // player name may not be "Gebruiker"
        if (userName.getText().equals("Gebruiker") || userName.getText().equals("Gebruiker2")) {
            showMessage(loginMessage, 1, "U mag deze gebruikersnaam niet gebruiken");
        }
        // username cannot be empty 
        else if (!userName.getText().isEmpty() || !userName.getText().isBlank()) {
            String player = userName.getText().toLowerCase();

            App.server.login(player, result -> {
                switch (result) {
                    case "OK":  // Login succes
                        user = new HumanPlayer(player);
                        App.server.setLoggedIn(true);

                        if (DEBUG) {
                            System.out.println(centerScreen.getChildren().toString());
                        }
                        break;
                    // Login not success
                    case "ERR already logged in":
                        showMessage(info, 1, "U bent al ingelogd");
                        break;

                    case "ERR duplicate name exists":
                        showMessage(info, 1, "Deze gebruikersnaam bestaat al");
                        break;
                    default:
                        showMessage(info, 1, "Er is iets fout gegaan, probeer het opnieuw.");
                        break;
                }
            });
        } else {
            showMessage(info, 1, "Gebruikersnaam is leeg of ongeldig! Kies een andere gebruikersnaam of vul gebruikersnaam in");
        }

        setTitleOfGameScreen("Welkom " + userName.getText());
        showMessage(info, 2, "Inloggen gelukt!");
        logOutButton.setVisible(true);
        showGameScreen();
    }

    /**
     * Method to show the gameScreen when user logs in
     */
    public void showGameScreen(){
        centerScreen.getChildren().remove(loginCenterBox);
        if (!centerScreen.getChildren().contains(gameCenterBox)){
            centerScreen.getChildren().add(gameCenterBox);
            gameCenterBox.setVisible(true);
        }
    }

    /**
     * Method to handle playmode (Homescreen)
     * user can play either online or local
     * @param e Button that has been pushed
     */
    @FXML
    public void handlePlayMode(ActionEvent e) {
        Button buttonThatHasBeenPushed = (Button) e.getTarget();
        backButton.setVisible(true);
        backButton.setText("Terug naar welkomscherm");
        homeScreen.setVisible(false);

        centerScreen.getChildren().remove(homeScreen);

        // play Online
        if (buttonThatHasBeenPushed.equals(buttonOnline)) {
            online = true;
            setTitleOfGameScreen("AI Gaming\tLogin");

            if (!centerScreen.getChildren().contains(loginCenterBox)) {
                centerScreen.getChildren().add(loginCenterBox);
            }
            centerScreen.getChildren().remove(gameCenterBox);
            centerScreen.getChildren().remove(games);
            centerScreen.getChildren().remove(gameBoard);

            loginCenterBox.setVisible(true);
            loginBox.setVisible(true);

            showMessage(info, 0, "Voer een gebruikersnaam in en login!");
            if (DEBUG){
                System.out.println(centerScreen.getChildren().toString());
            }

        // play Local
        } else if (buttonThatHasBeenPushed.equals(buttonLocal)) {
            lokaal = true;
            setTitleOfGameScreen("AI Gaming");

            centerScreen.getChildren().remove(loginCenterBox);
            if (!centerScreen.getChildren().contains(gameCenterBox)){
                centerScreen.getChildren().add(gameCenterBox);
            }
            if (!centerScreen.getChildren().contains(games)) {
                centerScreen.getChildren().add(games);
            }
            if (!centerScreen.getChildren().contains(gameBoard)) {
                centerScreen.getChildren().remove(gameBoard);
            }

            //Local user is a user with name "Gebruiker"
            user = new HumanPlayer("Gebruiker");
            player2 = new HumanPlayer("Gebruiker2");

            showMessage(info, 0, "Kies een spel, speel tegen de computer of een vriend");

            gameCenterBox.setVisible(true);
            if (DEBUG){
                System.out.println(centerScreen.getChildren().toString());
            }
        }
    }

    @FXML
    public void goBackButton(ActionEvent e){
        Button pushed = (Button) e.getTarget();

        if (pushed.getText().equals("Terug naar welkomscherm")) {
            if (lokaal) {
                lokaal = false;
//                System.out.println(centerScreen.getChildren().toString());
                centerScreen.getChildren().remove(gameCenterBox);
                centerScreen.getChildren().remove(games);
                centerScreen.getChildren().remove(gameBoard);
            } else if (online) {
                online = false;
//                System.out.println(centerScreen.getChildren().toString());
                centerScreen.getChildren().remove(loginCenterBox);
                centerScreen.getChildren().remove(gameCenterBox);
                centerScreen.getChildren().remove(games);
                centerScreen.getChildren().remove(gameBoard);
            }
            pushed.setVisible(false);
            logOutButton.setVisible(false);
            showHomeScreen();
        } else if (pushed.getText().equals("Ga Terug")) {
            if (lokaal) {
                centerScreen.getChildren().remove(games);
                games.getChildren().remove(centerGameLocal);
                gameCenterBox.setVisible(true);
            } else if (online) {
                centerScreen.getChildren().remove(games);
                games.getChildren().remove(centerGameOnline);
                games.getChildren().remove(playerList);
            }
            if (!centerScreen.getChildren().contains(gameCenterBox)) {
                centerScreen.getChildren().add(gameCenterBox);
            }
        }
        setTitleOfGameScreen("Welkom " + user.getName());
    }

    public void showHomeScreen() {
        setTitleOfGameScreen("Welkom bij AI Gaming");
        showMessage(info, 0, "Wil je Online of Lokaal spelen?");

        if (!centerScreen.getChildren().contains(homeScreen)) {
            centerScreen.getChildren().add(homeScreen);
            if (homeScreen.getChildren().contains(buttonOnline)) {
                homeScreen.getChildren().add(buttonOnline);
            } else if (homeScreen.getChildren().contains(buttonLocal)) {
                homeScreen.getChildren().add(buttonLocal);
            }
        }
        homeScreen.setVisible(true);
    }

    /**
     * The logout button
     * visible when user is logged in
     */
    @FXML
    public void logOut() {
        //Set variables
        if (App.server.isLoggedIn()) {
            App.server.setLoggedIn(false);
            App.server.logout();
        }

        userName.setText("");

        for (Object o : centerScreen.getChildren().toArray()) {
            centerScreen.getChildren().remove(o);
        }

        //Handle screen transitions
        backButton.setVisible(false);
        logOutButton.setVisible(false);
        showHomeScreen();
    }

    /**
     * Method to select GameType
     */
    @FXML
    public void setUpGame(MouseEvent event) {
        if (DEBUG) {
            System.out.println(event.getTarget().toString());
        }
        ImageView target = (ImageView) event.getTarget();
        info.setText("");
        backButton.setText("Ga Terug");
        centerScreen.getChildren().remove(gameCenterBox);

        if (target.equals(BOTERKAASEIEREN)) {
            gameType = BKE;
            // lokaal
            if (lokaal) {
                showLocalGameScreen();

                // online
            } else if (online) {
                showOnlineGameScreen();
            }

        } else if (target.equals(REVERSI)) {
            gameType = REV;
            // lokaal
            if (lokaal) {
                showLocalGameScreen();

                // online
            } else if (online) {
                showOnlineGameScreen();
            }


        }
        setTitleOfGameScreen(gameType);

    }

    /**
     * Method to show Local game screen
     */
    public void showLocalGameScreen(){
        if (!centerScreen.getChildren().contains(games)) {
            centerScreen.getChildren().add(games);
        }
        games.setVisible(true);
        if (!games.getChildren().contains(centerGameLocal)) {
            games.getChildren().add(centerGameLocal);
        }
        centerGameLocal.setVisible(true);
    }

    /**
     * Method to show Local game screen
     */
    public void showOnlineGameScreen(){
        if (!centerScreen.getChildren().contains(games)) {
            centerScreen.getChildren().add(games);
        }
        games.setVisible(true);
        if (!games.getChildren().contains(centerGameOnline)) {
            games.getChildren().add(centerGameOnline);
        }
        centerGameOnline.setVisible(true);
        if (!games.getChildren().contains(playerList)) {
            games.getChildren().add(playerList);
        }
        playerList.setVisible(true);
    }

    /**
     * Methode om een nieuwe Game te starten
     * Methode wordt gestart als gebruiker op Play New Game button drukt
     */
    @FXML
    public void playNewGame() {
        stateOfTile = new HashMap<>();

        if (inGame) {
            App.server.forfeit(); // forfeit game if inGame
        }

        // BoterKaas en Eieren
        if(gameType.equals(BKE)) {
            App.server.subscribe("Tic-tac-toe", result ->  System.out.println("Subscribed to Tic-tac-toe"));
        }

        // Reversi
        if(gameType.equals(REV)) {
            Thread thread = new Thread(this);
            thread.start();
            thread.setPriority(1);

            thisGame = new Game(gameType, this.user,  new HumanPlayer(App.server.getInputProcesser().opponent));
            App.server.getInputProcesser().setGame(thisGame);

            App.server.subscribe("Reversi", result -> System.out.println(""));
            info.setText("wacht op speler");
        }
    }

    @Override
    public void run() {
        while(!App.server.getInputProcesser().match && !App.server.getInputProcesser().gameOver) {
            // wait for response opponent
            System.out.print("");
        }
        if(!App.server.getInputProcesser().gameOver) {
            stateOfTile = new HashMap<>();
            for(int i = 0; i < 64; i++) {
                stateOfTile.put(""+i, CAPTUREDBYP1);
            }
            info.setText("Speler gevonden");
            Platform.runLater(() -> setUpActiveGameScreen(8, "Reversi", this.user, new HumanPlayer(App.server.getInputProcesser().opponent), stateOfTile));
            Thread thread = new Thread(() -> {
                while(!App.server.getInputProcesser().gameOver) {
                    LinkedList<Integer> moves = App.server.getInputProcesser().getMoves();
                    System.out.print("");
                    if(!(moves.size() == 0)) {
                        // Hier tile zetten
                        String id = ""+moves.getFirst();
                        Pane p = listOfPanes.get(id);

//                        ImageView thisView = thisGame.setPieceOnBoard( (ImageView) p.getChildren().get(0) );

                        Platform.runLater(() -> {
//                            p.getChildren().add(thisView);
                            thisGame.copyList();
                        });

                        String color;
                        if(thisGame.getCurrentPlayer().getName().equals(App.server.getInputProcesser().black)) {
                            color = "zwart";
                        } else {
                            color = "wit";
                        }
                        info.setText("De beurt is aan: "+thisGame.getCurrentPlayer().getName()+" : "+color);
                        App.server.getInputProcesser().removeFirstMove();
                    }
                }
                System.out.println("Winner zetten: ");
                thisGame.setWinner();
                if(thisGame.getWinner().getName().equals(this.user.getName())) {
                    showMessage(info, 2, this.user.getName()+" heeft gewonnen");
                }
                else if(!thisGame.getWinner().getName().equals(this.user.getName())) {
                    showMessage(info, 1, thisGame.getWinner().getName()+" heeft gewonnen");
                } else {
                    showMessage(info, 3, "Het is gelijkspel!");
                }
            });
            thisGame.getPlayer2().setName(App.server.getInputProcesser().opponent);
            thread.start();
        }
    }

    /**
     * Methode om een actief Gamescherm weer te geven
     * @param boardSize de groote van het bord (tic tac toe is 3 x 3, size = 3)
     * @param gameType naam van de game / gameType
     * @param player1 Player die begint als speler 1
     * @param player2 Player die begint als speler 2
     */
    public void setUpActiveGameScreen(int boardSize, String gameType, Player player1, Player player2, HashMap<String, Integer> states) {
        // verwijder het centerScreen van het mainPane
        mainPane.getChildren().remove(centerScreen);

        // stel het gameBoard in als Center op het mainPane
        mainPane.setCenter(gameBoard);
        gameBoard.setVisible(true);

        // maak een game met Type, bord en players
        if (thisGame == null) {
            thisGame = new Game(gameType, player1, player2);
            gameBoard.getChildren().add(gameTiles);
            gameTiles.setVisible(true);
        }

        Player p1 = thisGame.getPlayer1();
        Player p2 = thisGame.getPlayer2();

        // Set title of GameScreen
        setTitleOfGameScreen(gameType + "\t" + p1.getName() + " VS " + p2.getName());

        // naam van de huidige speler en de turn van de player
        if (gameType.equals(Game.BKE)) {
            info.setText(thisGame.getCurrentPlayer().getName() + " " + thisGame.getTurn() + " is aan zet");
        } else if (gameType.equals(Game.REV)) {
            // Speler 1 speelt met zwart
            if (thisGame.getCurrentPlayer().equals(p1)) {
                info.setText(thisGame.getCurrentPlayer().getName() + " zwart is aan zet");
            } else { // speler 2 speelt met wit
                info.setText(thisGame.getCurrentPlayer().getName() + " wit is aan zet");
            }
        }

        // plaats voor Debug
        if (DEBUG) { gameTiles.setGridLinesVisible(true); }

        // maak leeg bord
        Pane[][] gameBoardUI = thisGame.getBoardUI().getGameBoardUI();
        int x = 0;
        int y = 0;

        listOfPanes = new HashMap<>();
        for (Pane[] pane : gameBoardUI) {
            for (Pane p : pane) {
                boardTile = new ImageView(
                        thisGame.getBoardUI().getEmptyTile(gameType, p.getId())
                );

                // sla op in Array om status bij te houden
                if (p.getId().equals("35") || p.getId().equals("28")) {
                    // player 1
                    stateOfTile.put(p.getId(), CAPTUREDBYP1);
                } else if (p.getId().equals("27") || p.getId().equals("36")) {
                    // player 2
                    stateOfTile.put(p.getId(), CAPTUREDBYP2);
                } else {
                    stateOfTile.put(p.getId(), EMPTY);
                }

                // voeg Tile toe aan Pane
                p.getChildren().add(boardTile);

                listOfPanes.put(p.getId(), p);
                // voeg Pane toe aan GridPane
                gameTiles.add(p, x, y);
                x++;

                if (x % thisGame.getBoardUI().getHeight() == 0) {
                    y++;
                    x = 0;
                }

                // voeg functionaliteit toe aan Pane
                p.setOnMouseClicked(e -> {
                        userClickedTile(e, p, thisGame, p1);

                });
            }
        }

//        for (String id : listOfPanes.keySet()) {
//            System.out.println( id );
//        }
//        for (Pane id : listOfPanes.values()) {
//            System.out.println( id );
//        }
    }

    /**
     * Methode om de javaFx Text titel van de UI aan te passen
     * @param titleOfGameScreen de titel
     */
    public void setTitleOfGameScreen(String titleOfGameScreen){
        title.setText(titleOfGameScreen);
    }

    /**
     * Methode om Lokaal tegen een AI te spelen
     */
    @FXML
    public void playLocalvsAI() {
        // lijst om Tile status bij te houden
        stateOfTile = new HashMap<>();

        // Boter kaas en Eieren
        if(gameType.equals(BKE)) {
            //TODO AI implementeren en spel implementeren
            setUpActiveGameScreen(3, "Boter, Kaas en Eieren", user, ai, stateOfTile);
        }

        // Reversi
        if(gameType.equals(REV)) {
            //TODO AI implementeren en spel implementeren
            setUpActiveGameScreen(8, "Reversi", user, ai, stateOfTile);
        }
    }

    /**
     * Methode om Lokaal tegen een andere Lokale speler te spelen
     */
    @FXML
    public void playLocalvsLocal() {
        // lijst om Tile status bij te houden
        stateOfTile = new HashMap<>();

        // Boter kaas en Eieren
        if(gameType.equals(BKE)) {
            //TODO spel implementeren
            setUpActiveGameScreen(3, "Boter, Kaas en Eieren", user, player2, stateOfTile);
        }

        // Reversi
        if(gameType.equals(REV)) {
            //TODO AI spel implementeren
            setUpActiveGameScreen(8, "Reversi", user, player2, stateOfTile);
        }
    }

    /**
     * Functionaliteit voor als een Zet wordt gedaan
     * @param e mouseEvent, de ImageView waarop geklikt is
     * @param p pane waar de functionaliteit op moet toegepast worden
     * @param thisGame huidige game
     */
    public void userClickedTile(MouseEvent e, Pane p, Game thisGame, Player p1) {
        ImageView view = (ImageView) e.getTarget();
        System.out.println();

        // de huidige status van de Tile
        int status = stateOfTile.get(p.getId());

        // Speler die op dit moment aan de beurt is
        Player current = thisGame.getCurrentPlayer();

        // functionaliteiten afhankelijk van de status van de Tile
        switch (status) {
            case EMPTY: // Tile is Leeg
                // Speler 1
                if (current.equals(thisGame.getPlayer1())) {
                    // doe Zet
                    ImageView thisView = thisGame.setPieceOnBoard(view);
                    // voeg zet toe aan Pane
                    p.getChildren().add(thisView);
                    // stel status in op Captured by player 1
                    thisGame.addPointToPlayer(thisGame.getPlayer1());
                    status = CAPTUREDBYP1;

                    // Speler 2
                } else {
                    // doe Zet
                    ImageView thisView = thisGame.setPieceOnBoard(view);
                    // voeg zet toe aan Pane
                    p.getChildren().add(thisView);
                    // stel status in op Captured by player 2
                    thisGame.addPointToPlayer(thisGame.getPlayer2());
                    status = CAPTUREDBYP2;
                }
                // pas State of File in Array aan
                stateOfTile.put(p.getId(), status);

                //Aantal beurten omhoog
                thisGame.incrementTurns();
                // wissel van Beurt
                thisGame.changeTurn();
                break;

            // Tile is niet leeg
            case CAPTUREDBYP1:
            case CAPTUREDBYP2:
                // toon bericht aan gebruiker
                showMessage(gameLog, 1, "Vak is al bezet, kies een ander Vak");
                if (DEBUG) { System.out.println("DEBUG Tile already captured"); }
        }

        // TODO hou rekening met dat een gebruiker twee keer aan de beurt kan zijn

        // verander info voor huidige beurt
        if (gameType.equals(BKE)) {
            info.setText(thisGame.getCurrentPlayer().getName() + " " + thisGame.getTurn() + " is aan zet");
        } else if (gameType.equals(REV)) {
            // Speler 1 speelt met zwart
            if (thisGame.getCurrentPlayer().equals(p1)) {
                info.setText(thisGame.getCurrentPlayer().getName() + " zwart is aan zet");
            } else { // speler 2 speelt met wit
                info.setText(thisGame.getCurrentPlayer().getName() + " wit is aan zet");
            }
        }
    }

    /**
     * Methode om uitleg van een spel te tonen op het scherm
     */
    @FXML
    public void uitleg() {
        // TODO methode implementeren
        // textbox voor uitleg spel
        if (gameType.equals(BKE)) { // in gameType BKE
            // todo hulp bke
        } else if (gameType.equals(REV)) { // in gameType REV
            // todo hulp rev
        } else { // algemeen
            // todo hulp algemeen
        }
    }

    /**
     * Methode om een challenge te accepteren
     */
    @FXML
    public void acceptChallenge() {
        //TODO REVERSIGAME START OR TICTACTOEGAME START

        // Reversi
        if(gameType.equals(REV)) {
            App.server.acceptChallenge(event ->
                showMessage(challengeMessage, 1, "Je hebt nog geen open uitdagingen staan"));

        // Boter Kaas en Eieren
        } else if (gameType.equals(BKE)) {
            // todo
            //App.server.acceptChallenge();
        } else {
            showMessage(challengeMessage, 1, "Geen spel geselecteerd");
        }
    }

    /**
     * Methode om een speler uit te nodigen
     */
    @FXML
    public void challengePlayer() {
        if (!enemyUserName.getText().isEmpty() || !enemyUserName.getText().isBlank()) {
            String enemyPlayer = enemyUserName.getText();
            String gameName = title.getText();
            App.server.challengePlayer(enemyPlayer, gameName, result -> { 
                if (result.contains("SVR GAME CHALLENGE CANCELLED")) {
                    showMessage(challengeMessage, 1, "De huidige uitdaging is geannuleerd");
                } else if (result.contains("SVR GAME CHALLENGE")) { //TODO bericht als je uitgedaagd bent (LISTVIEW)
                    showMessage(challengeMessage, 0, "U bent uitgedaagd!");
                } else {
                switch (result) {
                    case "OK":
                        showMessage(challengeMessage, 0, ("Je hebt deze speler uitgedaagd: " + enemyPlayer));
                        break;
                    case "ERR player not found":
                        showMessage(challengeMessage, 1, ("Speler " + enemyPlayer + " niet gevonden."));
                        break;
                    default:
                        showMessage(challengeMessage, 1, "Er is iets fout gegaan, probeer het opnieuw.");
                        break;
                    }
                }
            }); 
        } else {
            showMessage(challengeMessage, 1, "Spelernaam kan niet leeg zijn.");
        }
    }
    
    @FXML
    public void getPlayerList(ActionEvent actionEvent) {
        App.server.getPlayerList( result -> {
            String[] arr = result.replace("[", "").replace("]", "").replace("\"", "").replace("SVR PLAYERLIST ", "").split(", ");
            if (result.contains("PLAYERLIST")) {
                toAdd = "";
                for (String player : arr) {
                    toAdd += (player + "\n");
                }
                Platform.runLater(() -> playerListList.setText(toAdd));
            }
        });
    }

    /**
     * Method to show succes / error messages to javaFx Text and terminal for Debug
     * @param textBox Text javafx field to place the message
     * @param type 0 = normal | 1 = error | 2 = succes | 3 = gameDraw
     * @param msg message to show
     */
    @FXML
    public void showMessage(Text textBox, int type, String msg) {
        if ( type == 0 ){
            textBox.setStyle("-fx-fill: BLACK;");
        } else if (type == 1) {
            textBox.setStyle("-fx-fill: RED;");
            if (DEBUG){ System.out.println("DEBUG ERROR " + msg); }
        } else if (type == 2) {
            textBox.setStyle("-fx-fill: GREEN;");
            if (DEBUG){ System.out.println("DEBUG SUCCESS " + msg); }
        } else if (type == 3) {
            textBox.setStyle("-fx-fill: YELLOW;");
            if (DEBUG){ System.out.println("DEBUG SUCCESS " + msg); }
        }
        textBox.setText(msg);
    }

    @FXML
    public void changeTurnTime() {
        try {
            int newTurnTime = Integer.parseInt(turnTime.getText());
        } catch (Exception e) { showMessage(challengeMessage, 1, "Vul alstublieft alleen een getal in"); }
        //TODO iets.changeTurnTime(newTurnTime);
        //showMessage(challengeMessage, 1, ("Beurttijd is veranderd naar: " + newTurnTime));
    }

}
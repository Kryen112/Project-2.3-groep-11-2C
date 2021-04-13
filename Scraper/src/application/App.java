package application;

import application.games.Board;
import application.serverconnect.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class voor project 2.3 Game Framework
 *
 * application.App zorgt voor de primaryStage, Start.application.fxml dient als root van de application.App
 *
 * @author Anouk, Stefan
 */
public class App extends Application {
    /** The primary stage */
    public Stage appPrimaryStage;

    /** The scene */
    public static Scene homeScene;

    /** The UI width */
    public static final int UIWIDTH = 900;

    /** The UI height */
    public static final int UIHEIGHT = 630;

    /** The name of the game */
    public static final String GAMENAME = "AI Gaming";

    /** The server */
    public static Server server;

    /** The board */
    public static Board board;

    /**
     * Constructor
     */
    public App() {
        //TODO board maken bij aanroep spel
//        board = new Board(8);
//        board.execute();
    }

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Method to make connection with the server
     */
    public static void makeConnectionWithServer() {
        Connection connection = new Connection();
        server = connection.getServer();
    }

    public static void makeConnectionWithServer(String ip, String port) {
        Connection connection = new Connection(ip, port);
        server = connection.getServer();
    }

    /**
     * Method to start the GUI application
     * @param primaryStage the stage to set
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../application/fxml/start.fxml"));
        appPrimaryStage = primaryStage;

        //Set close conditions
        appPrimaryStage.setOnCloseRequest(event -> {
            try {
                Input.closeApp();
                if (server.isLoggedIn()) {
                    server.logout();
                }
            } catch (Exception e) { }
        });
        setPrimaryStageUI(primaryStage, root, GAMENAME, UIWIDTH, UIHEIGHT);
    }


    /**
     * Method to set the primary stage for the UI
     * @param primaryStage stage to use
     * @param root parent to use
     * @param title title of the ui
     * @param uiWidth the width of the ui
     * @param uiHeight the height of the ui
     */
    public static void setPrimaryStageUI(Stage primaryStage, Parent root, String title, int uiWidth, int uiHeight){
        primaryStage.setTitle(title);
        homeScene = new Scene(root, uiWidth, uiHeight);
        primaryStage.setScene(homeScene);
        primaryStage.setResizable(true);    // stage is not resizable
        primaryStage.show();
    }
}


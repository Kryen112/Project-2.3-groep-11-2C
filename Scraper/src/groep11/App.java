package groep11;

import groep11.controllers.GameScreen;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class voor project 2.3 Game Framework
 *
 * App zorgt voor de primaryStage, Start.fxml dient als root van de App
 *
 * @author Anouk
 */
public class App extends Application {
    public static Stage appPrimaryStage;
    public static Scene homeScene;
    public static final int UIWIDTH = 900;
    public static final int UIHEIGHT = 630;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/start.fxml"));
        appPrimaryStage = primaryStage;
        setPrimaryStageUI(primaryStage, root, "AI Gaming", UIWIDTH, UIHEIGHT);
    }

    public static void main(String[] args) {
        launch(args);
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

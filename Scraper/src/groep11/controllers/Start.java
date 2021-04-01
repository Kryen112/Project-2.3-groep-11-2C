package groep11.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;

/**
 * Class Starts handles the Logic behind the FXML Start page
 * This class includes buttons that change the mainPane BorderPane
 *
 * @author Anouk,
 */
public class Start {
    @FXML private BorderPane mainPane;

    @FXML protected Button home;
    @FXML protected Button login;
    @FXML protected Button profile;
    @FXML protected Button instellingen;
    @FXML protected Button help;
    @FXML protected Button over;


    /**
     * Method to handle the games button
     * @param event
     */
    @FXML
    protected void handleHomeButton(ActionEvent event) {
        String pathToHome = "../fxml/gameScreen.fxml";

        // display game screen / home pane
        goToPaneView(pathToHome);
    }

    /**
     * Method handles Login of user
     * @param event
     */
    @FXML
    protected void handleLoginButton(ActionEvent event) {
        String pathToLogin = "../fxml/login.fxml";

        // print for testing
//        System.out.println("Login button clicked");

        // go to login pane view
        goToPaneView(pathToLogin);
    }

    /**
     * Method to handle Profile of user
     * @param event
     */
    @FXML
    protected void handleProfileButton(ActionEvent event) {
        // TODO add profilepath / fxml file / controllerfile
        // TODO user niet ingelogd / user wel ingelogd check
        String pathToProfile = "";

        // print for testing
        System.out.println("Profile button clicked");

        // go to profile pane view
        goToPaneView(pathToProfile);
    }

    /**
     * Method handles Settings of Application
     * @param event
     */
    @FXML
    protected void handleSettingsButton(ActionEvent event)  {
        String pathToSettings = "../fxml/settings.fxml";

        // printing for testing
//        System.out.println("Settings button clicked");

        // go to settings pane view
        goToPaneView(pathToSettings);
    }

    /**
     * Method handles HELP functions
     * @param event
     */
    @FXML
    protected void handleHelpButton(ActionEvent event) {
        String pathToHelp = "../fxml/help.fxml";

        // printing for testing
//        System.out.println("Help button clicked");

        // gp to help pane view
        goToPaneView(pathToHelp);

        // TODO implementeer help functies
    }

    /**
     * Methode die informatie over ons (AI) toont
     * @param event
     */
    @FXML
    protected void handleAboutUsButton(ActionEvent event)  {
        String pathToAboutUs = "../fxml/aboutUs.fxml";

        // printing for testing
//        System.out.println("About us button clicked");

        // go to About us pane view
        goToPaneView(pathToAboutUs);
    }

    /**
     * Method that handles the change of the BorderPane center section
     * @param pathToFile
     */
    public void goToPaneView(String pathToFile) {
        Loader l = new Loader();
        Pane view = l.getPageView(pathToFile);
        mainPane.setCenter(view);
    }
}

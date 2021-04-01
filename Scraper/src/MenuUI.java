import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MenuUI extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        //Label om tekst te laten zien
        BoterKaasEnEierenUI boterKaasEnEierenUI = new BoterKaasEnEierenUI();
        OthelloUI othelloUI = new OthelloUI();
        OverOnsUI overOnsUI = new OverOnsUI();
        InstellingenUI instellingenUI = new InstellingenUI();

        //Add TicTacToe button
        Button boterKaasEierenButton = new Button("Boter kaas en eieren");
        boterKaasEierenButton.setOnAction(event -> 
            boterKaasEnEierenUI.start(stage)
        );

        //Add Othello button
        Button othelloButton = new Button("Othello");
        othelloButton.setOnAction(event -> 
            othelloUI.start(stage)
        );

        //Add about us button
        Button overOnsButton = new Button("Over ons");
        overOnsButton.setOnAction(event ->
            overOnsUI.start(stage)
        );

        //Add settings button
        Button instellingenButton = new Button("Instellingen");
        instellingenButton.setOnAction(event -> 
            instellingenUI.start(stage)
        );

        //Creating a Grid Pane 
        GridPane gridPane = new GridPane();    

        gridPane.setId("pane");
        
        //Setting size for the pane  
        gridPane.setMinSize(1280, 720); 
        
        //Setting the padding  
        gridPane.setPadding(new Insets(10, 10, 10, 10)); 
        
        //Setting the vertical and horizontal gaps between the columns 
        gridPane.setVgap(5); 
        gridPane.setHgap(5);       
        
        //Zetten waar het gridPane moet staan
        gridPane.setAlignment(Pos.CENTER); 
        //gridPane.setGridLinesVisible(true);
        
        //Alle nodes in de gridPane zetten
        gridPane.add(boterKaasEierenButton, 5, 0);
        gridPane.add(othelloButton, 0, 0);
        gridPane.add(instellingenButton, 0, 80);
        gridPane.add(overOnsButton, 5, 80); 

        BorderPane borderPane = new BorderPane();
        borderPane.setId("topPane");
        Label welkomTekst = new Label("Welk spel wil je spelen?");
        borderPane.setTop(welkomTekst);
        borderPane.setAlignment(welkomTekst, Pos.CENTER);
        borderPane.setCenter(gridPane);
        
        Scene scene = new Scene(borderPane, 1280, 720);
        scene.getStylesheets().addAll(this.getClass().getResource("Menus.css").toExternalForm());
        stage.setTitle("Game menu");
        stage.setScene(scene);
        stage.show();
    }
}

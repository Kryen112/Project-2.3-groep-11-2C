import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
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

        //Label voor game text
        Label gameText = new Label("Welk spel wil je spelen?");

        //Creating a Grid Pane 
        GridPane gridPane = new GridPane();    
        
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
        gridPane.add(gameText, 1, 0);
        gridPane.add(boterKaasEierenButton, 2, 1);
        gridPane.add(othelloButton, 0, 1);
        gridPane.add(instellingenButton, 0, 10);
        gridPane.add(overOnsButton, 2, 10); 

        gridPane.getColumnConstraints().add(new ColumnConstraints(100)); //Kolom 0
        gridPane.getColumnConstraints().add(new ColumnConstraints(150)); //Kolom 1
        gridPane.getColumnConstraints().add(new ColumnConstraints(150)); //Kolom 2

        Scene scene = new Scene(gridPane, 1280, 720);
        stage.setTitle("Game menu");
        stage.setScene(scene);
        stage.show();
        
    }
}

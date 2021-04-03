package groep11;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class BoterKaasEnEierenUI extends Application {
    @Override
    public void start(Stage stage) {
        MenuUI menuUI = new MenuUI();
        BoterKaasEnEierenUitlegUI boterKaasEnEierenUitlegUI = new BoterKaasEnEierenUitlegUI();
        TicTacToeLocal ticTacToeLocal = new TicTacToeLocal();
        TicTacToeAI ticTacToeAI = new TicTacToeAI();

        Button tegenComputerButton = new Button("Speel tegen een computer");
        tegenComputerButton.setOnAction(event -> 
            ticTacToeAI.start(stage)
        );
        
        Button lokaalSpelButton = new Button("Speel lokaal tegen elkaar");
        lokaalSpelButton.setOnAction(event -> 
            ticTacToeLocal.start(stage)
        );

        Button onlineSpelButton = new Button("Speel online tegen andere spelers");
        onlineSpelButton.setOnAction(event -> 
            menuUI.start(stage) //TODO
        );

        Button terugButton = new Button("Terug naar het hoofdmenu");
        terugButton.setOnAction(event -> 
            menuUI.start(stage)
        );

        Button infoButton = new Button("Spelregels Boter kaas en eieren");
        infoButton.setOnAction(event -> 
            boterKaasEnEierenUitlegUI.start(stage)
        );

        GridPane gridPane = new GridPane();    
        
        gridPane.setId("pane");

        //Setting size for the pane  
        gridPane.setMinSize(1280, 720); 
        
        //Setting the padding  
        gridPane.setPadding(new Insets(10, 10, 10, 10)); 
        
        //Setting the vertical and horizontal gaps between the columns 
        gridPane.setVgap(5); 
        gridPane.setHgap(5);       
        
        //Setting the Grid alignment 
        gridPane.setAlignment(Pos.CENTER); 
        //gridPane.setGridLinesVisible(true);
        
        //Arranging all the nodes in the grid 
        gridPane.add(tegenComputerButton, 0, 0);
        gridPane.add(lokaalSpelButton, 5, 0);
        gridPane.add(onlineSpelButton, 10, 0);
        gridPane.add(terugButton, 0, 80);
        gridPane.add(infoButton, 10, 80);

        BorderPane borderPane = new BorderPane();
        borderPane.setId("topPane");

        Label welkomTekst = new Label("Welkom bij het keuzemenu van het spel Boter kaas en eieren");
        borderPane.setTop(welkomTekst);
        borderPane.setAlignment(welkomTekst, Pos.CENTER);
        borderPane.setCenter(gridPane);

        Scene scene = new Scene(borderPane, 1280, 720);
        scene.getStylesheets().addAll(this.getClass().getResource("groep11/Menus.css").toExternalForm());
        stage.setTitle("Boter kaas en eieren");
        stage.setScene(scene);
        stage.show();
        
    }
}

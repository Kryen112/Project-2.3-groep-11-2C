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

public class OverOnsUI extends Application {
    @Override
    public void start(Stage stage) {
        MenuUI menuUI = new MenuUI();

        Button terugButton = new Button("Terug naar het hoofdmenu");
        terugButton.setOnAction(event -> 
            menuUI.start(stage)
        );

        GridPane gridPane = new GridPane();    
        
        gridPane.setId("pane");

        //Setting size for the pane  
        gridPane.setMinSize(1280, 720); 
        
        //Setting the padding  
        gridPane.setPadding(new Insets(10, 10, 10, 10)); 
        
        //Setting the vertical and horizontal gaps between the columns 
        gridPane.setVgap(0); 
        gridPane.setHgap(0);       
        
        //Setting the Grid alignment 
        gridPane.setAlignment(Pos.CENTER); 
        //gridPane.setGridLinesVisible(true);
        
        //Arranging all the nodes in the grid 
        gridPane.add(terugButton, 0, 1);

        BorderPane borderPane = new BorderPane();
        borderPane.setId("topPane");

        Label welkomTekst = new Label("Over ons");
        borderPane.setTop(welkomTekst);
        borderPane.setAlignment(welkomTekst, Pos.CENTER);
        borderPane.setCenter(gridPane);

        Scene scene = new Scene(borderPane, 1280, 720);
        scene.getStylesheets().addAll(this.getClass().getResource("groep11/Menus.css").toExternalForm());
        stage.setTitle("Over ons");
        stage.setScene(scene);
        stage.show();
        
    }
}

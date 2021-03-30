import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class InstellingenUI extends Application {
    @Override
    public void start(Stage stage) {
        MenuUI menuUI = new MenuUI();

        Button terugButton = new Button("Terug naar het hoofdmenu");
        terugButton.setOnAction(event -> 
            menuUI.start(stage)
        );

        GridPane gridPane = new GridPane();    
        
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
        gridPane.add(new Label("We zijn in het instellingen scherm"), 1, 0);
        gridPane.add(terugButton, 0, 1);

        Scene scene = new Scene(gridPane, 1280, 720);
        stage.setTitle("Instellingen");
        stage.setScene(scene);
        stage.show();
        
    }
}

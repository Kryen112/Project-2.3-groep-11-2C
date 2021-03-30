import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BoterKaasEnEierenUitlegUI extends Application {
    @Override
    public void start(Stage stage) {
        BoterKaasEnEierenUI boterKaasEnEierenUI = new BoterKaasEnEierenUI();

        Button terugButton = new Button("Terug");
        terugButton.setOnAction(event -> 
            boterKaasEnEierenUI.start(stage)
        );

        Label uitlegText = new Label("Boter kaas en eieren is een spel dat je kan spelen met 2 spelers, of alleen tegen een computer.");
        uitlegText.setWrapText(true);
        uitlegText.setMaxWidth(1000);

        VBox vBox = new VBox();

        vBox.setId("pane");

        vBox.setMinSize(1280, 720); 
        
        //Setting the padding  
        vBox.setPadding(new Insets(10, 10, 10, 10)); 
        
        vBox.getChildren().add(uitlegText);
        vBox.getChildren().add(terugButton);

        BorderPane borderPane = new BorderPane();
        borderPane.setId("topPane");

        Label welkomTekst = new Label("Speluitleg boter kaas en eieren");
        borderPane.setTop(welkomTekst);
        borderPane.setAlignment(welkomTekst, Pos.CENTER);
        borderPane.setLeft(vBox);

        Scene scene = new Scene(borderPane, 1280, 720);
        scene.getStylesheets().addAll(this.getClass().getResource("Menus.css").toExternalForm());
        stage.setTitle("Speluitleg Boter kaas en eieren");
        stage.setScene(scene);
        stage.show();
    }
}

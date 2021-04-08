package application.fxml;

import application.fxml.Start;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import java.net.URL;

public class Loader {
    private Pane pageView;

    public Pane getPageView(String pathToFile) {

        try {
            URL fileURL = Start.class.getResource(pathToFile);
            // testing
            // System.out.println("" + fileURL);

            if (fileURL != null) {
                new FXMLLoader();
                pageView = FXMLLoader.load(fileURL);
            } else {
                throw new java.io.FileNotFoundException("file at " + fileURL + " cannot be found");
            }


        } catch (Exception e) {
            System.out.println("" + pathToFile + " not found");
        }

        return pageView;
    }
}

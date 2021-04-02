package groep11.controllers;

import com.sun.prism.Image;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameScreen {
    private List<String> images = new ArrayList<>();
    private int numberOfGameTypes;
    @FXML Text listOfGames;

    /**
     * Method to handle the gameScreen setup
     */
    public void setUpGameScreen(ActionEvent event) {
        // find all images in directory
        String[] imageNames = getAllGamesTypesFromDirectoryPath(new File(
                "C:\\Users\\smythe\\Documents\\GitHub\\Project-2.3-groep-11-2C\\Scraper\\src\\groep11\\images\\gametypes"));
        Collections.addAll(images, imageNames);

        System.out.println(images); // testing

        for ( String name : images) {

        }
    }

    /**
     * Method to find all game images in directory
     * These images equal the amount of game types in AI gaming
     * @param directoryPath the dir where the game type images are located
     */
    public String[] getAllGamesTypesFromDirectoryPath(File directoryPath) {
        // testing
        System.out.println(directoryPath.getAbsolutePath());
        File[] contentOfDir = directoryPath.listFiles();
        int size;

        // set size for Array
        if (contentOfDir == null) { size = 0; } else { size = contentOfDir.length; }

        String[] imagesInFolder = new String[size]; // create empty array to store image/file names

        if (contentOfDir != null) { // check if content not null
            int count = 0;          // start counting at 1

            // for each file in the directory
            for (File f: contentOfDir) {
                imagesInFolder[count] = f.getName();

                // testing
                int number = count+1;
                count++;
                System.out.println("" + number + " " + f.getAbsolutePath() + " " + f.getName());
            }
            setNumberOfGameTypes(count);
            System.out.println(getNumberOfGameTypes() + " files in directory");
        }
        return imagesInFolder;
    }

    /**
     * Method to set the number of game types
     * @param numberOfGameTypes
     */
    public void setNumberOfGameTypes(int numberOfGameTypes) {
        this.numberOfGameTypes = numberOfGameTypes;
    }

    /**
     * Method to get the number of game types
     * @return
     */
    public int getNumberOfGameTypes() {
        return numberOfGameTypes;
    }

    public void setUpBoterKaasEieren(ActionEvent actionEvent) {
    }

    public void setUpOthello(ActionEvent actionEvent) {
    }
}

package app;

import serverconnection.Connection;
import serverconnection.Server;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Main class voor project 2.3 Game Framework
 *
 * App zorgt voor de primaryStage, Start.fxml dient als root van de App
 *
 * @author Anouk
 */
public class App extends Application implements Runnable{
    public static final int PORT = 7789;
    public static final String IPSCHOOL = "145.33.225.170";
    public static Stage appPrimaryStage;

    /** The scene */
    public static Scene homeScene;

    /** The UI width */
    public static final int UIWIDTH = 900;

    /** The UI height */
    public static final int UIHEIGHT = 630;

    /** The name of the game */
    public static final String GAMENAME = "AI Gaming";

    /** The server */
    public Server server;

    /**
     * The constructor
     */
    public App() {
        Connection connection = new Connection();
        server = connection.getServer();
        server.help();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/start.fxml"));
        appPrimaryStage = primaryStage;
        setPrimaryStageUI(primaryStage, root, GAMENAME, UIWIDTH, UIHEIGHT);
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

    @Override
    public synchronized void run() {
        while (true) {
            try {
//            InetAddress ip = InetAddress.getByName("localhost");
                InetAddress ip = InetAddress.getByName(IPSCHOOL);
//            System.out.println(ip.getHostAddress());
                Socket s = new Socket(ip.getHostAddress(), PORT);

//            DataOutputStream output = new DataOutputStream(s.getOutputStream());
//            DataInputStream input = new DataInputStream(new BufferedInputStream());
                PrintWriter out = new PrintWriter(s.getOutputStream(), true);
                BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
                StringBuilder sBuilder = new StringBuilder();

                System.out.println("starting");

//            String userInput;
//            while ((userInput = stdIn.readLine()) != null) {
//                out.println(userInput);
//                System.out.println("Response:" + input.readLine());
//            }
                while (input.readLine() != null) {
//                    for (int i = 0; i < 2; i++) {
                        System.out.println("read line: " + input.readLine());
                        if(stdIn.ready()) {
                            out.println(stdIn.readLine());
                        }
//                    String userinput;

//                    if( (stdIn.readLine()) != null) {
//                        System.out.println("sending: " + stdIn.readLine());
//                        out.println(userinput);
//                    }
                }

                System.out.println("connection closed");
                s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String readAllLines(BufferedReader reader) throws IOException {
        StringBuilder content = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            content.append(line);
            content.append(System.lineSeparator());
        }

        return content.toString();
    }
}


package application.serverconnect;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * The Input class
 * Sets a socket up with the server and reads all the input from the server
 */
public class Input implements Runnable {
    /** The socket */
    protected final Socket socket;

    /** The inputProcessor */
    protected final InputProcesser inputProcesser;

    /** The server */
    protected final Server server;

    /** Boolean to check if the app is still on */
    protected static boolean connected;

    /**
     * Constructor
     * @param socket            The socket to connect to
     * @param inputProcessor    The inputprocessor to set
     * @param server            The server to connect to
     */
    public Input(Socket socket, InputProcesser inputProcessor, Server server) {
        this.socket = socket;
        this.server = server;
        inputProcesser = inputProcessor;
        setConnected();
        Thread thread = new Thread(this);
        thread.start();
    }

    /**
     * The run method for the thread
     */
    @Override
    public void run() {
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            while(connected) { //Read the incoming line and process the input
                String input = bf.readLine();
                System.out.println("DEBUG Answer from server: " + input);
                inputProcesser.processInput(input, server);
            }
        } catch (Exception e) { System.out.print("Something went wrong: "); e.printStackTrace(); }
    }

    /**
     * Set connected to true
     */
    public static void setConnected() {
        connected = true;
    }

    /**
     * Set connected to false
     */
    public static void closeApp() {
        connected=false;
    }
}
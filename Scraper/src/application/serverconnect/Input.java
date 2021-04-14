package application.serverconnect;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Class Input, runs on Thread
 * Sets a socket up with the server and reads all the input from the server
 *
 * @author Douwe
 * Project 2.3 Hanze Hogeschool 2021
 */
public class Input implements Runnable {
    protected final Socket socket;                  // the socket to use
    protected final InputProcesser inputProcesser;  // the inputProcessor to use
    protected final Server server;                  // the server to use

    /** Boolean to check if the app is still on */
    protected static boolean connected;

    /**
     * Constructor for Input
     * sets the socket, server and Inputprocessor
     * sets the socket
     * sets the server
     * sets the inputprocessor
     * sets the connection to True
     * creates a Thread and starts the Thread
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
        // Try to create the BufferedReader
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
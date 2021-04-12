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
    private final Socket socket;

    private final InputProcesser inputProcesser;

    private final Server server;

    public static boolean temp;

    /** The constructor
     * @param socket - The socket
     */
    public Input(Socket socket, InputProcesser inputP, Server s) {
        this.socket = socket;
        server = s;
        temp = true;
        inputProcesser = inputP;
        Thread thread = new Thread(this);
        thread.start();
    }

    public static void closeApp() {
        temp=false;
    }

    /**
     * The run method for the thread
     */
    @Override
    public void run() {
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            while(temp) {
                String input = bf.readLine();
                System.out.println("DEBUG Answer from server: " + input);
                inputProcesser.processInput(input, server);
            }
        } catch (Exception e) { }
    }
}
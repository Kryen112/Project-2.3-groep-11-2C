package application.serverconnect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * The Input class
 * Sets a socket up with the server and reads all the input from the server
 */
public class Input implements Runnable {
    /** The socket */
    private final Socket socket;

    /** The buffered reader */
    private BufferedReader bufferedReader;

    private final InputProcesser inputProcesser;

    private final Server server;

    /** The constructor
     * @param socket - The socket
     */
    public Input(Socket socket, InputProcesser inputP, Server s) {
        this.socket = socket;
        server = s;
        inputProcesser = inputP;
        setBufferedReader();
        Thread thread = new Thread(this);
        thread.start();
    }

    /**
     * Method that sets the buffered reader
     */
    public void setBufferedReader() {
        try {
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch(IOException e) {
            System.out.println("IO exception: "+e);
        }
    }

    /**
     * The run method for the thread
     */
    @Override
    public void run() {
        try {
            while(true) {
                String input = bufferedReader.readLine();
                System.out.println("Answer from server: "+input);
                inputProcesser.processInput(input, server);
            }
        } catch (Exception e) {
            System.out.print("Something went wrong: " + e);
        }
    }


}
package application.serverconnection;

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
    public Input(Socket socket, InputProcesser inputProcesser, Server server) {
        this.socket = socket;
        this.server = server;
        this.inputProcesser = inputProcesser;
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
                if(input.equals("OK")) {
                    this.inputProcesser.setAnswer("OK");
                    System.out.println("Answer from server: "+input);
                    server.setResult(input);
                }
                else if(input.equals("ERR Already logged in")) {
                    this.inputProcesser.setAnswer("ERR");
                    server.setResult(input);
                }
                //System.out.println(input);
            }
        } catch (Exception e) {
            System.out.print("Something went wrong: " + e);
        }
    }
}
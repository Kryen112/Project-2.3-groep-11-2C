package application.serverconnect;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.function.Consumer;

/**
 * The Server class
 * This class creates the server and sets up the connection
 */
public class Server {
    /** The socket */
    final Socket socket;

    /** The printWriter */
    private PrintWriter out;

    /** The class that processes the input */
    private InputProcesser inputProcesser;

    /** Consumer String */
    private Consumer<String> callback;

    /**
     * The constructor
     * @param socket - The socket
     */
    public Server(Socket socket, InputProcesser inputP) {
        this.socket = socket;
        inputProcesser = inputP;
        setPrintWriter();
    }

    /**
     * This method sets the printWriter
     */
    public void setPrintWriter() {
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch(IOException e) {
            System.out.println("IO exception: "+e);
        }
    }

    /**
     * This method sends commands to the server
     * @param command - The command
     */
    public void sendCommand(String command) {
        out.println(command);
    }

    /**
     * This method logs in in the server
     * @param name - The name
     */
    public void login(String name, Consumer<String> callback) {
        String command = "login "+name;
        this.callback = callback;
        processCommand(command);
    }

    /**
     * This method returns a boolean if the command is ok or not
     * @return - boolean
     */
    public boolean isOK() {
        return this.inputProcesser.isOK();
    }

    /**
     * This method gets the playerlist from the server
     */
    public void getPlayerList() {
        out.println("get playerlist");
    }

    /**
     * This method processes the command
     */
    public void processCommand(String command) {
        out.println(command);
    }

    /**
     * This method sets the result
     * @param result - The result
     */
    public void setResult(String result) {
        callback.accept(result);
    }
}


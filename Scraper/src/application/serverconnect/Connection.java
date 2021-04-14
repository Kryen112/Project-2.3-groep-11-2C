package application.serverconnect;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Class Connection
 * handles the Server Connection for Game Framework
 *
 * @author Douwe
 * Project 2.3 Hanze Hogeschool 2021
 */
public class Connection {
    /**
     * Hardcoded connection
     */
    protected static final int PORT = 7789;                     // The server port
    protected static final String IPSCHOOL = "145.33.225.170";  // The server ip

    protected Socket socket;    // the socket to use
    protected Server server;    // the server that is used
    protected Input input;      // class to handle the input
    protected final InputProcesser inputProcesser;              // class to process the input

    /**
     * The constructor for the standard connection (School server)
     * creates a new Inputprocessor
     * sets the socket
     * sets the server
     * sets the input
     */
    public Connection() {
        inputProcesser  = new InputProcesser();
        setSocket();
        setServer();
        setInput();
    }

    /**
     * The constructor for other connections
     * creates a new Inputprocessor
     * sets the socket
     * sets the server
     * sets the input
     * @param ip the ipAdress the use
     * @param port the ipAdress the use
     */
    public Connection(String ip, String port) {
        inputProcesser  = new InputProcesser();
        setSocket(ip, port);
        setServer();
        setInput();
    }

    /**
     * Method to set the Input
     * uses the Socket, inputProcessor and Server to set the Input
     */
    public void setInput() {
        input = new Input(socket, inputProcesser, server);
    }

    /**
     * Method to set the Output
     * uses the Socket and InputProcessor to set the Output
     */
    public void setServer() {
        server = new Server(socket, inputProcesser);
    }

    /**
     * Method to set the Socket of the default address+port (School server)
     */
    public void setSocket() {
        try {
            InetAddress ip = InetAddress.getByName(IPSCHOOL);
            socket = new Socket(ip.getHostAddress(), PORT);
        } catch(IOException e) {
            System.out.println("Unknown host exception" + e);
        }
    }

    /**
     * This method sets the Socket of other addresses/ports
     */
    public void setSocket(String ipAddress, String port) {
        try {
            InetAddress ip = InetAddress.getByName(ipAddress);
            socket = new Socket(ip.getHostAddress(), Integer.parseInt(port));
        } catch(IOException e) {
            System.out.println("Unknown host exception" + e);
        }
    }

    /**
     * Getter for the socket
     * @return Socket - The socket
     */
    public Socket getSocket() {
        return this.socket;
    }

    /**
     * Getter for the Output class
     * @return Output - The output class
     */
    public Server getServer() {
        return server;
    }

    /**
     * Getter for the Input class
     * @return Input - The input class
     */
    public Input getInput() {
        return input;
    }
}

package application.serverconnect;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Connection {
    /** The server port */
    protected static final int PORT = 7789;

    /** The server ip */
    protected static final String IPSCHOOL = "145.33.225.170";

    /** The socket */
    protected Socket socket;

    /** The Server class */
    protected Server server;

    /** The Input class */
    protected Input input;

    /** The input processer */
    protected final InputProcesser inputProcesser;

    /**
     * The constructor for the standard connection
     */
    public Connection() {
        inputProcesser  = new InputProcesser();
        setSocket();
        setServer();
        setInput();
    }

    /**
     * The constructor for other connections
     */
    public Connection(String ip, String port) {
        inputProcesser  = new InputProcesser();
        setSocket(ip, port);
        setServer();
        setInput();
    }

    /**
     * This method sets the Input
     */
    public void setInput() {
        input = new Input(socket, inputProcesser, server);
    }

    /**
     * This method sets the Output
     */
    public void setServer() {
        server = new Server(socket, inputProcesser);
    }

    /**
     * This method sets the Socket of the default address+port
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

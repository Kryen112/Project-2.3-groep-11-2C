package application.serverconnection;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Connection {
    /** The server port */
    public static final int PORT = 7789;

    /** The server ip */
    public static final String IPSCHOOL = "145.33.225.170";

    /** The socket */
    private Socket socket;

    /** The Server class */
    private Server server;

    /** The Input class */
    private Input input;

    /** The input processer */
    private final InputProcesser inputProcesser;

    /**
     * The constructor of ServerListener
     */
    public Connection() {
        setSocket();
        this.inputProcesser  = new InputProcesser();
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
     * This method sets the Socket
     */
    public void setSocket() {
        try {
            InetAddress ip = InetAddress.getByName(IPSCHOOL);
            socket = new Socket(ip.getHostAddress(), PORT);
        } catch(IOException e) {
            System.out.println("Uknown host exception" + e);
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
        return this.server;
    }

    /**
     * Getter for the Input class
     * @return Input - The input class
     */
    public Input getInput() {
        return this.input;
    }
}

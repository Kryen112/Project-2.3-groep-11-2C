package application.serverconnect;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.function.Consumer;

/**
 * The Server class
 * This class creates the server and sets up the connection
 * This class also contains all commands to send to the server
 *
 * @author Douwe
 * Project 2.3 Hanze Hogeschool 2021
 */
public class Server {
    protected final Socket socket;              // the socket to use
    protected PrintWriter printWriter;          // the printwriter to use
    protected InputProcesser inputProcesser;    // class that processes the input
    protected Consumer<String> callback;        // Consumer to handle the result
    protected boolean isLoggedIn;               // check if user is LoggedIn

    /**
     * Constructor for server
     * set logged in to false
     * set the socket
     * set the inputProcessor
     * set the printWriter
     * @param socket            The socket to connect to
     * @param inputProcessor    The inputprocessor to set
     */
    public Server(Socket socket, InputProcesser inputProcessor) {
        setLoggedIn(false);
        this.socket = socket;
        setInputProcesser(inputProcessor);
        setPrintWriter();
    }

    /**
     * This method sets the printWriter
     */
    public void setPrintWriter() {
        try {
            this.printWriter = new PrintWriter(socket.getOutputStream(), true);
        } catch(IOException e) {
            System.out.println("IO exception: " + e);
        }
    }

    /**
     * This method processes the command with the printWriter
     * @param command the command
     */
    public void processCommand(String command) {
        printWriter.println(command);
    }

    /**
     * Command to accept the incoming challenge
     * @param callback the consumer that handles the result
     */
    public void acceptChallenge(Consumer<String> callback) {
        setCallback(callback);
        processCommand("challenge accept " + inputProcesser.getChallengeNumber());
    }

    /**
     * Method for the challenge player button
     * set the callback and process the command
     * @param playerToChallenge The player to challenge 
     * @param game              The game to play
     * @param callback          The answer from the server
     */
    public void challengePlayer(String playerToChallenge, String game, Consumer<String> callback) {
        setCallback(callback);
        processCommand("challenge " + playerToChallenge + " " + game);
    }

    /**
     * Method to do a move on a board
     * @param position  The position to place the move
     */
    public void doMove(int position) {
        processCommand("move " + position);
    }

    /**
     * This method logs in in the server
     * @param username      The username to login with
     * @param callback  The answer from the server
     */
    public void login(String username, Consumer<String> callback) {
        setCallback(callback);
        processCommand("login " + username);
    }

    /**
     * Method to send the processCommand quit
     */
    public void quit() {
        processCommand("quit");
    }

    /**
     * Sends the forfeit command to the server
     */
    public void forfeit() {
        processCommand("forfeit");
    }

    /**
     * This method subscribes to a game
     * @param game      The game to subscribe to
     * @param callback  The answer from the server
     */
    public void subscribe(String game, Consumer<String> callback) {
        setCallback(callback);
        processCommand("subscribe " + game);
    }

    /**
     * This method gets the playerlist from the server
     * @param callback  The answer from the server
     */
    public void getPlayerList(Consumer<String> callback) {
        setCallback(callback);
        processCommand("get playerlist");
    }

    /**
     * Sends the logout command to the server
     */
    public void logout() {
        processCommand("logout");
    }

    /**
     * This method sets the result-answer from the server
     * @param result    The result to set
     */
    public void setResult(String result) {
        callback.accept(result);
    }

    /**
     * Method to check if someone is logged in
     * @return Boolean - true if user is logged in
     */
    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    /**
     * The method will set the login boolean
     * @param isLoggedIn    True if the player is logging in
     */
    public void setLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

    /**
     * Method to set the callback message
     * @param callback  The callback to set
     */
    public void setCallback(Consumer<String> callback) {
        this.callback = callback;
    }

    /**
     * Setter for the inputprocessor
     * @param inputProcesser    The inputprocessor to set
     */
    public void setInputProcesser(InputProcesser inputProcesser) {
        this.inputProcesser = inputProcesser;
    }

    /**
     * Getter for the inputprocessor
     * @return the inputprocessor
     */
    public InputProcesser getInputProcesser() {
        return this.inputProcesser;
    }


}


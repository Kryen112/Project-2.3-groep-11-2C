package application.serverconnect;

import application.games.Game;
import java.util.LinkedList;

/**
 * The Server class
 * This class creates the server and sets up the connection
 * This class also contains all commands to send to the server
 *
 * @author Douwe
 * Project 2.3 Hanze Hogeschool 2021
 */
public class InputProcesser {
    public String answer;           // the answer

    /**
     * Messages
     */
    public String[] matchMessage;
    public String[] turnMessage;
    public String[] lossMessage;
    public String[] winMessage;
    public String[] moveMessage;
    public String[] challenger;

    // List to keep track of the moves
    public LinkedList<Integer> moves = new LinkedList<>();

    public int move;            // current move
    public String opponent;     // opponent of Game
    public boolean turn;        // check if it is our turn

    public String winner;       // the Winner
    public String black;        // player who plays black

    public int challengeNumber; // challenge number from incomming challenge

    /**
     * Game checkers
     */
    public boolean match = false;
    public boolean gameOver = false;

    public Game game;           // the class Game that holds the game logic

    /**
     * Constructor for inputProcessor
     */
    public InputProcesser() {
        challengeNumber = 0;
    }

    /**
     * Method to set Start pieces on board for ReversiGame
     */
    public void setStart() {
        if(black.equals(opponent)) {
            game.getReversi().setStartPieces(game.getBoard(), 'x');
        } else {
            game.getReversi().setStartPieces(game.getBoard(), 'o');
        }
    }

    /**
     * Method to get the list of moves
     * @return list of moves
     */
    public LinkedList<Integer> getMoves() {
        return this.moves;
    }

    /**
     * Method to remove First of list moves
     */
    public void removeFirstMove() {
        this.moves.remove(0);
    }

    /**
     * Method to get the challenge number
     * @return number of challenge
     */
    public int getChallengeNumber() {
        return this.challengeNumber;
    }

    /**
     * Method to check if incomming message is OK
     * @return true if answer equals OK
     */
    public Boolean isOK() {
        return answer.equals("OK");
    }

    /**
     * Method to set the answer
     * @param answer
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     * Method to set move
     */
    public void setMove() {
        move = Integer.parseInt(moveMessage[1].replace("MOVE: ", "").replace("\"", ""));
    }

    /**
     * Method to set black
     */
    public void setBlack() {
        black = matchMessage[0].replace("PLAYERTOMOVE: ", "").replace("\"", "");
    }

    /**
     * Method to set Winner
     */
    public void setWinner() {
    }

    /**
     * Method to set the Opponent
     */
    public void setOpponent() {
        opponent = matchMessage[2].replace("OPPONENT: ", "").replace("\"", "");
    }

    /**
     * Method to set and get the messages
     * @param message the message to be set
     * @return array of message
     */
    public String[] setMessages(String message) {
        return message.replace("{", "").replace("}", "").split(", ");
    }

    /**
     * Method to process the input
     * @param input the input
     * @param server the server to use
     */
    public void processInput(String input, Server server) {
        // Challenge cancel message afvangen
        if(input != null) {
            if (input.contains("SVR GAME CHALLENGE CANCELLED") || input.contains("SVR PLAYERLIST")) {
                setAnswer(input);
                server.setResult(input);
            } else {
                switch (input) {
                    case "OK":
                    case "ERR already logged in":
                    case "ERR duplicate name exists":
                    case "ERR not logged in":
                    case "ERR player not found":
                    case "ERR invalid challenge":
                        setAnswer(input);
                        server.setResult(input);
                        break;
                }
            }

            String[] arr = input.split(" ", 4);
            String serverMessage = "";

            if (arr.length >= 3) {
                serverMessage = arr[0] + arr[1] + arr[2];
            }
            // try {
            //     Thread.sleep(500);
            // } catch (Exception e){}

            switch (serverMessage) {
                case "SVRGAMEMATCH":
                    gameOver = false;
                    System.out.println("Game match start!");
                    this.matchMessage = setMessages(arr[3]);
                    setBlack();
                    game.setFirstPlayer(this.black);
                    setOpponent();
                    setStart();
                    System.out.println("The opponent of the game is: " + this.opponent);
                    System.out.println("The player that starts first is: " + this.black);
                    match = true;
                    break;
                case "SVRGAMEYOURTURN":
                    System.out.println("It's your turn!");
                    this.turnMessage = setMessages(arr[3]);

                    //int set = App.board.getRandomSet();
                    //server.doMove(set);

                    try {
                        int move = game.getMm().miniMaxi(game.getBoard(),12,0,0,'o').getLastSet();
                        System.out.println(move);
                        //App.miniMax.miniMaxi(App.board,15,0,0,'o').pirntBoard();
                       server.doMove(move);
                    } catch (CloneNotSupportedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    }

                    turn = true;
                    break;
                case "SVRGAMELOSS":
                    System.out.println("You have lost!");
                    this.lossMessage = setMessages(arr[3]);
                    game.setWinner(game.getPlayer2());
                    gameOver = true;
                    break;
                case "SVRGAMEWIN":
                    System.out.println("You have won!");
                    this.winMessage = setMessages(arr[3]);
                    game.setWinner(game.getPlayer1());
                    gameOver = true;
                    break;
                case "SVRGAMEMOVE":
                    //System.out.print(App.board.getFreeSpacesX());
                    if (turn) {
                        System.out.println(" Move set");
                        this.moveMessage = setMessages(arr[3]);
                        setMove();
                        moves.add(move);
                        System.out.println("Deze zet is gedaan: " + this.move);
                        game.getReversi().setPieceOnBoard(game.getBoard(), this.move, 'o');
                        game.changeTurn();
                        //App.reversi.setPieceOnBoard(App.board, this.move, 'o');
                        turn = false;
                    } else {
                        System.out.println("Move set by opponent");
                        this.moveMessage = setMessages(arr[3]);
                        setMove();
                        moves.add(move);
                        System.out.println("Deze zet is gedaan: " + this.move);
                        game.getReversi().setPieceOnBoard(game.getBoard(), this.move, 'x');
                        game.changeTurn();
                    }// zet move op het bord van diegene die move heeft gezet
                    break;
                case "SVRGAMECHALLENGE":
                    if(input.contains("SVR GAME CHALLENGE CANCELLED")) {
                        System.out.println("Challenge canceled");
                    } else {
                        this.challenger = setMessages(arr[3]);
                        challengeNumber = Integer.parseInt(setMessages(arr[3])[1].replace("CHALLENGENUMBER: ", "").replace("\"", ""));
                        System.out.println("Challenge ontvangen van: " + challenger[0].replace("CHALLENGER: ", "").replace("\"", ""));
                    }
                    break;
                case "SVRGAMEDRAW":
                    gameOver = true;
            }
        } else {
                gameOver = true;
            }
        }

    /**
     * Setter for Game
      * @param game the game that needs to be set
     */
    public void setGame(Game game) {
//        System.out.println("This game is set!!!!");
        this.game = game;
    }
}

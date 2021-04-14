package application.serverconnect;

import application.App;
import application.fxml.Start;
import application.games.Game;
import application.games.players.HumanPlayer;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class InputProcesser {

    public String answer;

    public String[] matchMessage;
    public String[] turnMessage;
    public String[] lossMessage;
    public String[] winMessage;
    public String[] moveMessage;
    public String[] challenger;

    public LinkedList<Integer> moves = new LinkedList<>();

    public int move;
    public String opponent;
    public boolean turn;

    public String winner;
    public String black;

    public int challengeNumber;
    public boolean match = false;
    public boolean gameOver = false;

    public Game game;

    public InputProcesser() {
        challengeNumber = 0;
    }

    public void setStart() {
        if(black.equals(opponent)) {
            game.getReversi().setStartPieces(game.getBoard(), 'x');
        } else {
            game.getReversi().setStartPieces(game.getBoard(), 'o');
        }
    }

    public LinkedList<Integer> getMoves() {
        return this.moves;
    }

    public void removeFirstMove() {
        this.moves.remove(0);
    }

    public int getChallengeNumber() {
        return this.challengeNumber;
    }

    public Boolean isOK() {
        return answer.equals("OK");
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setMove() {
        move = Integer.parseInt(moveMessage[1].replace("MOVE: ", "").replace("\"", ""));
    }

    public void setBlack() {
        black = matchMessage[0].replace("PLAYERTOMOVE: ", "").replace("\"", "");

    }

    public void setWinner() {

    }

    public void setOpponent() {
        opponent = matchMessage[2].replace("OPPONENT: ", "").replace("\"", "");
    }

    public String[] setMessages(String message) {
        return message.replace("{", "").replace("}", "").split(", ");
    }

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

            // TODO challenge CANCEL vangt niet
            // TODO alles in 1 switch?
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
                        int move = game.getMm().miniMaxi(game.getBoard(),13,0,0,'o').getLastSet();
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
                    gameOver = true;
                    break;
                case "SVRGAMEWIN":
                    System.out.println("You have won!");
                    this.winMessage = setMessages(arr[3]);
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

            }
        } else {
                gameOver = true;
            }

        }

    public void setGame(Game game) {
//        System.out.println("This game is set!!!!");
        this.game = game;
    }
}

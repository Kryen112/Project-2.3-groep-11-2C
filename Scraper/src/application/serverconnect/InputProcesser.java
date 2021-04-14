package application.serverconnect;

import application.App;
import application.fxml.Start;

public class InputProcesser {

    public String answer;

    public String[] matchMessage;
    public String[] turnMessage;
    public String[] lossMessage;
    public String[] winMessage;
    public String[] moveMessage;
    public String[] challenger;

    public int move;
    public String opponent;
    public boolean turn;
    public String black;
    public int challengeNumber;

    public InputProcesser() {
        challengeNumber = 0;
    }

    public void setStart() {
        if(black.equals(opponent)) {
//            App.board.setStartPieces('x');
        } else {
//            App.board.setStartPieces('o');
        }
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

    public void setOpponent() {
        opponent = matchMessage[2].replace("OPPONENT: ", "").replace("\"", "");
    }

    public String[] setMessages(String message) {
        return message.replace("{", "").replace("}", "").split(", ");
    }

    public void processInput(String input, Server server) {
        // Challenge cancel message afvangen
        if (input.contains("SVR GAME CHALLENGE CANCELLED") || input.contains("SVR PLAYERLIST")) {
            setAnswer(input);
            server.setResult(input);
        } else {
            switch(input) {
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




            // TODO challenge CANCEL vangt niet
            // TODO alles in 1 switch?
            String[] arr = input.split(" ", 4);
            String serverMessage = "";

            if(arr.length >= 3) {
                serverMessage = arr[0]+arr[1]+arr[2];
            }
            // try {
            //     Thread.sleep(500);
            // } catch (Exception e){}

            switch(serverMessage) {
                case "SVRGAMEMATCH":
//                    App.board.clearBoard();
                    System.out.println("Game match start!");
                    this.matchMessage = setMessages(arr[3]);
                    setBlack();
                    setOpponent();
                    setStart();
                    System.out.println("The opponent of the game is: " + this.opponent);
                    System.out.println("The player that starts first is: " + this.black);
                    break;
                case "SVRGAMEYOURTURN":
                    System.out.println("It's your turn!");
                    this.turnMessage = setMessages(arr[3]);
//                    server.doMove(App.board.getRandomSet());
                    turn = true;
                    break;
                case "SVRGAMELOSS":
                    System.out.println("You have lost!");
                    this.lossMessage = setMessages(arr[3]);
                    break;
                case "SVRGAMEWIN":
                    System.out.println("You have won!");
                    this.winMessage = setMessages(arr[3]);
                    break;
                case "SVRGAMEMOVE":
//                    System.out.print(App.board.getFreeSpacesX());
                    if(turn) {
                        System.out.println(" Move set");
                        this.moveMessage = setMessages(arr[3]);
                        setMove();
                        System.out.println("Deze zet is gedaan: " + this.move);
                        turn = false;
                    } else {
                        System.out.println("Move set by opponent");
                        this.moveMessage = setMessages(arr[3]);
                        setMove();
                        System.out.println("Deze zet is gedaan: " + this.move);
//                        App.board.setPieceOnBoard(move, 'x');
                    }// zet move op het bord van diegene die move heeft gezet
                    break;
                case "SVRGAMECHALLENGE":
                    this.challenger = setMessages(arr[3]);
                    challengeNumber = Integer.parseInt(setMessages(arr[3])[1].replace("CHALLENGENUMBER: ", "").replace("\"", ""));
                    System.out.println("Challenge ontvangen van: " + challenger[0].replace("CHALLENGER: ", "").replace("\"", ""));
                    break;
            }
        }
    }
}

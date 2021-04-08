package application.serverconnect;

public class InputProcesser {

    String answer;

    public InputProcesser() {

    }

    public Boolean isOK() {
        return answer.equals("OK");
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void processInput(String input, Server server) {
        switch(input) {
            case "OK":
            case "ERR Already logged in":
            case "ERR Duplicate name exists":
                System.out.println("Setting answer to: "+input);
                setAnswer(input);
                server.setResult(input);
                break;
            default: setAnswer("");
        }
    }
}

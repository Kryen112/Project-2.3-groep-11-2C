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
}

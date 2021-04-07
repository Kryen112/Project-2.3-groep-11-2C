package application.serverconnection;

public class InputProcesser {

    String answer;

    public Boolean isOK() {
        return answer.equals("OK");
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}

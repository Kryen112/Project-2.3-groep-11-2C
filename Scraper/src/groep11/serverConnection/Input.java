package groep11.serverConnection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class Input implements Runnable {
    Socket s;

    public Input(Socket s) {
        this.s = s;
    }

    public void run() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(s.getInputStream()));

            while(true) {
                String input = bufferedReader.readLine();
                System.out.println(input);
            }
        } catch (Exception e) {
            System.out.print("Something went wrong: " + e);
        }
    }

}
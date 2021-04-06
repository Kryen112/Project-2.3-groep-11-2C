package groep11.serverConnection;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Server {
    Socket s;
    PrintWriter out;

    public Server(Socket s) {
        this.s = s;
        setPrintWriter();
    }

    public void setPrintWriter() {
        try {
            out = new PrintWriter(s.getOutputStream(), true);
        } catch(IOException e) {
            System.out.println("IO exception: "+e);
        }
    }

    public void sendCommand(String command) {
        out.println(command);
    }

    public void login(String name) {
        out.println("login " + name);
    }

    public void help() {
        out.println("help");
    }
}
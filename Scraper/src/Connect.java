import java.net.*;
import java.io.*;

public class Connect {
    
    //public static void main(String[] args) throws Exception{
    //    Connect CLIENT = new Connect();
    //    CLIENT.run();
    //}
    public static void main(String[] args)throws Exception{
        //Connect server_connect = new Connect();
        //server_connect.run("login henk");
        Test myThread = new Test();
        myThread.start();
        run("login piet");
        run("login henk");
        run("login henk");

    }

    public static void run(String task) throws Exception{
        Socket socket = new Socket( "localhost", 7789 );

            // Create input and output streams to read from and write to the server
            PrintStream out = new PrintStream( socket.getOutputStream() );
            

            
            out.println(task);
        try (ServerSocket server = new ServerSocket(7789)) {
            System.out.println("hello");
        }

        
        
        
    
    
    
        //    String ip ="localhost";//145.33.225.170
    //   int port = 7789;
    //    Socket s = new Socket(ip,port);

    //    PrintStream PS = new PrintStream(s.getOutputStream());

    //    PS.println("login henk");

    //    InputStreamReader IR = new InputStreamReader(s.getInputStream());
    //    BufferedReader BR = new BufferedReader(IR);

    //    String MESSAGE = BR.readLine();
    //    System.out.print(MESSAGE);
    }
    
}
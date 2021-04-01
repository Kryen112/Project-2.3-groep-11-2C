import java.net.*;
import java.io.*;

public class Test extends Thread {
    
    /*public void run() {
       
    //public static void main(String[] args) throws Exception{
    //    Connect CLIENT = new Connect();
    //    CLIENT.run();
    //}
    /*public static void main(String[] args)throws Exception{
        //Connect server_connect = new Connect();
        //server_connect.run("login henk");
        run("login henk");
        
    }
     */
    public void run() {
        try{
        Socket socket = new Socket( "localhost", 7789 );

            // Create input and output streams to read from and write to the server
        BufferedReader in = new BufferedReader( new InputStreamReader( socket.getInputStream() ) );

        
        
        String line = in.readLine();
        while( line != null )
            {
                    System.out.println( line );
                    line = in.readLine();
            }
            
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
        run();
        
    }
    

     
    
}
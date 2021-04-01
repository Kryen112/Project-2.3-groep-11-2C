import java.io.*;


public class Server_Commands {
    public static void main(String[] args)throws Exception{
        //Connect server_connect = new Connect();
        //server_connect.run("login henk");

        login("marc");
        login("henk");
    }

    public static void login(String naam) throws Exception{
        
        Connect.run("login "+ naam);

    }
    
    public static void logout() throws Exception{
        Connect.run("quit");
        
    }

    public static void gamelist() throws Exception{
        Connect.run("get gamelist");
        
    }

    public static void playerlist() throws Exception{
        Connect.run("get playerlist");
        
    }

    public static void inschrijven(String spel) throws Exception{
        Connect.run("subscribe " + spel);
       
    }

    public static void set(String zet) throws Exception{
        Connect.run("set "+ zet);
        
    }

    public static void forfeit() throws Exception{
        Connect.run("forfeit");
        
    }

    public static void challenge(String naam) throws Exception{
        Connect.run("challenge "+naam);
        
    }

    public static void accept(String nummer) throws Exception{
        Connect.run("challenge accept "+ nummer);
    }

    

}
package view;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class FlightGearClient {
    public int port;
    public String ip;
    public Socket theServer;
    public PrintWriter writer;
    public BufferedReader reader;

    public FlightGearClient( String ip,int port) {
        this.port = port;
        this.ip = ip;
        theServer = null;
        writer = null;
        reader = null;
    }


    public void runClient(){
        try{
            theServer = new Socket(ip,port);
            reader = new  BufferedReader(new InputStreamReader(theServer.getInputStream()));
            writer = new PrintWriter(theServer.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void writeClient(String data) {
        writer.write(data + "\r\n");
        writer.flush();
    }


    public void stopClient() throws IOException {
        theServer.close();
    }

}

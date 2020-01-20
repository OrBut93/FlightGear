package flightgear;

import commands.Variable.Variable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

public class FlightGearClient implements Client, Observer {
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

    @Override
    public void runClient(){
        try{
            theServer = new Socket(ip,port);
            reader = new  BufferedReader(new InputStreamReader(theServer.getInputStream()));
            writer = new PrintWriter(theServer.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeClient(String data) {
        writer.println(data);
        writer.flush();
    }

    @Override
    public void stopClient() throws IOException {
        theServer.close();
    }

    @Override
    public void update(Observable observable, Object o) {
        Variable obj = ((Variable)observable) ;
        String data = "set" + obj.getBindPath() + obj.getValue();
        this.writeClient(data);
    }
}

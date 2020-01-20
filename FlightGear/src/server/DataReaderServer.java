package server;

import commands.ConnectCommand;
import commands.Variable.Variable;
import interpeter.Interpeter;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class DataReaderServer implements Server {
    int port ;
    int readRate ;
    volatile boolean stop;
    String[] varNames;
    private static String[] Configure = new String[]{
            "simX",
            "simY",
            "simZ",
            "instrumentation/airspeed-indicator/indicated-speed-kt",
            "instrumentation/altimeter/indicated-altitude-ft",
            "instrumentation/altimeter/pressure-alt-ft",
            "instrumentation/attitude-indicator/indicated-pitch-deg",
            "instrumentation/attitude-indicator/indicated-roll-deg",
            "instrumentation/attitude-indicator/internal-pitch-deg",
            "instrumentation/attitude-indicator/internal-roll-deg",
            "instrumentation/encoder/indicated-altitude-ft",
            "instrumentation/encoder/pressure-alt-ft",
            "instrumentation/gps/indicated-altitude-ft",
            "instrumentation/gps/indicated-ground-speed-kt",
            "instrumentation/gps/indicated-vertical-speed",
            "instrumentation/heading-indicator/indicated-heading-deg",
            "instrumentation/magnetic-compass/indicated-heading-deg",
            "instrumentation/slip-skid-ball/indicated-slip-skid",
            "instrumentation/turn-indicator/indicated-turn-rate",
            "instrumentation/vertical-speed-indicator/indicated-speed-fpm",
            "controls/flight/aileron",
            "controls/flight/elevator",
            "controls/flight/rudder",
            "controls/flight/flaps",
            "controls/engines/current-engine/throttle",
            "engines/engine/rpm"
    };
    public DataReaderServer(int port, int readRate) {
        this.port = port;
        stop=false;
        this.readRate = readRate;
        this.varNames= Configure;
    }


    @Override
    public  void runServer() {
       new Thread(() -> {
            try {
                ServerSocket server = new ServerSocket(port);
                Socket client = server.accept();
                System.out.println("client connected...");
                BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
                int i;
                while (!stop) {
                    try {
                        String readLine;
                        while ((readLine = input.readLine()) != null) {
                            i = 0;
                            System.out.println("Recived: "+readLine);
                            String[] inputFromClient = readLine.split(",");
                            for (String s : inputFromClient) {
                                Interpeter.symbolTable.get(varNames[i]).setValue(Double.parseDouble(s));
                                System.out.println("Sent to Interpeter "+readLine);

                                i++;
                            }
                            try {
                                Thread.sleep(this.readRate);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                    catch (IOException e)
                    {

                    }
                input.close();
                client.close();
                server.close();
            } }catch (IOException e) {
            }

        }).start();
    }

    @Override
    public  void stopServer() {
        stop = true;
    }


}

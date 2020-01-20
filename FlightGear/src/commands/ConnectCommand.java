package commands;

import flightgear.Client;
import flightgear.FlightGearClient;
import interpeter.Interpeter;

public class ConnectCommand implements Command {

    @Override
    public void doCommand(String[] inputs)  {
        Interpeter.client = new FlightGearClient(inputs[0],Integer.parseInt(inputs[1]));
        Interpeter.client.runClient();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

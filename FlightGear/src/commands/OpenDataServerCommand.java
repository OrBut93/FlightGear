package commands;
import server.DataReaderServer;

public class OpenDataServerCommand implements Command {
    @Override
    public void doCommand(String[] inputs)  {
        if (inputs.length != 2){
            System.out.println("Too few params...");
        }
        else {

            DataReaderServer myServer = new DataReaderServer(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]));
            myServer.runServer();
        }
    }
}

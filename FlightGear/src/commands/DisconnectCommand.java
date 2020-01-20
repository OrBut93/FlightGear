package commands;

import interpeter.Interpeter;

import java.io.IOException;

public class DisconnectCommand implements Command {
    @Override
    public void doCommand(String[] inputs) {
        Interpeter.client.writeClient("bye");
        try {
            Interpeter.client.stopClient();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

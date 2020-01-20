package commands;

public class SleepCommand implements Command {

    @Override
    public void doCommand(String[] inputs) {
        try {
            Thread.sleep(Integer.parseInt(inputs[0]));
        }
        catch (InterruptedException ignored){
        }
    }
}

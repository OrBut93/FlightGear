package commands;

import interpeter.Interpeter;

public class PrintCommand implements Command {
    @Override
    public void doCommand(String[] inputs) {
        if (inputs.length != 1){
            System.out.println("Wrong params for Print Command");
        }
        else {
            if (Interpeter.symbolTable.containsKey(inputs[0])) {
                System.out.println(Interpeter.symbolTable.get(inputs[0]).getValue());
            }
            else {
                System.out.println(inputs[0].toString());
            }
        }
    }
}

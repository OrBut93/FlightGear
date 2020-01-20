package commands;

import commands.Variable.Variable;
import expressions.ShuntingYard;
import interpeter.Interpeter;
import test.MyInterpreter;

import java.util.Arrays;

public class ReturnCommand implements Command {
    @Override
    public void doCommand(String[] inputs)  {
        String [] shunting_input = Arrays.copyOfRange(inputs, 0, inputs.length);
        MyInterpreter.value = ShuntingYard.calc(shunting_input);
    }
}
